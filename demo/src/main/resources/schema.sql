
create table Person(
                       id integer primary key ,
                       personName VARCHAR(64),
                       secondName varchar(64),
                       email varchar(28),
                       telephone varchar(18),
                       passwords varchar(8),
                       createdAt TIMESTAMP,
                       roles varchar(10),
                       isactif  integer

);
create table Faculty(
                        id integer primary key ,
                        facultyname varchar(50),
                        doyen varchar(50)
);
create table Book(
                     isbn integer primary key ,
                     bookName varchar(64),
                     author   varchar(64),
                     category varchar(20),
                     booklanguage varchar(3)
);
create table Student_Book(
                             studentId integer ,
                             bookId integer,
                             foreign key (bookId) references Book(isbn),
                             foreign key (studentId) references Person(id)
);
create table Student_Faculty(
                                studentId integer,
                                facId integer,
                                foreign key (studentId) references Person(id),
                                foreign key (facId) references Faculty(id)
);
