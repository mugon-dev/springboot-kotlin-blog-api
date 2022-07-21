package com.conny.blog.web

import com.conny.blog.exception.AlreadyExistsException
import com.conny.blog.exception.NotFoundException
import com.conny.blog.exception.UserNotFoundException
import com.conny.blog.exception.auth.InvalidCurrentUserException
import com.conny.blog.infrastructure.model.response.BaseBodyResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(Exception::class)
    fun handleExceptionOutside(
        ex: Exception
    ): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                BaseBodyResponse(
                    status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    message = ex.message
                )
            )
    }

    @ExceptionHandler(AlreadyExistsException::class)
    fun handleAlreadyExistsException(
        ex: AlreadyExistsException,
    ): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(
                BaseBodyResponse(
                    status = HttpStatus.CONFLICT.value(),
                    message = ex.message
                )
            )
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(
        ex: UserNotFoundException,
    ): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                BaseBodyResponse(
                    status = HttpStatus.NOT_FOUND.value(),
                    message = ex.message
                )
            )
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(
        ex: NotFoundException,
    ): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                BaseBodyResponse(
                    status = HttpStatus.NOT_FOUND.value(),
                    message = ex.message
                )
            )
    }

    @ExceptionHandler(InvalidCurrentUserException::class)
    fun handleInvalidCurrentUserException(
        ex: InvalidCurrentUserException,
    ): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                BaseBodyResponse(
                    status = HttpStatus.BAD_REQUEST.value(),
                    message = ex.message
                )
            )
    }
}