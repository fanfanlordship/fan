#插入总后台测试人员
insert into SYS_USER (ID, USERNAME, NAME, PASSWORD, STATUS, TRUE_NAME, SEX, MOBILE, EMAIL, BIRTHDAY, IDNO, CREATE_BY, CREATE_TIME)
values (1, '111', '请叫我凡凡大人', '698d51a19d8a121ce581499d7b701668', 1, 'fanfanlordship', 1, '12345678900', '123456789@qq.com', STR_TO_DATE('29/05/1996', '%d/%m/%Y'), '12345678912345678', 0, now());
#插入角色
insert into SYS_ROLE (ID, NAME, STATUS, CREATE_BY, CREATE_TIME) values (1, '超级管理员', 1, 1, now());
#角色-测试人员关联信息
insert into SYS_USER_ROLE_LINK (FK_USER_ID, FK_ROLE_ID) values (1, 1);
#系统参数
insert into SYS_SYSTEMPARAM (ID, CODE, NAME, VALUE) values (1, 'SYS_NAME', '系统名称', 'FANFAN的后台');
insert into SYS_SYSTEMPARAM (ID, CODE, NAME, VALUE) values (2, 'DEFAULT_PASSWORD', '默认密码', '888888');
#系统权限管理
#系统管理员权限
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (1, '系统管理', 0, 1, null, 0, 'fa-caret-right', '系统管理', 1, 1, now());
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (2, '权限管理', 1, 1, '/manage/sys/permission', 0, 'fa-caret-right', '系统管理-权限管理', 1, 1, now());
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (3, '系统参数', 1, 1, '/manage/sys/sysparam', 0, 'fa-caret-right', '系统管理-系统参数', 2, 1, now());
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (4, '角色管理', 1, 1, '/manage/sys/role', 0, 'fa-caret-right', '系统管理-角色管理', 3, 1, now());
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (5, '用户管理', 1, 1, '/manage/sys/user', 0, 'fa-caret-right', '系统管理-用户管理', 4, 1, now());
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (6, '日志管理', 1, 1, null, 0, 'fa-caret-right', '系统管理-日志管理', 5, 1, now());
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (7, '操作日志', 6, 1, '/manage/sys/log/operate', 0, 'fa-caret-right', '系统管理-日志管理-操作日志', 1, 1, now());
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (8, '异常日志', 6, 1, '/manage/sys/log/error', 0, 'fa-caret-right', '系统管理-日志管理-异常日志', 2, 1, now());
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (9, '登录日志', 6, 1, '/manage/sys/log/login', 0, 'fa-caret-right', '系统管理-日志管理-登录日志', 3, 1, now());
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (10, '按钮权限', 2, 1, '', 1, '', '系统管理-权限管理-按钮权限', 1, 1, now());
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (11, '新增权限', 10, 1, 'system:permission:btn:addAuthority', 1, '', '系统管理-权限管理-按钮权限-新增权限', 1, 1, now());
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (12, '编辑权限', 10, 1, 'system:permission:btn:editAuthority', 1, '', '系统管理-权限管理-按钮权限-编辑菜单', 2, 1, now());
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (13, '系统调试', 0, 1, '', 0, 'fa-caret-right', '系统调试', 2, 1, now());
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (14, 'swagger', 13, 1, '/swagger-ui/index.html', 0, 'fa-bug', '系统调试-swagger', 1, 1, now());
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (15, '请求耗时', 13, 1, '/test/request/duration', 0, 'fa-bug', '系统调试-请求耗时', 2, 1, now());
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (16, '系统字典', 1, 1, '/manage/sys/dictionary', 0, 'fa-bug', '系统管理-系统字典', 6, 1, now());
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (17, '系统监控', 13, 1, '/test/monitor/server', 0, 'fa-caret-right', '系统调试-系统监控', 3, 1, now());

#权限-角色关联
insert into SYS_ROLE_PER_LINK (FK_ROLE_ID, FK_PER_ID) values (1, 1);
insert into SYS_ROLE_PER_LINK (FK_ROLE_ID, FK_PER_ID) values (1, 2);
insert into SYS_ROLE_PER_LINK (FK_ROLE_ID, FK_PER_ID) values (1, 3);
insert into SYS_ROLE_PER_LINK (FK_ROLE_ID, FK_PER_ID) values (1, 4);
insert into SYS_ROLE_PER_LINK (FK_ROLE_ID, FK_PER_ID) values (1, 5);
insert into SYS_ROLE_PER_LINK (FK_ROLE_ID, FK_PER_ID) values (1, 6);
insert into SYS_ROLE_PER_LINK (FK_ROLE_ID, FK_PER_ID) values (1, 7);
insert into SYS_ROLE_PER_LINK (FK_ROLE_ID, FK_PER_ID) values (1, 8);
insert into SYS_ROLE_PER_LINK (FK_ROLE_ID, FK_PER_ID) values (1, 9);
insert into SYS_ROLE_PER_LINK (FK_ROLE_ID, FK_PER_ID) values (1, 10);
insert into SYS_ROLE_PER_LINK (FK_ROLE_ID, FK_PER_ID) values (1, 11);
insert into SYS_ROLE_PER_LINK (FK_ROLE_ID, FK_PER_ID) values (1, 12);
insert into SYS_ROLE_PER_LINK (FK_ROLE_ID, FK_PER_ID) values (1, 13);
insert into SYS_ROLE_PER_LINK (FK_ROLE_ID, FK_PER_ID) values (1, 14);
insert into SYS_ROLE_PER_LINK (FK_ROLE_ID, FK_PER_ID) values (1, 15);
insert into SYS_ROLE_PER_LINK (FK_ROLE_ID, FK_PER_ID) values (1, 16);
insert into SYS_ROLE_PER_LINK (FK_ROLE_ID, FK_PER_ID) values (1, 17);