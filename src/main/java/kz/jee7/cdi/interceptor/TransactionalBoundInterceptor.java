package kz.jee7.cdi.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 27.04.2016 15:52
 * Copyright © LLP JazzSoft
 */
@Interceptor
@TransactionalBound
public class TransactionalBoundInterceptor {

    @AroundInvoke
    public Object handleTransaction(InvocationContext ctx)
            throws Exception {
        System.out.println("#handleTransaction *before* "+
                "invocation");
        Object value = ctx.proceed();
        System.out.println("#handleTransaction *after* "+
                "invocation");
        return value;
    }
}
