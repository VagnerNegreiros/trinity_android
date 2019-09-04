package br.com.vagnernegreiros.trinity;

import androidx.appcompat.app.AppCompatActivity;
import br.com.vagnernegreiros.trinity.model.Usuario;
import br.com.vagnernegreiros.trinity.util.PreferencesUtil;

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
                PreferencesUtil.clearPrefs(getApplicationContext());
                finish();
            }
        });

    }

    void findViews(){
        textViewLogado = findViewById(R.id.textViewLogado);
        buttonDeslogar = findViewById(R.id.buttonDeslogar);
    }
}
