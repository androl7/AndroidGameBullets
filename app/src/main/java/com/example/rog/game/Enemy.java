package com.example.rog.game;

import com.example.rog.game.enums.GameState;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ROG on 14.03.2018.
 */

class Enemy {
    private Random random = new Random();
    private String enemyOrient ="";
    private ArrayList<Coordinate> walls;
    private Coordinate enemy;

    Enemy(ArrayList<Coordinate> walls ){
        this.walls = walls;
        int randomWall = random.nextInt(walls.size()-1);
        this.enemy = new Coordinate(walls.get(randomWall).getX(),walls.get(randomWall).getY());
    }

    void enemysMechanic(){
        for(int i = 1; i< GameEngine.GameWidth; i++){
            if(enemy.equals(walls.get(i))){
                enemyOrient="UP";
            }
        }
        for(int i = GameEngine.GameWidth +1; i< GameEngine.GameWidth *2; i++){
            if(enemy.equals(walls.get(i))){
                enemyOrient="DOWN";
            }
        }
        for(int i = GameEngine.GameWidth *2+1; i< GameEngine.GameWidth *2+ GameEngine.GameHeight; i++){
            if(enemy.equals(walls.get(i))){
                enemyOrient="LEFT";
            }
        }
        for(int i = GameEngine.GameWidth *2+ GameEngine.GameHeight +1; i< GameEngine.GameWidth *2+ GameEngine.GameHeight + GameEngine.GameHeight; i++){
            if(enemy.equals(walls.get(i))){
                enemyOrient="RIGHT";
            }
        }
        switch (enemyOrient){
            case "UP":
                UpdateEnemy(0,1,enemy);
                if(enemy.getY()== GameEngine.GameHeight -1) changeEnemy();
                break;
            case "DOWN":
                UpdateEnemy(0,-1,enemy);
                if(enemy.getY()==1) changeEnemy();
                break;
            case "LEFT":
                UpdateEnemy(1,0,enemy);
                if(enemy.getX()== GameEngine.GameWidth -1) changeEnemy();
                break;
            case "RIGHT":
                UpdateEnemy(-1,0,enemy);
                if(enemy.getX()==1) changeEnemy();
                break;
        }
    }

    private void UpdateEnemy(int x, int y, Coordinate enemy){
        enemy.setX(enemy.getX() +x);
        enemy.setY(enemy.getY() +y);
    }

    Coordinate getEnemy() {
        return enemy;
    }
    private void changeEnemy(){
        int randomWall = random.nextInt(walls.size()-1);
        enemy = new Coordinate(walls.get(randomWall).getX(),walls.get(randomWall).getY());
    }

}
