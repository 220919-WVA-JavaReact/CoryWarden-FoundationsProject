CREATE TABLE users (
id SERIAL PRIMARY KEY,
fName varchar(40),
lName varchar(40),
email varchar(50),
username varchar(40),
pw varchar(40),
"role" varchar(20) NOT NULL DEFAULT 'Employee'
);

CREATE TABLE reimbursement (
status varchar(40) DEFAULT 'Pending',
ticket serial UNIQUE NOT NULL,
username varchar(40),
authID int REFERENCES users (id),
amount int,
description varchar(255)
);

INSERT INTO users (fName, lName, email, username, pw) VALUES('Cory', 'Warden', '12cwarden@gmail.com', '12cwarden', 'test123');
INSERT INTO users (fName, lName, email, username, pw, "role") VALUES('Admin', 'Admin', 'Admin@gmail.com', 'admin', 'admin', 'Manager');
INSERT INTO reimbursement (username, authID, amount, description) VALUES('12cwarden', 1, 150, 'travel');

