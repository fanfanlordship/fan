--������ռ�
CREATE TABLESPACE Manage DATAFILE 'D:\CodesAndFiles\DataBase\Community.DBF' SIZE 1M AUTOEXTEND ON NEXT 1M MAXSIZE 1G;
--�����û�
CREATE USER FANFAN IDENTIFIED BY 123 DEFAULT TABLESPACE Community;
--����Ȩ��
GRANT DBA TO FANFAN;

--������
CREATE TABLESPACE SpringbootManage DATAFILE '/database/database/Manage.DBF' SIZE 1M AUTOEXTEND ON NEXT 1M MAXSIZE 5G;
CREATE TABLESPACE BILL DATAFILE '/database/database/Bill.DBF' SIZE 1M AUTOEXTEND ON NEXT 1M MAXSIZE 5G;
CREATE USER FANFAN IDENTIFIED BY 12345678900 DEFAULT TABLESPACE SpringbootManage;
GRANT DBA TO FANFAN;
/*================================================================����Ĳ�������======================================================================*/
--�����ܺ�̨������Ա
insert into SYS_USER (ID, USERNAME, NAME, PASSWORD, STATUS, TRUE_NAME, SEX, MOBILE, EMAIL, BIRTHDAY, IDNO, CREATE_BY, CREATE_TIME)
values (1, '111', '����ҷ�������', '698d51a19d8a121ce581499d7b701668', 1, 'fanfanlordship', 1, '12345678900', '123456789@qq.com', to_date('29-05-1996', 'dd-mm-yyyy'), '12345678912345678', 0, sysdate);
--�����ɫ
insert into SYS_ROLE (ID, NAME, STATUS, CREATE_BY, CREATE_TIME) values (1, '��������Ա', 1, 1, sysdate);
--��ɫ-������Ա������Ϣ
insert into SYS_USER_ROLE_LINK (FK_USER_ID, FK_ROLE_ID) values (1, 1);
--ϵͳ����
insert into SYS_SYSTEMPARAM (ID, CODE, NAME, VALUE) values (1, 'SYS_NAME', 'ϵͳ����', 'FANFAN�ĺ�̨');
insert into SYS_SYSTEMPARAM (ID, CODE, NAME, VALUE) values (2, 'DEFAULT_PASSWORD', 'Ĭ������', '888888');
--ϵͳȨ�޹���
--ϵͳ����ԱȨ��
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (1, 'ϵͳ����', 0, 1, null, 0, 'fa-caret-right', 'ϵͳ����', 1, 1, sysdate);
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (2, 'Ȩ�޹���', 1, 1, '/manage/sys/menu', 0, 'fa-caret-right', 'ϵͳ����-Ȩ�޹���', 1, 1, sysdate);
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (3, 'ϵͳ����', 1, 1, '/manage/sys/sysparam', 0, 'fa-caret-right', 'ϵͳ����-ϵͳ����', 2, 1, sysdate);
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (4, '��ɫ����', 1, 1, '/manage/sys/role', 0, 'fa-caret-right', 'ϵͳ����-��ɫ����', 3, 1, sysdate);
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (5, '�û�����', 1, 1, '/manage/user/user', 0, 'fa-caret-right', 'ϵͳ����-�û�����', 4, 1, sysdate);
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (6, '��־����', 1, 1, null, 0, 'fa-caret-right', 'ϵͳ����-��־����', 5, 1, sysdate);
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (7, '������־', 6, 1, '/manage/sys/log/operate', 0, 'fa-caret-right', 'ϵͳ����-��־����-������־', 1, 1, sysdate);
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (8, '�쳣��־', 6, 1, '/manage/sys/log/error', 0, 'fa-caret-right', 'ϵͳ����-��־����-�쳣��־', 2, 1, sysdate);
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (9, '��¼��־', 6, 1, '/manage/sys/log/login', 0, 'fa-caret-right', 'ϵͳ����-��־����-��¼��־', 3, 1, sysdate);
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (10, '��ťȨ��', 2, 1, '', 1, '', 'ϵͳ����-Ȩ�޹���-��ťȨ��', 1, 1, sysdate);
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (11, '����Ȩ��', 10, 1, 'system:menu:btn:addAuthority', 1, '', 'ϵͳ����-Ȩ�޹���-��ťȨ��-����Ȩ��', 1, 1, sysdate);
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (12, '�༭Ȩ��', 10, 1, 'system:menu:btn:editAuthority', 1, '', 'ϵͳ����-Ȩ�޹���-��ťȨ��-�༭�˵�', 2, 1, sysdate);
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (13, 'ϵͳ����', 0, 1, '', 0, 'fa-caret-right', 'ϵͳ����', 2, 1, sysdate);
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (14, 'swagger', 13, 1, '/swagger-ui.html', 0, 'fa-bug', 'ϵͳ����-swagger', 1, 1, sysdate);
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (15, '�����ʱ', 13, 1, '/test/request/duration', 0, 'fa-bug', 'ϵͳ����-�����ʱ', 2, 1, sysdate);
insert into SYS_PERMISSION (ID, NAME, SUP_ID, STATUS, PERMISSION, TYPE, ICON, MEMO, SORT, CREATE_BY, CREATE_TIME)
values (16, 'ϵͳ�ֵ�', 1, 1, '/manage/sys/dictionary', 0, 'fa-bug', 'ϵͳ����-ϵͳ�ֵ�', 6, 1, sysdate);

--Ȩ��-��ɫ����
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
/*==========================================================================================================================================================*/
