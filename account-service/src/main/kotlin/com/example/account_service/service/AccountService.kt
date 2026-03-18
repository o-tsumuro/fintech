package com.example.account_service.service

import com.example.account_service.repository.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(
    private val accountRepository: AccountRepository
) {
    fun getBalance(accountId: Long): Long {
        val account = accountRepository.findById(accountId)
            .orElseThrow { RuntimeException("account no found") }

        return account.balance
    }

    @Transactional
    fun deposit(accountId: Long, amount: Long) {
        if (amount <= 0) {
            throw RuntimeException("invalid amount")
        }

        val account = accountRepository.findById(accountId)
            .orElseThrow { RuntimeException("account not found") }

        account.balance += amount

        accountRepository.save(account)
    }

    @Transactional
    fun withdraw(accountId: Long, amount: Long) {
        if (amount <= 0) {
            throw RuntimeException("invalid amount")
        }

        val account = accountRepository.findById(accountId)
            .orElseThrow { RuntimeException("account not found") }

        if (account.balance < amount) {
            throw RuntimeException("insufficient balance")
        }

        account.balance -= amount

        accountRepository.save(account)
    }
}