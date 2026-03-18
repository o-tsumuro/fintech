package com.example.account_service.controller

import com.example.account_service.service.AccountService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val accountService: AccountService
) {

    @GetMapping("/{id}/balance")
    fun getBalance(@PathVariable id: Long): Map<String, Long> {
        val balance = accountService.getBalance(id)
        return mapOf("balance" to balance)
    }
}