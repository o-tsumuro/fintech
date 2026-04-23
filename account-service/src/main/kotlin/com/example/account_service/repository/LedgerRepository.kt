package com.example.account_service.repository

import com.example.account_service.model.Ledger
import org.springframework.data.jpa.repository.JpaRepository

interface LedgerRepository : JpaRepository<Ledger, Long> {

    fun findByAccountId(accountId: Long): List<Ledger>
}