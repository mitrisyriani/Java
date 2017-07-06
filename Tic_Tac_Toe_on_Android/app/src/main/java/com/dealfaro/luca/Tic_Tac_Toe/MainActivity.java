package com.dealfaro.luca.Tic_Tac_Toe;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dealfaro.luca.class2.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int z = 0;
    TextView tt;
    Button btn;
    ImageButton a1,a2,a3,b1,b2,b3,c1,c2,c3;
    ImageButton[] bArray;
    boolean turn = true;
    // X = true ; 0 = false

    int turn_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // this links up a1 to A1 button in the layout file
        tt = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.ResetButton);
        a1 = (ImageButton) findViewById(R.id.A1);
        a2 = (ImageButton) findViewById(R.id.A2);
        a3 = (ImageButton) findViewById(R.id.A3);
        b1 = (ImageButton) findViewById(R.id.B1);
        b2 = (ImageButton) findViewById(R.id.B2);
        b3 = (ImageButton) findViewById(R.id.B3);
        c1 = (ImageButton) findViewById(R.id.C1);
        c2 = (ImageButton) findViewById(R.id.C2);
        c3 = (ImageButton) findViewById(R.id.C3);

        bArray = new ImageButton[]{a1,a2,a3,b1,b2,b3,c1,c2,c3};
        for(ImageButton b : bArray) {

            // for every Button b in the array bArray
            b1.setOnClickListener(this);


        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tt.setVisibility(View.INVISIBLE);
                turn = true;
                turn_count = 0;
                EnableDisableAllButtons(true);
            }
        });

    }

    public void onClick(View v){
        ImageButton b = (ImageButton) v;
        buttonClicked(b);

    }

    public void buttonClicked(ImageButton b){
        if(turn){

            b.setImageResource(R.drawable.cross);
            b.setTag("x");
        }
        else {
            b.setImageResource(R.drawable.aa);
            b.setTag("o");
       }
        turn_count++;
        b.setClickable(false);
        turn = !turn;

        checkForWinner();

   }

    public void checkForWinner(){
    boolean winner = false;
        if(a1.getTag() == a2.getTag() && a2.getTag() == a3.getTag())
              winner = true;
        else if(b1.getTag() == b2.getTag() && b2.getTag()== b3.getTag())
                winner = true;
        else if(c1.getTag() == c2.getTag() && c2.getTag()== c3.getTag())
                winner = true;
        else if(a1.getTag() == b1.getTag() && b1.getTag() == c1.getTag())
                winner = true;
        else if(a2.getTag() == b2.getTag() && b2.getTag() == c2.getTag())
            winner = true;
        else if(a3.getTag() == b3.getTag() && b3.getTag() == c3.getTag())
            winner = true;
        else if(a1.getTag() == b2.getTag() && b2.getTag() == c3.getTag())
            winner = true;
        else if(a3.getTag() == b2.getTag() && b2.getTag() == c1.getTag())
            winner = true;
        else if(turn_count == 9){
            tt.setText("TIE");
            tt.setVisibility(View.VISIBLE);
        }


        if(winner){
            if(turn){
                tt.setText("O WON!");
                tt.setVisibility(View.VISIBLE);
            }

            else{
                tt.setText("X WON!");
                tt.setVisibility(View.VISIBLE);
            }

            EnableDisableAllButtons(false);

        }

    }

    public void EnableDisableAllButtons(boolean enable){
        // false disable

        for(ImageButton b: bArray){
            b.setClickable(enable);
            b.setTag(z);
            z++;

            if(enable)
                 b.setImageResource(0);
        }
    }

}
