package com.wearenicecorp.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEdiText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button boton = (Button) findViewById(R.id.enterButton);
        usernameEdiText = (EditText) findViewById(R.id.usernameEditText);

        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String username = usernameEdiText.getText().toString();

                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("nombre_usuario", username);
                intent.putExtra("edad", 30);

                startActivity(intent);
            }

        });
    }
}
