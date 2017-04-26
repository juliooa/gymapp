package com.wearenicecorp.gymapp.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wearenicecorp.gymapp.R;
import com.wearenicecorp.gymapp.fragments.ExerciseDetailFragment;
import com.wearenicecorp.gymapp.fragments.ExercisesListFragment;
import com.wearenicecorp.gymapp.model.Exercise;

import java.util.ArrayList;

/**
 * Created by JulioAndres on 4/8/17.
 */

public class NewTrainingActivity extends AppCompatActivity implements ExercisesListFragment.ShowDetail {

    public static final int SELECT_EXERCISES_REQUEST_CODE = 2;

    ArrayList<Exercise> exercisesOfTraining = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Exercise exercise = new Exercise();
        exercise.setName("Sentadillas");
        exercise.setMuscularGroup("piernas");
        exercisesOfTraining.add(exercise);
        Exercise exercise2 = new Exercise();
        exercise2.setName("Mancuernas 5K");
        exercise2.setMuscularGroup("Biceps");
        exercisesOfTraining.add(exercise2);

        setContentView(R.layout.activity_new_training);

        Spinner muscularGroupSpinner = (Spinner) findViewById(R.id.spinnerMuscleGroup);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,R.array.muscular_groups, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        muscularGroupSpinner.setAdapter(adapter);

        Button addExerciseButton = (Button) findViewById(R.id.addExerciseButton);
        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //se va a la pantalla donde estan todos los ejercicios
                Intent intent = new Intent(NewTrainingActivity.this, ExercisesActivity.class);
                startActivityForResult(intent, SELECT_EXERCISES_REQUEST_CODE);
            }
        });

        Button backToList = (Button) findViewById(R.id.backToList);
        backToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showExercisesList(false);
            }
        });

        showExercisesList(true);
    }

    private void showExercisesList(boolean firstTime) {

        ExercisesListFragment exercisesListFragment = new ExercisesListFragment();

        Bundle bundl = new Bundle();
        bundl.putSerializable("exercisesList", exercisesOfTraining);
        exercisesListFragment.setArguments(bundl);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_left,
                R.anim.slide_out_to_right);
        if (firstTime) {
            fragmentTransaction.add(R.id.fragment_container, exercisesListFragment);
        } else {

            fragmentTransaction.replace(R.id.fragment_container,
                    exercisesListFragment);
        }
        fragmentTransaction.commit();
    }

    private void showDetailExerciseFragment(Exercise selectedExercise) {

        ExerciseDetailFragment fichaPersonalFragment = new ExerciseDetailFragment();
        Bundle bundl = new Bundle();
        bundl.putSerializable("selectedExercise", selectedExercise);
        fichaPersonalFragment.setArguments(bundl);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_right,
                R.anim.slide_out_to_left);
        fragmentTransaction.replace(R.id.fragment_container,
                fichaPersonalFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void showDetail(Exercise selectedExercise) {
        showDetailExerciseFragment(selectedExercise);
    }
}
