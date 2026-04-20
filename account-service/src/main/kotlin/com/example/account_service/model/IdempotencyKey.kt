package com.example.account_service.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "idempotency_keys")
data class IdempotencyKey (

    @Id
    val key: String,

    val createdAt: LocalDateTime = LocalDateTime.now()
)