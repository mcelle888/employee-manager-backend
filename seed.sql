-- create tables
CREATE TABLE state (
    id INT AUTO_INCREMENT PRIMARY KEY,
    state VARCHAR(255) NOT NULL
);

CREATE TABLE address (
    id INT AUTO_INCREMENT PRIMARY KEY,
    number INT NOT NULL,
    address VARCHAR(255) NOT NULL,
    postcode INT NOT NULL,
    state_id INT,
    FOREIGN KEY (state_id) REFERENCES state(id)
);

CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    f_name VARCHAR(255) NOT NULL,
    l_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone BIGINT NOT NULL,
    dob DATE NOT NULL,
    full_time BOOLEAN NOT NULL,
    permanent BOOLEAN NOT NULL,
    date_started DATE NOT NULL,
    address_id INT,
    image_link VARCHAR(255),
    FOREIGN KEY (address_id) REFERENCES address(id)
);


-- seed tables
INSERT INTO state (state) VALUES 
('VIC'),
('NSW'),
('QLD'),
('SA'),
('WA'),
('TAS'),
('NT'),
('ACT');


INSERT INTO address (number, address, postcode, state_id) VALUES 
(10, 'Apple St', 3000, (SELECT id FROM state WHERE state = 'VIC')),
(22, 'Banana St', 2000, (SELECT id FROM state WHERE state = 'NSW')),
(43, 'Orange St', 4000, (SELECT id FROM state WHERE state = 'QLD')),
(56, 'Peach St', 5000, (SELECT id FROM state WHERE state = 'SA')),
(67, 'Pear St', 6000, (SELECT id FROM state WHERE state = 'WA')),
(78, 'Plum St', 7000, (SELECT id FROM state WHERE state = 'TAS')),
(89, 'Cherry St', 8000, (SELECT id FROM state WHERE state = 'NT')),
(90, 'Grape St', 9000, (SELECT id FROM state WHERE state = 'ACT')),
(91, 'Mango St', 9100, (SELECT id FROM state WHERE state = 'VIC'));


INSERT INTO employee (f_name, l_name, email, phone, dob, full_time, permanent, date_started, address_id, image_link) VALUES 
('Jake', 'Peralta', 'jp@example.com', 401222123, '1981-06-01', true, true, CURRENT_DATE, (SELECT id FROM address WHERE address = 'Apple St'), 'default_image_url'),
('Amy', 'Santiago', 'as@example.com', 401333123, '1983-09-15', true, true, CURRENT_DATE, (SELECT id FROM address WHERE address = 'Banana St'), 'default_image_url'),
('Rosa', 'Diaz', 'rd@example.com', 401444123, '1982-04-20', true, true, CURRENT_DATE, (SELECT id FROM address WHERE address = 'Orange St'), 'default_image_url'),
('Terry', 'Jeffords', 'tj@example.com', 401555123, '1973-06-30', true, true, CURRENT_DATE, (SELECT id FROM address WHERE address = 'Peach St'), 'default_image_url'),
('Raymond', 'Holt', 'rh@example.com', 401666123, '1962-02-20', true, true, CURRENT_DATE, (SELECT id FROM address WHERE address = 'Pear St'), 'default_image_url'),
('Gina', 'Linetti', 'gl@example.com', 401777123, '1985-10-15', true, true, CURRENT_DATE, (SELECT id FROM address WHERE address = 'Plum St'), 'default_image_url'),
('Charles', 'Boyle', 'cb@example.com', 401888123, '1978-11-11', true, true, CURRENT_DATE, (SELECT id FROM address WHERE address = 'Cherry St'), 'default_image_url'),
('Hitchcock', 'Scully', 'hs@example.com', 401999123, '1960-09-09', true, true, CURRENT_DATE, (SELECT id FROM address WHERE address = 'Grape St'), 'default_image_url'),
('Kevin', 'Cozner', 'kc@example.com', 401101123, '1964-05-05', true, true, CURRENT_DATE, (SELECT id FROM address WHERE address = 'Mango St'), 'default_image_url');
