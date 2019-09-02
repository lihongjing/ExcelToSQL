/*
MySql
Source Schema         : XFJRCB
Target Server Type    : MySql
Date: Mon Sep 02 16:21:32 CST 2019
*/
-- ----------------------------
-- CREDIT_OPERATION_LOG
--授信申请人工操作记录表
-- ----------------------------
CREATE TABLE XFJRCB.CREDIT_OPERATION_LOG(
    ID BITGINT (20) NOT NULL COMMENT '主键',
    OPERA_TP VARCHAR (5) NOT NULL COMMENT '操作类型：授信信息修改/授信申请/失败授信重试/授信标记通过/授信拒绝',
    CHANGE_BEF VARCHAR (1000) COMMENT '修改前内容',
    CHANGE_AFT VARCHAR (1000) COMMENT '修改后内容',
    FIELD_NA VARCHAR (100) COMMENT '修改字段名',
    APPLY_DATA VARCHAR (10000) COMMENT '申请报文内容',
    OP_TM TIMESTAMP NOT NULL COMMENT '操作时间',
    OP_USER VARCHAR (10) NOT NULL COMMENT '操作人',
    PRIMARY KEY id
)ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='授信申请人工操作记录表';

