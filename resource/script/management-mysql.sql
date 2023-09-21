/*==============================================================*/
/* Table: SYS_ERROR_LOG                                         */
/*==============================================================*/
drop table if exists SYS_ERROR_LOG;

create table SYS_ERROR_LOG
(
    ID         BIGINT(20) not null comment 'ID',
    NAME       VARCHAR(200) comment '异常名称',
    ERROR_TIME DATETIME comment '异常发生时间',
    DETAIL     MEDIUMTEXT comment '异常内容',
    primary key (ID)
) auto_increment = 1 comment '异常日志表';

/*==============================================================*/
/* Table: SYS_LOG_OPERATE                                       */
/*==============================================================*/
drop table if exists SYS_LOG_OPERATE;

create table SYS_LOG_OPERATE
(
    ID          BIGINT(20) not null comment '日志ID',
    FK_USER_ID  BIGINT(20) comment '操作人',
    LOG_TIME    BIGINT(20) comment '操作时间',
    IP          VARCHAR(30) comment '操作IP',
    URL         VARCHAR(100) comment '请求的url',
    NAME        VARCHAR(50) comment '日志名称',
    CLASS_NAME  VARCHAR(300) comment 'class路径',
    METHOD_NAME VARCHAR(100) comment '方法名',
    DETAIL      MEDIUMTEXT comment '日志的详细内容',
    primary key (ID)
) auto_increment = 1 comment '操作日志表';

/*==============================================================*/
/* Table: SYS_PERMISSION                                        */
/*==============================================================*/
drop table if exists SYS_PERMISSION;

create table SYS_PERMISSION
(
    ID          BIGINT(20) not null comment '权限ID',
    NAME        VARCHAR(30) comment '权限名',
    SUP_ID      BIGINT(10) comment '这个权限是属于哪个权限下的，顶级权限则为0',
    STATUS      TINYINT(1) comment '状态',
    PERMISSION  VARCHAR(100) comment '权限的URL',
    TYPE        TINYINT(1) comment '权限类型，0 - 菜单权限，1 - 按钮权限',
    ICON        VARCHAR(50) comment '图标',
    MEMO        VARCHAR(100) comment '备注',
    SORT        SMALLINT(3) comment '顺序',
    CREATE_BY   BIGINT(20) comment '创建人',
    CREATE_TIME DATETIME comment '创建时间',
    primary key (ID)
) auto_increment = 1 comment '权限表';

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
drop table if exists SYS_ROLE;

create table SYS_ROLE
(
    ID          BIGINT(20) not null comment '角色ID',
    NAME        VARCHAR(30) comment '角色名',
    STATUS      TINYINT(1) comment '角色状态，0-不可用 1-可用',
    CREATE_BY   BIGINT(20) comment '创建人',
    CREATE_TIME DATETIME comment '创建时间',
    primary key (ID)
) auto_increment = 1 comment '角色表';

/*==============================================================*/
/* Table: SYS_ROLE_PER_LINK                                     */
/*==============================================================*/
create table SYS_ROLE_PER_LINK
(
    FK_ROLE_ID BIGINT(20),
    FK_PER_ID  BIGINT(20)
);

/*==============================================================*/
/* Table: SYS_SYSTEMPARAM                                       */
/*==============================================================*/
create table SYS_SYSTEMPARAM
(
    ID    BIGINT(20)  not null,
    CODE  VARCHAR(50) not null,
    NAME  VARCHAR(50),
    VALUE MEDIUMTEXT,
    constraint PK_SYS_SYSTEMPARAM primary key (ID)
);

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
create table SYS_USER
(
    ID          BIGINT(20) not null,
    USERNAME    VARCHAR(30),
    NAME        VARCHAR(30),
    PASSWORD    VARCHAR(100),
    STATUS      TINYINT(2),
    TRUE_NAME   VARCHAR(30),
    SEX         TINYINT(2),
    MOBILE      VARCHAR(15),
    EMAIL       VARCHAR(50),
    BIRTHDAY    DATE,
    IDNO        VARCHAR(20),
    CREATE_BY   BIGINT(20),
    CREATE_TIME DATETIME,
    constraint PK_T_USER primary key (ID)
);

/*==============================================================*/
/* Table: SYS_USER_ROLE_LINK                                    */
/*==============================================================*/
create table SYS_USER_ROLE_LINK
(
    FK_USER_ID BIGINT(20),
    FK_ROLE_ID BIGINT(20)
);

/*==============================================================*/
/* Table: T_DICT_ROOT                                    */
/*==============================================================*/
create table T_DICT_ROOT
(
    ID          BIGINT(20),
    NAME        VARCHAR(50),
    CODE        VARCHAR(150),
    STATUS      TINYINT(1),
    MEMO        TEXT,
    CREATE_BY   BIGINT(20),
    CREATE_TIME DATETIME,
    constraint PK_T_DICT_ROOT primary key (ID)
);