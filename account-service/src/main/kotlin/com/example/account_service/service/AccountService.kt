package com.example.account_service.service

import com.example.account_service.repository.AccountRepository
import com.example.account_service.repository.IdempotencyKeyRepository
import com.example.account_service.model.IdempotencyKey
import com.example.account_service.repository.LedgerRepository
import com.example.account_service.model.Ledger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val ledgerRepository: LedgerRepository,
    private val idempotencyKeyRepository: IdempotencyKeyRepository
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

        ledgerRepository.save(
            Ledger(
                accountId = accountId,
                amount = amount,
                type = "DEPOSIT"
            )
        )

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

        ledgerRepository.save(
            Ledger(
                accountId = accountId,
                amount =  -amount,
                type =  "WITHDRAW"
            )
        )

        accountRepository.save(account)
    }

    @Transactional
    fun transfer(
        fromId: Long,
        toId: Long,
        amount: Long,
        key: String
    ) {

        if (amount <= 0) {
            throw RuntimeException("invalid amount")
        }

        val from = accountRepository.findById(fromId)
            .orElseThrow { RuntimeException("from account not found") }

        val to = accountRepository.findById(toId)
            .orElseThrow { RuntimeException("to account not found") }

        if (from.balance < amount) {
            throw RuntimeException("insufficient balance")
        }

        from.balance -= amount
        to.balance += amount

        ledgerRepository.save(
            Ledger(
                accountId = fromId,
                amount = -amount,
                type = "TRANSFER_OUT"
            )
        )

        ledgerRepository.save(
            Ledger(
                accountId = toId,
                amount = amount,
                type = "TRANSFER_IN"
            )
        )

        idempotencyKeyRepository.save(IdempotencyKey(key))
    }
}