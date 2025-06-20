
create table Person(
                       id integer primary key auto_increment ,
                       personName VARCHAR(64),
                       secondName varchar(64),
                       email varchar(28),
                       telephone varchar(18),
                       password varchar(8),
                       createdAt TIMESTAMP,
                       roles varchar(10),
                       isactif  integer


);
create table Faculty(
                        id varchar(3) primary key ,
                        facultyname varchar(50),
                        doyen varchar(50)

);
create table Book(
                     id integer primary key auto_increment,
                     isbn  bigint,
                     book_name varchar(64),
                     author   varchar(64),
                     category varchar(20),
                     description varchar,
                     book_language varchar(3),
                     image varchar,
                     copies integer
);
create table Student_Book(
                             studentId integer ,
                             bookId integer,
                             foreign key (bookId) references Book(id),
                             foreign key (studentId) references Person(id)
);
create table Student_Faculty(
                                studentId integer,
                                facultyId VARCHAR,
                                foreign key (studentId) references Person(id),
                                foreign key (facultyId) references Faculty(id)
);


