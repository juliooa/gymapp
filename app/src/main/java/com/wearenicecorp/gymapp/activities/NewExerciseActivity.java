package com.wearenicecorp.gymapp.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.wearenicecorp.gymapp.R;
import com.wearenicecorp.gymapp.database.AppDatabaseHelper;
import com.wearenicecorp.gymapp.model.Exercise;

/**
 * Created by JulioAndres on 4/8/17.
 */

public class NewExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_exercise);

        Button addExerciseButton = (Button) findViewById(R.id.saveExerciseButton);
        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tiene que guardar el ejercicio, acá hace la logica de base de datos
                saveExerciseOnDataBase();
                finish();
            }
        });
        Spinner muscularGroupSpinner = (Spinner) findViewById(R.id.spinnerMuscleGroup);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,R.array.muscular_groups, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        muscularGroupSpinner.setAdapter(adapter);
    }

    private void saveExerciseOnDataBase() {

        String name = ((EditText)findViewById(R.id.editTextExerciseName)).getText().toString();
        String grupoMuscular = "piernas";
        int mesureType = Exercise.MESURE_TIME;

        ContentValues values = new ContentValues();

        values.put(AppDatabaseHelper.COLUMN_NAME, name);
        values.put(AppDatabaseHelper.COLUMN_MUSCULAR_GROUP, grupoMuscular);
        values.put(AppDatabaseHelper.COLUMN_MESURE_TYPE, mesureType);

        AppDatabaseHelper appDatabaseHelper = new AppDatabaseHelper(this);

        SQLiteDatabase db = appDatabaseHelper.getWritableDatabase();
        long recordId = db.insert(AppDatabaseHelper.TABLE_EXERCISES, null, values);

        Log.d("DEBUG", "recordId:" + recordId);

    }
}
