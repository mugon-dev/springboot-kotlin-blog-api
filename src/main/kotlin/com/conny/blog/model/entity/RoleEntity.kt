package com.conny.blog.model.entity

import com.conny.blog.constant.TableConstant
import com.conny.blog.infrastructure.model.entity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = TableConstant.ROLE)
open class RoleEntity constructor(
    @Column(length = 30, nullable = false)
    open var name: String? = null,
    @Column(length = 50)
    open var notes: String? = null,
) : BaseEntity<Long>() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RoleEntity) return false

        if (name != other.name) return false
        if (notes != other.notes) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (notes?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "RoleEntity(name=$name, notes=$notes)"
    }


}