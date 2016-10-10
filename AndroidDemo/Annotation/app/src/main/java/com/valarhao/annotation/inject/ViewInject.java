package com.valarhao.annotation.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target => 注解所修饰的对象范围，通过ElementType取值有8种：
 *             TYPE：类、接口（包括注解类型）或枚举
 *             FIELD：属性
 *             METHOD：方法
 *             PARAMETER：参数
 *             CONSTRUCTOR：构造函数
 *             LOCAL_VARIABLE：局部变量
 *             ANNOTATION_TYPE：注解类型
 *             PACKAGE：包
 * @Retention => 注解被保留的时间长短，通过RetentionPolicy取值有3种：
 *             SOURCE：在源文件中有效（即源文件保留）
 *             CLASS：在class文件中有效（即class保留）
 *             RUNTIME：在运行时有效（即运行时保留）
 * @Documented => 一个标记注解，用于描述其它类型的注解应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化
 * @Inherited => 一个标记注解，阐述了某个被标注的类型是被继承的
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewInject {
    //自定义注解如果只有一个参数成员，最好把定义体参数名称设为"value"
    int value();
}