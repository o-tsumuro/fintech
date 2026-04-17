#!/bin/bash

ACCOUNT_ID=1
AMOUNT=1000

curl -X POST http://localhost:8080/accounts/deposit \
-H "Content-Type: application/json" \
-d "{
  \"accountId\": $ACCOUNT_ID,
  \"amount\": $AMOUNT
}"