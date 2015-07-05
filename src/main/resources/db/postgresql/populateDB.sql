INSERT INTO samples_vet (id, first_name, last_name, email, pic_path, description) VALUES (1, 'James', 'Carter', 'JCarter@gmail.com', '/images/user.png', 'James Carter');
INSERT INTO samples_vet (id, first_name, last_name, email, pic_path, description) VALUES (2, 'Helen', 'Leary', 'HLeary@gmail.com', '/images/user.png', 'Helen Leary');
INSERT INTO samples_vet (id, first_name, last_name, email, pic_path, description) VALUES (3, 'Linda', 'Douglas', 'LDouglas@gmail.com', '/images/user.png', 'Linda Douglas');
INSERT INTO samples_vet (id, first_name, last_name, email, pic_path, description) VALUES (4, 'Rafael', 'Ortega', 'ROrtega@gmail.com', '/images/user.png', 'Rafael Ortega');
INSERT INTO samples_vet (id, first_name, last_name, email, pic_path, description) VALUES (5, 'Henry', 'Stevens', 'HStevens@gmail.com', '/images/user.png', 'Henry Stevens');
INSERT INTO samples_vet (id, first_name, last_name, email, pic_path, description) VALUES (6, 'Sharon', 'Jenkins', 'SJenkins@gmail.com', '/images/user.png', 'Sharon Jenkins');

INSERT INTO samples_specialty (id, name) VALUES (1, 'radiology');
INSERT INTO samples_specialty (id, name) VALUES (2, 'surgery');
INSERT INTO samples_specialty (id, name) VALUES (3, 'dentistry');

INSERT INTO samples_vet_specialty (vet_id, specialty_id) VALUES (2, 1);
INSERT INTO samples_vet_specialty (vet_id, specialty_id) VALUES (3, 2);
INSERT INTO samples_vet_specialty (vet_id, specialty_id) VALUES (3, 3);
INSERT INTO samples_vet_specialty (vet_id, specialty_id) VALUES (4, 2);
INSERT INTO samples_vet_specialty (vet_id, specialty_id) VALUES (5, 1);

INSERT INTO samples_pet_type (id, name) VALUES (1, 'cat');
INSERT INTO samples_pet_type (id, name) VALUES (2, 'dog');
INSERT INTO samples_pet_type (id, name) VALUES (3, 'lizard');
INSERT INTO samples_pet_type (id, name) VALUES (4, 'snake');
INSERT INTO samples_pet_type (id, name) VALUES (5, 'bird');
INSERT INTO samples_pet_type (id, name) VALUES (6, 'hamster');

INSERT INTO samples_owner (id, address, city, first_name, last_name, telephone) VALUES (1, '110 W. Liberty St.', 'Madison', 'George', 'Franklin', '6085551023');
INSERT INTO samples_owner (id, address, city, first_name, last_name, telephone) VALUES (2, '638 Cardinal Ave.', 'Sun Prairie', 'Betty', 'Davis', '6085551749');
INSERT INTO samples_owner (id, address, city, first_name, last_name, telephone) VALUES (3, '2693 Commerce St.', 'McFarland', 'Eduardo', 'Rodriquez', '6085558763');
INSERT INTO samples_owner (id, address, city, first_name, last_name, telephone) VALUES (4, '563 Friendly St.', 'Windsor', 'Harold', 'Davis', '6085553198');
INSERT INTO samples_owner (id, address, city, first_name, last_name, telephone) VALUES (5, '2387 S. Fair Way', 'Madison', 'Peter', 'McTavish', '6085552765');
INSERT INTO samples_owner (id, address, city, first_name, last_name, telephone) VALUES (6, '105 N. Lake St.', 'Monona', 'Jean', 'Coleman', '6085552654');
INSERT INTO samples_owner (id, address, city, first_name, last_name, telephone) VALUES (7, '1450 Oak Blvd.', 'Monona', 'Jeff', 'Black', '6085555387');
INSERT INTO samples_owner (id, address, city, first_name, last_name, telephone) VALUES (8, '345 Maple St.', 'Madison', 'Maria', 'Escobito', '6085557683');
INSERT INTO samples_owner (id, address, city, first_name, last_name, telephone) VALUES (9, '2749 Blackhawk Trail', 'Madison', 'David', 'Schroeder', '6085559435');
INSERT INTO samples_owner (id, address, city, first_name, last_name, telephone) VALUES (10, '2335 Independence La.', 'Waunakee', 'Carlos', 'Estaban', '6085555487');

