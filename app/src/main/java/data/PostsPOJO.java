package data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sheryar Khan on 8/8/2017.
 */

public class PostsPOJO {


    private int UserID;
    private String Username;
    private Long Timestamp;
    private String PostText;
    private String Location;
    private String profilePic;
    private List<String> content_post;




    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public int getUserID() {

        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public Long getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Long timestamp) {
        Timestamp = timestamp;
    }

    public String getPostText() {
        return PostText;
    }

    public void setPostText(String postText) {
        PostText = postText;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        this.Location = location;
    }


    public PostsPOJO(int userID,String profilepic, String username, Long timestamp, String postText, String location,List<String> postcontent)
    {
        UserID = userID;
        Username = username;
        Timestamp = timestamp;
        PostText = postText;
        Location = location;
        profilePic = profilepic;
        content_post = postcontent;

    }



    public PostsPOJO()
    {

    }

    public List<String> getcontent_post() {
        return content_post;
    }

    public void setcontent_post(List<String> content_post) {
        this.content_post = content_post;
    }
}
