package com.wearenicecorp.gymapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wearenicecorp.gymapp.R;

/**
 * Created by JulioAndres on 4/8/17.
 */

public class NewTrainingActivity extends AppCompatActivity {

    public static final int SELECT_EXERCISES_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_training);

        Button addExerciseButton = (Button) findViewById(R.id.addExerciseButton);
        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //se va a la pantalla donde estan todos los ejercicios

                Intent intent = new Intent(NewTrainingActivity.this, ExercisesActivity.class);
                startActivityForResult(intent, SELECT_EXERCISES_REQUEST_CODE);

            }
        });

        //al apretar +ejercicio tengo que ir a la pantalla de ejercicios, donde no va a haber ninguno
        //y va a haber un botón de agregar, en la otra pantalla formulario para agregar
        //ejercicio
        //esto lo guarda en la base de datos
        //luego vuelve y lo saca de la base de datos

    }
}
