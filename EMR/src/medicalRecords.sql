CREATE TABLE `electronicManagemetnSystem`.`patients`(
 `id` TEXT, 
`fname` TEXT,
 `lname` TEXT,
 `sex` TEXT,
 `email` TEXT,
 `DOB` TEXT);
 
 CREATE TABLE `electronicManagemetnSystem`. `admin`(`username` VARCHAR(20),
 `password` VARCHAR(20),
 `division`VARCHAR(20));
 
 INSERT INTO `electronicManagemetnSystem`.`patients`(id, fname,lname,sex,email,DOB) VALUES (
 '3',
 'Tukubo',
 'Nonso',
 'M',
 'tukNOso@gmail.com',
 '10-07-1998');
 
  INSERT INTO `electronicManagemetnSystem`.`admin`(username,password,division) VALUES ('Dami','dami','admin');
  