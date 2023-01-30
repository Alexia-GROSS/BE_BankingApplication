DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS transaction;


CREATE TABLE customer (
                          customerID SERIAL,
                          firstName VARCHAR(100) NOT NULL,
                          lastName VARCHAR(100) NOT NULL,
                          userName VARCHAR(100) NOT NULL,
                          password VARCHAR(100) NOT NULL,
                          PRIMARY KEY (customerID)
);

CREATE TABLE transaction (
                             transactionID SERIAL,
                             amount NUMERIC NOT NULL,
                             targetAccount VARCHAR(100) NOT NULL,
                             sendingAccount VARCHAR(100) NOT NULL,
                             date TIMESTAMP NOT NULL,
                             description VARCHAR(100),
                             PRIMARY KEY (transactionID)
);

CREATE TABLE account (
                         iban INT NOT NULL,
                         balance NUMERIC NOT NULL,
                         ownerID VARCHAR(100) NOT NULL
);