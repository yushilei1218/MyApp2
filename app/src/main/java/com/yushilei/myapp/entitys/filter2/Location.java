package com.yushilei.myapp.entitys.filter2;

import java.util.List;

/**
 * @auther by yushilei.
 * @time 2017/6/1-15:21
 * @desc
 */

public class Location<T extends Location> {
    String name;
    boolean isSelected = false;
    boolean isTempSelected = false;
    boolean isMutex = false;
    List<T> list = null;

    public Location(String name) {
        this.name = name;
    }

    public Location(String name, boolean isSelected) {
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

    public boolean isTempSelected() {
        return isTempSelected;
    }

    public void setTempSelected(boolean tempSelected) {
        isTempSelected = tempSelected;
    }

    public boolean isMutex() {
        return isMutex;
    }

    public void setMutex(boolean mutex) {
        isMutex = mutex;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
