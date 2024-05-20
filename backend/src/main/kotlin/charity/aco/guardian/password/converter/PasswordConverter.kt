package charity.aco.guardian.password.converter

import jakarta.persistence.AttributeConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.InvalidKeyException
import java.security.Key
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.spec.SecretKeySpec

/*

import jakarta.persistence.AttributeConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


@Component
class PasswordConverter : AttributeConverter<String, String> {

    private val AES: String = "AES"

    private var key: Key? = null
    private var cipher: Cipher = Cipher.getInstance(AES)

    @Value("\${guardian.psskee}")
    lateinit var SECRET: String

        init {
        key = SecretKeySpec(SECRET.toByteArray(), AES)
        cipher = Cipher.getInstance(AES)
    }


    override fun convertToDatabaseColumn(attribute: String?): String {
        key = SecretKeySpec(SECRET.toByteArray(), AES)
        cipher!!.init(Cipher.ENCRYPT_MODE, key)
        return Base64.getEncoder().encodeToString(cipher!!.doFinal(attribute!!.toByteArray()))
    }

    override fun convertToEntityAttribute(dbData: String?): String {
        key = SecretKeySpec(SECRET.toByteArray(), AES)
        cipher!!.init(Cipher.ENCRYPT_MODE, key)
        return Base64.getEncoder().encodeToString(cipher!!.doFinal(dbData!!.toByteArray()))
    }
}*/

@Component
class PasswordConverter : AttributeConverter<String, String> {
    companion object {
        private const val AES = "AES"
    }

    private val cipher: Cipher = Cipher.getInstance(AES)
    private var key: Key? = null

    @Value("\${guardian.psskee}")
    lateinit var SECRET: String

    override fun convertToDatabaseColumn(attribute: String): String {
        if (this.key == null) {
            this.key = SecretKeySpec(this.SECRET.toByteArray(), AES)
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE, this.key)
            return Base64.getEncoder().encodeToString(cipher.doFinal(attribute.toByteArray()))
        } catch (e: IllegalBlockSizeException) {
            throw IllegalStateException(e)
        } catch (e: BadPaddingException) {
            throw IllegalStateException(e)
        } catch (e: InvalidKeyException) {
            throw IllegalStateException(e)
        }
    }

    override fun convertToEntityAttribute(dbData: String): String {
        if (this.key == null) {
            this.key = SecretKeySpec(this.SECRET.toByteArray(), AES)
        }
        try {
            cipher.init(Cipher.DECRYPT_MODE, this.key)
            return String(cipher.doFinal(Base64.getDecoder().decode(dbData)))
        } catch (e: InvalidKeyException) {
            throw IllegalStateException(e)
        } catch (e: BadPaddingException) {
            throw IllegalStateException(e)
        } catch (e: IllegalBlockSizeException) {
            throw IllegalStateException(e)
        }
    }
}
