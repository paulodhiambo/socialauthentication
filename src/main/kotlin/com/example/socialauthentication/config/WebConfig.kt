package com.example.socialauthentication.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class WebConfig : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests { a ->
                a.antMatchers("/", "/error", "/webjars/**").permitAll()
                    .anyRequest().authenticated()
            }
            .exceptionHandling { e ->
                e.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            }
            .oauth2Login { o ->
                o.failureHandler { request: HttpServletRequest, response: HttpServletResponse?, exception: AuthenticationException ->
                    request.session.setAttribute("error.message", exception.message)
                    val handler: AuthenticationEntryPointFailureHandler? = null
                    assert(false)
                    handler!!.onAuthenticationFailure(request, response, exception)
                }
            }
    }
}