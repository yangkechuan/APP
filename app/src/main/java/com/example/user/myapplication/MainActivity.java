/**
 *  活动生命周期
 *
 * onCreate()             -----完成活动的初始化操作
 *
 * onStart()             ------在活动中由不可见变为可见
 *
 * onResume()            ------在活动准备好和用户交互的时候调用，此时的活动一定位于返回栈的栈顶，并处于运行状态
 *
 * onPause()             ------在系统准备启动或恢复另一个活动的时候调用
 *
 * onStop()              ------在活动被销毁前调用
 *
 * onRestart()           ------在活动由停止变为运行状态之前调用
 */

package com.example.user.myapplication;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class MainActivity extends BaseActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");

        /**
         * 设置两个button，进行Intent跳转，检测活动周期
         */
        Button startNormalActivity = (Button) findViewById(R.id.start_normal_acticity);
        Button startDialogActivity = (Button) findViewById(R.id.start_dialog_activity);
        Button finishAll_btn = (Button) findViewById(R.id.finishAll_btn);


        startNormalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NormalActivity.class);
                startActivity(intent);
            }
        });

        startDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DialogActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 通过调用ActivityCollecotr的finishAll()，随时都可以在当前活动退出APP
         */
        finishAll_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
            }
        });
    }



    /**
     * onSaveInstanceState使当前活动因为内存原因被销毁时，可以将临时数据进行保存
     * 通过携带一个Bundle类型的参数，用于保存数据
     * 然后通过修改onCreate()方法，再取出相应的值
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "Something you just typed";
        outState.putString("data_key",tempData);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.i(TAG, "onCreate");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            String tempData = savedInstanceState.getString("data_key");
            Log.i(TAG,tempData);
        }
    }




    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestory");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
