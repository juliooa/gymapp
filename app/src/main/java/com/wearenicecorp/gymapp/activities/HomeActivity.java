package com.wearenicecorp.gymapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.wearenicecorp.gymapp.R;
import com.wearenicecorp.gymapp.adapters.HistoryTrainingsAdapter;

public class HomeActivity extends AppCompatActivity {

    private ListView trainingsHistoryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        Button boton = (Button) findViewById(R.id.startButton);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this, TrainingsActivity.class);
                startActivity(intent);
            }

        });

        ImageView imageView = (ImageView)findViewById(R.id.imageAvatar);
        Bitmap avatar = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);
        RoundedBitmapDrawable roundDrawable = RoundedBitmapDrawableFactory.create(getResources(), avatar);
        roundDrawable.setCircular(true);
        imageView.setImageDrawable(roundDrawable);

        trainingsHistoryListView = (ListView) findViewById(R.id.trainingsHistoryListView);
        trainingsHistoryListView.setEmptyView(findViewById(R.id.empty_list));
        trainingsHistoryListView.setAdapter(new HistoryTrainingsAdapter());
    }
}
