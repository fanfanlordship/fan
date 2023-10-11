# 测试用户
truncate table FAN_USER;
insert into FAN_USER (ID, USERNAME, NICK_NAME, PASSWORD, STATUS, CREATE_BY, CREATE_TIME)
values ('B862D159CC874FD797BAFF5216F75A4F', '111', '请叫我凡凡大人', '$2a$10$76qEn35vYjf2oKsAlNwTeeULyTQI1ETgDvmtNW7C8hqxBkocPxbnG', 1, 'B862D159CC874FD797BAFF5216F75A4F', now());

truncate table FAN_USER_PASSWORD_HISTORY;
insert into FAN_USER_PASSWORD_HISTORY (ID, USER_ID, ENCODER_TYPE, PASSWORD, CREATE_TIME)
values (1, 'B862D159CC874FD797BAFF5216F75A4F', 0, '$2a$10$76qEn35vYjf2oKsAlNwTeeULyTQI1ETgDvmtNW7C8hqxBkocPxbnG', now());

# 插入角色
truncate table FAN_ROLE;
insert into FAN_ROLE (ID, NAME, STATUS, CREATE_BY, CREATE_TIME) values ('DD1194F6B5274375A983E7C6F9D1DAD8', '超级管理员', 1, 1, now());

# 角色-测试人员关联信息
truncate table FAN_LINK_USER_ROLE;
insert into FAN_LINK_USER_ROLE (FK_USER_ID, FK_ROLE_ID) values ('B862D159CC874FD797BAFF5216F75A4F', 'DD1194F6B5274375A983E7C6F9D1DAD8');

# 系统参数
truncate table FAN_SYSTEM_CONFIG;
insert into FAN_SYSTEM_CONFIG (ID, CODE, NAME, VALUE) values (1, 'SYS_NAME', '系统名称', 'FANFAN的后台');
insert into FAN_SYSTEM_CONFIG (ID, CODE, NAME, VALUE) values (2, 'DEFAULT_PASSWORD', '默认密码', '888888');
insert into FAN_SYSTEM_CONFIG (ID, CODE, NAME, VALUE) values (3, 'CAPTCHA_SIZE', '验证码长度', '4');

#系统权限管理
#系统管理员权限
truncate table FAN_SYSTEM_AUTH;
insert into FAN_SYSTEM_AUTH (ID, NAME, PARENT_ID, STATUS, ROUTER, TYPE, ICON, SORT) values (1, '系统管理', 0, 1, null, 0, 'fa-caret-right', 1);
insert into FAN_SYSTEM_AUTH (ID, NAME, PARENT_ID, STATUS, ROUTER, TYPE, ICON, SORT) values (2, '权限管理', 1, 1, '/manage/sys/permission', 0, 'fa-caret-right', 1);
insert into FAN_SYSTEM_AUTH (ID, NAME, PARENT_ID, STATUS, ROUTER, TYPE, ICON, SORT) values (3, '角色管理', 1, 1, '/manage/sys/role', 0, 'fa-caret-right', 2);
insert into FAN_SYSTEM_AUTH (ID, NAME, PARENT_ID, STATUS, ROUTER, TYPE, ICON, SORT) values (4, '用户管理', 1, 1, '/manage/sys/user', 0, 'fa-caret-right', 3);

#权限-角色关联
truncate table FAN_LINK_ROLE_AUTH;
insert into FAN_LINK_ROLE_AUTH (FK_ROLE_ID, FK_AUTH_ID) values ('DD1194F6B5274375A983E7C6F9D1DAD8', 1);
insert into FAN_LINK_ROLE_AUTH (FK_ROLE_ID, FK_AUTH_ID) values ('DD1194F6B5274375A983E7C6F9D1DAD8', 2);
insert into FAN_LINK_ROLE_AUTH (FK_ROLE_ID, FK_AUTH_ID) values ('DD1194F6B5274375A983E7C6F9D1DAD8', 3);
insert into FAN_LINK_ROLE_AUTH (FK_ROLE_ID, FK_AUTH_ID) values ('DD1194F6B5274375A983E7C6F9D1DAD8', 4);
