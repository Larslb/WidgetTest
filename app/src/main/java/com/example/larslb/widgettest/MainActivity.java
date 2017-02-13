package com.example.larslb.widgettest;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.larslb.widgettest.NewExerciseFragment;

import java.util.ArrayList;

import static android.R.attr.fragment;
import static android.R.attr.switchMinWidth;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private final static String adrr = "00:11:22:33:44";
    public final static String EXTRA_NAME = "NAME";

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private ActionBar mActionBar;
    private ArrayList<String> mData;
    private ArrayList<Integer> mTime;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mStartupMenu;
    private String[] mShootingTiles;
    int clickCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShootingTiles = getResources().getStringArray(R.array.shooting_tile_array);
        mStartupMenu = getResources().getStringArray(R.array.menu_array);
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mData = new ArrayList<>();

        ArrayAdapter<String> seriesAdapter = new ArrayAdapter<String>(this,R.layout.drawer_list_item,mShootingTiles);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item,mStartupMenu));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);


        final ActionBar ab = getSupportActionBar();

        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open_drawer,R.string.close_drawer){
            public void onDrawerClosed(View view){
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }
            public void onDrawerOpened(View view){
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };

        if (savedInstanceState == null) {
            selectItem(0);
        }
        mDrawerLayout.addDrawerListener(mDrawerToggle);


        createData();
    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            case R.id.action_websearch:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setTitle(CharSequence title){
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration config){
        super.onConfigurationChanged(config);
        mDrawerToggle.onConfigurationChanged(config);
    }


    public void createData(){
        for (int i=0;i<10;i++){
            StringBuilder s = new StringBuilder();
            s.append(i);
            mData.add(s.toString());
        }
    }

    public void selectItem(int pos){

        String name = getResources().getStringArray(R.array.menu_array)[pos];
        FragmentManager fragMag = getFragmentManager();
        switch(name) {

            case "Home":
                break;
            case "New Exercise":
                Fragment fragment = new NewExerciseFragment();
                fragMag.beginTransaction().replace(R.id.content_frame,fragment).commit();
                Log.d(TAG,name + " Clicked!");
                mDrawerList.setItemChecked(pos,true);


                break;
            case "Load Data":

                Fragment loadDataFrag = new LoadDataFragment();
                fragMag.beginTransaction().replace(R.id.content_frame,loadDataFrag).commit();
                mDrawerList.setItemChecked(pos,true);

                break;
            case "Options":
                break;
            default:
                finish();
        }

        setTitle(name);
        mDrawerLayout.closeDrawers();

        //Intent intent = new Intent(this,GraphingValueActivity.class);
        //intent.putExtra(EXTRA_NAME,name);
        //startActivity(intent);

    }




}
