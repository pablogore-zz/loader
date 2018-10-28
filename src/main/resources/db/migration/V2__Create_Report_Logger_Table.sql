create table report_logger(
    OP_DATE TIMESTAMP not null,
    IP varchar(50) not null,
    REQUEST varchar(50) not null,
    STATUS varchar(100) not null,
    USER_AGENT text not null,
    TOTAL integer not null
);