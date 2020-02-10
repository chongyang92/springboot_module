package com.weboot.springboot.core;

/**
 * 项目常量
 */
public final class ProjectConstant {

    // =======================================系统管理常量start==============================================
    // 密码加密算法
    public static final String PSW_ARITHMETIC = "SHA-256";
    // 加密次数
    public static final Integer PSW_HASHITERATIONS = 2;
    // 密码编码
    public static final boolean STORED_CREDENTIALS_HEX_ENCODED = false;

    /**
     * 树状结构的第一层节点
     */
    public static final String ROOT = "Root";

    /**
     * shiro session 中 userId的属性key
     */
    public static final String SHIRO_SESSION_USER_ID_KEY = "CUR_USER_ID";

    /**
     * Path资源的默认请求方式
     */
    public static final String DEFAULT_HTTP_METHOD_TYPE = "*";
    // =======================================系统管理常量end==============================================


    /**
     * 解密数据库连接密码beanName
     */
    public static final String DECRYPT_DATASOURCE_PSW_BEAN_NAME = "decryptDataSourcePwd";
}
