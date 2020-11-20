drop table user_mst;
drop table auth_mst;
drop table period_tbl;
drop table detail_tbl;

create table user_mst (id varchar(20), name varchar(20));
create table auth_mst (id varchar(20), account varchar(20), roles varchar(10), password varchar(20));
create table period_tbl (id varchar(20), period varchar(20), status varchar(1));
create table detail_tbl (id varchar(20), period varchar(20), date varchar(20), week varchar(20), type varchar(20), open_time varchar(10), close_time varchar(10), break_time varchar(10), total_time varchar(10), remark varchar(100));
