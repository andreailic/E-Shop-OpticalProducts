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
	quantity INTEGER NOT NULL,
    description VARCHAR(255) NOT NULL,
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
    payment_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE order_item(
   PRIMARY KEY (product_id,order_id),
    quantity INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    price INTEGER NOT NULL,
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

