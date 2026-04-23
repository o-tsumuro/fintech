#!/bin/bash

FROM_ACCOUNT_ID=1
TO_ACCOUNT_ID=2
AMOUNT=1000

IDEMPOTENCY_KEY=$(uuidgen)

curl -X POST http://localhost:8080/accounts/transfer \
-H "Content-Type: application/json" \
-d "{
  \"fromAccountId\": $FROM_ACCOUNT_ID,
  \"toAccountId\": $TO_ACCOUNT_ID,
  \"amount\": $AMOUNT,
  \"idempotencyKey\": \"$IDEMPOTENCY_KEY\"
}"