package charity.aco.guardian.password.api

import charity.aco.guardian.password.dto.PasswordDto
import charity.aco.guardian.password.service.PasswordService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/password")
class PasswordApi(private val passwordService: PasswordService) {

    @GetMapping("all")
    fun findAll(): ResponseEntity<List<PasswordDto>> {
        val result = this.passwordService.findAll()
        return ResponseEntity.ok(result)
    }

    @PostMapping("create")
    fun createPassword(@RequestBody passwordDto: PasswordDto): ResponseEntity<PasswordDto> {
        val result = this.passwordService.createPassword(passwordDto)
        return ResponseEntity.ok(result)
    }
}