package com.example.sheryarkhan.projectcity;

import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sheryarkhan.projectcity.Utils.BottomNavigationViewHelper;
import com.example.sheryarkhan.projectcity.Utils.VideosViewPager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.example.sheryarkhan.projectcity.adapter.NewsFeedListAdapter;
import com.google.firebase.storage.StorageReference;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import data.NewsFeedItemPOJO;
import data.PostsPOJO;

public class NewsFeedActivity extends AppCompatActivity {


    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView newsFeedRecyclerView;
    private NewsFeedListAdapter newsFeedListAdapter;
    private List<NewsFeedItemPOJO> feedItemsList;
    private FirebaseStorage firebaseStorage;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private StorageReference storageReference;
    private List<String> images = Collections.emptyList();


    private ArrayList<PostsPOJO> list;
//    ArrayList<NewsFeedItemPOJO> list3;
//    private ViewPager viewPager;
//    private ViewPagerAdapter viewPagerAdapter;


//    private String[] images = {"btnbackground","camera","q1bg"};

//    private String[] locations = {"Clifton Block 2","Clifton Block 6","Shahrah e Faisal","Clifton Block 5",
//    "Clifton Block 3","Clifton Block 9","Clifton Block 4","Clifton Block 7"
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        setupBottomNavigationView();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
//
//        Query user = databaseReference.orderByChild("")
//        for(int i=0;i<locations.length;i++)
//        {
//            databaseReference.child("Posts").child(String.valueOf(i+3)).child("Location").setValue(locations[i]);
//            databaseReference.child("Posts").child(String.valueOf(i+3)).child("PostText").setValue("Lorem ipsum dolor sit amet");
//            databaseReference.child("Posts").child(String.valueOf(i+3)).child("Timestamp").setValue(1502179255);
//            databaseReference.child("Posts").child(String.valueOf(i+3)).child("UserID").setValue(3);
//        }

        list = new ArrayList<>();

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getBaseContext(),"Refreshed",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        newsFeedRecyclerView = (RecyclerView) findViewById(R.id.news_feed_recyclerview);
        //feedItemsList = new ArrayList<>();

        //newsFeedListAdapter = new NewsFeedListAdapter(this,generateDummyData(list));
        //newsFeedRecyclerView.setAdapter(newsFeedListAdapter);



        newsFeedRecyclerView.setHasFixedSize(true);
        newsFeedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsFeedListAdapter = new NewsFeedListAdapter(NewsFeedActivity.this,list,images);
        newsFeedRecyclerView.setAdapter(newsFeedListAdapter);

        //FIREBASE REFERENCE
        //databaseReference = FirebaseDatabase.getInstance().getReference();

        //Query query = databaseReference.child("Posts").orderByChild("UserID").equalTo(3);

        Query query = databaseReference.child("Posts");
//        final Query userDetails = databaseReference.child("Users");

//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot item : dataSnapshot.getChildren()) {
//                    PostsPOJO newsFeedItemPOJO = item.getValue(PostsPOJO.class);
//                    list.add(newsFeedItemPOJO);
//                }
//                //list3 = generateDummyData(list);
//
//
//                try {
//                    newsFeedListAdapter = new NewsFeedListAdapter(NewsFeedActivity.this,generateDummyData(list));
//                    newsFeedRecyclerView.setAdapter(newsFeedListAdapter);
//                    newsFeedRecyclerView.setLayoutManager(new LinearLayoutManager(NewsFeedActivity.this));
//                }catch (Exception ex)
//                {
//                    Log.d("dadaex",ex.toString());
//                }
//                //list3 = generateDummyData(list);
//
//                //Log.d("dada",list.toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot data : dataSnapshot.getChildren())
                        {
                            final PostsPOJO postsPOJO = data.getValue(PostsPOJO.class);
                            images = postsPOJO.getcontent_post();
//                            List<String> ls = dataSnapshot.child("content_post").getValue(List.class);
//                            GenericTypeIndicator<List<PostsPOJO>> t = new GenericTypeIndicator<List<PostsPOJO>>() {};
//
//                            List<PostsPOJO> yourStringArray = dataSnapshot.getValue(t);
                            Query userDetails = databaseReference.child("Users/"+postsPOJO.getUserID());
                            userDetails.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String username = dataSnapshot.child("Username").getValue(String.class);
                                    String profilePicturePath = dataSnapshot.child("ProfilePicture").getValue(String.class);

//
                                    //StorageReference filePath = storageReference.child("images").child("pic1");
                                    //filePath.getFile(filePath).addOnSuccessListener();
                                    list.add(new PostsPOJO(postsPOJO.getUserID(),profilePicturePath ,username, postsPOJO.getTimestamp(),postsPOJO.getPostText(),postsPOJO.getLocation(),postsPOJO.getcontent_post()));
                                    Log.d("dadalist", list.toString());
                                    newsFeedListAdapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });


                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



