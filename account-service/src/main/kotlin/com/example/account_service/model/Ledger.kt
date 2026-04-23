package com.example.account_service.model

import jakarta.persistence.GenerationType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "ledger")
data class Ledger(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    val id: Long = 0,

    val accountId: Long,

    val type: String,

    val createdAt: LocalDateTime = LocalDateTime.now()
)