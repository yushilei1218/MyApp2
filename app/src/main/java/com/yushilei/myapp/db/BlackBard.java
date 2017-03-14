package com.yushilei.myapp.db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * @auther by yushilei.
 * @time 2017/3/14-14:00
 * @desc
 */
@Table(name = "BlackBard")
public class BlackBard {
    @Column(name = "cardNo", isId = true)
    private String cardNo;
    @Column(name = "info")
    private String info = "";

    public BlackBard() {
    }

    public BlackBard(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
