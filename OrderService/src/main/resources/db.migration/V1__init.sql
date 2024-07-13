CREATE TABLE order_details
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    productName         VARCHAR(255)    NULL,
    quantity            INT          NULL,
    amount              BIGINT          NULL,
    discountinPercentage INT   NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

insert into order_details (id,productName,quantity,amount,discountinPercentage)
values (1, 'Product1',1,100, 10);
insert into order_details (id,productName,quantity,amount,discountinPercentage) values (2, 'Product2',2,200, 15);
insert into order_details (id,productName,quantity,amount,discountinPercentage) values (3, 'Product3',1, 100, 20);
insert into order_details (id,productName,quantity,amount,discountinPercentage) values (4, 'Product4',1, 100, 5);
insert into order_details (id,productName,quantity,amount,discountinPercentage) values (5, 'Product5',2,200, 25);
