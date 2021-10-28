# Revobank Coding Exercise


### localhost:8080/swagger-ui.html - Swagger Api documentation


## Usage


### Account

POST localhost:8080/accounts/create - Create new account.

GET localhost:8080/accounts - Get all accounts.

POST localhost:8080/accounts/{id} - Find account by id.

PUT localhost:8080/accounts/{id} - Update existing Account entry.

### Balance 

POST localhost:8080/balances/debit - Add debit by account id.

GET localhost:8080/balances/{id} - Find balance by account id.

GET localhost:8080/balances - Get all balances.

GET localhost:8080/balances/1/debits - Find All debits by account id.


