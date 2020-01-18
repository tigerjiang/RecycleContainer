package com.multimedia.yihuishou;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.multimedia.yihuishou.log.LogUtils;
import com.multimedia.yihuishou.view.BaseFragment;
import com.multimedia.yihuishou.view.ExchangeFragment;
import com.multimedia.yihuishou.view.QueryFragment;
import com.multimedia.yihuishou.view.RecycleFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FrameLayout mContainerView;
    private BaseFragment mCurrentFragment;
    /**
     * 界面——0：全部删除
     */
    protected static final int FRAGMENT_REMOVEALL = 0;
    /**
     * 界面——1：添加 或替换或显示
     */
    protected static final int FRAGMENT_SHOW = 1;
    /**
     * 界面——2：隐藏
     */
    protected static final int FRAGMENT_HIDE = 2;
    /**
     * 界面——3：移除
     */
    protected static final int FRAGMENT_REMOVE = 3;
    /**
     * 界面——4：销毁
     */
    protected static final int FRAGMENT_DETACH = 4;


    /**
     * 界面管理对象
     */
    private FragmentManager mFragmentManager = getSupportFragmentManager();

    private enum FragmentType {
        RECYCLE_TYPE, EXCHANGE_TYPE, QUERY_FRAGMENT
    }

    /**
     * 界面MAP
     */
    private Map<Integer, Fragment> mFragmentMap = new HashMap<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return showFragment(FragmentType.RECYCLE_TYPE);
                case R.id.navigation_dashboard:
                    return showFragment(FragmentType.EXCHANGE_TYPE);
                case R.id.navigation_notifications:
                    return showFragment(FragmentType.QUERY_FRAGMENT);
            }
            return false;
        }
    };

    private boolean showFragment(FragmentType type) {
        switch (type) {
            case RECYCLE_TYPE:
                mCurrentFragment = new RecycleFragment();
                break;
            case EXCHANGE_TYPE:
                mCurrentFragment = new ExchangeFragment();
                break;
            case QUERY_FRAGMENT:
                mCurrentFragment = new QueryFragment();
                break;
        }
        runFragment(R.id.container, mCurrentFragment, FRAGMENT_SHOW, false);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mContainerView = findViewById(R.id.container);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        showFragment(FragmentType.RECYCLE_TYPE);
    }



    /**
     * 执行界面机制
     *
     * @param containerResId 容器资源ID
     * @param fragment       界面
     * @param type           操作类型
     * @param addToBackStack 是否添加到回退栈
     */
    protected void runFragment(int containerResId, Fragment fragment, int type, boolean addToBackStack) {
        LogUtils.d(this, "runFragment", "containerResId= "
                + containerResId
                + "  fragment= "
                + fragment
                + "  type= "
                + type
                + "  addToBackStack= "
                + addToBackStack);
        try {
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            switch (type) {
                case FRAGMENT_REMOVEALL:
                    mFragmentMap.clear();
                    break;
                case FRAGMENT_SHOW:
                    Fragment tmp = mFragmentManager.findFragmentById(containerResId);
                    if (tmp == null) {
                        ft.add(containerResId, fragment);
                    } else if (tmp != fragment) {
                        ft.replace(containerResId, fragment);
                    } else {
                        ft.show(mFragmentMap.get(containerResId));
                    }
                    if (addToBackStack) {
                        ft.addToBackStack(null);
                    }
                    mFragmentMap.put(containerResId, fragment);
                    break;
                case FRAGMENT_REMOVE:
                    if (mFragmentMap.get(containerResId) != null) {
                        ft.remove(mFragmentMap.remove(containerResId));
                    }
                    break;
                case FRAGMENT_HIDE:
                    if (mFragmentMap.get(containerResId) != null) {
                        ft.hide(mFragmentMap.get(containerResId));
                    }
                    break;
                case FRAGMENT_DETACH:
                    if (mFragmentMap.get(containerResId) != null) {
                        ft.detach(mFragmentMap.remove(containerResId));
                    }
                    break;
                default:
                    break;
            }
            ft.commitAllowingStateLoss();
            // ft.commit();
        } catch (Exception e) {

            Log.d(TAG, "runFragment failed ", e);

        }
    }

}
