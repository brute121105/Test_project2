package com.example.asus.test_project2;

import android.util.Log;

import com.example.asus.test_project2.getWinXinData.service.ParseWXDataService;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        ParseWXDataService service = new ParseWXDataService();
        service.queryData();
    }
}