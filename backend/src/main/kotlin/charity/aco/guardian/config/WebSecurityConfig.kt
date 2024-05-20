package charity.aco.guardian.config

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.stereotype.Component

@Component
class WebSecurityConfig {

    @Bean
    @Throws(java.lang.Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http.cors(Customizer.withDefaults())
        http.csrf { it.disable() }
        http.sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.ALWAYS) }
        http.authorizeHttpRequests {
            it.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/swagger", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers("/oauth2/**").permitAll()
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
        }
        http.oauth2Login(Customizer.withDefaults())
        http.logout { it.clearAuthentication(true) }

        return http.build()
    }
}