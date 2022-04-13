package com.example.shocobangame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Buttons
    private ImageView[][]Matrix = new ImageView[8][8];
    private ImageView background;
    private Button ResetLevel, NextLevel;
    private TableLayout tableLayout;
    int man_i, man_j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting ID's
        setID();
        setButtonListeners();
        StringReader();

        background.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                KeyUp();
            }
            public void onSwipeRight() {
                KeyRight();
            }
            public void onSwipeLeft() {
                KeyLeft();
            }
            public void onSwipeBottom() {
                KeyDown();
            }
        });

    }

    // Setting buttons / images ID - recognise the resource
    private void setID(){
        // Buttons
        ResetLevel = findViewById(R.id.ResetLevelButton);
        NextLevel = findViewById(R.id.NextLevelButton);

        // Layouts
        tableLayout = findViewById(R.id.tableLayout);
        background = findViewById(R.id.background);

        // Row 1
        Matrix[0][0] = findViewById(R.id.imageView11);
        Matrix[0][1] = findViewById(R.id.imageView12);
        Matrix[0][2] = findViewById(R.id.imageView13);
        Matrix[0][3] = findViewById(R.id.imageView14);
        Matrix[0][4] = findViewById(R.id.imageView15);
        Matrix[0][5] = findViewById(R.id.imageView16);
        Matrix[0][6] = findViewById(R.id.imageView17);
        Matrix[0][7] = findViewById(R.id.imageView18);

        // Row 2
        Matrix[1][0] = findViewById(R.id.imageView21);
        Matrix[1][1] = findViewById(R.id.imageView22);
        Matrix[1][2] = findViewById(R.id.imageView23);
        Matrix[1][3] = findViewById(R.id.imageView24);
        Matrix[1][4] = findViewById(R.id.imageView25);
        Matrix[1][5] = findViewById(R.id.imageView26);
        Matrix[1][6] = findViewById(R.id.imageView27);
        Matrix[1][7] = findViewById(R.id.imageView28);

        // Row 3
        Matrix[2][0] = findViewById(R.id.imageView31);
        Matrix[2][1] = findViewById(R.id.imageView32);
        Matrix[2][2] = findViewById(R.id.imageView33);
        Matrix[2][3] = findViewById(R.id.imageView34);
        Matrix[2][4] = findViewById(R.id.imageView35);
        Matrix[2][5] = findViewById(R.id.imageView36);
        Matrix[2][6] = findViewById(R.id.imageView37);
        Matrix[2][7] = findViewById(R.id.imageView38);

        // Row 4
        Matrix[3][0] = findViewById(R.id.imageView41);
        Matrix[3][1] = findViewById(R.id.imageView42);
        Matrix[3][2] = findViewById(R.id.imageView43);
        Matrix[3][3] = findViewById(R.id.imageView44);
        Matrix[3][4] = findViewById(R.id.imageView45);
        Matrix[3][5] = findViewById(R.id.imageView46);
        Matrix[3][6] = findViewById(R.id.imageView47);
        Matrix[3][7] = findViewById(R.id.imageView48);

        // Row 5
        Matrix[4][0] = findViewById(R.id.imageView51);
        Matrix[4][1] = findViewById(R.id.imageView52);
        Matrix[4][2] = findViewById(R.id.imageView53);
        Matrix[4][3] = findViewById(R.id.imageView54);
        Matrix[4][4] = findViewById(R.id.imageView55);
        Matrix[4][5] = findViewById(R.id.imageView56);
        Matrix[4][6] = findViewById(R.id.imageView57);
        Matrix[4][7] = findViewById(R.id.imageView58);

        // Row 6
        Matrix[5][0] = findViewById(R.id.imageView61);
        Matrix[5][1] = findViewById(R.id.imageView62);
        Matrix[5][2] = findViewById(R.id.imageView63);
        Matrix[5][3] = findViewById(R.id.imageView64);
        Matrix[5][4] = findViewById(R.id.imageView65);
        Matrix[5][5] = findViewById(R.id.imageView66);
        Matrix[5][6] = findViewById(R.id.imageView67);
        Matrix[5][7] = findViewById(R.id.imageView68);

        // Row 7
        Matrix[6][0] = findViewById(R.id.imageView71);
        Matrix[6][1] = findViewById(R.id.imageView72);
        Matrix[6][2] = findViewById(R.id.imageView73);
        Matrix[6][3] = findViewById(R.id.imageView74);
        Matrix[6][4] = findViewById(R.id.imageView75);
        Matrix[6][5] = findViewById(R.id.imageView76);
        Matrix[6][6] = findViewById(R.id.imageView77);
        Matrix[6][7] = findViewById(R.id.imageView78);

        // Row 8
        Matrix[7][0] = findViewById(R.id.imageView81);
        Matrix[7][1] = findViewById(R.id.imageView82);
        Matrix[7][2] = findViewById(R.id.imageView83);
        Matrix[7][3] = findViewById(R.id.imageView84);
        Matrix[7][4] = findViewById(R.id.imageView85);
        Matrix[7][5] = findViewById(R.id.imageView86);
        Matrix[7][6] = findViewById(R.id.imageView87);
        Matrix[7][7] = findViewById(R.id.imageView88);

    }

    // What buttons should do - can call a method here
    private void setButtonListeners(){
        ResetLevel.setOnClickListener(view -> {
            //reset LV Method
        });
        NextLevel.setOnClickListener(view -> {
            TestingServer();
        });
    }

    // Methods

    public void StringReader(){
        String msg = "1100016011004666100401561011410100040040000100001111113011111111";
        int count =0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j <8 ; j++) {
                if(msg.charAt(count) == '0') Matrix[i][j].setImageResource(R.drawable.teren);
                else if(msg.charAt(count) == '1') Matrix[i][j].setImageResource(R.drawable.wall1);
                else if(msg.charAt(count) == '2') Matrix[i][j].setImageResource(R.drawable.wall3);
                else if(msg.charAt(count) == '3') Matrix[i][j].setImageResource(R.drawable.ghost);
                else if(msg.charAt(count) == '4') Matrix[i][j].setImageResource(R.drawable.crateopen);
                else if(msg.charAt(count) == '5') Matrix[i][j].setImageResource(R.drawable.crateclosed);
                else if(msg.charAt(count) == '6') Matrix[i][j].setImageResource(R.drawable.flag);
                count++;

            }
        }
    }

    private void TestingServer(){
        // Trimitere mesaj
        try{
            String lv = "lv";
            ClientThread thread = new ClientThread(lv);
            thread.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Toast.makeText(this, "Message sent!", Toast.LENGTH_LONG).show();
    }

    public void KeyRight(){
        Toast.makeText(this, "Swiped right", Toast.LENGTH_LONG).show();
    }

    public void KeyLeft(){
        Toast.makeText(this, "Swiped letf", Toast.LENGTH_LONG).show();
    }

    public void KeyDown(){
        Toast.makeText(this, "Swiped down", Toast.LENGTH_LONG).show();
    }

    public void KeyUp(){
        Toast.makeText(this, "Swiped up", Toast.LENGTH_LONG).show();
    }

}