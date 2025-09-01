package com.dll.log.monitor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

/**
 * 日志埋点工具类
 *
 */
@Slf4j
public class LogUtil {
    private LogUtil() {
        throw new IllegalStateException("Utility class");
    }


    public static final String SPLIT = "|";

    //业务系统打印默认json格式参数
    private static final String DEFAULT_JSON_FORMAT_PATTERN = "{}";
    //业务logger
    private static final Logger bizInfoLog = BizLoggerFactory.getLogger(LogFileName.BIZ_INFO);
    private static final Logger bizTraceLog = BizLoggerFactory.getLogger(LogFileName.BIZ_TRACE);
    private static final Logger bizDebugLog = BizLoggerFactory.getLogger(LogFileName.BIZ_DEBUG);
    private static final Logger bizErrorLog = BizLoggerFactory.getLogger(LogFileName.BIZ_ERROR);

    /**
     * 格式为：{时间}|{来源}|{对象id}|{类型}|{对象属性(以&分割)}
     * 例子2：2019-07-27 23:37:23|file-center|c0a895e114526786450161001d1ed9|file-upload|fileName=xxx&filePath=xxx
     *
     * @param id      对象id
     * @param type    类型
     * @param message 对象属性
     */
//    public static void info(String id, String type, String message) {
//        log.info(id + SPLIT + type + SPLIT + message);
//    }

