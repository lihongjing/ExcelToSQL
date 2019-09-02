/*
MySql
Source Schema         : XFJRCB
Target Server Type    : MySql
Date: Mon Sep 02 16:21:32 CST 2019
*/
-- ----------------------------
-- CB_CRE_APP_INF
--授信申请信息表
-- ----------------------------
CREATE TABLE XFJRCB.CB_CRE_APP_INF(
    CHANNEL_NO VARCHAR(20) NOT NULL COMMENT '渠道号',
    CHANNEL_SEQ VARCHAR(30) COMMENT '渠道订单号',
    APPLY_DATA VARCHAR (10000) COMMENT '申请报文信息内容',
    IS_MANUAL VARCHAR (3) COMMENT '人工创建标识，标识是否人工添加的授信信息',
    MANUAL_DISPOSE_STATUS VARCHAR (3) COMMENT '“人工创建”授信处理状态，人工添加并发起的授信处理状态，未处理/处理中/处理成功/处理失败',
    CREDIT_RESULT VARCHAR (3) COMMENT '“人工创建”授信结果，人工添加并发起的授信结果，授信通过/授信未通过',
    CR_TM TIMESTAMP NOT NULL COMMENT '创建时间，格式：yyyy-MM-dd HH:mm:ss',
    UP_TM TIMESTAMP NOT NULL COMMENT '更新时间，格式：yyyy-MM-dd HH:mm:ss',
    PRIMARY KEY CHANNEL_NO
)ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='授信申请信息表';

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

