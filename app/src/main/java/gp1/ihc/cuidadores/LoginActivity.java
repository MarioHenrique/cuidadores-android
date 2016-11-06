package gp1.ihc.cuidadores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.softcare.cuidadores.client.CuidadoresCliente;
import br.com.softcare.cuidadores.exceptions.BusinessException;
import gp1.ihc.cuidadores.ws.WebServices;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void entrar(View view) {
        Intent intentBusca = new Intent(this, BuscaActivity.class);
        String email = ((EditText) findViewById(R.id.login_email)).getText().toString();
        String senha = ((EditText) findViewById(R.id.login_senha)).getText().toString();
        try {
            WebServices.cuidadores.login(email, senha);
            startActivity(intentBusca);
        } catch (BusinessException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    public void cadastrar(View view) {
        Intent intentCadastrar = new Intent(this, CadastroActivity.class);
        startActivity(intentCadastrar);
    }

}