    private static Logger logger = new Logger() {
        /**
         * 将参数列表转为json列表数组
         * @param arguments
         * @return
         */
        private Object[] resolveArray(Object ... arguments){
            Object[] objArray = null;
            if (arguments != null && arguments.length > 0){
                objArray = new Object[arguments.length];
                int i = 0;
                for (Object o : arguments){
                    objArray[i++] = JSONObject.toJSONString(o);
                }
            }else {
                objArray = new Object[0];
            }
            return  objArray;
        }

        @Override
        public String getName() {
            return log.getName();
        }

        @Override
        public boolean isTraceEnabled() {
            return log.isTraceEnabled();
        }

        @Override
        public void trace(String msg) {
            log.trace(msg);
        }

        @Override
        public void trace(String format, Object arg) {
            log.trace(format, JSONObject.toJSONString(arg));
        }

        @Override
        public void trace(String format, Object arg1, Object arg2) {
            log.trace(format, JSONObject.toJSONString(arg1), JSONObject.toJSON(arg2));
        }

        @Override
        public void trace(String format, Object... arguments) {
            log.trace(format, resolveArray(arguments));
        }

        @Override
        public void trace(String msg, Throwable t) {
            log.trace(msg, JSONObject.toJSONString(t));
        }

        @Override
        public boolean isTraceEnabled(Marker marker) {
            return log.isTraceEnabled(marker);
        }

        @Override
        public void trace(Marker marker, String msg) {
            log.trace(marker, msg);
        }

        @Override
        public void trace(Marker marker, String format, Object arg) {
            log.trace(marker, format, JSONObject.toJSONString(arg));
        }

        @Override
        public void trace(Marker marker, String format, Object arg1, Object arg2) {
            log.trace(marker, format, JSONObject.toJSONString(arg1), JSONObject.toJSONString(arg2));
        }

        @Override
        public void trace(Marker marker, String format, Object... argArray) {
            log.trace(marker, format, resolveArray(argArray));
        }

        @Override
        public void trace(Marker marker, String msg, Throwable t) {
            log.trace(marker, msg, JSONObject.toJSONString(t));
        }

        @Override
        public boolean isDebugEnabled() {
            return log.isDebugEnabled();
        }

        @Override
        public void debug(String msg) {
            log.debug(msg);
        }

        @Override
        public void debug(String format, Object arg) {
            log.debug(format, JSONObject.toJSONString(arg));
        }

        @Override
        public void debug(String format, Object arg1, Object arg2) {
            log.debug(format, JSONObject.toJSONString(arg1), JSONObject.toJSONString(arg2));
        }

        @Override
        public void debug(String format, Object... arguments) {
            log.debug(format, resolveArray(arguments));
        }

        @Override
        public void debug(String msg, Throwable t) {
            log.debug(msg, JSONObject.toJSONString(t));
        }

        @Override
        public boolean isDebugEnabled(Marker marker) {
            return log.isDebugEnabled(marker);
        }

        @Override
        public void debug(Marker marker, String msg) {
            log.debug(marker, msg);
        }

        @Override
        public void debug(Marker marker, String format, Object arg) {
            log.debug(marker, format, JSONObject.toJSONString(arg));
        }

        @Override
        public void debug(Marker marker, String format, Object arg1, Object arg2) {
            log.debug(marker, format, JSONObject.toJSONString(arg1), JSONObject.toJSONString(arg2));
        }

        @Override
        public void debug(Marker marker, String format, Object... arguments) {
            log.debug(marker, format, resolveArray(arguments));
        }

        @Override
        public void debug(Marker marker, String msg, Throwable t) {
            log.debug(marker, msg, JSONObject.toJSONString(t));
        }

        @Override
        public boolean isInfoEnabled() {
            return log.isInfoEnabled();
        }

        @Override
        public void info(String msg) {
            log.info(msg);
        }

        @Override
        public void info(String format, Object arg) {
            log.info(format, JSONObject.toJSONString(arg));
        }

        @Override
        public void info(String format, Object arg1, Object arg2) {
            log.info(format, JSONObject.toJSONString(arg1), JSONObject.toJSONString(arg2));
        }

        @Override
        public void info(String format, Object... arguments) {
            log.info(format, resolveArray(arguments));
        }

        @Override
        public void info(String msg, Throwable t) {
            log.info(msg, JSONObject.toJSONString(t));
        }

        @Override
        public boolean isInfoEnabled(Marker marker) {
            return log.isInfoEnabled(marker);
        }

        @Override
        public void info(Marker marker, String msg) {
            log.info(marker, msg);
        }

        @Override
        public void info(Marker marker, String format, Object arg) {
            log.info(marker, format, JSONObject.toJSONString(arg));
        }

        @Override
        public void info(Marker marker, String format, Object arg1, Object arg2) {
            log.info(marker, format, JSONObject.toJSONString(arg1), JSONObject.toJSONString(arg2));
        }

        @Override
        public void info(Marker marker, String format, Object... arguments) {
            log.info(marker, format, resolveArray(arguments));
        }

        @Override
        public void info(Marker marker, String msg, Throwable t) {
            log.info(marker, msg, JSONObject.toJSONString(t));
        }

        @Override
        public boolean isWarnEnabled() {
            return log.isWarnEnabled();
        }

        @Override
        public void warn(String msg) {
            log.warn(msg);
        }

        @Override
        public void warn(String format, Object arg) {
            log.warn(format, JSONObject.toJSONString(arg));
        }

        @Override
        public void warn(String format, Object... arguments) {
            log.warn(format, resolveArray(arguments));
        }

        @Override
        public void warn(String format, Object arg1, Object arg2) {
            log.warn(format, JSONObject.toJSONString(arg1), JSONObject.toJSONString(arg2));
        }

        @Override
        public void warn(String msg, Throwable t) {
            log.warn(msg, JSONObject.toJSONString(t));
        }

        @Override
        public boolean isWarnEnabled(Marker marker) {
            return log.isWarnEnabled(marker);
        }

        @Override
        public void warn(Marker marker, String msg) {
            log.warn(marker, msg);
        }

        @Override
        public void warn(Marker marker, String format, Object arg) {
            log.warn(marker, format, JSONObject.toJSONString(arg));
        }

        @Override
        public void warn(Marker marker, String format, Object arg1, Object arg2) {
            log.warn(marker, format, JSONObject.toJSONString(arg1), JSONObject.toJSONString(arg2));
        }

        @Override
        public void warn(Marker marker, String format, Object... arguments) {
            log.warn(marker, format, resolveArray(arguments));
        }

        @Override
        public void warn(Marker marker, String msg, Throwable t) {
            log.warn(marker, msg, JSONObject.toJSONString(t));
        }

        @Override
        public boolean isErrorEnabled() {
            return log.isErrorEnabled();
        }

        @Override
        public void error(String msg) {
            log.error(msg);
        }

        @Override
        public void error(String format, Object arg) {
            log.error(format, JSONObject.toJSONString(arg));
        }

        @Override
        public void error(String format, Object arg1, Object arg2) {
            log.error(format, JSONObject.toJSONString(arg1), JSONObject.toJSONString(arg2));
        }

        @Override
        public void error(String format, Object... arguments) {
            log.error(format, resolveArray(arguments));
        }

        @Override
        public void error(String msg, Throwable t) {
            log.error(msg, JSONObject.toJSONString(t));
        }

        @Override
        public boolean isErrorEnabled(Marker marker) {
            return log.isErrorEnabled(marker);
        }

        @Override
        public void error(Marker marker, String msg) {
            log.error(marker, msg);
        }

        @Override
        public void error(Marker marker, String format, Object arg) {
            log.error(marker, format, JSONObject.toJSONString(arg));
        }

        @Override
        public void error(Marker marker, String format, Object arg1, Object arg2) {
            log.error(marker, format, JSONObject.toJSONString(arg1), JSONObject.toJSONString(arg2));
        }

        @Override
        public void error(Marker marker, String format, Object... arguments) {
            log.error(marker, format, resolveArray(arguments));
        }

        @Override
        public void error(Marker marker, String msg, Throwable t) {
            log.error(marker, msg, JSONObject.toJSONString(t));
        }
    };


