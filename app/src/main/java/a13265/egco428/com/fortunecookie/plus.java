package a13265.egco428.com.fortunecookie;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by USER on 6/11/2559.
 */
public class plus extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private long lastUpdate;
    boolean flag=false;
    Intent intent;
    String name;
    String current;
    Button ss;
    String[] textId = {"You will get A","You're Lucky","Don't Panic","Something surprise you today","Work Harder"};
    public int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plus);
        ss = (Button) findViewById(R.id.ssBtn);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();

        final Button ss = (Button)findViewById(R.id.ssBtn);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Shake your phone", Toast.LENGTH_SHORT).show();
                flag = true;
                ss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent();
                        intent.putExtra(MainActivity.image, name);
                        intent.putExtra(MainActivity.result, textId[i]);
                        intent.putExtra(MainActivity.time, current);
                        setResult(RESULT_OK, intent);
                        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            }
        });

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        long actualTime = System.currentTimeMillis();
        if(flag==true) {
            if (accelationSquareRoot >= 2) {
                if (actualTime - lastUpdate < 2500) {
                    Button ss = (Button)findViewById(R.id.ssBtn);
                    ss.setText("Shaking!");
                    return;
                }
                ImageView resultImage = (ImageView) findViewById(R.id.imageResult);
                TextView result = (TextView) findViewById(R.id.result);
                TextView date = (TextView)findViewById(R.id.date);
                Random random = new Random();
                i = random.nextInt(5);
                name = "opened_cookie_" + i;
                int res = getResources().getIdentifier(name, "drawable", getPackageName());
                resultImage.setImageResource(res);
                result.setText("Result:" + textId[i]);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                current = dateFormat.format(new Date());
                date.setText("Date: " + current);
                ss.setText("Save");
                if (i == 2|| i == 4) {
                    result.setTextColor(Color.parseColor("#FFFFC150"));
                }
                lastUpdate = actualTime;
                flag = false;
            }
        }
    }

    @Override
    public  void onAccuracyChanged(Sensor sensor,int accuracy){

    }
    @Override
    protected  void onResume(){
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}
