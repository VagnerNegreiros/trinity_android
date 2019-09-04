package br.com.vagnernegreiros.trinity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView textViewLogado;
    Button buttonDeslogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViews();

        String email = getIntent().getStringExtra("email");

        textViewLogado.setText("Logado : " + email);

        buttonDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    void findViews(){
        textViewLogado = findViewById(R.id.textViewLogado);
        buttonDeslogar = findViewById(R.id.buttonDeslogar);
    }
}
