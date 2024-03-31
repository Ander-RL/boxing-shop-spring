
INSERT INTO products(product_id, keyword, name, img, quantity, unitary_amount, description) VALUES(1, 'gloves', '10oz Boxing Gloves', 'Gloves.jpg', 12, 40, '10oz Boxing Gloves');
INSERT INTO products(product_id, keyword, name, img, quantity, unitary_amount, description) VALUES(2, 'gloves', '12oz Boxing Gloves', 'Gloves.jpg', 26, 50, '12oz Boxing Gloves');
INSERT INTO products(product_id, keyword, name, img, quantity, unitary_amount, description) VALUES(3, 'gloves', '14oz Boxing Gloves', 'Gloves.jpg', 18, 60, '14oz Boxing Gloves');
INSERT INTO products(product_id, keyword, name, img, quantity, unitary_amount, description) VALUES(4, 'headguard', 'Male HeadGuard', 'Headguard.jpg', 12, 70, 'Male HeadGuard');
INSERT INTO products(product_id, keyword, name, img, quantity, unitary_amount, description) VALUES(5, 'headguard', 'Female HeadGuard', 'Headguard.jpg', 6, 70, 'Female HeadGuard');
INSERT INTO products(product_id, keyword, name, img, quantity, unitary_amount, description) VALUES(6, 'headguard', 'Child HeadGuard', 'Headguard.jpg', 3, 50, 'Child HeadGuard');
INSERT INTO products(product_id, keyword, name, img, quantity, unitary_amount, description) VALUES(7, 'bag', 'Punching Bag', 'HookBag.jpg', 10, 200, 'Punching bag for practicing hooks');
INSERT INTO products(product_id, keyword, name, img, quantity, unitary_amount, description) VALUES(8, 'bag', 'Common Punching Bag', 'HookBag.jpg', 22, 250, 'Punching bag for general practicing');
INSERT INTO products(product_id, keyword, name, img, quantity, unitary_amount, description) VALUES(9, 'vandages', '5m Vandages', 'Vandages.jpg', 32, 25, '5m Vandages');
INSERT INTO products(product_id, keyword, name, img, quantity, unitary_amount, description) VALUES(10, 'vandages', '4m Vandages', 'Vandages.jpg', 40, 25, '4m Vandages');
INSERT INTO products(product_id, keyword, name, img, quantity, unitary_amount, description) VALUES(11, 'vandages', '3m Vandages', 'Vandages.jpg', 44, 20, '3m Vandages');
INSERT INTO products(product_id, keyword, name, img, quantity, unitary_amount, description) VALUES(12, 'pads', 'Regular Hand Pads', 'Pads.png', 24, 50, 'Pads for practicing technic');
INSERT INTO products(product_id, keyword, name, img, quantity, unitary_amount, description) VALUES(13, 'pads', 'Small Hand Pads', 'Pads.png', 14, 20, 'Pads for practicing technic');
INSERT INTO products(product_id, keyword, name, img, quantity, unitary_amount, description) VALUES(14, 'mouthpiece', 'Simple Mouthpiece', 'Mouthpiece.jpg', 67, 20, 'Simple to adapt mouthpiece');
INSERT INTO products(product_id, keyword, name, img, quantity, unitary_amount, description) VALUES(15, 'mouthpiece', 'Pro Mouthpiece', 'Mouthpiece.jpg', 26, 50, 'Professional mouthpiece');


INSERT INTO roles(role_id, authority) VALUES (1, 'ADMIN');
INSERT INTO roles(role_id, authority) VALUES (2, 'USER');

/*'Password = 123 using https://bcrypt-generator.com/'*/
INSERT INTO users(user_id, username, password) VALUES (1, 'ander', '$2a$12$OoiAK/YOO45A5myb5ylrbe5FhRScZfjubneHuqSPBE6OHvzT6k20K');

INSERT INTO user_role_junction(user_id, role_id) VALUES (1, 1);