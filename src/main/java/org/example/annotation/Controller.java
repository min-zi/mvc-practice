package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME) // 런타임 시점까지 유지 (Reflection API 로 어노테이션 정보 조회 가능)
public @interface Controller {

}
