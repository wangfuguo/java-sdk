package com.zeyiyouhuo.api.response;

import java.io.Serializable;

/**
 * 则一基础响应信息
 */
public class ZeyiResponse implements Serializable {

    private static final long serialVersionUID = 5028379068812021022L;
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 是否业务异常
     */
    private boolean businessException;
    /**
     * 状态码
     */
    private String errorCode;
    /**
     * 信息
     */
    private String message;
    /**
     * 返回结果
     */
    private String result;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isBusinessException() {
        return businessException;
    }

    public void setBusinessException(boolean businessException) {
        this.businessException = businessException;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
