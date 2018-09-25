package com.example.mitri.project4;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView car, lane0, lane1, lane2, bomb;
    int schigh , scwidth;
    int speed;
    private float accelerometer;
    private float dila;
    Random rand = new Random();
    int randX, randY;
    int currentdistance, highscore_distance ;
    private TextView Speed, High, Speed2;
    String speedText, speedText2, highscore;


    @Override
    public View findViewById(int id) {
        return super.findViewById(id);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        car = (ImageView) findViewById(R.id.car);
        lane0 = (ImageView) findViewById(R.id.lane0);
        lane1 = (ImageView) findViewById(R.id.lane1);
        lane2 = (ImageView) findViewById(R.id.lane2);
        bomb = (ImageView) findViewById(R.id.bomb);
        scwidth = size.x;
        schigh = size.y;

        lane0.setY(0);
        lane1.setY(schigh/2);
        lane2.setY(-schigh/2);
        speed = 20;

        randX = rand.nextInt(scwidth);
        bomb.setX(randX);

        if(randX<120) {
            randX=randX -120;
        }

        randY = rand.nextInt(2000);

        Speed = (TextView) findViewById(R.id.speed);
        Speed2 = (TextView) findViewById(R.id.speed2);

        High = (TextView) findViewById(R.id.high);

        ((SensorManager)getSystemService(Context.SENSOR_SERVICE)).registerListener(
                new SensorEventListener() {

                    @Override
                    public void onSensorChanged(SensorEvent event) {

                        dila = event.values[0];
                        if(dila<=10 && dila>= 3)
                            if(car.getX() >= 60)
                                car.setX(car.getX() - 60);


                        if(dila<3 && dila>= 1)
                            if(car.getX() >= 40)
                                car.setX(car.getX() - 40);

                        if(dila<-1 && dila>= -3)
                            if(car.getX() <= scwidth-160)
                                car.setX(car.getX() + 40);

                        if(dila<-3 && dila>= -10)
                            if(car.getX() <= scwidth-160)
                                car.setX(car.getX() + 60);



                        accelerometer = event.values[1];
                        if(accelerometer <=0 && accelerometer < 3)
                            accelerometer = 2;

                        if(accelerometer >= 3 && accelerometer < 5)
                            accelerometer = 1;

                        if(accelerometer >= 5 && accelerometer < 7)
                            accelerometer = 0;

                        if(accelerometer >= 7 && accelerometer < 8.5)
                            accelerometer = -1;


                        if(accelerometer >= 8.5 && accelerometer <=10)
                            accelerometer = -2;


                        speed+= accelerometer;
                        lane0.setY(lane0.getY() + speed);
                        lane1.setY(lane1.getY() + speed);
                        lane2.setY(lane2.getY() + speed);
                        bomb.setY(bomb.getY() + speed);

                        if(lane0.getY() >= schigh/2){
                            lane0.setY(0);
                            lane1.setY(schigh/2);
                            lane2.setY(-schigh/2);
                        }
                        if (bomb.getY() >= schigh ){

                            randX = rand.nextInt(scwidth);
                            if(randX > scwidth/2) {
                                randX=randX - scwidth/8;
                            }
                            randY = rand.nextInt(2000);
                            bomb.setY(randY-2000);
                            bomb.setX(randX);

                        }

                        currentdistance+=speed;

                        speedText = "Distance: " + String.valueOf(currentdistance/30) + " meters";
                        highscore = "HighScore: " + String.valueOf(highscore_distance) + " meters";
                        speedText2 = "Speed: " + String.valueOf(speed*2) + " mph";

                        Speed.setText(speedText);
                        Speed2.setText(speedText2);
                        High.setText(highscore);


                        if(highscore_distance<= currentdistance/30)
                            highscore_distance = currentdistance/30;


                        if ((bomb.getY() < car.getY() && car.getY() < bomb.getY() +120) && (bomb.getX()< car.getX()+90  && car.getX() < bomb.getX() +210)){
                            currentdistance= 0;
                            Toast.makeText(MainActivity.this, "Game Over! Try again", Toast.LENGTH_SHORT ).show();

                        }

                        if(speed< 20){
                            speed = 20;
                        }
                        if(speed> 60){
                            speed = 60;
                        }
                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int accuracy) {

                    }
                },
                ((SensorManager)getSystemService(Context.SENSOR_SERVICE))
                        .getSensorList(Sensor.TYPE_ACCELEROMETER).get(0),
                SensorManager.SENSOR_DELAY_NORMAL);



    }



}
