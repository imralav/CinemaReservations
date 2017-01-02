INSERT INTO customer (CODE) VALUES
(1234),
(3124);

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
('Hugo', 7),
('Mafia', 7),
('Koszmar z ulicy Wiązów', 1),
('Nie zadzieraj z fryzjerem', 2),
('Świat według kiepskich', 8);


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

INSERT INTO showing (MOVIE_ID, SHOWING_DATETIME) VALUES
(1, '2017-1-25 21:00:00'),
(1, '2017-1-26 21:00:00'),
(1, '2017-1-27 21:00:00'),
(1, '2017-1-28 21:00:00'),
(1, '2017-1-29 21:00:00'),
(1, '2017-1-30 21:00:00');

INSERT INTO booking (CUSTOMER_ID, SHOWING_ID) VALUES
(1, 1),
(2, 2);

INSERT INTO booking_seats (BOOKING_ID, SEAT_ID) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15); -- customer 1 took all the seats for showing 1 in booking 1 --


INSERT INTO booking_seats (BOOKING_ID, SEAT_ID) VALUES
(2, 1),
(2, 2),
(2, 3); -- customer 2 took only 3 seats for showing 2 in booking 2 --