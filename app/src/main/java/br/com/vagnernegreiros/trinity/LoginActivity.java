package br.com.vagnernegreiros.trinity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import br.com.vagnernegreiros.trinity.api.API;
import br.com.vagnernegreiros.trinity.model.Usuario;
import br.com.vagnernegreiros.trinity.service.Service;
import br.com.vagnernegreiros.trinity.util.PreferencesUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextSenha;
    Button buttonLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();

        buttonLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = validate();

                if(isValid){
                    login();
                }
            }
        });

        // Obtendo usuário do preferences
        Usuario usuario = PreferencesUtil.getUsuario(getApplicationContext());

        if(!usuario.getUser_email().equals("")){
            abrirTelaHome(usuario);
        }

    }

    void findViews(){
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSenha = findViewById(R.id.editTextSenha);
        buttonLogar = findViewById(R.id.buttonLogar);
    }

    boolean validate(){

        if(editTextEmail.getText().toString().equals("")){
            editTextEmail.setError("Digite um email válido");
            return false;
        }

        if(editTextSenha.getText().toString().equals("")){
            editTextSenha.setError("Digite uma senha");
            return false;
        }

        return true;
    }

    void login(){

        final Usuario usuario = new Usuario(editTextEmail.getText().toString() , editTextSenha.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

        service.login(usuario).enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.code() == 200){
                    abrirTelaHome(usuario);
                } else {
                    exibirAlertaErro("Atenção" , "Email ou senha inválidos.");
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                exibirAlertaErro("Erro" , "Ocorreu um erro no servidor");
            }
        });
    }

    private void abrirTelaHome(Usuario usuario){
        // Salvando usuário no preferences
        PreferencesUtil.saveUsuario(usuario , getApplicationContext());

        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.putExtra("email", usuario.getUser_email());
        startActivity(intent);
    }

    private void exibirAlertaErro(String titulo , String mensagem) {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle(titulo);
        //define a mensagem
        builder.setMessage(mensagem);
        //define um botão como positivo
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        //cria o AlertDialog
        builder.create().show();
    }

}
