CREATE TABLE IF NOT EXISTS CUSTOMER (
ID INT AUTO_INCREMENT NOT NULL UNIQUE,
CODE INT(4) NOT NULL UNIQUE,
PRIMARY KEY (ID)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS GENRE (
ID INTEGER AUTO_INCREMENT,
NAME VARCHAR(20) NOT NULL UNIQUE,
PRIMARY KEY (ID)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS MOVIE (
ID INTEGER AUTO_INCREMENT,
TITLE VARCHAR(30) NOT NULL UNIQUE,
GENRE_ID INTEGER NOT NULL,
PRIMARY KEY (ID),
FOREIGN KEY (GENRE_ID) REFERENCES GENRE(ID)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS SEAT (
ID INTEGER AUTO_INCREMENT,
SEAT_NUMBER INTEGER NOT NULL,
ROW_NUMBER INTEGER NOT NULL,
PRIMARY KEY (ID)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS SHOWING (
ID INTEGER AUTO_INCREMENT,
MOVIE_ID INTEGER NOT NULL,
SHOWING_DATETIME DATETIME NOT NULL,
PRIMARY KEY (ID),
FOREIGN KEY (MOVIE_ID) REFERENCES MOVIE(ID)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS BOOKING (
ID INTEGER AUTO_INCREMENT,
CUSTOMER_ID INTEGER NOT NULL,
SEAT_ID INTEGER NOT NULL,
SHOWING_ID INTEGER NOT NULL,
PRIMARY KEY (ID),
FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(ID),
FOREIGN KEY (SHOWING_ID) REFERENCES SHOWING(ID),
FOREIGN KEY (SEAT_ID) REFERENCES SEAT(ID)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;