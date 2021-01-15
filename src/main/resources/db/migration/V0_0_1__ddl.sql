select 1;

-- ユーザテーブル
create table if not exists user_mst(
    id varchar (20),
    name varchar (20)
);

-- 認証テーブル
create table if not exists auth_mst(
    id varchar (20)
    , account varchar (20)
    , roles varchar (10)
    , password varchar (20)
);

-- 勤怠期間テーブル
create table if not exists period_tbl(
    id varchar (20)
    , period varchar (20)
    , status varchar (1)
);

-- 勤怠詳細テーブル
create table if not exists detail_tbl(
    id varchar (20)
    , period varchar (20)
    , date varchar (20)
    , week varchar (20)
    , type varchar (20)
    , open_time varchar (10)
    , close_time varchar (10)
    , break_time varchar (10)
    , total_time varchar (10)
    , remark varchar (100)
);

comment on table  user_mst   is 'ユーザテーブル';
comment on table  auth_mst   is '認証テーブル';
comment on table  period_tbl is '勤怠期間テーブル';
comment on table  detail_tbl is '勤怠詳細テーブル';

-- ユーザテーブル
comment on column user_mst.id   is 'ユーザID';
comment on column user_mst.name is 'ユーザ氏名';

-- 認証テーブル
comment on column auth_mst.id       is 'ユーザID';
comment on column auth_mst.account  is 'ログインアカウント';
comment on column auth_mst.roles    is '権限';
comment on column auth_mst.password is 'パスワード';

-- 勤怠期間テーブル
comment on column period_tbl.id     is 'ユーザID';
comment on column period_tbl.period is '勤怠年月日';
comment on column period_tbl.status is '勤怠ステータス';

-- 勤怠詳細テーブル
comment on column detail_tbl.id          is 'ユーザID';
comment on column detail_tbl.period      is '勤怠年月日';
comment on column detail_tbl.date       is '勤怠日';
comment on column detail_tbl.week        is '勤怠曜日';
comment on column detail_tbl.type       is '勤怠種別';
comment on column detail_tbl.open_time   is '始業時間';
comment on column detail_tbl.close_time  is '終業時間';
comment on column detail_tbl.break_time  is '休憩時間';
comment on column detail_tbl.total_time  is '勤務時間';
comment on column detail_tbl.remark      is '備考';
