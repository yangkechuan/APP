package com.example.user.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义一个实体类，作为ListView适配器的适配类型
 */

class Fruit{

    private String name;
    private int    imageId;

    public Fruit(String name,int imageId){
        this.name = name;
        this.imageId = imageId;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}

class FruitApapter extends ArrayAdapter<Fruit>{

    private int resourceID;

    public FruitApapter(Context context, int resource, List<Fruit> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position);//获取当前项的Fruit实例
        /**
         * 优化1.
         * 优化运行效率，防止每次都会重新加载布局,如果convertView为空使用Layoutinflater加载布局
         * 否则对convertView重用
         */
        View view;
        
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceID,null);
        }
        else {
            view = convertView;
        }
        //View view = LayoutInflater.from(getContext()).inflate(resourceID, null);


        ImageView  fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
        TextView fruitName = (TextView) view.findViewById(R.id.fruit_name);
        fruitImage.setImageResource(fruit.getImageId());
        fruitName.setText(fruit.getName());
        return view;
    }
}


public class ImageListView extends Activity {

    private List<Fruit> fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list_view);

        initFruits(); //初始化水果数据
        FruitApapter  adapter = new FruitApapter(ImageListView.this,R.layout.fruit_item,fruitList);
        ListView listView = (ListView) findViewById(R.id.activity_list_view2);
        listView.setAdapter(adapter);
    }

    private void initFruits(){
        for (int a = 0 ; a <= 30 ; a++){
            Fruit apple = new Fruit("apple"+a,R.mipmap.ic_launcher);
            fruitList.add(apple);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_list_view, menu);
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
