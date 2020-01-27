package com.multimedia.yihuishou.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Observable;

public  class SharePreferenceHelper {
    protected static SharedPreferences        sSharedPreference;
    protected static SharedPreferences.Editor sEditor;
    private static final String SP_FILE  = "user_info";

    public static void initSp(Context context){
        sSharedPreference = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        sEditor = sSharedPreference.edit();
    }

    /**
     * 存储
     */
    public static void saveSharedPreference(String key, Object object) {
        if (sEditor == null) {
            return;
        }
        if (object instanceof String) {
            sEditor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            sEditor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            sEditor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            sEditor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            sEditor.putLong(key, (Long) object);
        } else {
            sEditor.putString(key, object.toString());
        }
        sEditor.apply();
    }

    /**
     * 获取保存的数据
     */
    public static Object getSharedPreference(String key, Object defaultObject) {
        if (sSharedPreference == null) {
            return null;
        }
        if (defaultObject instanceof String) {
            return sSharedPreference.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sSharedPreference.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sSharedPreference.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sSharedPreference.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sSharedPreference.getLong(key, (Long) defaultObject);
        } else {
            return sSharedPreference.getString(key, null);
        }
    }



    /**
     * 移除某个key值已经对应的值
     */
    public static void remove(String key) {
        if (sEditor == null) {
            return;
        }
        sEditor.remove(key);
        sEditor.commit();
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        if (sEditor == null) {
            return;
        }
        sEditor.clear();
        sEditor.commit();
    }

    /**
     * 查询某个key是否存在
     */
    public Boolean contain(String key) {
        if (sSharedPreference == null) {
            return false;
        }
        return sSharedPreference.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public Map<String, ?> getAll() {
        if (sSharedPreference == null) {
            return null;
        }
        return sSharedPreference.getAll();
    }

}
