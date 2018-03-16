package com.example.rog.game;
import android.widget.TextView;

import com.example.rog.game.enums.Direction;
import com.example.rog.game.enums.GameState;
import com.example.rog.game.enums.ObjectsTypes;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ROG on 13.03.2018.
 */

class GameEngine {
    static final int GameWidth = 30;
    static final int GameHeight = 45;
    private int POINTS;
    private int HP = 3;



    private ArrayList<Coordinate> walls = new ArrayList<>();
    private Coordinate player;
    private Coordinate point;
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private Enemy enemy4;
    private Enemy enemy5;
    private Random random = new Random();


    private Direction currentDirection = Direction.East;
    private GameState currentGameState = GameState.Running;

    void initGame(){
        AddWalls();
        AddPlayer();
        AddPiont();
        enemy1 = new Enemy(walls);
        enemy2 = new Enemy(walls);
        enemy3 = new Enemy(walls);
        enemy4 = new Enemy(walls);
        enemy5 = new Enemy(walls);
    }


    void UpdateDirection(Direction newDirection){
        if(Math.abs(newDirection.ordinal() -  currentDirection.ordinal()) % 2 ==1){
            currentDirection = newDirection;
        }
    }

    void UpDate(){
        // Update player
        switch (currentDirection) {
            case North:
                UpdatePlayer(0,1);
                break;
            case East:
                UpdatePlayer(1,0);
                break;
            case South:
                UpdatePlayer(0,-1);
                break;
            case West:
                UpdatePlayer(-1,0);
                break;
        }
        //END GAME
        for(Coordinate w :walls){
            if(player.equals(w)){
                currentGameState = GameState.Lost;
            }
        }
        //HP LOST
        if(player.equals(enemy1.getEnemy())|| player.equals(enemy2.getEnemy())|| player.equals(enemy3.getEnemy())|| player.equals(enemy4.getEnemy())|| player.equals(enemy5.getEnemy())){
            HP = HP-1;
            if(HP==0){
                currentGameState = GameState.Lost;
            }
        }

        //add Mechnic to enemys
        enemy1.enemysMechanic();
        if(POINTS>1){
            enemy2.enemysMechanic();
        }
        if(POINTS>4){
            enemy3.enemysMechanic();
        }
        if(POINTS>6){
            enemy4.enemysMechanic();
        }
        if(POINTS>8){
            enemy5.enemysMechanic();
        }

        if(player.equals(point)){
            POINTS++;
            AddPiont();
        }
    }
    ObjectsTypes[][] getMap(){

            ObjectsTypes[][] map = new ObjectsTypes[GameWidth][GameHeight];
            //add objects to map
            //empty Cooridinate
            for(int x=0;x<GameWidth;x++){
                for(int y=0;y<GameHeight;y++){
                    map[x][y] =ObjectsTypes.Nothing;
                }
            }
            //walls
            for(Coordinate wall:walls){
                map[wall.getX()][wall.getY()] = ObjectsTypes.Wall;
            }


            //enemys
            map[enemy1.getEnemy().getX()][enemy1.getEnemy().getY()] = ObjectsTypes.Enemy;
            if(POINTS>1)map[enemy2.getEnemy().getX()][enemy2.getEnemy().getY()]= ObjectsTypes.Enemy;
            if(POINTS>4)map[enemy3.getEnemy().getX()][enemy3.getEnemy().getY()]= ObjectsTypes.Enemy;
            if(POINTS>6)map[enemy4.getEnemy().getX()][enemy4.getEnemy().getY()]= ObjectsTypes.Enemy;
            if(POINTS>8)map[enemy5.getEnemy().getX()][enemy5.getEnemy().getY()]= ObjectsTypes.Enemy;
            //player
            map[player.getX()][player.getY()] = ObjectsTypes.Player;
            //point
            map[point.getX()][point.getY()] = ObjectsTypes.Point;


            return map;
    }

    private void UpdatePlayer(int x, int y){
        player.setX(player.getX() +x);
        player.setY(player.getY() +y);
    }

    private void AddWalls() {
        //Top and Bottom
        for(int x=0;x<GameWidth;x++){
            walls.add(new Coordinate(x,0));

        }
        for(int x=0;x<GameWidth;x++){
            walls.add(new Coordinate(x,GameHeight-1));
        }
        // Left and Right
        for(int y=0;y<GameHeight;y++) {
            walls.add(new Coordinate(0, y));

        }
        for(int y=0;y<GameHeight;y++) {

            walls.add(new Coordinate(GameWidth - 1, y));
        }
    }

    private void AddPlayer(){
            player = new Coordinate(GameWidth/2,GameHeight/2);
    }


    private void AddPiont(){
        int x = random.nextInt(GameWidth);
        int y = random.nextInt(GameHeight);
        point = new Coordinate(x,y);
        while(walls.contains(point)){
            AddPiont();
        }
    }


    GameState getCurrentGameState() {
        return currentGameState;
    }

    int getPOINTS() {
        return POINTS;
    }
    int getHP(){ return HP;}

}
