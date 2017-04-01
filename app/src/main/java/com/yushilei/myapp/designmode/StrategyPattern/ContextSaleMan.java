package com.yushilei.myapp.designmode.StrategyPattern;

/**
 * @auther by yushilei.
 * @time 2017/4/1-11:33
 * @desc
 */

public class ContextSaleMan {
    private Strategy strategy;

    public ContextSaleMan(String festival) {
        switch (festival) {
            case "春节":
                strategy = new StrategyA();
                break;
            case "端午":
                strategy = new StrategyB();
                break;
        }
    }

    public void showStrategy() {
        if (strategy != null)
            strategy.show();
    }
}
