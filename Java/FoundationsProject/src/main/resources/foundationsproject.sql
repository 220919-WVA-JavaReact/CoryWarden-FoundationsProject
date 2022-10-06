CREATE TABLE users (
id SERIAL PRIMARY KEY,
fName varchar(40),
lName varchar(40),
email varchar(50) NOT NULL UNIQUE,
username varchar(40) NOT NULL UNIQUE,
pw varchar(40) NOT NULL,
"role" varchar(20) NOT NULL DEFAULT 'employee'
);

CREATE TABLE reimbursement (
ticket serial UNIQUE NOT NULL,
status varchar(40) DEFAULT 'Pending',
authID int REFERENCES users,
username varchar(40),
amount int,
description varchar(255)
);

INSERT INTO users (fName, lName, email, username, pw) VALUES('Cory', 'Warden', '12cwarden@gmail.com', '12cwarden', 'test123');
INSERT INTO users (fName, lName, email, username, pw, "role") VALUES('Admin', 'Admin', 'Admin@gmail.com', 'admin', 'admin', 'manager');
INSERT INTO reimbursement (username, authID, amount, description) VALUES('12cwarden', 1, 150, 'travel');

insert into users (fName, lName, email, username, pw) values ('Janet', 'Stearn', 'jstearn0@tripadvisor.com', 'jstearn0', 'PWyl2t');
insert into users (fName, lName, email, username, pw) values ('Emmerich', 'Halls', 'ehalls1@i2i.jp', 'ehalls1', 'smB90KRbHN0');
insert into users (fName, lName, email, username, pw) values ('Judye', 'Dibbin', 'jdibbin2@about.com', 'jdibbin2', 'THD9axxyB94');
insert into users (fName, lName, email, username, pw) values ('Donal', 'Munehay', 'dmunehay3@xinhuanet.com', 'dmunehay3', 'vkiIJL7');
insert into users (fName, lName, email, username, pw) values ('Shelagh', 'Newlove', 'snewlove4@samsung.com', 'snewlove4', 'AN2Ra3');
insert into users (fName, lName, email, username, pw) values ('Addia', 'Briffett', 'abriffett5@msn.com', 'abriffett5', 'KLXLxtxsFtQN');
insert into users (fName, lName, email, username, pw) values ('Lurlene', 'Trazzi', 'ltrazzi6@google.co.jp', 'ltrazzi6', 'nSz2Z1IoMQB');
insert into users (fName, lName, email, username, pw) values ('Gianni', 'Cancellor', 'gcancellor7@g.co', 'gcancellor7', 'TPLy1Vs');
insert into users (fName, lName, email, username, pw) values ('Elbertine', 'Bavister', 'ebavister8@mapquest.com', 'ebavister8', 'cKvrsAh');
insert into users (fName, lName, email, username, pw) values ('Mikael', 'Thridgould', 'mthridgould9@addthis.com', 'mthridgould9', 'slEn6Z');
insert into users (fName, lName, email, username, pw) values ('Basilio', 'Skidmore', 'bskidmorea@g.co', 'bskidmorea', 'elSlNI7');
insert into users (fName, lName, email, username, pw) values ('Leda', 'Larne', 'llarneb@indiegogo.com', 'llarneb', '8a3Z81btNoLh');
insert into users (fName, lName, email, username, pw) values ('Matteo', 'Carletto', 'mcarlettoc@sun.com', 'mcarlettoc', 'P81JRDhttTU3');
insert into users (fName, lName, email, username, pw) values ('Augustine', 'Gately', 'agatelyd@qq.com', 'agatelyd', 'NCpgRXW');
insert into users (fName, lName, email, username, pw) values ('Shea', 'Keavy', 'skeavye@marriott.com', 'skeavye', 'XOGVgtlIQ7z');
insert into users (fName, lName, email, username, pw) values ('Enrika', 'Coye', 'ecoyef@abc.net.au', 'ecoyef', 'AkQZYNeAbt8');
insert into users (fName, lName, email, username, pw) values ('Lionel', 'Pudner', 'lpudnerg@behance.net', 'lpudnerg', '14k4sQQl');
insert into users (fName, lName, email, username, pw) values ('Natty', 'Brainsby', 'nbrainsbyh@smh.com.au', 'nbrainsbyh', 'ZrhSzgTjmyKb');
insert into users (fName, lName, email, username, pw) values ('Donna', 'Sneddon', 'dsneddoni@gizmodo.com', 'dsneddoni', 'EE1KBddOOTVM');
insert into users (fName, lName, email, username, pw) values ('Newton', 'D''Ambrosio', 'ndambrosioj@nature.com', 'ndambrosioj', '4GPU6hn');

INSERT INTO reimbursement (authID, username, amount, description) VALUES(5, 'jdibbin2', 200, 'food');
INSERT INTO reimbursement (authID, username, amount, description) VALUES(5, 'jdibbin2', 750, 'lodging');
INSERT INTO reimbursement (authID, username, amount, description) VALUES(18, 'ecoyef', 180, 'general expense');
INSERT INTO reimbursement (authID, username, amount, description) VALUES(19, 'lpudnerg', 950, 'travel');
INSERT INTO reimbursement (authID, username, amount, description) VALUES(20, 'nbrainsbyh', 1050, 'travel');
INSERT INTO reimbursement (authID, username, amount, description) VALUES(21, 'dsneddoni', 75, 'food');
INSERT INTO reimbursement (authID, username, amount, description) VALUES(5, 'jdibbin2', 25, 'food');
INSERT INTO reimbursement (authID, username, amount, description) VALUES(22, 'ndambrosioj', 150, 'lodging');


