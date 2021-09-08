package com.zeyiyouhuo.constants;

public interface Constants {

    /**
     * 共享参数
     **/
    String APP_KEY = "appKey";
    String METHOD = "method";
    String TIMESTAMP = "timestamp";
    String NONCE = "nonce";
    String VERSION = "v";
    String SIGN = "sign";
    String AUTHORIZATION = "authorization";
    String BEARER = "Bearer ";
    String USERNAME = "username";
    String PASSWORD = "password";

    String ZEYI_CONTENT_TYPE = "text/xml;charset=utf-8";
    String ZEYI_CONTENT_TYPE_JSON = "application/json;charset=utf-8";

    /**
     * 默认时间格式
     **/
    String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Date默认时区
     **/
    String DATE_TIMEZONE = "GMT+8";

    /**
     * UTF-8字符集
     **/
    String CHARSET_UTF8 = "UTF-8";

    /**
     * GBK字符集
     **/
    String CHARSET_GBK = "GBK";

    /**
     * HTTP请求相关
     **/
    String METHOD_POST = "POST";
    String METHOD_GET = "GET";
    String CTYPE_FORM_DATA = "application/x-www-form-urlencoded";
    String CTYPE_FILE_UPLOAD = "multipart/form-data";
    String CTYPE_TEXT_XML = "text/xml";
    String CTYPE_APPLICATION_XML = "application/xml";
    String CTYPE_TEXT_PLAIN = "text/plain";
    String CTYPE_APP_JSON = "application/json";


    /**
     * JSON 应格式
     */
    String FORMAT_JSON = "json";

    /**
     * MD5签名方式
     */
    String SIGN_METHOD_MD5 = "md5";
    /**
     * HMAC签名方式
     */
    String SIGN_METHOD_HMAC = "hmac";
    /**
     * HMAC-SHA256签名方式
     */
    String SIGN_METHOD_HMAC_SHA256 = "hmac-sha256";

    /**
     * 响应编码
     */
    String ACCEPT = "Accept";
    String ACCEPT_VALUE = "*/*";
    String ACCEPT_ENCODING = "Accept-Encoding";
    String CONTENT_ENCODING = "Content-Encoding";
    String CONTENT_ENCODING_GZIP = "gzip";

    /**
     * 默认媒体类型
     **/
    String MIME_TYPE_DEFAULT = "application/octet-stream";

    /**
     * 默认流式读取缓冲区大小
     **/
    int READ_BUFFER_SIZE = 1024 * 4;


    String HTTP_DNS_HOST = "_HTTP_DNS_HOST";

    /**
     * API网关请求content type
     **/
    String CONTENT_TYPE_JSON = "json";
    String CONTENT_TYPE_FORM = "form";


}
