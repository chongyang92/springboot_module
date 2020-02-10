package com.weboot.springboot.core.shiro;

import com.weboot.springboot.core.ProjectConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroPwdEncryptUtil {

    private static final Logger logger = LoggerFactory.getLogger(ShiroPwdEncryptUtil.class);
    
    public static String encryptPwd(String userId, String loginName, String password) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(loginName) || StringUtils.isBlank(password)) {
            logger.info("encryptPwd fail : params is empty");
            throw new IllegalArgumentException("encryptPwd params is empty");
        }
        StringBuilder sb = new StringBuilder(userId).append(loginName);
        SimpleHash simpleHash = new SimpleHash(ProjectConstant.PSW_ARITHMETIC, password, sb.toString(),
                ProjectConstant.PSW_HASHITERATIONS);
        if (ProjectConstant.STORED_CREDENTIALS_HEX_ENCODED) {
            return simpleHash.toHex();
        } else {
            return simpleHash.toBase64();
        }
    }
    
}
