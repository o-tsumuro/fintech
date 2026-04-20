package com.example.account_service.controller

data class TransferRequest (
    val fromAccountId: Long,
    val toAccountId: Long,
    val amount: Long,
    val idempotencyKey: String,
)