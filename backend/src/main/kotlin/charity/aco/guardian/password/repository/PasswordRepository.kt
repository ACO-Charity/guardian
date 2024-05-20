package charity.aco.guardian.password.repository

import charity.aco.guardian.password.domain.Password
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PasswordRepository : JpaRepository<Password, UUID>