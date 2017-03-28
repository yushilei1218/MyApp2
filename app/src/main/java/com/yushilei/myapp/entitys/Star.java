package com.yushilei.myapp.entitys;

/**
 * @auther by yushilei.
 * @time 2017/3/28-15:38
 * @desc
 */

public class Star {
    private String name;
    private boolean isSelected;
    private boolean cacheSelected;
    private int star;

    public Star(String name) {
        this.name = name;
    }

    public Star(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public boolean isCacheSelected() {
        return cacheSelected;
    }

    public void setCacheSelected(boolean cacheSelected) {
        this.cacheSelected = cacheSelected;
    }
}
