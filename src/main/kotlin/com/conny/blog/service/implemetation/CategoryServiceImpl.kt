package com.conny.blog.service.implemetation

import com.conny.blog.model.entity.CategoryEntity
import com.conny.blog.repository.CategoryRepository
import com.conny.blog.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl @Autowired constructor(
    private val categoryRepository: CategoryRepository,
) : CategoryService {
    override fun create(request: CategoryEntity): CategoryEntity? = categoryRepository.save(request)
}