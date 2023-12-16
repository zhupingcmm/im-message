package com.ocbc.project.infrastructure.vo;

import com.ocbc.project.infrastructure.exception.MessageCode;
import lombok.Data;
import org.slf4j.MDC;

@Data
public class Result <T>{

    public final static String TRACE_LOG_ID = "traceId";

    /**
     * 状态码：200正常，500异常
     */
    private int status;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 数据体
     */
    private T data;
    /**
     * traceId
     */
    private String traceId;
    /**
     * errMsg
     */
    private String errMsg;

    public Result() {
    }



    public Result(int status, String message, String traceId) {
        this.status = status;
        this.message = message;
        this.traceId = traceId;
    }



    public Result(int status, String message, T data, String traceId) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.traceId = traceId;
    }


    public static <T> Result<T> success(T data) {
        return new Result<T>(MessageCode.SUCCESS.getStatus(), MessageCode.SUCCESS.getMessage(), data, MDC.get(TRACE_LOG_ID));
    }

    public static <T> Result<T> error(int code, String message) {
        return new Result<T>(code, message, MDC.get(TRACE_LOG_ID));
    }

    public static <T> Result<T> error(String message) {
        return new Result<T>(MessageCode.SYSTEM_ERROR.getStatus(), message, MDC.get(TRACE_LOG_ID));
    }



}
