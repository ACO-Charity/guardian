package charity.aco.guardian.password.domain

import charity.aco.guardian.common.domain.BaseEntity
import charity.aco.guardian.password.converter.PasswordConverter
import charity.aco.guardian.password.dto.PasswordDto
import jakarta.persistence.Convert
import jakarta.persistence.Entity

@Entity
data class Password(
    var name: String,

    @Convert(converter = PasswordConverter::class)
    var password: String
) : BaseEntity() {

    fun toDto(): PasswordDto = PasswordDto(
        id = this.id,
        name = this.name,
        password = this.password
    )
}
