package com.example.account_service.controller

data class WithdrawRequest(
    val accountId: Long,
    val amount: Long
)