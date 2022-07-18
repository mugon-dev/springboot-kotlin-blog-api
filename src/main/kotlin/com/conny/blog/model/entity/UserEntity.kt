package com.conny.blog.model.entity

import com.conny.blog.constant.TableConstant
import com.conny.blog.infrastructure.model.entity.BaseEntity
import org.hibernate.Hibernate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

// open => 상속하게 해줄때 필요
@Entity
@Table(name = TableConstant.USER)
open class UserEntity constructor(
    @Column(nullable = false, unique = true, length = 30)
    open var username: String? = null,

    @Column(nullable = false, length = 60)
    open var password: String? = null,

    @Column(nullable = false)
    open var enabled: Boolean? = true,

    @Column(nullable = false)
    open var enabledUser: Boolean? = true,
) : BaseEntity<Long>() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        other as UserEntity

        return id != null && id === other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
    override fun toString(): String {
        return "UserEntity(username=$username, password=$password, enabled=$enabled, enabledUser=$enabledUser)"
    }


}