    public static boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    public static void trace(String msg) {
        logger.trace(msg);
    }

    public static void trace(String format, Object arg) {
        logger.trace(format, arg);
    }

    public static void trace(String format, Object arg1, Object arg2) {
        logger.trace(format, arg1, arg2);
    }

    public static void trace(String format, Object... arguments) {
        logger.trace(format, arguments);
    }

    public static void trace(String msg, Throwable t) {
        logger.trace(msg, t);
    }

    public static boolean isTraceEnabled(Marker marker) {
        return logger.isTraceEnabled(marker);
    }

    public static void trace(Marker marker, String msg) {
        logger.trace(marker, msg);
    }

    public static void trace(Marker marker, String format, Object arg) {
        logger.trace(marker, format, arg);
    }

    public static void trace(Marker marker, String format, Object arg1, Object arg2) {
        logger.trace(marker, format, arg1, arg2);
    }

    public static void trace(Marker marker, String format, Object... argArray) {
        logger.trace(marker, format, argArray);
    }

    public static void trace(Marker marker, String msg, Throwable t) {
        logger.trace(marker, msg, t);
    }

    public static boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public static void debug(String msg) {
        logger.debug(msg);
    }

    public static void debug(String format, Object arg) {
        logger.debug(format, arg);
    }

    public static void debug(String format, Object arg1, Object arg2) {
        logger.debug(format, arg1, arg2);
    }

    public static void debug(String format, Object... arguments) {
        logger.debug(format, arguments);
    }

    public static void debug(String msg, Throwable t) {
        logger.debug(msg, t);
    }

