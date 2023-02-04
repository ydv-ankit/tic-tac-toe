package com.ankit.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0 - X
    // 1 - O
    boolean gameActive = true;
    int activeCount = 1;
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};      // 2 - NULL

    // winning positions
    int[][] winPositions = {{0,1,2},{3,4,5},{6,7,8},    //horizontal pos
                            {0,3,6},{1,4,7},{2,5,8},    //vertical pos
                            {0,4,8},{2,4,6}};           // diagonal pos

    public void played(View view){
        ImageView img = (ImageView)view;
        int tappedImg = Integer.parseInt(img.getTag().toString());
        if ( !gameActive){
            gameReset(view);
        }
        if(gameState[tappedImg] == 2 && gameActive) {
            gameState[tappedImg] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn. Tap to play");
                activeCount +=1;
            }
            else{
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn. Tap to play");
                activeCount +=1;
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        //check if any player has won
        for(int[] winPosition: winPositions){
            if ( gameState[winPosition[0]]== gameState[winPosition[1]] &&
                    gameState[winPosition[1]]== gameState[winPosition[2]] && gameState[winPosition[0]] != 2 ){
                // somebody has won... Find out who?
                String winnerStr;
                gameActive = false;
                if(gameState[winPosition[0]]==0){
                    winnerStr = "X has Won !";
                }
                else{
                    winnerStr = "O has Won !";
                }
                // update status bar for winner
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
        // no win no lose
        if(activeCount >= 9){
            gameActive = false;
            TextView status = findViewById(R.id.status);
            status.setText("No Win No Lose");
        }
    }
    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        activeCount = 1;
        for(int i=0; i< gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}