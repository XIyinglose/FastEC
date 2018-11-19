package com.beyondsot.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //是用在类上面
@Retention(RetentionPolicy.SOURCE) //告诉代码 在编译上面使用
public @interface EntryGenerator {
    String packageName(); //包名
    Class<?> entryTemplate(); //类名
}
