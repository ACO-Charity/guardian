package charity.aco.guardian.demo.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/demo")
@RestController
class DemoApi {

    @GetMapping("")
    fun demoApi(): ResponseEntity<String>{
        return ResponseEntity("Guardian DEMO", HttpStatus.OK)
    }
}