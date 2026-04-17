#!/bin/bash

ACCOUNT_ID=1
AMOUNT=500

curl -X POST http://localhost:8080/accounts/withdraw \
-H "Content-Type: application/json" \
-d "{
  \"accountId\": $ACCOUNT_ID,
  \"amount\": $AMOUNT
}"