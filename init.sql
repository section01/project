drop table user_mst;
drop table auth_mst;
drop table period_tbl;
drop table detail_tbl;

create table user_mst (id varchar(20), name varchar(20));
create table auth_mst (id varchar(20), account varchar(20), roles varchar(1), password varchar(20));
create table period_tbl (id varchar(20), period varchar(20));
create table detail_tbl (id varchar(20), period varchar(20), date varchar(20), week varchar(20), type varchar(20), open_time varchar(10), close_time varchar(10), break_time varchar(10), total_time varchar(10), remark varchar(100));

insert into user_mst (id, name) values ('00001', '00001');
insert into auth_mst (id, account, roles, password) values ('00001', '00001', '1', '00001');
insert into user_mst (id, name) values ('00002', '00002');
insert into auth_mst (id, account, roles, password) values ('00002', '00002', '2', '00002');