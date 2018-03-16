package com.example.rog.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.rog.game.enums.Direction;
import com.example.rog.game.enums.GameState;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private GameEngine gameEngine;
    private GameView gameView;
    private final Handler handler = new Handler();
    private  final long updateDeley = 70;//70
    private  float prevX, prevY;
    public static Activity fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //for finish activity
        fa =this;

        gameEngine = new GameEngine();
        gameEngine.initGame();


        gameView = findViewById(R.id.gameView);
        gameView.setOnTouchListener(this);

        startUpdateHandler();
    }

    private void startUpdateHandler(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameEngine.UpDate();

                if(gameEngine.getCurrentGameState() == GameState.Running){
                    handler.postDelayed(this,updateDeley);
                }
                if(gameEngine.getCurrentGameState()==GameState.Lost){
                    messageOnGameLost();
                }
                //SHOW POINTS AND HP in Title
                setTitle("POINTS: " + gameEngine.getPOINTS()+"  HP: "+gameEngine.getHP());
                gameView.setGameViewMap(gameEngine.getMap());
                gameView.invalidate();
            }
        },updateDeley);
    }
    private void messageOnGameLost(){
        startActivity(new Intent(this,PopUp.class).putExtra("points",gameEngine.getPOINTS()));
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                prevX = event.getX();
                prevY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                float newX = event.getX();
                float newY = event.getY();
            //Calculate where we swiped
            if(Math.abs(newX - prevX)>Math.abs(newY-prevY)){
                //LEFT RIGHT
                if(newX>prevX){
                    //Right
                    gameEngine.UpdateDirection(Direction.East);
                }else {
                    //Left
                    gameEngine.UpdateDirection(Direction.West);
                }
            }else {
                if(newY>prevY){
                    //Up
                    gameEngine.UpdateDirection(Direction.North);
                }else{
                    //Down
                    gameEngine.UpdateDirection(Direction.South);
                }
            }
        }
        return true;
    }
}
