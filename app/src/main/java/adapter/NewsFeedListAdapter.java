package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.sheryarkhan.projectcity.CommentsActivity;
import com.example.sheryarkhan.projectcity.PostImageDisplayActivity;
import com.example.sheryarkhan.projectcity.R;
import com.example.sheryarkhan.projectcity.UserProfileActivity;

import org.w3c.dom.Text;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import data.NewsFeedItemPOJO;

/**
 * Created by Sheryar Khan on 8/1/2017.
 */

public class NewsFeedListAdapter extends RecyclerView.Adapter<NewsFeedListAdapter.MainViewHolder> {

    private Activity activity;
    private LayoutInflater inflater;
    private Context context;

    private static final int TYPE_PICTURE = 1;
    private static final int TYPE_VIDEO = 2;
    //private List<NewsFeedItemPOJO> newsFeedItems;

    public static List<NewsFeedItemPOJO> newsFeedItemPOJOs = Collections.emptyList();

    public NewsFeedListAdapter(Context context, List<NewsFeedItemPOJO> newsFeedItems)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.newsFeedItemPOJOs = newsFeedItems;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case TYPE_PICTURE:
                return new OnlyPostImageViewHolder(inflater.inflate(R.layout.news_feed_list_item, parent, false));
            case TYPE_VIDEO:
                return new OnlyPostVideoViewHolder(inflater.inflate(R.layout.news_feed_video_post_item, parent, false));
        }
        return null;
//        View view = inflater.inflate(R.layout.news_feed_list_item,parent,false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        return viewHolder;
    }


//    @Override
//    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
//    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, final int position) {

        if(holder.getItemViewType() == TYPE_PICTURE){
            OnlyPostImageViewHolder mholder = (OnlyPostImageViewHolder) holder;
            setUpPictureView(mholder,position);
        }
        else {
            OnlyPostVideoViewHolder mHolder = (OnlyPostVideoViewHolder) holder;
            NewsFeedItemPOJO currentData = newsFeedItemPOJOs.get(position);
            mHolder.Id = currentData.getId();

            mHolder.txtName.setText(currentData.getName());

            // Converting timestamp into X ago format
            CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                    Long.parseLong(currentData.getTimeStamp()),
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
            mHolder.txtTimeStamp.setText(timeAgo);

            mHolder.txtUrl.setText(currentData.getUrl());

            mHolder.txtStatusMsg.setText(currentData.getStatus());

            mHolder.imgProfilePic.setImageDrawable(currentData.getProfilePic());


            mHolder.imgPost.setImageDrawable(currentData.getImage());

            mHolder.txtUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"URL:"+position,Toast.LENGTH_SHORT).show();
                }
            });


            mHolder.imgPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, PostImageDisplayActivity.class);
                    intent.putExtra("imageIndex", position);
                    context.startActivity(intent);

                }
            });


        }

    }

    private void setUpPictureView(OnlyPostImageViewHolder mholder, final int position) {

        NewsFeedItemPOJO currentData = newsFeedItemPOJOs.get(position);
        mholder.Id = currentData.getId();

        mholder.txtName.setText(currentData.getName());

        // Converting timestamp into X ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(currentData.getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        mholder.txtTimeStamp.setText(timeAgo);

        mholder.txtUrl.setText(currentData.getUrl());

        mholder.txtStatusMsg.setText(currentData.getStatus());

        mholder.imgProfilePic.setImageDrawable(currentData.getProfilePic());


        mholder.imgPost.setImageDrawable(currentData.getImage());

        mholder.txtUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"URL:"+position,Toast.LENGTH_SHORT).show();
            }
        });


        mholder.imgPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, PostImageDisplayActivity.class);
                intent.putExtra("imageIndex", position);
                context.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemViewType(int position) {

//        if(newsFeedItemPOJOs.get(position).get)
        return (position == 0 ? TYPE_PICTURE : TYPE_VIDEO);
        //return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return newsFeedItemPOJOs.size();
    }


    public class OnlyPostImageViewHolder extends MainViewHolder {

        int Id;
        TextView txtName;
        TextView txtTimeStamp;
        TextView txtStatusMsg;
        TextView txtUrl;
        ImageView imgProfilePic;
        ImageView imgPost;
        Button btnHelpful;
        Button btnComments;

        public OnlyPostImageViewHolder(View itemView) {

            super(itemView);

            //itemView.setOnClickListener(this);

            Id=0;
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtTimeStamp = (TextView) itemView.findViewById(R.id.txtTimeStamp);
            txtStatusMsg = (TextView) itemView.findViewById(R.id.txtStatusMsg);
            txtUrl = (TextView) itemView.findViewById(R.id.txtUrl);
            imgPost = (ImageView) itemView.findViewById(R.id.imgPost);
            imgProfilePic = (ImageView) itemView.findViewById(R.id.imgProfilePic);
//            btnHelpful = (Button) itemView.findViewById(R.id.btnHelpful);
//            btnComments = (Button) itemView.findViewById(R.id.btnComments);

        }


    }


    public class OnlyPostVideoViewHolder extends MainViewHolder {

        int Id;
        TextView txtName;
        TextView txtTimeStamp;
        TextView txtStatusMsg;
        TextView txtUrl;
        ImageView imgProfilePic;
        ImageView imgPost;


        public OnlyPostVideoViewHolder(View itemView) {

            super(itemView);

            //itemView.setOnClickListener(this);

            Id=0;
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtTimeStamp = (TextView) itemView.findViewById(R.id.txtTimeStamp);
            txtStatusMsg = (TextView) itemView.findViewById(R.id.txtStatusMsg);
            txtUrl = (TextView) itemView.findViewById(R.id.txtUrl);
            imgPost = (ImageView) itemView.findViewById(R.id.imgPost);
            imgProfilePic = (ImageView) itemView.findViewById(R.id.imgProfilePic);




        }


    }

    public class MainViewHolder extends  RecyclerView.ViewHolder {
        public MainViewHolder(View v) {
            super(v);
        }
    }

