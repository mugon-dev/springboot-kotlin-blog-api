package com.conny.blog.infrastructure.extension

import com.conny.blog.util.JsonUtils

fun Any?.toJsonNode() = JsonUtils.toJsonNode(this)