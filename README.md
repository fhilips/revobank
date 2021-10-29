# Revobank Coding Exercise

#### Postman Collection

Link: https://www.getpostman.com/collections/9155a07a17abf6f612a9

#### Swagger Api documentation

localhost:8080/swagger-ui.html

## Usage

#### Account

POST localhost:8080/accounts/create - Create new account.

GET localhost:8080/accounts - Get all accounts.

POST localhost:8080/accounts/{id} - Find account by id.

PUT localhost:8080/accounts/{id} - Update existing Account entry.

#### Balance

POST localhost:8080/balances/debit - Add debit by account id.

GET localhost:8080/balances/{id} - Find balance by account id.

GET localhost:8080/balances - Get all balances.

GET localhost:8080/balances/1/debits - Find All debits by account id.

## Tools

- Java 11
- Spring Boot
- H2 Database
- Spring Data JPA
- Bean Validation
- Swagger - Api documentation
