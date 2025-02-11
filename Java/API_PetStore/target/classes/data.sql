INSERT INTO store (address, city, state, zip, name, phone) VALUES ('123 Main St', 'NoWhere', 'CA', '91111', 'Pawtastic', '310-444-5566')
INSERT INTO store (address, city, state, zip, name, phone) VALUES ('999 Elm St', 'Hell', 'CA', '96661', 'Wagging Tails', '310-999-5666')
INSERT INTO store (address, city, state, zip, name, phone) VALUES ('2 Second Ave', 'Secondary', 'CA', '92222', 'Number Two', '310-222-2222')

INSERT INTO employee (store_id, first_name, last_name, phone, job_title) VALUES (1, 'Michael', 'Scott', '9526664444', 'Founder and CEO')
INSERT INTO employee (store_id, first_name, last_name, phone, job_title) VALUES (1, 'Dwight', 'Schrute', '9526664444', 'Regional Manager')

INSERT INTO customer (email, first_name, last_name) VALUES ('deadpool@marvel.com', 'Wade', 'Wilson')
INSERT INTO customer (email, first_name, last_name) VALUES ('spiderman@marvel.com', 'Peter', 'Parker')
INSERT INTO customer (email, first_name, last_name) VALUES ('ironman@marvel.com', 'Tony', 'Stark')

--INSERT INTO pet_store_customers (customer_id, pet_store_id) VALUES (1, 1)
--INSERT INTO pet_store_customers (customer_id, pet_store_id) VALUES (1, 2)
--INSERT INTO pet_store_customers (customer_id, pet_store_id) VALUES (1, 3)
--INSERT INTO pet_store_customers (customer_id, pet_store_id) VALUES (2, 1)
--INSERT INTO pet_store_customers (customer_id, pet_store_id) VALUES (3, 1)

INSERT INTO pet (category, status, name, tags) VALUES (0, 0, "Max", null)
INSERT INTO pet (category, status, name, tags) VALUES (1, 0, "Void", null)

INSERT INTO product (price, description, name) VALUES (79.99, "Special dog food", "Hills Dog Food")
INSERT INTO product (price, description, name) VALUES (59.99, "Puppy dog food", "Hills Puppy Dog Food")

INSERT INTO inventory (quantity, reorder_level, store_id, product_id) VALUES (5, 1, 1, 1)
INSERT INTO inventory (quantity, reorder_level, store_id, product_id) VALUES (20, 0, 1, 2)