INSERT INTO customer (CODE) VALUES
(1234),
(3124),
(3123),
(3213);

INSERT INTO genre (NAME) VALUES
('horror'),
('komedia'),
('sensacja'),
('akcja'),
('dramat'),
('thriller'),
('familijny'),
('dokumentalny');

INSERT INTO movie (TITLE, GENRE_ID) VALUES 
('Koszmar z ulicy Wiązów', 1),
('Nie zadzieraj z fryzjerem', 2),
('Hugo', 7),
('Świat według kiepskich', 8),
('Ojciec Chrzestny', 7);


-- populate available seat. There are 3 rows of 5 seats each
INSERT INTO seat (SEAT_NUMBER, ROW_NUMBER) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(1, 3),
(2, 3),
(3, 3),
(4, 3),
(5, 3);

-- insert horrors each day at 21 o'clock
INSERT INTO showing (MOVIE_ID, SHOWING_DATETIME) VALUES
(1, '2016-12-25 21:00:00'),
(1, '2016-12-26 21:00:00'),
(1, '2016-12-27 21:00:00'),
(1, '2016-12-28 21:00:00'),
(1, '2016-12-29 21:00:00'),
(1, '2016-12-30 21:00:00');

-- insert comedies each day at 15 o'clock
INSERT INTO showing (MOVIE_ID, SHOWING_DATETIME) VALUES
(2, '2016-12-25 15:00:00'),
(2, '2016-12-26 15:00:00'),
(2, '2016-12-27 15:00:00'),
(2, '2016-12-28 15:00:00'),
(2, '2016-12-29 15:00:00'),
(2, '2016-12-30 15:00:00');

INSERT INTO booking (CUSTOMER_ID, SHOWING_ID) VALUES
(1, 1),
(2, 7);

INSERT INTO booking_seats (BOOKING_ID, SEAT_ID) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 3),
(2, 4),
(2, 5);
