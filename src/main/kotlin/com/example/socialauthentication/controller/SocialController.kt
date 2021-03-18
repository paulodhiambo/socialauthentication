package com.example.socialauthentication.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/")
class SocialController {

    @GetMapping("/user")
    fun user(@AuthenticationPrincipal principal: OAuth2User): Map<String, Any?>? {
        return Collections.singletonMap("name", principal.getAttribute("name"))
    }
}