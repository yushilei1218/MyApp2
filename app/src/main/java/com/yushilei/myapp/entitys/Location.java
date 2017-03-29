package com.yushilei.myapp.entitys;

import java.util.LinkedList;
import java.util.List;

/**
 * @auther by yushilei.
 * @time 2017/3/29-10:38
 * @desc
 */

public class Location {
    private String name;
    private boolean isShowTag;
    List<Location> subLocations = new LinkedList<>();

    public Location(String name) {
        this.name = name;
    }

    public List<Location> getSubLocations() {
        return subLocations;
    }

    public void setSubLocations(List<Location> subLocations) {
        this.subLocations = subLocations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isShowTag() {
        return isShowTag;
    }

    public void setShowTag(boolean showTag) {
        isShowTag = showTag;
    }
}
