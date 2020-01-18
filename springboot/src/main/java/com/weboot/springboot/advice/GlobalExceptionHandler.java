package com.weboot.springboot.advice;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 全局异常处理器
 *
 * @author yxkj-liuchao
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String DEFAULT_VALIDATED_MSG_SEPARATOR = ";";

    //@Value(value = "${validated.msg.separator}")
    private String validatedMsgSeparator = ":";

    /**
     * HttpMessageNotReadableException处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.OK)
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String msg = "参数解析异常";
        logger.error(msg, e.getMessage());
        StringBuilder sb = new StringBuilder(msg);
        return sb.toString();
    }

    /**
     * 数据校验ConstraintViolationException处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public String handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<String> msgs = new ArrayList<String>();
        if (violations != null && !violations.isEmpty()) {
            for (ConstraintViolation<?> violation : violations) {
                msgs.add(violation.getMessage());
            }
        }
        Collections.reverse(msgs);
        String msg = StringUtils.join(msgs,
                StringUtils.isNotBlank(validatedMsgSeparator) ? validatedMsgSeparator : DEFAULT_VALIDATED_MSG_SEPARATOR);
        logger.info("ConstraintViolationException : {}", msg);
        return msg;
    }




}