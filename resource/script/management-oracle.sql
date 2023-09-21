/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2021/3/9 22:08:40                            */
/*==============================================================*/


drop table SYS_ERROR_LOG cascade constraints;

drop table SYS_LOG_OPERATE cascade constraints;

drop table SYS_PERMISSION cascade constraints;

drop table SYS_ROLE cascade constraints;

drop table SYS_ROLE_PER_LINK cascade constraints;

drop table SYS_SYSTEMPARAM cascade constraints;

drop table SYS_USER cascade constraints;

drop table SYS_USER_ROLE_LINK cascade constraints;

drop table T_DICT_ROOT cascade constraints;

drop sequence SEQ_ERROR_LOG;

drop sequence SEQ_LOG_OPERATE;

drop sequence SEQ_PREMISSION;

drop sequence SEQ_ROLE;

drop sequence SEQ_USER;

drop sequence SEQ_DICT_ROOT;

create sequence SEQ_ERROR_LOG;

create sequence SEQ_LOG_OPERATE;

create sequence SEQ_PREMISSION;

create sequence SEQ_ROLE;

create sequence SEQ_USER;

create sequence SEQ_DICT_ROOT;

/*==============================================================*/
/* Table: SYS_ERROR_LOG                                         */
/*==============================================================*/
create table SYS_ERROR_LOG
(
   ID                   NUMBER(20)           not null,
   NAME                 VARCHAR2(200),
   ERROR_TIME           DATE,
   DETAIL               CLOB,
   constraint PK_SYS_ERROR_LOG primary key (ID)
);

comment on table SYS_ERROR_LOG is
'异常日志';

comment on column SYS_ERROR_LOG.ID is
'ID';

comment on column SYS_ERROR_LOG.NAME is
'异常名称';

comment on column SYS_ERROR_LOG.ERROR_TIME is
'异常发生时间';

comment on column SYS_ERROR_LOG.DETAIL is
'异常内容';

/*==============================================================*/
/* Table: SYS_LOG_OPERATE                                       */
/*==============================================================*/
create table SYS_LOG_OPERATE
(
   ID                   NUMBER(20)           not null,
   FK_USER_ID           NUMBER(20),
   LOG_TIME             number(20),
   IP                   VARCHAR2(30),
   URL                  VARCHAR2(100),
   NAME                 varchar2(50),
   CLASS_NAME           varchar2(300),
   METHOD_NAME          varchar2(100),
   DETAIL               CLOB,
   constraint PK_SYS_LOG_OPERATE primary key (ID)
);

comment on table SYS_LOG_OPERATE is
'操作日志';

comment on column SYS_LOG_OPERATE.ID is
'日志ID';

comment on column SYS_LOG_OPERATE.FK_USER_ID is
'操作人';

comment on column SYS_LOG_OPERATE.LOG_TIME is
'操作的时间';

comment on column SYS_LOG_OPERATE.IP is
'IP';

comment on column SYS_LOG_OPERATE.URL is
'请求的url';

comment on column SYS_LOG_OPERATE.NAME is
'日志名称';

comment on column SYS_LOG_OPERATE.CLASS_NAME is
'class路径';

comment on column SYS_LOG_OPERATE.METHOD_NAME is
'方法名';

comment on column SYS_LOG_OPERATE.DETAIL is
'日志的详细内容';

/*==============================================================*/
/* Table: SYS_PERMISSION                                        */
/*==============================================================*/
create table SYS_PERMISSION
(
   ID                   NUMBER(20)           not null,
   NAME                 VARCHAR2(30),
   SUP_ID               NUMBER(10),
   STATUS               NUMBER(5),
   PERMISSION           VARCHAR2(100),
   TYPE                 NUMBER(1),
   ICON                 VARCHAR2(50),
   MEMO                 VARCHAR2(100),
   SORT                 number(3),
   CREATE_BY            number(20),
   CREATE_TIME          DATE,
   constraint PK_T_PERMISSION primary key (ID)
);

comment on table SYS_PERMISSION is
'权限表';

comment on column SYS_PERMISSION.ID is
'权限ID';

comment on column SYS_PERMISSION.NAME is
'权限名';

comment on column SYS_PERMISSION.SUP_ID is
'这个权限是属于哪个权限下的，顶级权限则为0';

comment on column SYS_PERMISSION.STATUS is
'状态';

comment on column SYS_PERMISSION.PERMISSION is
'权限的URL';

comment on column SYS_PERMISSION.TYPE is
'权限类型，0 - 菜单权限，1 - 按钮权限';

comment on column SYS_PERMISSION.ICON is
'图标';

comment on column SYS_PERMISSION.MEMO is
'备注';

comment on column SYS_PERMISSION.SORT is
'顺序';

comment on column SYS_PERMISSION.CREATE_BY is
'创建人';

comment on column SYS_PERMISSION.CREATE_TIME is
'创建时间';

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
create table SYS_ROLE
(
   ID                   NUMBER(20)           not null,
   NAME                 VARCHAR2(30),
   STATUS               NUMBER(1),
   CREATE_BY            NUMBER(20),
   CREATE_TIME          DATE,
   constraint PK_T_ROLE primary key (ID)
);

