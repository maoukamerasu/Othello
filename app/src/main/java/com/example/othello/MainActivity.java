package com.example.othello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    public int gamearea[][]={
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,2,1,0,0,0},
            {0,0,0,1,2,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0}
    };
    public int reset[][]={
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,2,1,0,0,0},
            {0,0,0,1,2,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0}
    };
    public int decision[][]={
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0}
    };
    int other;
    int new_x,new_y;
    int side;
    int black;
    int white;
    SampleView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button resetbutton;
        super.onCreate(savedInstanceState);
        LinearLayout ll = new LinearLayout(this);
        setContentView(ll);
        sv = new SampleView(this);
        ll.addView(sv);
        resetbutton=new Button(this);
        resetbutton.setText("リセット");
        ll.addView(resetbutton);
    }
    class SampleView extends View{
        float mousex,mousey;
        Paint p;
        int player=1;
        boolean gameover=false;
        public SampleView(Context cn)
        {
            super(cn);
            p = new Paint();
        }
        public boolean onTouchEvent(MotionEvent e){
            mousex = e.getX();
            mousey = e.getY();
            for(int x=0;x<8;x++) {
                for(int y=0;y<8;y++) {
                    if (mousex > 100+x*150 && mousex < 250+x*150 && mousey > 100+y*150 && mousey < 250+y*150&&gamearea[y][x]==0&&decision[y][x]==1&&gameover==false) {
                        gamearea[y][x] = player;
                        for (side = 0; side < 8; side++) {
                            other = 0;
                            new_x = x;
                            new_y = y;
                            while (new_x < 8 && new_y < 8 && new_x >= 0 && new_y >=0) {
                                switch (side) {
                                    case 0:
                                        new_x = x + other + 1;
                                        break;
                                    case 1:
                                        new_x = x - other - 1;
                                        break;
                                    case 2:
                                        new_y = y + other + 1;
                                        break;
                                    case 3:
                                        new_y = y - other - 1;
                                        break;
                                    case 4:
                                        new_x = x + other + 1;
                                        new_y = y + other + 1;
                                        break;
                                    case 5:
                                        new_x = x + other + 1;
                                        new_y = y - other - 1;
                                        break;
                                    case 6:
                                        new_x = x - other - 1;
                                        new_y = y + other + 1;
                                        break;
                                    case 7:
                                        new_x = x - other - 1;
                                        new_y = y - other - 1;
                                        break;
                                }
                                if (new_x < 8 && new_y < 8 && new_x >= 0 && new_y >= 0){
                                    if (gamearea[new_y][new_x] != player && gamearea[new_y][new_x] != 0)
                                        other += 1;
                                    else if (gamearea[new_y][new_x] == player && other > 0) {
                                        break;
                                    } else {
                                        other = 0;
                                        break;
                                    }
                            }else
                                other=0;
                            }
                            for (int change = 0; change <= other; change++) {
                                switch (side) {
                                    case 0:
                                        gamearea[y][x + change] = player;
                                        break;
                                    case 1:
                                        gamearea[y][x - change] = player;
                                        break;
                                    case 2:
                                        gamearea[y + change][x] = player;
                                        break;
                                    case 3:
                                        gamearea[y - change][x] = player;
                                        break;
                                    case 4:
                                        gamearea[y + change][x + change] = player;
                                        break;
                                    case 5:
                                        gamearea[y - change][x + change] = player;
                                        break;
                                    case 6:
                                        gamearea[y + change][x - change] = player;
                                        break;
                                    case 7:
                                        gamearea[y - change][x - change] = player;
                                        break;
                                }
                            }
                        }
                        switch (player) {
                            case 1:
                                player = 2;
                                break;
                            case 2:
                                player = 1;
                                break;
                        }
                    }
                    }
                }
            this.invalidate();
            return true;
        }
        protected void onDraw(Canvas cs)
        {
            super.onDraw(cs);
            //背景
            p.setColor(Color.GREEN);
            p.setARGB(255,122,188,122);
            p.setStyle(Paint.Style.FILL);
            cs.drawRect(100,100,1300,1300,p);
            p.setColor(Color.WHITE);
            p.setStrokeWidth(5);
            for(int x=0; x < 9;x++) {
                cs.drawLine(100, 100+x*150, 1300, 100+x*150, p);
                cs.drawLine(100+x*150,100,  100+x*150,1300,  p);
            }
            p.setARGB(0,166,0,178);
            p.setStyle(Paint.Style.FILL);
            //判定の初期化
            for(int x=0;x<8;x++){
                for(int y=0;y<8;y++){
                    decision[y][x]=0;
                }
            }
            //判定の生成
            for(int x=0;x<8;x++){
                for(int y=0;y<8;y++){
                    if(gamearea[y][x]==player){
                        for(side=0;side<8;side++) {
                            other=0;
                            new_x=x;
                            new_y=y;
                            while (new_x < 8 && new_y < 8&&new_x>=0&&new_y>=0) {
                                switch (side) {
                                    case 0:
                                        new_x = x + other + 1;
                                        break;
                                    case 1:
                                        new_x = x - other - 1;
                                        break;
                                    case 2:
                                        new_y = y + other + 1;
                                        break;
                                    case 3:
                                        new_y = y - other - 1;
                                        break;
                                    case 4:
                                        new_x = x + other + 1;
                                        new_y = y + other + 1;
                                        break;
                                    case 5:
                                        new_x = x + other + 1;
                                        new_y = y - other - 1;
                                        break;
                                    case 6:
                                        new_x = x - other - 1;
                                        new_y = y + other + 1;
                                        break;
                                    case 7:
                                        new_x = x - other - 1;
                                        new_y = y - other - 1;
                                        break;
                                }
                                if (new_x < 8 && new_y < 8 && new_x >= 0 && new_y >= 0) {
                                    if (gamearea[new_y][new_x] != player && gamearea[new_y][new_x] != 0)
                                        other += 1;
                                    else if (gamearea[new_y][new_x] == 0 && other > 0) {
                                        decision[new_y][new_x] = 1;
                                        break;
                                    } else
                                        break;
                                }
                            }
                        }
                    }
                }
            }
            //判定描画
            for(int x=0;x<8;x++) {
                for(int y=0;y<8;y++) {
                    if(decision[y][x]==1) {
                        p.setColor(Color.RED);
                        p.setStyle(Paint.Style.FILL);
                        cs.drawRect(100 + x * 150, 100+ y * 150, 250 + x * 150, 250+ y * 150, p);
                    }
                }
            }
            //石
            for(int x=0;x<8;x++) {
                for(int y=0;y<8;y++) {
                    if(gamearea[y][x]==1) {
                        p.setColor(Color.BLACK);
                        p.setStyle(Paint.Style.FILL);
                        cs.drawCircle(175 + x * 150, 175 + y * 150, 50, p);
                    }
                    else if(gamearea[y][x]==2){
                        p.setColor(Color.WHITE);
                        p.setStyle(Paint.Style.FILL);
                        cs.drawCircle(175 + x * 150, 175 + y * 150, 50, p);
                    }
                }
            }
            p.setColor(Color.BLACK);
        }
    }
}