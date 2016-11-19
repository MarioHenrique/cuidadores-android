package br.com.softcare.cuidadores.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.com.softcare.cuidadores.client.WebServices;
import br.com.softcare.cuidadores.dto.Usuario;
import br.com.softcare.cuidadores.exceptions.BusinessException;
import gp1.ihc.cuidadores.R;

import static br.com.softcare.cuidadores.utils.Utils.getValorDaTextView;

public class LoginActivity extends Activity {

    private Usuario usuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void operation() throws BusinessException {
        final String senha = getValorDaTextView(this,R.id.login_senha);
        final String email = getValorDaTextView(this,R.id.login_email);
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
