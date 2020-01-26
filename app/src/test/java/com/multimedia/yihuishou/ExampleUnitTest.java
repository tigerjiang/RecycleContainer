package com.multimedia.yihuishou;

import android.util.Log;

import com.multimedia.yihuishou.log.LogUtils;
import com.multimedia.yihuishou.net.NetDataUtils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testgetCommunityTypeList(){
        NetDataUtils.getInstance().getCommunityTypeList(null);
    }
    @Test
    public void testT(){
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        if(classStringArrayList.equals(classIntegerArrayList)){
//            LogUtils.d("泛型测试","类型相同");
        }
    }
}