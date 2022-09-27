CREATE TABLE users (
id SERIAL PRIMARY KEY,
fName varchar(40),
lName varchar(40),
email varchar(50),
username varchar(40),
pw varchar(40)
);

CREATE TABLE reimbursement (
status varchar(40),
ticket int,
username varchar(40),
amount int,
description varchar(255)
);

INSERT INTO users (fName, lName, email, username, pw) VALUES('Cory', 'Warden', '12cwarden@gmail.com', '12cwarden', 'test123');
INSERT INTO users (fName, lName, email, username, pw) VALUES('Admin', 'Admin', 'Admin@gmail.com', 'admin', 'password');
INSERT INTO reimbursement VALUES('Pending', 1389, '12cwarden', 150, 'travel')