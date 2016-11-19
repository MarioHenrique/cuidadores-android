package br.com.softcare.cuidadores.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.softcare.cuidadores.dto.Usuario;
import br.com.softcare.cuidadores.exceptions.BusinessException;
import br.com.softcare.cuidadores.exceptions.ExceptionError;
import gp1.ihc.cuidadores.R;
import br.com.softcare.cuidadores.client.WebServices;

public class LoginActivity extends Activity {

    private Usuario usuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void operation() throws BusinessException {
        final String senha = ((EditText) findViewById(R.id.login_senha)).getText().toString();
        final String email = ((EditText) findViewById(R.id.login_email)).getText().toString();
        usuarioLogado = WebServices.cuidadores.login(email, senha);
    }

    @Override
    protected void onSuccess() {
        Intent intentBusca = new Intent(this, MenuActivity.class);
        intentBusca.putExtra("usuario", usuarioLogado);
        startActivity(intentBusca);
    }

    public void entrar(View view) {
        doInBackground(this);
    }

    public void cadastrar(View view) {
        Intent intentCadastrar = new Intent(this, CadastroActivity.class);
        startActivity(intentCadastrar);
    }

}
