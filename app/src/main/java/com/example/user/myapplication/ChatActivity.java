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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


/**
 * content表示消息内容，type表示消息类型
 *
 * TYPE_RECEIVED表示收到的消息
 *TYPE_SENT表示发出的消息
 */
class Msg{
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;
    private String content;
    private int    type;

    public Msg(String content,int type){
        this.content = content;
        this.type = type;
    }
    public String getContent(){
        return content;
    }
    public int getType(){
        return type;
    }
}


class MsgAdapter extends ArrayAdapter<Msg>{

    private int resourceId;

    public MsgAdapter(Context context, int resource, List<Msg> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Msg msg = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
            viewHolder.rightLayout =(LinearLayout) view.findViewById(R.id.right_layout);
            viewHolder.leftMsg = (TextView) view.findViewById(R.id.left_msg);
            viewHolder.rightMsg = (TextView) view.findViewById(R.id.right_msg);
            view.setTag(viewHolder);
        }
        else {
            view = convertView ;
            viewHolder = (ViewHolder) view.getTag();
        }
        if (msg.getType() == Msg.TYPE_RECEIVED){
            //如果是收到的消息，显示左边的布局，隐藏右边
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(msg.getContent());
        }
        else if (msg.getType() == Msg.TYPE_SENT){
            //如果是发出的消息，则显示右边布局，隐藏左边
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightMsg.setText(msg.getContent());
        }
        return view;
    }

    class ViewHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
    }
}




public class ChatActivity extends Activity {

    private ListView msgListView;
    private EditText inputText;
    private Button   send;
    private MsgAdapter adapter;
    private List<Msg> msgList = new ArrayList<Msg>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initMsgs();//初始化消息
        adapter = new MsgAdapter(ChatActivity.this,R.layout.msg_item,msgList);
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.meg_send);
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        msgListView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();//当有新消息时，刷新ListView中的显示
                    msgListView.setSelection(msgList.size());//将ListView定位到最后一行
                    inputText.setText("");//清空输入框中的内容
                }
            }
        });

    }

    private void initMsgs(){
        Msg msg1 = new Msg("hello guy",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("hi,who is that",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("this is Tom ,Nice talking to you",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
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
