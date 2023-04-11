DROP TRIGGER discount_trigger ON orders;
DROP FUNCTION apply_discount() ;

DROP TRIGGER update_stock ON orders;
DROP FUNCTION check_stock() ;

DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS brand;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS address;




CREATE TABLE address(
    address_id SERIAL PRIMARY KEY NOT NULL,
    street VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL,
    zip INTEGER NOT NULL
);


CREATE TABLE users(
    user_id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    address_id INTEGER NOT NULL,
    phone VARCHAR(20) NOT NULL,
	is_admin BOOLEAN NOT NULL,
    user_name VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
	FOREIGN KEY(address_id) REFERENCES address(address_id)
);

CREATE TABLE brand(
    brand_id SERIAL PRIMARY KEY NOT NULL,
    brand_name VARCHAR(255) NOT NULL
);

CREATE TABLE category(
    category_id SERIAL PRIMARY KEY NOT NULL,
    category_name VARCHAR(255) NOT NULL
);

CREATE TABLE product(
    product_id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(8, 2) NOT NULL,
    description VARCHAR(255) NOT NULL,
	lager INTEGER NOT NULL,
    brand_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
	FOREIGN KEY(brand_id) REFERENCES brand(brand_id),
	FOREIGN KEY(category_id) REFERENCES category(category_id)
);

CREATE TABLE review(
    review_id SERIAL PRIMARY KEY NOT NULL,
    rating DOUBLE PRECISION NOT NULL,
    comment VARCHAR(255) NOT NULL,
    user_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
	FOREIGN KEY(user_id) REFERENCES users(user_id),
	FOREIGN KEY(product_id) REFERENCES product(product_id)
);


CREATE TABLE orders(
    order_id SERIAL PRIMARY KEY NOT NULL,
    order_date DATE NOT NULL,
    order_status VARCHAR(255) NOT NULL,
    total_price DOUBLE PRECISION NOT NULL,
    user_id INTEGER NOT NULL,
	discount DECIMAL DEFAULT 0.0,
    FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE order_item(
   PRIMARY KEY (product_id,order_id),
    quantity INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    order_id INTEGER NOT NULL,
	FOREIGN KEY(product_id) REFERENCES product(product_id),
	FOREIGN KEY(order_id) REFERENCES orders(order_id)
);


CREATE TABLE payment(
    payment_id SERIAL PRIMARY KEY NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    order_id INTEGER NOT NULL,
    FOREIGN KEY(order_id) REFERENCES orders(order_id)
);

INSERT INTO address (street, city, country, zip)
VALUES ('123 Main St', 'New York', 'United States', 10001);
INSERT INTO address (street, city, country, zip)
VALUES ('1000 Park Ave Apt 3A', 'San Francisco', 'United States', 94108);

INSERT INTO users (name, surname, email, address_id, phone, is_admin, user_name, password)
VALUES ('John', 'Doe', 'johndoe@example.com', 1, '555-1234', false, 'johndoe', 'password123');
INSERT INTO users (name, surname, email, address_id, phone, is_admin, user_name, password)
VALUES ('Jane', 'Doe', 'janedoe@example.com', 2, '555-5678', true, 'adminjane', 'securepassword');


INSERT INTO brand (brand_name)
VALUES ('Dior');
INSERT INTO brand (brand_name)
VALUES ('Dolce & Gabbana');

INSERT INTO category (category_name)
VALUES ('Lenses');

INSERT INTO category (category_name)
VALUES ('Sunglasses');


INSERT INTO product (name, price, description,lager, brand_id, category_id)
VALUES ('F1-36 DG', 15000, 'classic model of D&G sunglasses',12, 2, 2);

INSERT INTO product (name, price,  description,lager, brand_id, category_id)
VALUES ('Blue Pro F3', 17900, 'Powerful lenses for professional swimmers',7, 2, 1);


INSERT INTO orders (order_date, order_status, total_price, user_id, discount)
VALUES ('2023-03-15', 'Shipped', 15500, 2, 0);
INSERT INTO orders (order_date, order_status, total_price, user_id, discount)
VALUES ('2023-03-18', 'Prepared', 12000, 1, 0);


INSERT INTO payment (payment_method, order_id)
VALUES ('PayPal', 2);
INSERT INTO payment (payment_method, order_id)
VALUES ('Credit Card', 1);

INSERT INTO review (rating, comment, user_id, product_id)
VALUES (3.0, 'Average product, nothing special.', 2, 1);
INSERT INTO review (rating, comment, user_id, product_id)
VALUES (4.5, 'Great product, highly recommended!', 1, 2);

INSERT INTO order_item (quantity, product_id, order_id)
VALUES (3, 1, 1);
INSERT INTO order_item (quantity, product_id, order_id)
VALUES (1, 2, 2);


CREATE OR REPLACE FUNCTION apply_discount() RETURNS TRIGGER AS $$
BEGIN
    IF NEW.total_price > 30000 THEN
        NEW.discount := 0.15;
        NEW.total_price := NEW.total_price * (1 - NEW.discount);
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER discount_trigger
BEFORE INSERT OR UPDATE ON orders
FOR EACH ROW
EXECUTE FUNCTION apply_discount();



select * from orders

update orders set total_price = 42000 where order_id = 1

INSERT INTO orders (order_date, order_status, total_price, user_id, discount)
VALUES ('2023-03-15', 'Shipped', 15500, 2, );




CREATE OR REPLACE FUNCTION check_stock() RETURNS TRIGGER AS $$
BEGIN
    IF NEW.quantity > (SELECT lager FROM product WHERE product_id = NEW.product_id) THEN
        RAISE EXCEPTION 'Out of stock';
    ELSE
        UPDATE product SET lager = lager - NEW.quantity WHERE product_id = NEW.product_id;
        RETURN NEW;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_stock
BEFORE INSERT ON order_item
FOR EACH ROW
EXECUTE FUNCTION check_stock();


select * from order_item;
select * from product;

INSERT INTO order_item (quantity, product_id, order_id) VALUES (15, 1, 1);


