package com.conny.blog.web.controller.frontend

import com.conny.blog.constant.RestUriConstant
import com.conny.blog.model.request.PostRequest
import com.conny.blog.model.response.PostResponse
import com.conny.blog.service.PostService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api(
    tags = ["Frontend Post"],
    description = "Welcome to frontend post api"
)
@RestController
@RequestMapping(RestUriConstant.Frontend.POST)
class FrontendPostController @Autowired constructor(
    private val postService: PostService,
) {
    @PostMapping
    fun create(
        @RequestBody request: PostRequest
    ): ResponseEntity<Any> {
        val data = postService.create(request)
        return ResponseEntity.ok(PostResponse.toEntity(data))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: PostRequest): ResponseEntity<Any> {
        val data = postService.update(id, request)
        return ResponseEntity.status(HttpStatus.OK).body(PostResponse.toEntity(data))
    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): ResponseEntity<Any> {
        val data = postService.findById(id)
            ?: throw Exception("Category not found")
        return ResponseEntity.status(HttpStatus.OK).body(PostResponse.toEntity(data))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        val data = postService.delete(id)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(PostResponse.toEntity(data))
    }

    @DeleteMapping("/soft/{id}")
    fun softDelete(@PathVariable id: Long): ResponseEntity<Any> {
        val data = postService.softDelete(id)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(PostResponse.toEntity(data))
    }

    @GetMapping("/page")
    fun findAllAvailableByPage(): ResponseEntity<Any> {
        val data = postService.findAllAvailable(Pageable.ofSize(20)).map {
            PostResponse.toEntity(it)
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(data.content)

    }
}