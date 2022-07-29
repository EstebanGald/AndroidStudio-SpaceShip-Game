package com.example.steve.game_proyect;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.graphics.Rect;

public class Enemy {

    private GameActivity gameActivity; //Con esto llamo a la clase del juego para poder encontrar las imagenes del enemigo y las balas, o si no no funciona

    private ImageView Enemy, Bullet,explosion,Gameover;
    private int finalX,Y,pos_x,speed,pos_bulletX,pos_bulletY;
    private boolean flag;
    private Handler handler = new Handler();
    private Runnable runnable1,runnable2,runnable3,runnable4;
    private Rect rect1,rect2,rect3,rect4;

    private boolean flaggy1 = false;
    private boolean flaggy2 = false;

    public Enemy(GameActivity gameActivity,int getID, int MarginLeft, int MarginTop) {

        this.gameActivity = gameActivity;
        /*Aqui elige la imagen que debe usar dependiendo del enemigo, debido a que puse 3 enemigos
          tuve que crear hacer otras 2 copias del enemigo original con sus respectivas balas
         */
        if (getID == 1) {
            Enemy = (ImageView) gameActivity.findViewById(R.id.enemy1_1);
            Bullet = (ImageView) gameActivity.findViewById(R.id.bullet_enemy1_1);
        } else if (getID == 2) {
            Enemy = (ImageView) gameActivity.findViewById(R.id.enemy1_2);
            Bullet = (ImageView) gameActivity.findViewById(R.id.bullet_enemy1_2);
        } else if (getID == 3) {
            Enemy = (ImageView) gameActivity.findViewById(R.id.enemy1_3);
            Bullet = (ImageView) gameActivity.findViewById(R.id.bullet_enemy1_3);
        } else if (getID == 4) {
            Enemy = (ImageView) gameActivity.findViewById(R.id.sec_enemy1);
            Bullet = (ImageView) gameActivity.findViewById(R.id.bull_1);
        } else if (getID == 5) {
            Enemy = (ImageView) gameActivity.findViewById(R.id.sec_enemy2);
            Bullet = (ImageView) gameActivity.findViewById(R.id.bull_2);
        } else if (getID == 6) {
            Enemy = (ImageView) gameActivity.findViewById(R.id.sec_enemy3);
            Bullet = (ImageView) gameActivity.findViewById(R.id.bull_3);
        }

        RelativeLayout.LayoutParams EnemyParams = (RelativeLayout.LayoutParams) Enemy.getLayoutParams();
        EnemyParams.leftMargin = MarginLeft;
        EnemyParams.topMargin = MarginTop;
        Enemy.setLayoutParams(EnemyParams);
    }

    public void Movement(int StartPoint,int getFinalX,int getMarginLeft,int getSpeed) {


        RelativeLayout.LayoutParams eneParams = (RelativeLayout.LayoutParams) Enemy.getLayoutParams();
        eneParams.leftMargin = StartPoint;
        Enemy.setLayoutParams(eneParams);

        finalX = getFinalX;
        pos_x = getMarginLeft;
        speed = getSpeed;

        runnable1 = new Runnable() {
            @Override
            public void run() {

                RelativeLayout.LayoutParams eParams = (RelativeLayout.LayoutParams) Enemy.getLayoutParams();
                pos_x += speed;

                if (pos_x <= finalX) {

                    eParams.leftMargin = finalX;

                } else {
                    eParams.leftMargin = pos_x;
                }

                Enemy.setLayoutParams(eParams);

                handler.postDelayed(runnable1,1);
            }
        };

        handler.post(runnable1);

    }

    public void Movement2(int StartPoint,int getFinalX,int getMarginLeft,int getSpeed) {


        RelativeLayout.LayoutParams eneParams = (RelativeLayout.LayoutParams) Enemy.getLayoutParams();
        eneParams.leftMargin = StartPoint;
        Enemy.setLayoutParams(eneParams);

        finalX = getFinalX;
        pos_x = getMarginLeft;
        speed = getSpeed;

        runnable1 = new Runnable() {
            @Override
            public void run() {

                RelativeLayout.LayoutParams eParams = (RelativeLayout.LayoutParams) Enemy.getLayoutParams();
                pos_x += speed;

                if (pos_x >= finalX) {

                    eParams.leftMargin = finalX;

                } else {
                    eParams.leftMargin = pos_x;
                }

                Enemy.setLayoutParams(eParams);

                handler.postDelayed(runnable1,1);
            }
        };

        handler.post(runnable1);

    }

    public void Shoot() {

        flag = true;

        runnable2 = new Runnable() {
            @Override
            public void run() {

                RelativeLayout.LayoutParams fParams = (RelativeLayout.LayoutParams) Enemy.getLayoutParams();
                RelativeLayout.LayoutParams sParams = (RelativeLayout.LayoutParams) Bullet.getLayoutParams();

                if (flag) {
                    pos_bulletY = fParams.topMargin + 204;
                    pos_bulletX = fParams.leftMargin + 84;
                }
                flag = false;



                sParams.topMargin = pos_bulletY;

                if (pos_bulletX <= fParams.leftMargin + 84) {
                    sParams.leftMargin = fParams.leftMargin+84;
                } else {
                    sParams.leftMargin = pos_bulletX;
                }



                pos_bulletY += 10;
                pos_bulletX += speed;

                if (pos_bulletY >= 1900) {
                    pos_bulletY = fParams.topMargin + 204;
                    pos_bulletX = fParams.leftMargin +84;
                }

                Bullet.setLayoutParams(sParams);




                handler.postDelayed(runnable2,1);
            }
        };

        handler.post(runnable2);
    }

