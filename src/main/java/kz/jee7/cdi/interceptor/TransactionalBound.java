package kz.jee7.cdi.interceptor;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 27.04.2016 15:50
 * Copyright © LLP JazzSoft
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@InterceptorBinding
public @interface TransactionalBound {

}
