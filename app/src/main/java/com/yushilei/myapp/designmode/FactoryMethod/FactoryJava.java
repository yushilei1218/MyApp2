package com.yushilei.myapp.designmode.FactoryMethod;

/**
 * @auther by yushilei.
 * @time 2017/4/1-11:18
 * @desc
 */

public class FactoryJava extends Factory {
    @Override
    public Book createBook() {
        return new BookJava();
    }
}
