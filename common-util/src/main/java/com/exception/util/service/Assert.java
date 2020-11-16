package com.exception.util.service;

/**
 * @author chenyao
 * @date 2020/5/4 20:53
 * @description
 */
public interface Assert {

    /***
     * @description //TODO 仅适用于 code msg提示
     * @author chenyao
     * @date 2020/5/4 21:02
     * @param obj:
     * @return void
     */
     void assertNotNull(Object obj);

    /***
     * @description //TODO 适用于抛出异常 附带属性 例如："code：700 msg:系统异常 属性1：属性2..."
     * @author chenyao
     * @date 2020/5/4 21:03
     * @param obj:
     * @param args:  message占位符对应的参数列表
     * @return void
     */
     void assertNotNull(Object obj, String pattern, String... args);
}
