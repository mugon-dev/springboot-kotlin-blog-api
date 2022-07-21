package com.conny.blog.web.controller.backend

import com.conny.blog.constant.RestUriConstant
import com.conny.blog.infrastructure.model.response.BodyResponse
import com.conny.blog.model.request.category.CategoryRequest
import com.conny.blog.model.response.category.AdminCategoryResponse
import com.conny.blog.service.CategoryService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Api(
    tags = ["Backend Category"],
    description = "Welcome to backend category api"
)
@RestController
@RequestMapping(value = [RestUriConstant.Backend.CATEGORY])
@PreAuthorize("""hasAnyRole("ADMIN","SUPER_ADMIN")""")
class BackendCategoryController @Autowired constructor(
    private val categoryService: CategoryService,
) {
    @PostMapping
    fun create(
        @RequestBody request: CategoryRequest
    ): ResponseEntity<Any> {
        val data = categoryService.create(request)
        return BodyResponse.success(AdminCategoryResponse.toEntity(data), message = "Create Successful")
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: CategoryRequest): ResponseEntity<Any> {
        val data = categoryService.update(id, request)
        return BodyResponse.success(AdminCategoryResponse.toEntity(data), message = "Update Successful")
    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): ResponseEntity<Any> {
        val data = categoryService.findById(id)
            ?: throw Exception("Category not found")
        return BodyResponse.success(AdminCategoryResponse.toEntity(data), message = "Find One Successful")
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        val data = categoryService.delete(id)
        return BodyResponse.success(AdminCategoryResponse.toEntity(data), message = "Delete One Successful")
    }

    @DeleteMapping("/soft/{id}")
    fun softDelete(@PathVariable id: Long): ResponseEntity<Any> {
        val data = categoryService.softDelete(id)
        return BodyResponse.success(AdminCategoryResponse.toEntity(data), message = "Soft Delete One Successful")
    }

    @GetMapping("/page")
    fun findAllAvailableByPage(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "20") size: Int,
        @RequestParam(required = false, defaultValue = "id") sort: String,
        @RequestParam(required = false, defaultValue = "desc") sortBy: String,
        @RequestParam(required = false, defaultValue = "false") paged: Boolean,

        ): ResponseEntity<Any> {
        val data = if (paged) {
            categoryService.findAllAvailable(Pageable.unpaged()).map {
                AdminCategoryResponse.toEntity(it)
            }
        } else {

            val sb = if (sortBy == "desc") {
                Sort.by(sort).descending()
            } else {
                Sort.by(sort).ascending()
            }
            categoryService.findAllAvailable(PageRequest.of(page, size, sb)).map {
                AdminCategoryResponse.toEntity(it)
            }
        }
        return BodyResponse.success(data, message = "Find All Successful")
    }
}