package a13265.egco428.com.fortunecookie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected List<Comment> data = new ArrayList<>();
    public static final int secret = 7777;
    ArrayAdapter<Comment> adapter;
    private CommentDataSource commentDataSource;
    public static final long id = 1;
    public static final String result = "result";
    public static final String time = "time";
    public static final String image = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView)findViewById(R.id.listView);
        commentDataSource = new CommentDataSource(this);
        commentDataSource.open();
        data = commentDataSource.getAllComments();
        adapter = new CustomAdapter(this,0,data);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, final int position, long id) {
                final Comment dataDelete = (Comment)adapter.getItem(position);
                commentDataSource.deleteFortuneResult(dataDelete);
                view.animate().setDuration(1000).alpha(0).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        adapter.remove(dataDelete);
                        adapter.notifyDataSetChanged();
                        view.setAlpha(1);
                    }
                });
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == secret) {
            if(resultCode == RESULT_OK){
                commentDataSource.open();
                String getImage = data.getStringExtra(image);
                String getResult = data.getStringExtra(result);
                String getTime = data.getStringExtra(time);
                Comment result = commentDataSource.createMessage(getImage,getResult,getTime);
                adapter.add(result);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main_action,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, plus.class);
        startActivityForResult(intent, secret);
        return true;
    }

    @Override
    protected void onResume(){
        commentDataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause(){
        commentDataSource.close();
        super.onPause();
    }

}
