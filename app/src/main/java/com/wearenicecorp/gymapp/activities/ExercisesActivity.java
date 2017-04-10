package com.wearenicecorp.gymapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wearenicecorp.gymapp.R;
import com.wearenicecorp.gymapp.database.AppDatabaseHelper;
import com.wearenicecorp.gymapp.model.Exercise;

import java.util.ArrayList;

/**
 * Created by JulioAndres on 4/8/17.
 */

public class ExercisesActivity extends AppCompatActivity {

    private static final int NEW_EXERCISE_REQUEST_CODE = 3;
    private ListView exercisesListView;
    ArrayList<Exercise> exercises = new ArrayList<>();
    private ExercisesAdapter exercisesAdapter;

    @Override
    protected void onResume() {
        super.onResume();

        exercises.clear();
        loadFromDataBase();
        exercisesAdapter.notifyDataSetChanged();
    }

    private void loadFromDataBase() {

        AppDatabaseHelper appDatabaseHelper = new AppDatabaseHelper(this);
        SQLiteDatabase database = appDatabaseHelper.getReadableDatabase();

        Cursor resultadosCursor = database.query(AppDatabaseHelper.TABLE_EXERCISES,
                null, null, null, null,null,AppDatabaseHelper.COLUMN_NAME);

        while(resultadosCursor.moveToNext()){

            Exercise exercise = new Exercise();

            String name = resultadosCursor.
                    getString(resultadosCursor.getColumnIndex(AppDatabaseHelper.COLUMN_NAME));
            exercise.setName(name);
            String grupo = resultadosCursor.
                    getString(resultadosCursor.getColumnIndex(AppDatabaseHelper.COLUMN_MUSCULAR_GROUP));
            exercise.setMuscularGroup(grupo);

            exercises.add(exercise);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exercises);

        exercisesListView = (ListView) findViewById(R.id.exercisesListView);
        exercisesAdapter  = new ExercisesAdapter();
        exercisesListView.setAdapter(exercisesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_exercises, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_exercise:

                Intent intent = new Intent(ExercisesActivity.this, NewExerciseActivity.class);
                startActivityForResult(intent, NEW_EXERCISE_REQUEST_CODE);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class ExercisesAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return exercises.size();
        }

        @Override
        public Exercise getItem(int i) {
         return exercises.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            Exercise exercise = getItem(position);

            View row = LayoutInflater.from(ExercisesActivity.this)
                    .inflate(R.layout.fila_exercise, viewGroup, false);

            TextView nombreTextView = (TextView)row.findViewById(R.id.textViewFilaNombreExercise);
            nombreTextView.setText(exercise.getName());

            TextView muscularGroupTextView = (TextView)row.findViewById(R.id.textViewFilaMuscularGroupExercise);
            muscularGroupTextView.setText(exercise.getMuscularGroup());

            return row;
        }
    }
}
