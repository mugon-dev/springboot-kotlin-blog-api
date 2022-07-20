package com.conny.blog.module.security

import com.conny.blog.module.security.util.JwtUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

class JwtSecurityConfigurer @Autowired constructor(
    private val userDetailsService: UserDetailsService
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    override fun configure(builder: HttpSecurity?) {
        JwtUtils.setUserDetailsService(userDetailsService)
        builder?.addFilterBefore(JwtTokenFilter(), BasicAuthenticationFilter::class.java)
    }
}