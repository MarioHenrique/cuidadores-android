package gp1.ihc.cuidadores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void entrar(View view) {
        Intent intentBusca = new Intent(this, BuscaActivity.class);
        startActivity(intentBusca);
    }

    public void cadastrar(View view) {
        Intent intentCadastrar = new Intent(this, CadastroActivity.class);
        startActivity(intentCadastrar);
    }

}
