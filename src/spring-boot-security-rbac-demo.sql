/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/1/7 21:26:42                            */
/*==============================================================*/



drop table if exists authority;

drop table if exists hierarchy;

drop table if exists role;

drop table if exists user;



/*==============================================================*/
/* Table: authority                                             */
/*==============================================================*/
create table authority
(
   id                   varchar(32) not null comment '主键，使用UUID',
   rol_id               varchar(32) comment '主键，使用UUID',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   authority_name       varchar(100) comment '权限名称',
   authority_description text comment '权限描述',
   primary key (id)
);

alter table authority comment '权限表';

/*==============================================================*/
/* Table: hierarchy                                             */
/*==============================================================*/
create table hierarchy
(
   id                   varchar(32) not null comment '主键，使用UUID',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   hierarchy_content    text not null comment '权限表达式内容',
   primary key (id)
);

alter table hierarchy comment '权限继承';

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   id                   varchar(32) not null comment '主键，使用UUID',
   use_id               varchar(32) comment '主键，使用UUID',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   role_name            varchar(100) comment '角色名称',
   role_description     text,
   primary key (id)
);

alter table role comment '角色表';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   varchar(32) not null comment '主键，使用UUID',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   username             varchar(100) comment '用户名',
   password             varchar(100) comment '密码',
   primary key (id)
);

alter table user comment '用户表';





