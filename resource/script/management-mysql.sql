/*==============================================================*/
/* Table: FAN_SYSTEM_AUTH                                       */
/*==============================================================*/
drop table if exists FAN_SYSTEM_AUTH;

create table FAN_SYSTEM_AUTH
(
    ID        int not null comment '主键',
    NAME      VARCHAR(30) comment '权限名',
    PARENT_ID int comment '这个权限是属于哪个权限下的，顶级权限则为0',
    STATUS    TINYINT(1) comment '状态',
    ROUTER    VARCHAR(100) comment '权限的URL',
    TYPE      TINYINT(1) comment '权限类型，0 - 菜单权限，1 - 按钮权限',
    ICON      VARCHAR(50) comment '图标',
    SORT      SMALLINT(3) comment '顺序',
    primary key (ID)
) auto_increment = 1 comment '权限表';

/*==============================================================*/
/* Table: FAN_ROLE                                              */
/*==============================================================*/
drop table if exists FAN_ROLE;

create table FAN_ROLE
(
    ID          VARCHAR(32) not null comment '角色ID',
    NAME        VARCHAR(30) comment '角色名',
    STATUS      TINYINT(1) comment '角色状态，0-不可用 1-可用',
    CREATE_BY   VARCHAR(32) comment '创建人',
    CREATE_TIME DATETIME comment '创建时间',
    primary key (ID)
) comment '角色表';

/*==============================================================*/
/* Table: FAN_LINK_ROLE_AUTH                                    */
/*==============================================================*/
drop table if exists FAN_LINK_ROLE_AUTH;

create table FAN_LINK_ROLE_AUTH
(
    FK_ROLE_ID VARCHAR(32) comment '角色ID',
    FK_AUTH_ID int comment '权限ID',
    primary key (FK_ROLE_ID, FK_AUTH_ID)
) comment '角色权限关联表';

/*==============================================================*/
/* Table: FAN_SYSTEM_CONFIG                                     */
/*==============================================================*/
drop table if exists FAN_SYSTEM_CONFIG;

create table FAN_SYSTEM_CONFIG
(
    ID    INT         not null comment '配置主键',
    CODE  VARCHAR(50) not null comment '配置编码',
    NAME  VARCHAR(50) comment '配置名称',
    VALUE VARCHAR(300) comment '配置值',
    PRIMARY KEY (ID)
) comment '系统配置表';

/*==============================================================*/
/* Table: FAN_USER                                              */
/*==============================================================*/
drop table if exists FAN_USER;

create table FAN_USER
(
    ID          VARCHAR(32) not null comment '主键',
    USERNAME    VARCHAR(30) comment '账户（登录名）',
    NICK_NAME   VARCHAR(50) comment '昵称',
    PASSWORD    VARCHAR(100) comment '密码',
    STATUS      TINYINT(1) comment '状态',
    CREATE_BY   VARCHAR(32) comment '创建人',
    CREATE_TIME DATETIME comment '创建时间',
    primary key (ID)
) comment '用户表';

/*==============================================================*/
/* Table: FAN_USER_PASSWORD_HISTORY                             */
/*==============================================================*/
drop table if exists FAN_USER_PASSWORD_HISTORY;

create table FAN_USER_PASSWORD_HISTORY
(
    ID           int not null comment '主键',
    USER_ID      VARCHAR(32) comment '用户ID',
    ENCODER_TYPE TINYINT comment '加密方式',
    PASSWORD     VARCHAR(100) comment '密码',
    CREATE_TIME  DATETIME comment '创建时间',
    primary key (ID)
) comment '用户密码历史表';

/*==============================================================*/
/* Table: FAN_LINK_USER_ROLE                                    */
/*==============================================================*/
drop table if exists FAN_LINK_USER_ROLE;

create table FAN_LINK_USER_ROLE
(
    FK_USER_ID VARCHAR(32) comment '用户ID',
    FK_ROLE_ID VARCHAR(32) comment '角色ID',
    primary key (FK_USER_ID, FK_ROLE_ID)
) comment '用户角色关联表';

