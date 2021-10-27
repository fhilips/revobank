INSERT INTO tb_account (name, document, birth_date, created_at, updated_at, job_title, status, account, account_digit ) VALUES 
('Carlos Albuquerque', '69290530820', '1990-07-12', TIMESTAMP WITH TIME ZONE '2021-07-14T10:00:00Z', TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'PROGRAMMER', 'ACTIVE', '1234567', '2');

INSERT INTO tb_account (name, document, birth_date, created_at, updated_at, job_title, status, account, account_digit ) VALUES 
('Maria Candida', '51456678841', '1980-08-10', TIMESTAMP WITH TIME ZONE '2021-10-18T10:00:00Z', TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'DIRECTOR', 'ACTIVE', '6957468', '7');

INSERT INTO tb_account (name, document, birth_date, created_at, updated_at, job_title, status, account, account_digit ) VALUES 
('João Augusto', '78667129801', '1977-01-09', TIMESTAMP WITH TIME ZONE '2021-10-20T10:00:00Z', TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'COUNTER', 'ACTIVE', '9874526', '4');

INSERT INTO tb_balance (balance, account_id, updated_at) VALUES (5000, 1, TIMESTAMP WITH TIME ZONE '2021-07-14T10:00:00Z');

INSERT INTO tb_balance (balance, account_id, updated_at) VALUES (5000, 2, TIMESTAMP WITH TIME ZONE '2021-10-18T10:00:00Z');

INSERT INTO tb_balance (balance, account_id, updated_at) VALUES (5000, 3, TIMESTAMP WITH TIME ZONE '2021-10-20T10:00:00Z');