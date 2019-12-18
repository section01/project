create table user_mst (id varchar(20), name varchar(20));
create table auth_mst (id varchar(20), account varchar(20), password varchar(20));
insert into user_mst (id , name) values ('00001', '00001');
insert into auth_mst (id , account ,password) values ('00001', '00001', '00001');

create table period_tbl (id varchar(20), period varchar(20));
create table detail_tbl (id varchar(20), period varchar(20), date varchar(20), week varchar(20), type varchar(20), open_time date, close_time date, break_time date, total_time date, remark varchar(100));

