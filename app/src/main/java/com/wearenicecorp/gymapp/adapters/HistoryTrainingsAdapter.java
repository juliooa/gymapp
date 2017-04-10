package com.wearenicecorp.gymapp.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wearenicecorp.gymapp.model.HistoryTraining;

import java.util.ArrayList;

/**
 * Created by JulioAndres on 4/8/17.
 */

public class HistoryTrainingsAdapter extends BaseAdapter {

    ArrayList<HistoryTraining> trainings = new ArrayList<>();

    @Override
    public int getCount() {
        return trainings.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
