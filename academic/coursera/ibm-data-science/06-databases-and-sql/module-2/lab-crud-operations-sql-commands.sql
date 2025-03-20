CREATE TABLE pet_sale (
        id INTEGER NOT NULL,
        pet CHAR(20),
        sale_price DECIMAL(6,2),
        profit DECIMAL(6,2),
        sale_date DATE
        );
        
CREATE TABLE pet (
        id INTEGER NOT NULL,
        animal VARCHAR(20),
        quantity INTEGER
        );
        
INSERT INTO pet_sale VALUES
        (1,'Cat',450.09,100.47,'2018-05-29'),
        (2,'Dog',666.66,150.76,'2018-06-01'),
        (3,'Parrot',50.00,8.9,'2018-06-04'),
        (4,'Hamster',60.60,12,'2018-06-11'),
        (5,'Goldfish',48.48,3.5,'2018-06-14');
        
INSERT INTO pet VALUES
        (1,'Cat',3),
        (2,'Dog',4),
        (3,'Hamster',2);
        
SELECT * FROM pet_sale;
SELECT * FROM pet;