package a13265.egco428.com.fortunecookie;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by USER on 6/11/2559.
 */
public class CustomAdapter extends ArrayAdapter<Comment> {
    Context context;
    List<Comment> objects;

    public CustomAdapter(Context context, int resource, List<Comment> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Comment comment = objects.get(position);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE); //link with interface
        View view = inflater.inflate(R.layout.list,null);

        TextView txt = (TextView)view.findViewById(R.id.fortuneresult);
        txt.setText(comment.getResult());

        TextView txtDT = (TextView)view.findViewById(R.id.date);
        txtDT.setText(comment.getTime());

        ImageView image = (ImageView)view.findViewById(R.id.imageshow);
        int res = context.getResources().getIdentifier(comment.getImage(),"drawable",context.getPackageName());
        image.setImageResource(res);
        if(comment.getImage().equals("opened_cookie_2")||comment.getImage().equals("opened_cookie_4")){
            txt.setTextColor(Color.parseColor("#FFFFC150"));
        }
        else{
            txt.setTextColor(Color.parseColor("#FF59AEF9"));
        }
        return view;
    }

}

