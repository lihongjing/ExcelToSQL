/*
DB2
Source Schema         : XFJRCB
Target Server Type    : DB2
Date: Mon Sep 02 16:21:32 CST 2019
*/
-- ----------------------------
-- CB_CRE_APP_INF
--����������Ϣ��
-- ----------------------------
CREATE TABLE XFJRCB.CB_CRE_APP_INF(
    CHANNEL_NO VARCHAR(20) NOT NULL,
    CHANNEL_SEQ VARCHAR(30),
    APPLY_DATA VARCHAR (10000),
    IS_MANUAL VARCHAR (3),
    MANUAL_DISPOSE_STATUS VARCHAR (3),
    CREDIT_RESULT VARCHAR (3),
    CR_TM TIMESTAMP(10,6) NOT NULL,
    UP_TM TIMESTAMP(10,6) NOT NULL,
    PRIMARY KEY CHANNEL_NO
);
COMMENT ON TABLE XFJRCB.CB_CRE_APP_INF 
    IS'����������Ϣ��';
COMMENT ON COLUMN XFJRCB.CB_CRE_APP_INF.CHANNEL_NO 
    IS '������';
COMMENT ON COLUMN XFJRCB.CB_CRE_APP_INF.CHANNEL_SEQ 
    IS '����������';
COMMENT ON COLUMN XFJRCB.CB_CRE_APP_INF.APPLY_DATA 
    IS '���뱨����Ϣ����';
COMMENT ON COLUMN XFJRCB.CB_CRE_APP_INF.IS_MANUAL 
    IS '�˹�������ʶ����ʶ�Ƿ��˹���ӵ�������Ϣ';
COMMENT ON COLUMN XFJRCB.CB_CRE_APP_INF.MANUAL_DISPOSE_STATUS 
    IS '���˹����������Ŵ���״̬���˹���Ӳ���������Ŵ���״̬��δ����/������/����ɹ�/����ʧ��';
COMMENT ON COLUMN XFJRCB.CB_CRE_APP_INF.CREDIT_RESULT 
    IS '���˹����������Ž�����˹���Ӳ���������Ž��������ͨ��/����δͨ��';
COMMENT ON COLUMN XFJRCB.CB_CRE_APP_INF.CR_TM 
    IS '����ʱ�䣬��ʽ��yyyy-MM-dd HH:mm:ss';
COMMENT ON COLUMN XFJRCB.CB_CRE_APP_INF.UP_TM 
    IS '����ʱ�䣬��ʽ��yyyy-MM-dd HH:mm:ss';

CREATE SEQUENCE CB_CRE_APP_INF_SEQ
START WITH 1
INCREMENT BY 1
minvalue 1
MAXVALUE 2147483647
NO CYCLE;


