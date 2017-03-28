package com.yushilei.myapp.entitys;

/**
 * @auther by yushilei.
 * @time 2017/3/28-15:35
 * @desc
 */

public class Sort {
    private String name;
    private int sort;
    private boolean isSelected;

    public Sort(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
