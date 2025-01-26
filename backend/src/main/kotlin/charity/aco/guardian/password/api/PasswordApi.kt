package charity.aco.guardian.password.api

import charity.aco.guardian.password.dto.PasswordDto
import charity.aco.guardian.password.service.PasswordService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

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

    @PostMapping("update")
    fun updatePassword(@RequestBody passwordDto: PasswordDto): ResponseEntity<PasswordDto> {
        val result = this.passwordService.updatePassword(passwordDto)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/{passwordId}")
    fun getById(@PathVariable passwordId: UUID): ResponseEntity<PasswordDto> {
        val result = this.passwordService.findById(passwordId)
        return ResponseEntity.ok(result)
    }

    @DeleteMapping("/{passwordId}")
    fun deleteEvent(@PathVariable passwordId: UUID): ResponseEntity<Void> {
        this.passwordService.deletePassword(passwordId)
        return ResponseEntity(HttpStatus.OK)
    }
}