    public void Shoot2() {

        flag = true;

        runnable2 = new Runnable() {
            @Override
            public void run() {

                RelativeLayout.LayoutParams fParams = (RelativeLayout.LayoutParams) Enemy.getLayoutParams();
                RelativeLayout.LayoutParams sParams = (RelativeLayout.LayoutParams) Bullet.getLayoutParams();

                if (flag) {
                    pos_bulletY = fParams.topMargin + 204;
                    pos_bulletX = fParams.leftMargin + 84;
                }
                flag = false;



                sParams.topMargin = pos_bulletY;

                if (pos_bulletX >= fParams.leftMargin + 84) {
                    sParams.leftMargin = fParams.leftMargin+84;
                } else {
                    sParams.leftMargin = pos_bulletX;
                }



                pos_bulletY += 10;
                pos_bulletX += speed;

                if (pos_bulletY >= 1900) {
                    pos_bulletY = fParams.topMargin + 204;
                    pos_bulletX = fParams.leftMargin +84;
                }

                Bullet.setLayoutParams(sParams);




                handler.postDelayed(runnable2,1);
            }
        };

        handler.post(runnable2);
    }

    public boolean Collision() {

        runnable3 = new Runnable() {
            @Override
            public void run() {

                rect1 = new Rect();
                Enemy.getHitRect(rect1);
                rect2 = new Rect();
                gameActivity.bullet_player.getHitRect(rect2);

                RelativeLayout.LayoutParams eParams = (RelativeLayout.LayoutParams) Enemy.getLayoutParams();
                RelativeLayout.LayoutParams ggParams = (RelativeLayout.LayoutParams) gameActivity.player_ship.getLayoutParams();


                if (Rect.intersects(rect1,rect2)) {
                    flaggy1 = true;
                    eParams.leftMargin = 1300;
                    Enemy.setLayoutParams(eParams);
                    finalX = 1300;

                    gameActivity.pos_bullet_Y = ggParams.topMargin;

                }

                handler.postDelayed(runnable3,1);

            }
        };

        handler.post(runnable3);

        return flaggy1;

    }

    public boolean Collision2() {

        runnable3 = new Runnable() {
            @Override
            public void run() {

                rect1 = new Rect();
                Enemy.getHitRect(rect1);
                rect2 = new Rect();
                gameActivity.bullet_player.getHitRect(rect2);

                RelativeLayout.LayoutParams eParams = (RelativeLayout.LayoutParams) Enemy.getLayoutParams();
                RelativeLayout.LayoutParams ggParams = (RelativeLayout.LayoutParams) gameActivity.player_ship.getLayoutParams();


                if (Rect.intersects(rect1,rect2)) {
                    flaggy1 = true;
                    eParams.leftMargin = -200;
                    Enemy.setLayoutParams(eParams);
                    finalX = -200;

                    gameActivity.pos_bullet_Y = ggParams.topMargin;

                }

                handler.postDelayed(runnable3,1);

            }
        };

        handler.post(runnable3);

        return flaggy1;

    }

    public boolean PlayerDies() {

        runnable4 = new Runnable() {
            @Override
            public void run() {

                rect3 = new Rect();
                Bullet.getHitRect(rect3);
                rect4 = new Rect();
                gameActivity.player_ship.getHitRect(rect4);

                explosion = (ImageView) gameActivity.findViewById(R.id.explosion);
                Gameover = (ImageView) gameActivity.findViewById(R.id.gameover);
                RelativeLayout.LayoutParams overParams = (RelativeLayout.LayoutParams) Gameover.getLayoutParams();

                if (Rect.intersects(rect3,rect4)) {
                    RelativeLayout.LayoutParams exParams = (RelativeLayout.LayoutParams) explosion.getLayoutParams();
                    RelativeLayout.LayoutParams plParams = (RelativeLayout.LayoutParams) gameActivity.player_ship.getLayoutParams();
                    RelativeLayout.LayoutParams buParams = (RelativeLayout.LayoutParams) Bullet.getLayoutParams();

                    exParams.leftMargin = plParams.leftMargin-350;
                    exParams.topMargin = plParams.topMargin-400;
                    buParams.leftMargin = 1000;

                    Bullet.setLayoutParams(buParams);
                    explosion.setLayoutParams(exParams);

                    explosion.setImageDrawable(gameActivity.getResources().getDrawable(R.drawable.explosion_animation));
                    AnimationDrawable Frames = (AnimationDrawable) explosion.getDrawable();
                    Frames.start();

                    overParams.leftMargin = 0;
                    Gameover.setLayoutParams(overParams);
                    flaggy2 = true;


                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent GameOver = new Intent(gameActivity, MenuActivity.class);
                            gameActivity.startActivity(GameOver);
                            gameActivity.finish();

                        }
                    },1000);
                }

                handler.postDelayed(runnable4,1);

            }
        };

        handler.post(runnable4);

        return flaggy2;
    }
}







