package com.conny.blog.web.controller.frontend

import com.conny.blog.constant.RestUriConstant
import com.conny.blog.infrastructure.model.response.BodyResponse
import com.conny.blog.model.request.user.UserAuthRequest
import com.conny.blog.model.response.user.UserResponse
import com.conny.blog.service.UserAuthService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(
    tags = ["Frontend Auth"],
    description = "Welcome to frontend auth api"
)
@RestController
@RequestMapping(RestUriConstant.Frontend.AUTH)
class FrontendAuthController @Autowired constructor(
    private val userAuthService: UserAuthService,
) {
    @PostMapping("/register")
    fun register(@RequestBody request: UserAuthRequest): ResponseEntity<Any> {
        val data = userAuthService.register(request)
        return BodyResponse.success(
            UserResponse.toEntity(data),
            message = "Register Successful"
        )
    }

    @PostMapping("login")
    fun login(
        @RequestBody request: UserAuthRequest,
    ): ResponseEntity<Any> {
        val data = userAuthService.login(request)
        return BodyResponse.success(
            data,
            message = "Login Successful"
        )
    }
}