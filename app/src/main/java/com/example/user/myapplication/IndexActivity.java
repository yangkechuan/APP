package com.example.user.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 使用接口方式完成监听事件
 */

public class IndexActivity extends Activity implements View.OnClickListener {

    private Button index_btn1,index_btn2,index_btn3;
    private EditText index_editText1;
    private ImageView index_imageView1;
    private ProgressBar index_progressBar1,index_progressBar2;
    private TextView index_textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        index_btn1 =(Button)findViewById(R.id.index_btn1);
        index_btn1.setOnClickListener(this);
        index_btn2 = (Button) findViewById(R.id.index_btn2);
        index_btn2.setOnClickListener(this);
        index_btn3 = (Button) findViewById(R.id.index_btn3);
        index_btn3.setOnClickListener(this);
        index_editText1 = (EditText) findViewById(R.id.index_editText1);
        index_editText1.setOnClickListener(this);
        index_imageView1 = (ImageView) findViewById(R.id.index_imageView1);
        index_imageView1.setOnClickListener(this);
        index_progressBar1 = (ProgressBar) findViewById(R.id.index_progressBar1);
        index_progressBar1.setOnClickListener(this);
        index_progressBar2 = (ProgressBar) findViewById(R.id.index_progressBar2);
        index_progressBar2.setOnClickListener(this);
        index_textView1 = (TextView) findViewById(R.id.index_textView1);
        index_textView1.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.index_btn1 :
                String inputText = index_editText1.getText().toString();
                Toast.makeText(IndexActivity.this,inputText,Toast.LENGTH_SHORT).show();
                break;

            case R.id.index_imageView1://点击替换图片
                index_imageView1.setImageResource(R.drawable.ic_launcher);
                break;

            case R.id.index_progressBar1://点击取消进度条
                if (index_progressBar1.getVisibility() == View.GONE){
                    index_progressBar1.setVisibility(View.VISIBLE);
                }else {
                    index_progressBar1.setVisibility(View.GONE);
                }
                break;

            case R.id.index_progressBar2://点击进度条，增加数值
                int progress = index_progressBar2.getProgress();
                progress+=10;
                index_progressBar2.setProgress(progress);
                index_textView1.setText("当前进度："+index_progressBar2.getProgress()+"/100");
                break;


            /**
             * ProgressDialog与AlterDialog相似，如果setCancelable设置为false,则不可以通过Back取消
             * 当数据加载完毕必须调用ProgressDialog的dismiss()方法来关闭对话框，否则ProgressDialog一直存在
             */
            case R.id.index_btn3:
                ProgressDialog progressDialog = new ProgressDialog(IndexActivity.this);
                progressDialog.setTitle("ProgressDialog");
                progressDialog.setMessage("loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;


            /**
             * 通过AlterDialog.Builder创建一个AlertDialog实例，设置标题，内容，可否取消等属性
             * 设置setPositiveButton()和setNegativeButton(),分别为确定按钮和取消按钮的点击事件
             * 最后show()方法将对话框显示
             */
            case R.id.index_btn2://触发一个AlertDialog事件
                AlertDialog.Builder dialog = new AlertDialog.Builder(IndexActivity.this);
                dialog.setTitle("this is Dialog");
                dialog.setMessage("hello world");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(IndexActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(IndexActivity.this, "Cancle", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                break;

            default:
                break;
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_index, menu);
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
