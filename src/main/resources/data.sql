INSERT INTO users (username, password, enabled, apikey, email) VALUES ('henk', '$2a$12$Y8eLViCk5IUVeOzu84PFo.aRC4dEDHHq87alktyco7nF8n0RamEG6', true, '7847493',
 'test@testy.tst'),('Rahman', '$2a$12$Y8eLViCk5IUVeOzu84PFo.aRC4dEDHHq87alktyco7nF8n0RamEG6', true, '7847493',
                    'test@testy.tst');

INSERT INTO authorities (username, authority) VALUES ('henk', 'ROLE_ADMIN'),('Rahman','ROLE_MECHANIC');

INSERT INTO cars(brand,model,manufacturingYear)VALUES('Toyota','C-HR',2020);
INSERT INTO carmechanics(name,age,address,email)VALUES ('Robert',25,'Tulpstraat 24','robert456@.gmail.com');
INSERT INTO clients(name,age,email)VALUES ('Jan',30,'Jan@.hotmail.com');
INSERT INTO garagereceptionists(name,phone_number,email)VALUES ('Mark',0686986617,'mark12@.gmail.com');
INSERT INTO inspections(status,report,inspection_cost)VALUES (true,'Inspection completed successfully',130.30);
INSERT INTO invoices(description,repair_cost,part_cost,inspection_cost,total_cost)VALUES ('Replacement of front tires',200.50,100.10,50.00
,350.60);
INSERT INTO repairs(description,treatment,status)VALUES ('General maintenance and repair','Replace front tires',true);
