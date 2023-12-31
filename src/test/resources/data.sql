DROP TABLE IF EXISTS customers;

CREATE TABLE IF NOT EXISTS customers (
                          id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          moneyspent INTEGER,
                          rewardstotal INTEGER);

insert into customers (name, moneyspent, rewardstotal) values ('Randy', 100, 10);
insert into customers (name, moneyspent, rewardstotal) values ('Tom', 310, 0);
insert into customers (name, moneyspent, rewardstotal) values ('Sarah', 250, 20);
insert into customers (name, moneyspent, rewardstotal) values ('Maddie', 99, 0);
insert into customers (name, moneyspent, rewardstotal) values ('Brad', 240, 10);