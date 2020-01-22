drop table user_mst;
create table user_mst (id varchar(20), name varchar(20));

drop table auth_mst
create table auth_mst (id varchar(20), account varchar(20), password varchar(20));

insert into user_mst (id , name) values ('00001', '00001');
insert into auth_mst (id , account ,password) values ('00001', '00001', '00001');

drop table period_tbl;
create table period_tbl (id varchar(20), period varchar(20));

drop table detail_tbl;
create table detail_tbl (id varchar(20), period varchar(20), date varchar(20), week varchar(20), type varchar(20), open_time varchar(10), close_time varchar(10), break_time varchar(10), total_time varchar(10), remark varchar(100));
