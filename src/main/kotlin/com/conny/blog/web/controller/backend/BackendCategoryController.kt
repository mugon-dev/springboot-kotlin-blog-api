package com.conny.blog.web.controller.backend

import com.conny.blog.constant.RestUriConstant
import com.conny.blog.model.entity.CategoryEntity
import com.conny.blog.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = [RestUriConstant.Backend.CATEGORY])
class BackendCategoryController @Autowired constructor(
    private val categoryService: CategoryService,
) {
    @PostMapping
    fun create(
        @RequestBody request: CategoryEntity
    ): ResponseEntity<Any> {
        val data = categoryService.create(request)
        return ResponseEntity.ok(data)
    }
}