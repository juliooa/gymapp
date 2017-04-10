package com.wearenicecorp.gymapp.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wearenicecorp.gymapp.adapters.TrainingAdapter;
import com.wearenicecorp.gymapp.network.BackEndAPI;
import com.wearenicecorp.gymapp.network.BackEndClient;
import com.wearenicecorp.gymapp.model.Training;
import com.wearenicecorp.gymapp.model.TrainingDTO;
import com.wearenicecorp.gymapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TrainingsActivity extends AppCompatActivity implements Callback<TrainingDTO> {

    private static final String getTrainingURL = "http://wearenicecorp.com/apps/gymapp/entrenamientos.php";
    public static final int NEW_TRAINING_REQUEST_CODE = 1;

    ListView trainingsListView;
    ArrayList<Training> entrenamientos = new ArrayList<>();
    private TrainingAdapter trainingsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainings);

        trainingsListView = (ListView) findViewById(R.id.trainingsListView);

        trainingsAdapter = new TrainingAdapter(entrenamientos, TrainingsActivity.this);
        trainingsListView.setAdapter(trainingsAdapter);

        FloatingActionButton newTrainingButton = (FloatingActionButton) findViewById(R.id.newTrainingButton);
        newTrainingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainingsActivity.this, NewTrainingActivity.class);
                startActivityForResult(intent, NEW_TRAINING_REQUEST_CODE);
            }
        });

        //getTrainings(); Descomentar esto para usar AsyncTask
        getTrainingsWithLibrary();
    }

    private void getTrainingsWithLibrary() {
        //Se hacen los llamados al webservice
        Retrofit retrofit = BackEndClient.getClient();
        BackEndAPI backEndAPI = retrofit.create(BackEndAPI.class);
        Call<TrainingDTO> call = backEndAPI.getTrainings();
        call.enqueue(this);
    }

    private void getTrainings() {

        GetTrainingsAsync getTrainingsAsync = new GetTrainingsAsync();
        getTrainingsAsync.execute();
    }

    @Override
    public void onResponse(Call<TrainingDTO> call, Response<TrainingDTO> response) {

        (findViewById(R.id.progressBar)).setVisibility(View.GONE);
        if(response.isSuccessful()){
            trainingsListView.setVisibility(View.VISIBLE);
            entrenamientos.addAll(response.body().getEntrenamientos());
            trainingsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(Call<TrainingDTO> call, Throwable t) {
        (findViewById(R.id.progressBar)).setVisibility(View.GONE);
        Toast.makeText(TrainingsActivity.this,"Error",Toast.LENGTH_LONG).show();
    }

    private class GetTrainingsAsync extends AsyncTask<String, Integer, ArrayList<Training>> {

        @Override
        protected ArrayList<Training> doInBackground(String... strings) {

            try {
                HttpURLConnection httpURLConnection =
                        (HttpURLConnection)new URL(getTrainingURL).openConnection();

                InputStream in = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                TrainingDTO trainingsObtenidos = new Gson().fromJson(reader, TrainingDTO.class);
                reader.close();

                return trainingsObtenidos.getEntrenamientos();

            } catch (IOException e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Training> trainings) {
            super.onPostExecute(trainings);

            (findViewById(R.id.progressBar)).setVisibility(View.GONE);

            if (trainings == null) {
                Toast.makeText(TrainingsActivity.this,"Error",Toast.LENGTH_LONG).show();
            } else {
                trainingsListView.setVisibility(View.VISIBLE);

                entrenamientos.addAll(trainings);
                trainingsAdapter.notifyDataSetChanged();
            }
        }
    }
}
