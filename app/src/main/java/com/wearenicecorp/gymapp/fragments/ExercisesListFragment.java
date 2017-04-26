package com.wearenicecorp.gymapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wearenicecorp.gymapp.R;
import com.wearenicecorp.gymapp.model.Exercise;

import java.util.ArrayList;

/**
 * Created by JulioAndres on 4/13/17.
 */

public class ExercisesListFragment extends Fragment {

    private ListView exercisesListView;
    private ExercisesOfTrainingAdapter exercisesAdapter;
    private ArrayList<Exercise> exercisesOfTraining = new ArrayList<>();

    private ShowDetail showDetailListener;

    public ExercisesListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        showDetailListener = (ShowDetail) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        exercisesOfTraining = (ArrayList<Exercise>) getArguments().get("exercisesList");

        View view = inflater.inflate(R.layout.fragment_exercises_list, container, false);
        exercisesListView = (ListView) view.findViewById(R.id.exercisesListView);
        exercisesAdapter  = new ExercisesOfTrainingAdapter();
        exercisesListView.setAdapter(exercisesAdapter);

        exercisesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Exercise selectedExercise = (Exercise) adapterView.getItemAtPosition(position);
                showDetailListener.showDetail(selectedExercise);
            }
        });


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private class ExercisesOfTrainingAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return exercisesOfTraining.size();
        }

        @Override
        public Exercise getItem(int i) {
            return exercisesOfTraining.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            Exercise exercise = getItem(position);

            View row = LayoutInflater.from(getActivity())
                    .inflate(R.layout.fila_exercise_of_training, viewGroup, false);

            TextView nombreTextView = (TextView)row.findViewById(R.id.textViewFilaNombreExercise);
            nombreTextView.setText(exercise.getName());

            TextView muscularGroupTextView = (TextView)row.findViewById(R.id.textViewFilaMuscularGroupExercise);
            muscularGroupTextView.setText(exercise.getMuscularGroup());

            return row;
        }
    }

    public interface ShowDetail {
        void showDetail(Exercise selectedExercise);
    }
}
