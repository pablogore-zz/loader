create table access_logger(
    ID varchar(50) not null PRIMARY KEY,
    D DATE not null,
    IP varchar(50) not null,
    REQUEST varchar(50) not null,
    STATUS varchar(100) not null,
    USER_AGENT text not null
);