//    private static class ViewHolder {
//
//        TextView txtName;
//        TextView txtTimeStamp;
//        TextView txtStatusMsg;
//        TextView txtUrl;
//        ImageView imgProfilePic;
//        ImageView imgPost;
//
//    }

//    class ListViewObject {
//
//        TextView txtName;
//        TextView txtTimeStamp;
//        TextView txtStatusMsg;
//        TextView txtUrl;
//        ImageView imgProfilePic;
//        ImageView imgPost;
//
//    }

//    @Override
//    public int getCount() {
//        return newsFeedItems.size();
//    }
//
//    @Override
//    public Object getItem(int location) {
//        return newsFeedItems.get(location);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        ViewHolder viewHolder;
//        if(inflater == null)
//        {
//            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//        if(convertView == null)
//        {
//            convertView = inflater.inflate(R.layout.news_feed_list_item,null);
//        }
//
//        viewHolder = new ViewHolder();
//
//        viewHolder.txtName = (TextView)convertView.findViewById(R.id.txtName);
//        viewHolder.txtTimeStamp = (TextView)convertView.findViewById(R.id.txtTimeStamp);
//        viewHolder.txtStatusMsg = (TextView)convertView.findViewById(R.id.txtStatusMsg);
//        viewHolder.txtUrl = (TextView)convertView.findViewById(R.id.txtUrl);
//        viewHolder.imgProfilePic = (ImageView)convertView.findViewById(R.id.imgProfilePic);
//        viewHolder.imgPost = (ImageView)convertView.findViewById(R.id.imgPost);
////      TextView name = (TextView)convertView.findViewById(R.id.name);
//
//        NewsFeedItemPOJO item = newsFeedItems.get(position);
//
//
//        try {
//            viewHolder.txtName.setText(item.getName());
//
//            // Converting timestamp into X ago format
//            CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
//                    Long.parseLong(item.getTimeStamp()),
//                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
//            viewHolder.txtTimeStamp.setText(timeAgo);
//
//
//            if (!TextUtils.isEmpty(item.getStatus())) {
//                viewHolder.txtStatusMsg.setText(item.getStatus());
//            } else {
//                viewHolder.txtStatusMsg.setVisibility(View.GONE);
//            }
//
//            // Checking for null feed url
//            if (item.getUrl() != null) {
//                viewHolder.txtUrl.setText(Html.fromHtml("<a href=\"" + item.getUrl() + "\">"
//                        + item.getUrl() + "</a> "));
//
//                // Making url clickable
//                viewHolder.txtUrl.setMovementMethod(LinkMovementMethod.getInstance());
//                viewHolder.txtUrl.setVisibility(View.VISIBLE);
//            } else {
//                // url is null, remove from the view
//                viewHolder.txtUrl.setVisibility(View.GONE);
//            }
//
//            // user profile pic
//            viewHolder.imgProfilePic.setImageBitmap(item.getProfilePic());
//
//
//            // Feed image
//            if (item.getImage() != null) {
//                //feedImageView.setImageUrl(item.getImge(), imageLoader);
//                viewHolder.imgPost.setImageBitmap(item.getImage());
//                viewHolder.imgPost.setVisibility(View.VISIBLE);
////            feedImageView
////                    .setResponseObserver(new FeedImageView.ResponseObserver() {
////                        @Override
////                        public void onError() {
////                        }
////
////                        @Override
////                        public void onSuccess() {
////                        }
////                    });
//            } else {
//                viewHolder.imgPost.setVisibility(View.GONE);
//            }
//
//
//
////            viewHolder.txtName.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View view) {
////
////
////
////                }
////            });
//
//
//        }catch (Exception ex)
//        {
//            Log.d("dada2",ex.toString());
//        }
//        return convertView;
//    }


}
