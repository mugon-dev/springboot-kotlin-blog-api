package com.conny.blog.web.controller.frontend

import com.conny.blog.constant.RestUriConstant
import com.conny.blog.infrastructure.model.response.BodyResponse
import com.conny.blog.model.request.post.PostRequest
import com.conny.blog.model.response.post.PostResponse
import com.conny.blog.service.PostService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
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
    @PreAuthorize("""hasAnyRole("USER")""")
    fun create(
        @RequestBody request: PostRequest
    ): ResponseEntity<Any> {
        val data = postService.create(request)
        return BodyResponse.success(PostResponse.toEntity(data), message = "Create Successful")
    }

    @PutMapping("/{id}")
    @PreAuthorize("""hasAnyRole("USER")""")
    fun update(@PathVariable id: Long, @RequestBody request: PostRequest): ResponseEntity<Any> {
        val data = postService.update(id, request)
        return BodyResponse.success(PostResponse.toEntity(data), message = "Update Successful")
    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): ResponseEntity<Any> {
        val data = postService.findById(id)
            ?: throw Exception("Category not found")
        return BodyResponse.success(PostResponse.toEntity(data), message = "Find one Successful")
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("""hasAnyRole("USER")""")
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        val data = postService.delete(id)
        return BodyResponse.success(PostResponse.toEntity(data), message = "Delete one Successful")
    }

    @DeleteMapping("/soft/{id}")
    fun softDelete(@PathVariable id: Long): ResponseEntity<Any> {
        val data = postService.softDelete(id)
        return BodyResponse.success(PostResponse.toEntity(data), message = "Soft Delete one Successful")
    }

    @GetMapping("/all/{categoryId}")
    fun findAllAvailableByPage(
        @PathVariable categoryId: Long,
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "20") size: Int,
        @RequestParam(required = false, defaultValue = "id") sort: String,
        @RequestParam(required = false, defaultValue = "desc") sortBy: String,
        @RequestParam(required = false, defaultValue = "false") paged: Boolean,
    ): ResponseEntity<Any> {
        val data = if (paged) {
            postService.findAllAvailable(categoryId, Pageable.unpaged()).map {
                PostResponse.toEntity(it)
            }
        } else {

            val sb = if (sortBy == "desc") {
                Sort.by(sort).descending()
            } else {
                Sort.by(sort).ascending()
            }
            if (categoryId == 0L) {
                postService.findAllAvailable(categoryId, PageRequest.of(page, size, sb)).map {
                    PostResponse.toEntity(it)
                }
            } else {
                postService.findAllAvailable(categoryId, PageRequest.of(page, size, sb)).map {
                    PostResponse.toEntity(it)
                }
            }
        }
        return BodyResponse.success(data, message = "Find all Successful")

    }
}