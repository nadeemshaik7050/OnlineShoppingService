CREATE TABLE order_details
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    product_name         VARCHAR(255)    NULL,
    quantity            INT          NULL,
    amount              BIGINT          NULL,
    discount_in_percentage INT   NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

insert into order_details (id,product_name,quantity,amount,discount_in_percentage) values (1, 'Product1',1,100, 10);
insert into order_details (id,product_name,quantity,amount,discount_in_percentage) values (2, 'Product2',2,200, 15);
insert into order_details (id,product_name,quantity,amount,discount_in_percentage) values (3, 'Product3',1, 100, 20);
insert into order_details (id,product_name,quantity,amount,discount_in_percentage) values (4, 'Product4',1, 100, 5);
insert into order_details (id,product_name,quantity,amount,discount_in_percentage) values (5, 'Product5',2,200, 25);
insert into order_details (id,product_name,quantity,amount,discount_in_percentage) values (6, 'Product6',1,100, 10);
insert into order_details (id,product_name,quantity,amount,discount_in_percentage) values (7, 'Product7',2,200, 15);
insert into order_details (id,product_name,quantity,amount,discount_in_percentage) values (8, 'Product8',1, 100, 20);
insert into order_details (id,product_name,quantity,amount,discount_in_percentage) values (9, 'Product9',1, 100, 5);
insert into order_details (id,product_name,quantity,amount,discount_in_percentage) values (10, 'Product10',2,200, 25);

