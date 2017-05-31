package com.yushilei.myapp;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.util.Arrays;
import java.util.List;

/**
 * @auther by yushilei.
 * @time 2017/5/31-14:58
 * @desc
 */

public class RepeatRule implements MethodRule {
    int count;
    String[] methodNames;

    public RepeatRule(int count, String[] methodNames) {
        this.count = count;
        this.methodNames = methodNames;
    }

    @Override
    public Statement apply(final Statement base, final FrameworkMethod method, Object target) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                int loopTime = 1;
                if (Arrays.asList(methodNames).contains(method.getName())) {
                    loopTime = count;
                    for (int i = 0; i < loopTime; i++) {
                        base.evaluate();
                    }
                }
            }
        };
    }
}
