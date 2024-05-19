package charity.aco.guardian.password.service

import charity.aco.guardian.password.domain.Password
import charity.aco.guardian.password.dto.PasswordDto
import charity.aco.guardian.password.repository.PasswordRepository
import org.springframework.stereotype.Service

@Service
class PasswordService(private val passwordRepository: PasswordRepository) {

    fun findAll(): List<PasswordDto> {
        val all = passwordRepository.findAll()
        return all.map { it.toDto() }
    }

    fun createPassword(passwordDto: PasswordDto): PasswordDto {
        val toSave = Password(name = passwordDto.name, password = passwordDto.password)
        val saved = this.passwordRepository.save(toSave)
        return saved.toDto()
    }
}