package com.example.rog.game; /**
 * Created by ROG on 13.03.2018.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.rog.game.enums.ObjectsTypes;

public class GameView extends View {
    private Paint mPaint = new Paint();
    private ObjectsTypes gameViewMap[][];

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setGameViewMap(ObjectsTypes[][] map){
        this.gameViewMap=map;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(gameViewMap !=null){
            float titleSizeX = canvas.getWidth()/gameViewMap.length;
            float titleSizeY = canvas.getHeight()/gameViewMap[0].length;

            float circleSize = Math.min(titleSizeX,titleSizeY)/2;

            for (int x=0;x<gameViewMap.length;x++){
                for(int y=0;y<gameViewMap[x].length;y++){
                    switch (gameViewMap[x][y]){
                        case Nothing:
                            mPaint.setColor(Color.WHITE);
                            break;
                        case Wall:
                            mPaint.setColor(Color.RED);
                            break;
                        case Enemy:
                            mPaint.setColor(Color.MAGENTA);
                            break;
                        case Point:
                            mPaint.setColor(Color.GREEN);
                            break;
                        case Player:
                            mPaint.setColor(Color.DKGRAY);
                            break;
                    }
                    canvas.drawCircle(x * titleSizeX + titleSizeX/2f +circleSize/2,y*titleSizeY + titleSizeY/2f + circleSize/2,circleSize,mPaint);

                }
            }

        }
    }
}
