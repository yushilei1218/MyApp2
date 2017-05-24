package com.yushilei.myapp;


import android.view.View;

import com.yushilei.myapp.util.CollectionUtil;
import com.yushilei.myapp.widget.ClipRegionView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private String name;
    private static final String TAG = "ExampleUnitTest";

    @Before
    public void setUp() {
        System.out.println("setUp");
        name = "20170522";
    }

    @After
    public void tearDown() {
        System.out.println("tearDown");
    }

    @Test
    public void addition_isCorrect() throws Exception {
        System.out.println("addition_isCorrect");
        assertEquals(4, 2 + 1);
    }

    @Test
    public void case1() {
        System.out.println("case1");

        assertTrue(CollectionUtil.isEmpty(new LinkedList()));
    }
}