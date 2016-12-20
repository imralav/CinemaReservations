INSERT INTO CUSTOMER (CODE) VALUES (1234);
INSERT INTO CUSTOMER (CODE) VALUES (3124);
INSERT INTO CUSTOMER (CODE) VALUES (3123);
INSERT INTO CUSTOMER (CODE) VALUES (3213);

INSERT INTO GENRE (NAME) VALUES ('horror');
INSERT INTO GENRE (NAME) VALUES ('komedia');
INSERT INTO GENRE (NAME) VALUES ('sensacja');
INSERT INTO GENRE (NAME) VALUES ('akcja');
INSERT INTO GENRE (NAME) VALUES ('dramat');
INSERT INTO GENRE (NAME) VALUES ('thriller');
INSERT INTO GENRE (NAME) VALUES ('familijny');
INSERT INTO GENRE (NAME) VALUES ('dokumentalny');

INSERT INTO MOVIE (TITLE, GENRE_ID) VALUES ('Koszmar z ulicy Wiazow', 1);
INSERT INTO MOVIE (TITLE, GENRE_ID) VALUES ('Nie zadzieraj z fryzjerem', 2);
INSERT INTO MOVIE (TITLE, GENRE_ID) VALUES ('Hugo', 7);
INSERT INTO MOVIE (TITLE, GENRE_ID) VALUES ('Świat według kiepskich', 8);

INSERT INTO SEAT (SEAT_NUMBER, ROW_NUMBER) VALUES (2, 2);
INSERT INTO SEAT (SEAT_NUMBER, ROW_NUMBER) VALUES (3, 2);

INSERT INTO SHOWING (MOVIE_ID, SHOWING_DATETIME) VALUES (2, '2016-12-25 18:00:00');
INSERT INTO SHOWING (MOVIE_ID, SHOWING_DATETIME) VALUES (1, '2016-12-25 21:00:00');
INSERT INTO SHOWING (MOVIE_ID, SHOWING_DATETIME) VALUES (1, '2016-12-26 21:00:00');

INSERT INTO BOOKING (CUSTOMER_ID, SHOWING_ID) VALUES (1, 1);
INSERT INTO BOOKING (CUSTOMER_ID, SHOWING_ID) VALUES (2, 1);

INSERT INTO BOOKING_SEATS (BOOKING_ID, SEAT_ID) VALUES (1, 1);
INSERT INTO BOOKING_SEATS (BOOKING_ID, SEAT_ID) VALUES (1, 2);
