package com.example.account_service.controller

import com.example.account_service.service.AccountService
import com.example.account_service.model.Ledger
import com.example.account_service.repository.LedgerRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val accountService: AccountService,
    private val ledgerRepository: LedgerRepository
) {

    @GetMapping("/{id}/balance")
    fun getBalance(@PathVariable id: Long): Map<String, Long> {
        val balance = accountService.getBalance(id)
        return mapOf("balance" to balance)
    }

    @PostMapping("/deposit")
    fun deposit(@RequestBody request: DepositRequest) {
        accountService.deposit(request.accountId, request.amount)
    }

    @PostMapping("/withdraw")
    fun withdraw(@RequestBody request: WithdrawRequest) {
        accountService.withdraw(request.accountId, request.amount)
    }

    @PostMapping("/transfer")
    fun transfer(@RequestBody request: TransferRequest) {
        accountService.transfer(
            request.fromAccountId,
            request.toAccountId,
            request.amount,
            request.idempotencyKey,
        )
    }

    @GetMapping("/{id}/transactions")
    fun getTransactions(@PathVariable id: Long): List<Ledger> {
        return ledgerRepository.findByAccountId(id)
    }
}