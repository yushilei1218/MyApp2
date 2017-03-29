package com.yushilei.myapp.entitys.filter;

import com.yushilei.myapp.util.CollectionUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * @auther by yushilei.
 * @time 2017/3/29-15:55
 * @desc
 */

public class Address {
    private String name;
    private boolean isTagging;//缓存的
    private boolean isTagged;//真实被选中的
    private List<Address> data;

    public Address(String name) {
        this.name = name;
    }

    public void addAddress(Address address) {
        if (CollectionUtil.isEmpty(data)) {
            data = new LinkedList<>();
        }
        data.add(address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTagging() {
        return isTagging;
    }

    public void setTagging(boolean tagging) {
        isTagging = tagging;
    }

    public boolean isTagged() {
        return isTagged;
    }

    public void setTagged(boolean tagged) {
        isTagged = tagged;
    }

    public List<Address> getData() {
        return data;
    }

    public void setData(List<Address> data) {
        this.data = data;
    }
}
