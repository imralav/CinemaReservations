INSERT INTO CUSTOMER (CODE) VALUES
(1234),
(3124),
(3123),
(3213);

INSERT INTO GENRE (NAME) VALUES
('horror'),
('komedia'),
('sensacja'),
('akcja'),
('dramat'),
('thriller'),
('familijny'),
('dokumentalny');

INSERT INTO MOVIE (TITLE, GENRE_ID) VALUES 
('Koszmar z ulicy Wiązów', 1),
('Nie zadzieraj z fryzjerem', 2),
('Hugo', 7),
('Świat według kiepskich', 8);

INSERT INTO AUDITORIUM (NAME) VALUES
('Sala 1');

-- populate available seats for first auditorium. There are 3 rows of 5 seats each
INSERT INTO SEAT (SEAT_NUMBER, ROW_NUMBER, AUDITORIUM_ID) VALUES
(1, 1, 1),
(2, 1, 1),
(3, 1, 1),
(4, 1, 1),
(5, 1, 1),
(1, 2, 1),
(2, 2, 1),
(3, 2, 1),
(4, 2, 1),
(5, 2, 1),
(1, 3, 1),
(2, 3, 1),
(3, 3, 1),
(4, 3, 1),
(5, 3, 1);

-- insert horrors each day at 21 o'clock
INSERT INTO SHOWING (MOVIE_ID, AUDITORIUM_ID, SHOWING_DATETIME) VALUES
(1, 1, '2016-12-25 21:00:00'),
(1, 1, '2016-12-26 21:00:00'),
(1, 1, '2016-12-27 21:00:00'),
(1, 1, '2016-12-28 21:00:00'),
(1, 1, '2016-12-29 21:00:00'),
(1, 1, '2016-12-30 21:00:00');

-- insert comedies each day at 15 o'clock
INSERT INTO SHOWING (MOVIE_ID, AUDITORIUM_ID, SHOWING_DATETIME) VALUES
(2, 1, '2016-12-25 15:00:00'),
(2, 1, '2016-12-26 15:00:00'),
(2, 1, '2016-12-27 15:00:00'),
(2, 1, '2016-12-28 15:00:00'),
(2, 1, '2016-12-29 15:00:00'),
(2, 1, '2016-12-30 15:00:00');

INSERT INTO BOOKING (CUSTOMER_ID, SEAT_ID, SHOWING_ID) VALUES
(1, 1, 1),
(2, 15, 7);