    public static boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker);
    }

    public static void debug(Marker marker, String msg) {
        logger.debug(marker, msg);
    }

    public static void debug(Marker marker, String format, Object arg) {
        logger.debug(marker, format, arg);
    }

    public static void debug(Marker marker, String format, Object arg1, Object arg2) {
        logger.debug(marker, format, arg1, arg2);
    }

    public static void debug(Marker marker, String format, Object... arguments) {
        logger.debug(marker, format, arguments);
    }

    public static void debug(Marker marker, String msg, Throwable t) {
        logger.debug(marker, msg, t);
    }

    public static boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public static void info(String msg) {
        logger.info(msg);
    }

    public static void info(String format, Object arg) {
        logger.info(format, arg);
    }

    public static void info(String format, Object arg1, Object arg2) {
        logger.info(format, arg1, arg2);
    }

    public static void info(String format, Object... arguments) {
        logger.info(format, arguments);
    }

    public static void info(String msg, Throwable t) {
        logger.info(msg, t);
    }

    public static boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker);
    }

    public static void info(Marker marker, String msg) {
        logger.info(marker, msg);
    }

    public static void info(Marker marker, String format, Object arg) {
        logger.info(marker, format, arg);
    }

    public static void info(Marker marker, String format, Object arg1, Object arg2) {
        logger.info(marker, format, arg1, arg2);
    }

    public static void info(Marker marker, String format, Object... arguments) {
        logger.info(marker, format, arguments);
    }

    public static void info(Marker marker, String msg, Throwable t) {
        logger.info(marker, msg, t);
    }

    public static boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    public static void warn(String msg) {
        logger.warn(msg);
    }

    public static void warn(String format, Object arg) {
        logger.warn(format, arg);
    }

    public static void warn(String format, Object... arguments) {
        logger.warn(format, arguments);
    }

    public static void warn(String format, Object arg1, Object arg2) {
        logger.warn(format, arg1, arg2);
    }

    public static void warn(String msg, Throwable t) {
        logger.warn(msg, t);
    }

    public static boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled(marker);
    }

    public static void warn(Marker marker, String msg) {
        logger.warn(marker, msg);
    }

    public static void warn(Marker marker, String format, Object arg) {
        logger.warn(marker, format, arg);
    }

    public static void warn(Marker marker, String format, Object arg1, Object arg2) {
        logger.warn(marker, format, arg1, arg2);
    }

    public static void warn(Marker marker, String format, Object... arguments) {
        logger.warn(marker, format, arguments);
    }

    public static void warn(Marker marker, String msg, Throwable t) {
        logger.warn(marker, msg, t);
    }

    public static boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    public static void error(String msg) {
        logger.error(msg);
    }

    public static void error(String format, Object arg) {
        logger.error(format, arg);
    }

    public static void error(String format, Object arg1, Object arg2) {
        logger.error(format, arg1, arg2);
    }

    public static void error(String format, Object... arguments) {
        logger.error(format, arguments);
    }

    public static void error(String msg, Throwable t) {
        logger.error(msg, t);
    }

    public static boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker);
    }

    public static void error(Marker marker, String msg) {
        logger.error(marker, msg);
    }

    public static void error(Marker marker, String format, Object arg) {
        logger.error(marker, format, arg);
    }

    public static void error(Marker marker, String format, Object arg1, Object arg2) {
        logger.error(marker, format, arg1, arg2);
    }

    public static void error(Marker marker, String format, Object... arguments) {
        logger.error(marker, format, arguments);
    }

    public static void error(Marker marker, String msg, Throwable t) {
        logger.error(marker, msg, t);
    }

    //业务日志
    public static void bizTrace(String msg){
        logger.trace(msg);
    }

    public static void bizTraceJson(Object arg){
        bizTraceLog.trace(DEFAULT_JSON_FORMAT_PATTERN, dealJsonEscape(arg));
    }

    public static void bizTrace(String format, Object ... args){
        logger.trace(format, args);
    }

    public static void bizInfo(String msg){
        logger.info(msg);
    }

    public static void bizInfoJson(Object arg){
        bizInfoLog.info(DEFAULT_JSON_FORMAT_PATTERN, dealJsonEscape(arg));
    }

    public static void bizInfo(String format, Object ... args){
        logger.info(format, args);
    }

    public static void bizDebug(String msg){
        logger.debug(msg);
    }

    public static void bizDebugJson(Object arg){
        bizDebugLog.debug(DEFAULT_JSON_FORMAT_PATTERN, dealJsonEscape(arg));
    }

    public static void bizDebug(String format, Object ... args){
        logger.debug(format, args);
    }

    public static void bizError(String msg){
        logger.error(msg);
    }

    public static void bizErrorJson(Object arg){
        bizErrorLog.error(DEFAULT_JSON_FORMAT_PATTERN, dealJsonEscape(arg));
    }

    public static void bizError(String format, Object ... args){
        logger.error(format, args);
    }

    /**
     * 对象转为jsonString并将双引号转为单引号
     * @param obj
     * @return
     */
    private static String dealJsonEscape(Object obj){
        if (obj == null){
            return "\\{}";
        }else {
            String jsonStr = JSONObject.toJSONString(obj);
            return jsonStr.replaceAll("\"", "\'");
        }
    }

    enum LogFileName{
        BIZ_INFO("bizInfo"),
        BIZ_TRACE("bizTrace"),
        BIZ_DEBUG("bizDebug"),
        BIZ_ERROR("bizError");
        private String name;

        LogFileName(String name){
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    /**
     * 日志工厂
     */
    static  class BizLoggerFactory{
        public static Logger getLogger(LogFileName logFileName){
            Logger logger = LoggerFactory.getLogger(logFileName.getName());
            return logger;
        }
    }
}
