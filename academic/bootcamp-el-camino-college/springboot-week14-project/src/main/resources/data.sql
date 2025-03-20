INSERT INTO pet_store (address, city, state, zip, name, phone) VALUES ('123 Main St', 'NoWhere', 'CA', '91111', 'Pawtastic', '310-444-5566')
INSERT INTO pet_store (address, city, state, zip, name, phone) VALUES ('999 Elm St', 'Hell', 'CA', '96661', 'Wagging Tails', '310-999-5666')
INSERT INTO pet_store (address, city, state, zip, name, phone) VALUES ('2 Second Ave', 'Secondary', 'CA', '92222', 'Number Two', '310-222-2222')

INSERT INTO employee (pet_store_id, first_name, last_name, job_title, phone) VALUES (1, 'Michael', 'Scott', '9526664444', 'Founder and CEO')
INSERT INTO employee (pet_store_id, first_name, last_name, job_title, phone) VALUES (1, 'Dwight', 'Schrute', '9526664444', 'Regional Manager')

INSERT INTO customer (email, first_name, last_name) VALUES ('deadpool@marvel.com', 'Wade', 'Wilson')
INSERT INTO customer (email, first_name, last_name) VALUES ('spiderman@marvel.com', 'Peter', 'Parker')
INSERT INTO customer (email, first_name, last_name) VALUES ('ironman@marvel.com', 'Tony', 'Stark')

INSERT INTO pet_store_customers (customer_id, pet_store_id) VALUES (1, 1)
INSERT INTO pet_store_customers (customer_id, pet_store_id) VALUES (1, 2)
INSERT INTO pet_store_customers (customer_id, pet_store_id) VALUES (1, 3)
INSERT INTO pet_store_customers (customer_id, pet_store_id) VALUES (2, 1)
INSERT INTO pet_store_customers (customer_id, pet_store_id) VALUES (3, 1)