package com.yushilei.myapp.entitys;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * @auther by yushilei.
 * @time 2017/3/8-16:54
 * @desc
 */
@Table(name = "Bean")
public class Bean {
    @Column(name = "ID", isId = true)
    private int ID;
    @Column(name = "Name")
    private String Name;
    @Column(name = "Age")
    private int Age;

    public Bean() {
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Age=" + Age +
                '}';
    }
}
