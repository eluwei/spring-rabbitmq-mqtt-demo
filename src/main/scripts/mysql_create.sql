drop table if exists tmp_push_log;
CREATE TABLE tmp_push_log
(
   id integer not null AUTO_INCREMENT,
   topic varchar(500) NOT NULL,
   message varchar(1000) NOT NULL,
   create_date timestamp NOT NULL default current_timestamp,
   update_date timestamp NULL DEFAULT NULL,
   primary key (id)
);