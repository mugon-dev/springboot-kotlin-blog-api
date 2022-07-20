package com.conny.blog.repository

import com.conny.blog.model.entity.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<RoleEntity, Long> {
    fun queryByNameAndDeletedAtIsNull(name: String): RoleEntity?
}