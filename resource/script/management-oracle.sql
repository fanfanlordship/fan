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
'�쳣��־';

comment on column SYS_ERROR_LOG.ID is
'ID';

comment on column SYS_ERROR_LOG.NAME is
'�쳣����';

comment on column SYS_ERROR_LOG.ERROR_TIME is
'�쳣����ʱ��';

comment on column SYS_ERROR_LOG.DETAIL is
'�쳣����';

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
'������־';

comment on column SYS_LOG_OPERATE.ID is
'��־ID';

comment on column SYS_LOG_OPERATE.FK_USER_ID is
'������';

comment on column SYS_LOG_OPERATE.LOG_TIME is
'������ʱ��';

comment on column SYS_LOG_OPERATE.IP is
'IP';

comment on column SYS_LOG_OPERATE.URL is
'�����url';

comment on column SYS_LOG_OPERATE.NAME is
'��־����';

comment on column SYS_LOG_OPERATE.CLASS_NAME is
'class·��';

comment on column SYS_LOG_OPERATE.METHOD_NAME is
'������';

comment on column SYS_LOG_OPERATE.DETAIL is
'��־����ϸ����';

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
'Ȩ�ޱ�';

comment on column SYS_PERMISSION.ID is
'Ȩ��ID';

comment on column SYS_PERMISSION.NAME is
'Ȩ����';

comment on column SYS_PERMISSION.SUP_ID is
'���Ȩ���������ĸ�Ȩ���µģ�����Ȩ����Ϊ0';

comment on column SYS_PERMISSION.STATUS is
'״̬';

comment on column SYS_PERMISSION.PERMISSION is
'Ȩ�޵�URL';

comment on column SYS_PERMISSION.TYPE is
'Ȩ�����ͣ�0 - �˵�Ȩ�ޣ�1 - ��ťȨ��';

comment on column SYS_PERMISSION.ICON is
'ͼ��';

comment on column SYS_PERMISSION.MEMO is
'��ע';

comment on column SYS_PERMISSION.SORT is
'˳��';

comment on column SYS_PERMISSION.CREATE_BY is
'������';

comment on column SYS_PERMISSION.CREATE_TIME is
'����ʱ��';

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
'��ɫ��';

comment on column SYS_ROLE.ID is
'��ɫID';

comment on column SYS_ROLE.NAME is
'��ɫ��';

comment on column SYS_ROLE.STATUS is
'��ɫ״̬��0-������ 1-����';

comment on column SYS_ROLE.CREATE_BY is
'������';

comment on column SYS_ROLE.CREATE_TIME is
'����ʱ��';

/*==============================================================*/
/* Table: SYS_ROLE_PER_LINK                                     */
/*==============================================================*/
create table SYS_ROLE_PER_LINK
(
   FK_ROLE_ID           NUMBER(20),
   FK_PER_ID            NUMBER(20)
);

comment on table SYS_ROLE_PER_LINK is
'��ɫ-Ȩ�޹�����';

comment on column SYS_ROLE_PER_LINK.FK_ROLE_ID is
'��ɫID';

comment on column SYS_ROLE_PER_LINK.FK_PER_ID is
'Ȩ��ID';

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
'ϵͳ������';

comment on column SYS_SYSTEMPARAM.ID is
'����ID';

comment on column SYS_SYSTEMPARAM.CODE is
'������';

comment on column SYS_SYSTEMPARAM.NAME is
'������';

comment on column SYS_SYSTEMPARAM.VALUE is
'����ֵ';

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
'�û���';

comment on column SYS_USER.ID is
'�û�ID��ÿ���û���ΨһID��';

comment on column SYS_USER.USERNAME is
'�˺ţ��û���¼�õ��˺�';

comment on column SYS_USER.NAME is
'�û���';

comment on column SYS_USER.PASSWORD is
'����';

comment on column SYS_USER.STATUS is
'�û���״̬��0-�����ã�1-���ã�2-����';

comment on column SYS_USER.TRUE_NAME is
'����';

comment on column SYS_USER.SEX is
'�Ա�0-Ů��1-��';

comment on column SYS_USER.MOBILE is
'�绰';

comment on column SYS_USER.EMAIL is
'����';

comment on column SYS_USER.BIRTHDAY is
'��������';

comment on column SYS_USER.IDNO is
'���֤';

comment on column SYS_USER.CREATE_BY is
'������';

comment on column SYS_USER.CREATE_TIME is
'����ʱ��';

/*==============================================================*/
/* Table: SYS_USER_ROLE_LINK                                    */
/*==============================================================*/
create table SYS_USER_ROLE_LINK
(
   FK_USER_ID           NUMBER(20),
   FK_ROLE_ID           NUMBER(20)
);

comment on table SYS_USER_ROLE_LINK is
'�û�-��ɫ������';

comment on column SYS_USER_ROLE_LINK.FK_USER_ID is
'�û�ID';

comment on column SYS_USER_ROLE_LINK.FK_ROLE_ID is
'��ɫID';

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
'�ֵ��';

comment on column T_DICT_ROOT.ID is
'�ֵ�ID';

comment on column T_DICT_ROOT.NAME is
'�ֵ�����';

comment on column T_DICT_ROOT.CODE is
'�ֵ���';

comment on column T_DICT_ROOT.STATUS is
'�ֵ��״̬��0-�����ã�1-����';

comment on column T_DICT_ROOT.MEMO is
'��ע';

comment on column T_DICT_ROOT.CREATE_BY is
'������';

comment on column T_DICT_ROOT.CREATE_TIME is
'����ʱ��';