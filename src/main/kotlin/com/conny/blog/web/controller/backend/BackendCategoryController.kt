package com.conny.blog.web.controller.backend

import com.conny.blog.constant.RestUriConstant
import com.conny.blog.model.entity.CategoryEntity
import com.conny.blog.model.request.CategoryRequest
import com.conny.blog.service.CategoryService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api(
    tags = ["Backend Category"],
    description = "Welcome to backend category api"
)
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

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: CategoryRequest): ResponseEntity<Any> {
        val data = categoryService.update(id, request)
        return ResponseEntity.status(HttpStatus.OK).body(data)
    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): ResponseEntity<Any> {
        val data = categoryService.findById(id)
            ?: throw Exception("Category not found")
        return ResponseEntity.status(HttpStatus.OK).body(data)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        val data = categoryService.delete(id)
        return ResponseEntity.status(HttpStatus.OK).body(data)
    }

    @DeleteMapping("/soft/{id}")
    fun softDelete(@PathVariable id: Long): ResponseEntity<Any> {
        val data = categoryService.softDelete(id)
        return ResponseEntity.status(HttpStatus.OK).body(data)
    }

    @GetMapping("/page")
    fun findAllAvailableByPage(): ResponseEntity<Any> {
        val data = categoryService.findAllAvailable(Pageable.ofSize(2))
        return ResponseEntity.status(HttpStatus.OK).body(data.content)
    }
}