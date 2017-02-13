package com.example.larslb.widgettest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


class ViewHolder{
    String name;
    String Address;
}


public class ListViewerActivity extends AppCompatActivity {
    private static final String TAG = ListViewerActivity.class.getSimpleName();

    ListView mListView;
    ArrayList<String> mLeDevices = new ArrayList<String>();
    ArrayAdapter<String> mAdapter;

    final static String EXTRA_NAME = "EXTRA_NAME";
    final static String EXTRA_ADDRESS = "EXTRA_ADDRESS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_viewer);


        mListView = (ListView) findViewById(R.id.list);



        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,mLeDevices);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Value = (String) mListView.getItemAtPosition(position);
                String[] parts = Value.split("\n");
                StartGraphingActivity(parts[0],parts[1]);

            }
        });

    }

    private void StartGraphingActivity(String Name, String Address) {
        Intent intent = new Intent(this,GraphingValueActivity.class);
        intent.putExtra(EXTRA_NAME,Name);
        intent.putExtra(EXTRA_ADDRESS,Address);
        startActivity(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        makeSamples();
    }

    public void makeSamples(){
        ViewHolder Trigger = new ViewHolder();
        Trigger.name = "TRIGGER_DEVICE";
        Trigger.Address = "00:11:22:33:44";
        ViewHolder Example = new ViewHolder();
        Example.name = "EXAMPLE_DEIVCE";
        Example.Address = "11:33:22:44:FF";
        mLeDevices.add(Trigger.name + "\n" + Trigger.Address);
        mLeDevices.add(Example.name + "\n" + Example.Address);
        mAdapter.notifyDataSetChanged();
    }
}
