package com.conny.blog.model.entity

import com.conny.blog.constant.TableConstant
import com.conny.blog.infrastructure.model.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = TableConstant.CATEGORY)
open class CategoryEntity constructor(
    @Column(length = 30, nullable = false)
    open var name: String? = null,

    @Column(length = 50)
    open var notes: String? = null,

    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.DETACH, CascadeType.REFRESH],
        mappedBy = "category"
    )
    open var post: MutableList<PostEntity>? = mutableListOf(),

    ) : BaseEntity<Long>()