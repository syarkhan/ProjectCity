package com.example.sheryarkhan.projectcity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.NewsFeedListAdapter;
import data.NewsFeedItemPOJO;

public class NewsFeedActivity extends AppCompatActivity {

    private ListView newsFeedListView;
    private RecyclerView newsFeedRecyclerView;
    private NewsFeedListAdapter newsFeedListAdapter;
    private List<NewsFeedItemPOJO> feedItemsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);



        //newsFeedListView = (ListView)findViewById(R.id.news_feed_listView);
        newsFeedRecyclerView = (RecyclerView) findViewById(R.id.news_feed_recyclerview);
        feedItemsList = new ArrayList<>();

        try {
            newsFeedListAdapter = new NewsFeedListAdapter(this,generateDummyData());
            newsFeedRecyclerView.setAdapter(newsFeedListAdapter);
            newsFeedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }catch (Exception ex)
        {
            Log.d("dada",ex.toString());
        }

//        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3b5998")));
//        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));


        Bitmap profilePic = BitmapFactory.decodeResource(getResources(), R.drawable.loginbg);
        Bitmap postPic = BitmapFactory.decodeResource(getResources(), R.drawable.discoverbtn);

//        for(int i=0; i < 12;i++)
//        {
//
//            NewsFeedItemPOJO item = new NewsFeedItemPOJO();
//            item.setName("Sheryar Khan");
//            item.setStatus("Hello how are you? I am fine. What about you?");
//            item.setId(i);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                item.setImage(getDrawable(R.drawable.camera));
//            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                item.setProfilePic(getDrawable(R.drawable.confirm));
//            }
//            //item.setProfilePic(profilePic);
//            item.setTimeStamp("1501583198");
//            item.setUrl("www.google.com");
//
//            feedItemsList.add(item);
//
//
//        }

        //newsFeedListAdapter.notifyDataSetChanged();


//        newsFeedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//
//                String gg = adapterView.getAdapter().getItem(position).toString();
//
//                Toast.makeText(getBaseContext(),gg,Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }


    private ArrayList<NewsFeedItemPOJO> generateDummyData()
    {
        ArrayList<NewsFeedItemPOJO> list = new ArrayList<>();

        for(int i=0; i < 50;i++)
        {

            NewsFeedItemPOJO item = new NewsFeedItemPOJO();

            if(i == 1 || i == 3 || i == 5 || i == 7 || i == 9) {
                item.setName("Sheryar Khan");
                item.setStatus("Hello how are you? I am fine. What about you?");
                item.setId(i);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    item.setImage(getDrawable(R.drawable.q1bg));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    item.setProfilePic(getDrawable(R.drawable.confirm));
                }
                //item.setProfilePic(profilePic);
                item.setTimeStamp("1401723773793");
                item.setUrl("www.google.com");
            }
            else{
                item.setName("Dawood Khan");
                item.setStatus("Bad news!! Today I saw a very horrible accident near Bakery.");
                item.setId(i);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    item.setImage(getDrawable(R.drawable.discoverbtn));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    item.setProfilePic(getDrawable(R.drawable.camera));
                }
                //item.setProfilePic(profilePic);
                item.setTimeStamp("1401723873793");
                item.setUrl("www.youtube.com");
            }


            list.add(item);


        }

        return list;
    }
}
