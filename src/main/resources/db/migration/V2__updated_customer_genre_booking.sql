alter table CUSTOMER
add unique (CODE);

alter table GENRE
add unique (NAME);

alter table MOVIE
add unique (TITLE);

alter table BOOKING
add foreign key (SEAT_ID)
references SEAT(ID);