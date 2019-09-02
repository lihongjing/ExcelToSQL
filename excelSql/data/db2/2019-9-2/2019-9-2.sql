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
    IS '�˹�������ʶ����ʶ�Ƿ��˹����ӵ�������Ϣ';
COMMENT ON COLUMN XFJRCB.CB_CRE_APP_INF.MANUAL_DISPOSE_STATUS 
    IS '���˹����������Ŵ���״̬���˹����Ӳ���������Ŵ���״̬��δ����/������/�����ɹ�/����ʧ��';
COMMENT ON COLUMN XFJRCB.CB_CRE_APP_INF.CREDIT_RESULT 
    IS '���˹����������Ž�����˹����Ӳ���������Ž��������ͨ��/����δͨ��';
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


/*
DB2
Source Schema         : XFJRCB
Target Server Type    : DB2
Date: Mon Sep 02 16:21:32 CST 2019
*/
-- ----------------------------
-- CREDIT_OPERATION_LOG
--���������˹�������¼��
-- ----------------------------
CREATE TABLE XFJRCB.CREDIT_OPERATION_LOG(
    ID BITGINT (20) NOT NULL,
    OPERA_TP VARCHAR (5) NOT NULL,
    CHANGE_BEF VARCHAR (1000),
    CHANGE_AFT VARCHAR (1000),
    FIELD_NA VARCHAR (100),
    APPLY_DATA VARCHAR (10000),
    OP_TM TIMESTAMP(10,6) NOT NULL,
    OP_USER VARCHAR (10) NOT NULL,
    PRIMARY KEY id
);
COMMENT ON TABLE XFJRCB.CREDIT_OPERATION_LOG 
    IS'���������˹�������¼��';
COMMENT ON COLUMN XFJRCB.CREDIT_OPERATION_LOG.id 
    IS '����';
COMMENT ON COLUMN XFJRCB.CREDIT_OPERATION_LOG.OPERA_TP 
    IS '�������ͣ�������Ϣ�޸�/��������/ʧ����������/���ű��ͨ��/���žܾ�';
COMMENT ON COLUMN XFJRCB.CREDIT_OPERATION_LOG.CHANGE_BEF 
    IS '�޸�ǰ����';
COMMENT ON COLUMN XFJRCB.CREDIT_OPERATION_LOG.CHANGE_AFT 
    IS '�޸ĺ�����';
COMMENT ON COLUMN XFJRCB.CREDIT_OPERATION_LOG.FIELD_NA 
    IS '�޸��ֶ���';
COMMENT ON COLUMN XFJRCB.CREDIT_OPERATION_LOG.APPLY_DATA 
    IS '���뱨������';
COMMENT ON COLUMN XFJRCB.CREDIT_OPERATION_LOG.OP_TM 
    IS '����ʱ��';
COMMENT ON COLUMN XFJRCB.CREDIT_OPERATION_LOG.OP_USER 
    IS '������';

CREATE SEQUENCE AAA
START WITH 1
INCREMENT BY 1
minvalue 1
MAXVALUE 2147483647
NO CYCLE;

