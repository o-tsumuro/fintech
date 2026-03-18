package com.example.account_service.service

import com.example.account_service.repository.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository
) {
    fun getBalance(accountId: Long): Long {
        val account = accountRepository.findById(accountId)
            .orElseThrow { RuntimeException("account no found") }

        return account.balance
    }
}