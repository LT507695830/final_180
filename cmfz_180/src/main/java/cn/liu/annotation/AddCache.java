package cn.liu.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//声明注解使用位置
@Retention(RetentionPolicy.RUNTIME) // 生效实际
public @interface AddCache {
}
