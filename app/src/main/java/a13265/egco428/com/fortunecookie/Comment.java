package a13265.egco428.com.fortunecookie;

/**
 * Created by USER on 6/11/2559.
 */
public class Comment {
    private String result;
    private String time;
    private String image;

    private long id;
    public long getId(){return id;}
    public void setId(long id){this.id = id;}

    public Comment(long id, String image, String result, String time){
        this.id = id;
        this.result = result;
        this.time = time;
        this.image = image;
    }

    public String getResult(){return result;}
    public String getTime(){return time;}
    public String getImage(){return image;}
}
