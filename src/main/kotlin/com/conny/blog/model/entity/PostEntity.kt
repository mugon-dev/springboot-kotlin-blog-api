package com.conny.blog.model.entity

import com.conny.blog.constant.TableConstant
import com.conny.blog.infrastructure.model.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = TableConstant.POST)
open class PostEntity constructor(
    @Column(nullable = false, length = 50)
    open var title: String? = null,

    @Basic
    open var description: String? = null,

    @Basic
    open var attachment: String? = null,

    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.DETACH, CascadeType.REFRESH]
    )
    @JoinColumn(name = "categoryId")
    open var category: CategoryEntity? = null,

    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.DETACH, CascadeType.REFRESH],
    )
    @JoinColumn(name = "userId")
    open var user: UserEntity? = null,

    ) : BaseEntity<Long>()