package com.conny.blog.infrastructure.model.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.io.Serializable
import java.util.*
import javax.persistence.*

// 객체의 입장에서 공통 매핑 정보가 필요할 때 사용
// 부모 클래스에 선언하고 속성만 상속 받아서 사용하고 싶을 때
// 자식 클래스에 매핑 정보만 제공
// 추상 클래스로 만드는 것을 권장
@MappedSuperclass
abstract class BaseEntity<ID : Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: ID? = null

    @CreatedBy
    @Column(length = 30)
    open var createdBy: String? = null

    @CreatedDate
    open var createdDate: Date? = null

    @LastModifiedBy
    @Column(length = 30)
    open var updatedBy: String? = null

    @LastModifiedDate
    open var updatedDate: Date? = null

    @Column
    open var deleteAt: Date? = null
}