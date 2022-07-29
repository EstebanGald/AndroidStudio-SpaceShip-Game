package com.example.steve.game_proyect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.view.View.OnTouchListener;
import java.util.concurrent.ExecutorService;
import android.graphics.Rect;

public class GameActivity extends Activity {

    public ImageView blackbackground;
    public ImageView player_ship;
    public ImageView bullet_player;
    public int pos_bullet_Y = 1300;
    public Handler handler = new Handler(); //Con handler y runnable hago los loops infinitos
    public Runnable runnable1,runnable2; //Handler tambien sirve para retrasar el inicio de una funcion
    public ImageButton GoBack;

    public Enemy enemy1,enemy2,enemy3,enemy4,enemy5,enemy6; //Declaracion clase Enemy

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Todo esto me oculta la barra de notificaciones y la barra de navegación
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        if (currentapiVersion >= 19) { //Algunas de esas funciones solo funcionan para una API de nivel mayor o igual a 19
            getWindow().getDecorView().setSystemUiVisibility(flags); //Aplica los cambios

        }

        player_ship = (ImageView) findViewById(R.id.player);
        bullet_player = (ImageView) findViewById(R.id.bullet_player);

        final DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics); //Esto me obtiene el ancho y largo del celular

        final int height = displayMetrics.heightPixels; //Los guardo en estas variables
        final int width = displayMetrics.widthPixels;

        player_ship.setOnTouchListener(new OnTouchListener() { //Si el usuario toca la pantalla

            public boolean onTouch(View view, MotionEvent event) {

                int select = event.getAction();
                /* event.getAction() obtiene el evento que se ejecuto, son tres tipos: ACTION_DOWN: Si el usuario presiona
                ACTION_MOVE: Si el usuario mueve el dedo por la pantalla
                ACTION_UP: Si el usuario levanta su dedo
                 */
                switch (select) {
                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) player_ship.getLayoutParams(); //Obtengo parámetros de la imagen
                        RelativeLayout.LayoutParams wParams = (RelativeLayout.LayoutParams) bullet_player.getLayoutParams();


                        int x = (int) event.getRawX(); //Obtengo coordenadas del dedo del usuario
                        int y = (int) event.getRawY();

                        mParams.leftMargin = x - 140; //Modifico la posicion de la imagen en funcion de las coordenadas del dedo del usuario
                        mParams.topMargin = y - 200;

                        if (pos_bullet_Y == mParams.topMargin) {
                            wParams.leftMargin = mParams.leftMargin + 89;
                            /*Aqui digo que si pos_bullet_Y esta en la posicion Y de la nave, la coordenada X de la bala se ajuste a la
                            coordenada X de la nave */
                        }

                        if (pos_bullet_Y < 0) {
                            pos_bullet_Y = mParams.topMargin;
                            //Para que la bala vuelva a la imagen
                        }


                        wParams.topMargin = pos_bullet_Y; //Cambio coordenada Y de la bala

                        bullet_player.setLayoutParams(wParams); //Aplico cambios de posicion de la imagen


                        if (100 <= x && x <= width - 100 && 200 <= y && y <= height + 50) {
                            player_ship.setLayoutParams(mParams); //Aplico cambios de posicion de la imagen
                        }

                        break;

                    default:

                        break;
                }
                return true;
            }
        });


        runnable1 = new Runnable() { //Inicio loop infinito

            @Override
            public void run() {

                RelativeLayout.LayoutParams cParams = (RelativeLayout.LayoutParams) bullet_player.getLayoutParams();
                RelativeLayout.LayoutParams dParams = (RelativeLayout.LayoutParams) player_ship.getLayoutParams();

                cParams.topMargin = pos_bullet_Y;
                pos_bullet_Y -= 30;


                if (pos_bullet_Y < 0) {
                    pos_bullet_Y = dParams.topMargin;
                }

                if (pos_bullet_Y == dParams.topMargin) {
                    cParams.leftMargin = dParams.leftMargin + 89;
                }

                bullet_player.setLayoutParams(cParams);

                handler.postDelayed(runnable1,1); //Esto es necesario poner para que funcione el loop por algun motivo
            }
        };

        handler.post(runnable1); //Esto inicia el loop




        blackbackground = (ImageView) findViewById(R.id.background);

        blackbackground.setImageDrawable(getResources().getDrawable(R.drawable.space_animation)); //Obtengo el xml que se hizo para la animacion del fondo
        AnimationDrawable frames = (AnimationDrawable) blackbackground.getDrawable(); //La asigno a una variable
        frames.start(); //Inicio la animacion

        GoBack = (ImageButton) findViewById(R.id.GoBack);
        GoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            //Con esto vuelvo al menu
            public void onClick(View view) {
                Intent GoBack = new Intent(GameActivity.this,MenuActivity.class);
                startActivity(GoBack);
                finish();
            }
        });



        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //Creo 3 objetos de la clase con su respectivo movimiento y disparo

                enemy1 = new Enemy(GameActivity.this,1,800,44);
                enemy1.Movement(1200,720,800,-8);
                enemy1.Shoot();
                enemy1.Collision();
                enemy1.PlayerDies();

                enemy2 = new Enemy(GameActivity.this,2,800,234);
                enemy2.Movement(1200,420,800,-8);
                enemy2.Shoot();
                enemy2.Collision();
                enemy2.PlayerDies();

                enemy3 = new Enemy(GameActivity.this,3,800,434);
                enemy3.Movement(1200,120,800,-5);
                enemy3.Shoot();
                enemy3.Collision();
                enemy3.PlayerDies();


            }
        },2000); //Retraso el inicio del codigo en 2 segundos  con esta funcion


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //Creo 3 objetos de la clase con su respectivo movimiento y disparo

                enemy4 = new Enemy(GameActivity.this,4,-200,44);
                enemy4.Movement2(-200,620,-200,8);
                enemy4.Shoot2();
                enemy4.Collision2();
                enemy4.PlayerDies();

                enemy5 = new Enemy(GameActivity.this,5,-200,234);
                enemy5.Movement2(-200,420,-200,8);
                enemy5.Shoot2();
                enemy5.Collision2();
                enemy5.PlayerDies();

                enemy6 = new Enemy(GameActivity.this,6,-200,434);
                enemy6.Movement2(-200,120,-200,5);
                enemy6.Shoot2();
                enemy6.Collision2();
                enemy6.PlayerDies();


            }
        },7000);

    }
}