//                query.addChildEventListener(new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//
//                        final PostsPOJO postsPOJO = dataSnapshot.getValue(PostsPOJO.class);
//
//                        list.add(postsPOJO);
//
////                Query userDetails = databaseReference.child("Users/"+postsPOJO.getUserID());
////                String u = String.valueOf(userDetails);
////                userDetails.addValueEventListener(new ValueEventListener() {
////                    @Override
////                    public void onDataChange(DataSnapshot dataSnapshot) {
////
////                        String username = dataSnapshot.child("Username").getValue(String.class);
////
////                        list.add(new PostsPOJO(postsPOJO.getUserID(), username, postsPOJO.getTimestamp(),postsPOJO.getPostText(),postsPOJO.getLocation()));
////
////                    }
////
////                    @Override
////                    public void onCancelled(DatabaseError databaseError) {
////
////                    }
////                });
//
//                        //PostsPOJO newsFeedItemPOJO = dataSnapshot.getValue(PostsPOJO.class);
//                        //list.add(newsFeedItemPOJO);
//
//                        Log.d("dadalist", list.toString());
//
//                        newsFeedListAdapter.notifyDataSetChanged();
////                newsFeedListAdapter = new NewsFeedListAdapter(NewsFeedActivity.this,generateDummyData(newsFeedItemPOJO));
////                newsFeedRecyclerView.setAdapter(newsFeedListAdapter);
//                        //newsFeedListAdapter.notifyDataSetChanged();
//
//
//                    }
//
//                    @Override
//                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                    }
//
//                    @Override
//                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });


        //newsFeedListAdapter.notifyDataSetChanged();
//        viewPager = (ViewPager)findViewById(R.id.viewPager);
//        viewPagerAdapter = new ViewPagerAdapter(this,images);
//        viewPager.setAdapter(viewPagerAdapter);


        //newsFeedListView = (ListView)findViewById(R.id.news_feed_listView);


//        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3b5998")));
//        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));


//        Bitmap profilePic = BitmapFactory.decodeResource(getResources(), R.drawable.loginbg);
//        Bitmap postPic = BitmapFactory.decodeResource(getResources(), R.drawable.discoverbtn);

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


//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        VideosViewPager questionVideo = new VideosViewPager(NewsFeedActivity.this);
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//            questionVideo.setDimensions(1920, 1080);
//            questionVideo.getHolder().setFixedSize(1920, 1080);
//
//        } else {
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//
//            questionVideo.setDimensions(1920, 1080);
//            questionVideo.getHolder().setFixedSize(1920, 1080);
//
//        }
//    }

//    private ArrayList<NewsFeedItemPOJO> generateDummyData(ArrayList<PostsPOJO> list)
//    {
//        ArrayList<NewsFeedItemPOJO> list2 = new ArrayList<>();
//
//        for(int i=0; i < list.size();i++)
//        {
//
////list.get(i).getUserID();
////            Query query = databaseReference.child("UserID");
////            query.addListenerForSingleValueEvent(new ValueEventListener() {
////                @Override
////                public void onDataChange(DataSnapshot dataSnapshot) {
////
////                }
////
////                @Override
////                public void onCancelled(DatabaseError databaseError) {
////
////                }
////            });
//            NewsFeedItemPOJO item = new NewsFeedItemPOJO();
//
//            item.setName(list.get(i).getLocation());
//            item.setStatus(list.get(i).getPostText());
//            item.setId(i);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                item.setImage(getDrawable(R.drawable.q1bg));
//            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                item.setProfilePic(getDrawable(R.drawable.confirm));
//            }
//            //item.setProfilePic(profilePic);
//            item.setTimeStamp(list.get(i).getTimestamp().toString());
//            item.setUrl("www.google.com");
//
////            if(i == 1 || i == 3 || i == 5 || i == 7 || i == 9) {
////                item.setName("Sheryar Khan");
////                item.setStatus("Hello how are you? I am fine. What about you?");
////                item.setId(i);
////                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                    item.setImage(getDrawable(R.drawable.q1bg));
////                }
////                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                    item.setProfilePic(getDrawable(R.drawable.confirm));
////                }
////                //item.setProfilePic(profilePic);
////                item.setTimeStamp("1401723773793");
////                item.setUrl("www.google.com");
////            }
////            else{
////                item.setName("Dawood Khan");
////                item.setStatus("Bad news!! Today I saw a very horrible accident near Bakery.");
////                item.setId(i);
////                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                    item.setImage(getDrawable(R.drawable.discoverbtn));
////                }
////                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                    item.setProfilePic(getDrawable(R.drawable.camera));
////                }
////                //item.setProfilePic(profilePic);
////                item.setTimeStamp("1401723873793");
////                item.setUrl("www.youtube.com");
////            }
//
//
//            list2.add(item);
//
//
//        }
//
//        return list2;
//    }

    private void setupBottomNavigationView()
    {
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx)findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
    }
}
