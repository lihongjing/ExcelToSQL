/*
MySql
Source Schema         : XFJRCB
Target Server Type    : MySql
Date: Mon Sep 02 16:21:32 CST 2019
*/
-- ----------------------------
-- CB_CRE_APP_INF
--����������Ϣ��
-- ----------------------------
CREATE TABLE XFJRCB.CB_CRE_APP_INF(
    CHANNEL_NO VARCHAR(20) NOT NULL COMMENT '������',
    CHANNEL_SEQ VARCHAR(30) COMMENT '����������',
    APPLY_DATA VARCHAR (10000) COMMENT '���뱨����Ϣ����',
    IS_MANUAL VARCHAR (3) COMMENT '�˹�������ʶ����ʶ�Ƿ��˹���ӵ�������Ϣ',
    MANUAL_DISPOSE_STATUS VARCHAR (3) COMMENT '���˹����������Ŵ���״̬���˹���Ӳ���������Ŵ���״̬��δ����/������/����ɹ�/����ʧ��',
    CREDIT_RESULT VARCHAR (3) COMMENT '���˹����������Ž�����˹���Ӳ���������Ž��������ͨ��/����δͨ��',
    CR_TM TIMESTAMP NOT NULL COMMENT '����ʱ�䣬��ʽ��yyyy-MM-dd HH:mm:ss',
    UP_TM TIMESTAMP NOT NULL COMMENT '����ʱ�䣬��ʽ��yyyy-MM-dd HH:mm:ss',
    PRIMARY KEY CHANNEL_NO
)ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='����������Ϣ��';

