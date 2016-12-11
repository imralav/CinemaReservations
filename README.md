# CinemaReservations
Project for Warsaw Military Academy of Technology about using voice recognition capabilities of Evolution Voxeo portal to create cinema tickets reservation system.

# Development tips
Before populating database using `classpath:db/populate.sql` script, either log in to mysql using `mysql --default-character-set=utf8` command or setup character set using .cnf file:
```
[mysql]
default-character-set=utf8
```