comment on table SYS_ROLE is
'角色表';

comment on column SYS_ROLE.ID is
'角色ID';

comment on column SYS_ROLE.NAME is
'角色名';

comment on column SYS_ROLE.STATUS is
'角色状态，0-不可用 1-可用';

comment on column SYS_ROLE.CREATE_BY is
'创建人';

comment on column SYS_ROLE.CREATE_TIME is
'创建时间';

/*==============================================================*/
/* Table: SYS_ROLE_PER_LINK                                     */
/*==============================================================*/
create table SYS_ROLE_PER_LINK
(
   FK_ROLE_ID           NUMBER(20),
   FK_PER_ID            NUMBER(20)
);

comment on table SYS_ROLE_PER_LINK is
'角色-权限关联表';

comment on column SYS_ROLE_PER_LINK.FK_ROLE_ID is
'角色ID';

comment on column SYS_ROLE_PER_LINK.FK_PER_ID is
'权限ID';

/*==============================================================*/
/* Table: SYS_SYSTEMPARAM                                       */
/*==============================================================*/
create table SYS_SYSTEMPARAM
(
   ID                   NUMBER(20)           not null,
   CODE                 VARCHAR2(50)         not null,
   NAME                 VARCHAR2(50),
   VALUE                clob,
   constraint PK_SYS_SYSTEMPARAM primary key (ID)
);

comment on table SYS_SYSTEMPARAM is
'系统参数表';

comment on column SYS_SYSTEMPARAM.ID is
'参数ID';

comment on column SYS_SYSTEMPARAM.CODE is
'参数码';

comment on column SYS_SYSTEMPARAM.NAME is
'参数名';

comment on column SYS_SYSTEMPARAM.VALUE is
'参数值';

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
create table SYS_USER
(
   ID                   NUMBER(20)           not null,
   USERNAME             VARCHAR2(30),
   NAME                 VARCHAR2(30),
   PASSWORD             VARCHAR2(100),
   STATUS               NUMBER(2),
   TRUE_NAME            VARCHAR2(30),
   SEX                  NUMBER(2),
   MOBILE               VARCHAR2(15),
   EMAIL                VARCHAR2(50),
   BIRTHDAY             DATE,
   IDNO                 VARCHAR2(20),
   CREATE_BY            NUMBER(20),
   CREATE_TIME          DATE,
   constraint PK_T_USER primary key (ID)
);

comment on table SYS_USER is
'用户表';

comment on column SYS_USER.ID is
'用户ID：每个用户的唯一ID号';

comment on column SYS_USER.USERNAME is
'账号：用户登录用的账号';

comment on column SYS_USER.NAME is
'用户名';

comment on column SYS_USER.PASSWORD is
'密码';

comment on column SYS_USER.STATUS is
'用户的状态，0-不可用，1-可用，2-禁用';

comment on column SYS_USER.TRUE_NAME is
'姓名';

comment on column SYS_USER.SEX is
'性别，0-女，1-男';

comment on column SYS_USER.MOBILE is
'电话';

comment on column SYS_USER.EMAIL is
'邮箱';

comment on column SYS_USER.BIRTHDAY is
'出生日期';

comment on column SYS_USER.IDNO is
'身份证';

comment on column SYS_USER.CREATE_BY is
'创建人';

comment on column SYS_USER.CREATE_TIME is
'创建时间';

/*==============================================================*/
/* Table: SYS_USER_ROLE_LINK                                    */
/*==============================================================*/
create table SYS_USER_ROLE_LINK
(
   FK_USER_ID           NUMBER(20),
   FK_ROLE_ID           NUMBER(20)
);

comment on table SYS_USER_ROLE_LINK is
'用户-角色关联表';

comment on column SYS_USER_ROLE_LINK.FK_USER_ID is
'用户ID';

comment on column SYS_USER_ROLE_LINK.FK_ROLE_ID is
'角色ID';

/*==============================================================*/
/* Table: T_DICT_ROOT                                    */
/*==============================================================*/
create table T_DICT_ROOT
(
   ID           		NUMBER(20)				not null,
   NAME           		VARCHAR2(50),
   CODE           		VARCHAR2(150),
   STATUS           	NUMBER(1),
   MEMO           		CLOB,
   CREATE_BY           	NUMBER(20),
   CREATE_TIME          DATE,
   constraint PK_T_DICT_ROOT primary key (ID)
);

comment on table T_DICT_ROOT is
'字典表';

comment on column T_DICT_ROOT.ID is
'字典ID';

comment on column T_DICT_ROOT.NAME is
'字典名称';

comment on column T_DICT_ROOT.CODE is
'字典码';

comment on column T_DICT_ROOT.STATUS is
'字典的状态，0-不可用，1-可用';

comment on column T_DICT_ROOT.MEMO is
'备注';

comment on column T_DICT_ROOT.CREATE_BY is
'创建人';

comment on column T_DICT_ROOT.CREATE_TIME is
'创建时间';