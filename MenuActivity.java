package com.example.steve.game_proyect;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MenuActivity extends AppCompatActivity {

    ImageButton play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_main);

        play = (ImageButton) findViewById(R.id.GoToPlay);

        play.setOnClickListener(new View.OnClickListener() { //Evento Click, se ejecuta la funcion
            @Override
            public void onClick(View view) {
                Intent GoToGame = new Intent(MenuActivity.this,GameActivity.class); //Cambio de Actividad
                startActivityForResult(GoToGame,0); // Este inicia la otra actividad, esperando un resultado, que seria el score
                finish();
            }
        });
    }
}
