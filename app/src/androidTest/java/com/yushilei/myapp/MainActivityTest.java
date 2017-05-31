package com.yushilei.myapp;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * @auther by yushilei.
 * @time 2017/5/31-14:21
 * @desc
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private int count = 0;
    @Rule
    public RepeatRule rule = new RepeatRule(3, new String[]{"test1"});
    private ActivityTestRule<MainActivity> rule1;

    @Before
    public void setUp() throws Exception {
        Log.i("MainActivityTest", "setUp");
        rule1 = new ActivityTestRule<MainActivity>(MainActivity.class);
        rule1.launchActivity(new Intent());

    }

    @Test
    public void test1() {
        count++;
        Log.i("MainActivityTest", "test1 运行第" + count + "次");
        SystemClock.sleep(3000);
    }
    @Test
    public void test2() {
        Log.i("MainActivityTest", "test2 运行");
    }

    @After
    public void tearDown() {
        Log.i("MainActivityTest", "tearDown");
        rule1.getActivity().finish();
    }

}