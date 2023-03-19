drop table if exists payment;
create table payment
(
    id     bigint(20) not null auto_increment comment 'ID',
    serial varchar(200) DEFAULT '',
    primary key (id)
) auto_increment = 1;