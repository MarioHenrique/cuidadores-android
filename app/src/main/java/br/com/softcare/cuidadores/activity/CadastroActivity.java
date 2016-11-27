package br.com.softcare.cuidadores.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.softcare.cuidadores.adapter.PerfilAdapter;
import br.com.softcare.cuidadores.client.WebServices;
import br.com.softcare.cuidadores.dto.UsuarioAlteracao;
import br.com.softcare.cuidadores.enuns.Perfil;
import gp1.ihc.cuidadores.R;

import static br.com.softcare.cuidadores.utils.Utils.getValorDaTextView;
import static gp1.ihc.cuidadores.R.drawable.cuidador;

public class CadastroActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Spinner  spinnerPerfil = (Spinner) findViewById(R.id.cadastro_perfil);
        spinnerPerfil.setAdapter(new PerfilAdapter(this));
    }

    public void cadastrar(View view) {
        doInBackground(this);
    }

    @Override
    protected void operation() throws Exception {
        final UsuarioAlteracao usuario = new UsuarioAlteracao();
        usuario.setContato(getValorDaTextView(this, R.id.cadastro_contato));
        usuario.setCep(getValorDaTextView(this, R.id.cadastro_zipcode));
        usuario.setEmail(getValorDaTextView(this, R.id.cadastro_email));
        usuario.setNome(getValorDaTextView(this, R.id.cadastro_nome));
        usuario.setSenha(getValorDaTextView(this, R.id.cadastro_senha));
        Spinner spinner = (Spinner) findViewById(R.id.cadastro_perfil);
        usuario.adicionarPerfil((Perfil)spinner.getSelectedItem());
        WebServices.cuidadores.cadastrarUsuario(usuario);
    }

    @Override
    protected void onSuccess() {
        Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
        finish();
    }



}
