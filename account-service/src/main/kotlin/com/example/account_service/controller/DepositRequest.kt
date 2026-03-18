package com.example.account_service.controller

data class DepositRequest(
    val accountId: Long,
    val amount: Long
)