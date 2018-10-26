create table Logger(
    ID varchar(50) not null PRIMARY KEY,
    Date DATE not null,
    IP varchar(50) not null,
    REQUEST varchar(50) not null,
    STATUS varchar(100) not null,
    USER_AGENT VARCHAR not null
);