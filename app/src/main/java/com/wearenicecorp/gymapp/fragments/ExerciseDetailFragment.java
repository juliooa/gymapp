package com.wearenicecorp.gymapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wearenicecorp.gymapp.R;
import com.wearenicecorp.gymapp.model.Exercise;

/**
 * Created by JulioAndres on 4/24/17.
 */

public class ExerciseDetailFragment extends Fragment {

    public ExerciseDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Exercise selectedExercise = (Exercise) getArguments().getSerializable("selectedExercise");

        View view = inflater.inflate(R.layout.fragment_exercise_detail, container, false);
        TextView exerciseName = (TextView) view.findViewById(R.id.exerciseName);
        exerciseName.setText(selectedExercise.getName());

        return view;
    }
}