INSERT INTO samples_pet (id, birth_date, name, portrait_file, owner_id, pet_type_id) VALUES (1, '2010-09-07 00:00:00', 'Leo', NULL, 1, 1);
INSERT INTO samples_pet (id, birth_date, name, portrait_file, owner_id, pet_type_id) VALUES (2, '2012-08-06 00:00:00', 'Basil', NULL, 2, 6);
INSERT INTO samples_pet (id, birth_date, name, portrait_file, owner_id, pet_type_id) VALUES (3, '2011-04-17 00:00:00', 'Rosy', NULL, 3, 2);
INSERT INTO samples_pet (id, birth_date, name, portrait_file, owner_id, pet_type_id) VALUES (4, '2010-03-07 00:00:00', 'Jewel', NULL, 3, 2);
INSERT INTO samples_pet (id, birth_date, name, portrait_file, owner_id, pet_type_id) VALUES (5, '2010-11-30 00:00:00', 'Iggy', NULL, 4, 3);
INSERT INTO samples_pet (id, birth_date, name, portrait_file, owner_id, pet_type_id) VALUES (6, '2010-01-20 00:00:00', 'George', NULL, 5, 4);
INSERT INTO samples_pet (id, birth_date, name, portrait_file, owner_id, pet_type_id) VALUES (7, '2012-09-04 00:00:00', 'Samantha', NULL, 6, 1);
INSERT INTO samples_pet (id, birth_date, name, portrait_file, owner_id, pet_type_id) VALUES (8, '2012-09-04 00:00:00', 'Max', NULL, 6, 1);
INSERT INTO samples_pet (id, birth_date, name, portrait_file, owner_id, pet_type_id) VALUES (9, '2011-08-06 00:00:00', 'Lucky', NULL, 7, 5);
INSERT INTO samples_pet (id, birth_date, name, portrait_file, owner_id, pet_type_id) VALUES (10, '2007-02-24 00:00:00', 'Mulligan', NULL, 8, 2);
INSERT INTO samples_pet (id, birth_date, name, portrait_file, owner_id, pet_type_id) VALUES (11, '2010-03-09 00:00:00', 'Freddy', NULL, 9, 5);
INSERT INTO samples_pet (id, birth_date, name, portrait_file, owner_id, pet_type_id) VALUES (12, '2010-06-24 00:00:00', 'Lucky', NULL, 10, 2);
INSERT INTO samples_pet (id, birth_date, name, portrait_file, owner_id, pet_type_id) VALUES (13, '2012-06-08 00:00:00', 'Sly', NULL, 10, 1);

INSERT INTO samples_visit (id, visit_date, description, pet_id) VALUES (1, '2013-01-01 00:00:00', 'rabies shot', 7);
INSERT INTO samples_visit (id, visit_date, description, pet_id) VALUES (2, '2013-01-02 00:00:00', 'rabies shot', 8);
INSERT INTO samples_visit (id, visit_date, description, pet_id) VALUES (3, '2013-01-03 00:00:00', 'neutered', 8);
INSERT INTO samples_visit (id, visit_date, description, pet_id) VALUES (4, '2013-01-04 00:00:00', 'spayed', 7);

INSERT INTO permission (id, permission_name, permission_desc) VALUES (1, 'menu_add_vet', 'vet:add');
INSERT INTO permission (id, permission_name, permission_desc) VALUES (2, 'menu_edit_vet_self', 'vet:edit:self');
INSERT INTO permission (id, permission_name, permission_desc) VALUES (3, 'menu_delete_vet', 'vet:delete');
INSERT INTO permission (id, permission_name, permission_desc) VALUES (4, 'menu_query_vet', 'vet:query');
INSERT INTO permission (id, permission_name, permission_desc) VALUES (5, 'menu_edit_vet_all', 'vet:edit:all');

INSERT INTO role (id, role_name, role_desc) VALUES (1, 'Administrators', 'admin');
INSERT INTO role (id, role_name, role_desc) VALUES (2, 'Visitors', 'visitor');
INSERT INTO role (id, role_name, role_desc) VALUES (3, 'Owners', 'owner');
INSERT INTO role (id, role_name, role_desc) VALUES (4, 'Veterinarian', 'vet');

INSERT INTO role_permission VALUES (1, 1);
INSERT INTO role_permission VALUES (1, 3);
INSERT INTO role_permission VALUES (1, 4);
INSERT INTO role_permission VALUES (4, 2);
INSERT INTO role_permission VALUES (4, 4);
INSERT INTO role_permission VALUES (1, 5);

INSERT INTO users (id, email, password, user_name) VALUES (1, 'yun.wu@tradevan.com.tw', '8c93cd35d2c753657eb0a18984001dba9f3c0409dcac98a381290d9e1ed151f4', '2904');
INSERT INTO users (id, email, password, user_name) VALUES (2, 'junit@tradevan.com.tw', '4d3b0a94c9aee412cc0a73b6ff7690d86959bbaa146b00ed7ced5a79a40c5f73', 'JunitTest');
INSERT INTO users (id, email, password, user_name) VALUES (3, 'owner@tradevan.com.tw', '4c1029697ee358715d3a14a2add817c4b01651440de808371f78165ac90dc581', 'owner');
INSERT INTO users (id, email, password, user_name) VALUES (4, 'vet@tradevan.com.tw', '5f9276129b845b99053564cf174d84da47da2943157720aa17ef889919222ea2', 'vet');
INSERT INTO users (id, email, password, user_name) VALUES (5, 'visitor@tradevan.com.tw', '5f14f9e6d80f802a65269804f2552ef9889f2c7ccec5067214e58a1e48e0b3ff', 'visitor');

INSERT INTO user_role VALUES (1, 1);
INSERT INTO user_role VALUES (2, 2);
INSERT INTO user_role VALUES (3, 3);
INSERT INTO user_role VALUES (4, 4);
INSERT INTO user_role VALUES (5, 2);

INSERT INTO hibernate_sequences VALUES ('samples_specialty', 3);
INSERT INTO hibernate_sequences VALUES ('samples_pet_type', 6);
INSERT INTO hibernate_sequences VALUES ('samples_owner', 10);
INSERT INTO hibernate_sequences VALUES ('samples_visit', 4);
INSERT INTO hibernate_sequences VALUES ('samples_pet', 14);
INSERT INTO hibernate_sequences VALUES ('samples_vet', 13);
INSERT INTO hibernate_sequences VALUES ('users', 10);
INSERT INTO hibernate_sequences VALUES ('role', 4);
INSERT INTO hibernate_sequences VALUES ('permission', 5);
