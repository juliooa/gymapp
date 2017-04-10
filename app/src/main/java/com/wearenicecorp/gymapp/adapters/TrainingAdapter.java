package com.wearenicecorp.gymapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wearenicecorp.gymapp.R;
import com.wearenicecorp.gymapp.model.Training;

import java.util.ArrayList;

/**
 * Created by JulioAndres on 4/8/17.
 */

public class TrainingAdapter extends BaseAdapter {

    ArrayList<Training> entrenamientos = new ArrayList<>();
    private LayoutInflater inflater;

    public TrainingAdapter(ArrayList<Training> entrenamientos, Context context) {
        this.entrenamientos = entrenamientos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return entrenamientos.size();
    }

    @Override
    public Training getItem(int pos) {
        return entrenamientos.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Training training = getItem(position);

        View row = inflater.inflate(R.layout.fila_training, viewGroup, false);

        TextView nombreTextView = (TextView)row.findViewById(R.id.textViewFilaNombreTraining);
        nombreTextView.setText(training.getNombre());

        TextView descripcionTextView = (TextView)row.findViewById(R.id.textViewFilaDescTraining);
        descripcionTextView.setText(training.getDescripcion());

        return row;
    }
}
