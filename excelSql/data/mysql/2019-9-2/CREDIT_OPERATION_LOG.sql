/*
MySql
Source Schema         : XFJRCB
Target Server Type    : MySql
Date: Mon Sep 02 16:21:32 CST 2019
*/
-- ----------------------------
-- CREDIT_OPERATION_LOG
--���������˹�������¼��
-- ----------------------------
CREATE TABLE XFJRCB.CREDIT_OPERATION_LOG(
    ID BITGINT (20) NOT NULL COMMENT '����',
    OPERA_TP VARCHAR (5) NOT NULL COMMENT '�������ͣ�������Ϣ�޸�/��������/ʧ����������/���ű��ͨ��/���žܾ�',
    CHANGE_BEF VARCHAR (1000) COMMENT '�޸�ǰ����',
    CHANGE_AFT VARCHAR (1000) COMMENT '�޸ĺ�����',
    FIELD_NA VARCHAR (100) COMMENT '�޸��ֶ���',
    APPLY_DATA VARCHAR (10000) COMMENT '���뱨������',
    OP_TM TIMESTAMP NOT NULL COMMENT '����ʱ��',
    OP_USER VARCHAR (10) NOT NULL COMMENT '������',
    PRIMARY KEY id
)ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='���������˹�������¼��';

