package br.com.softcare.cuidadores.activity;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.softcare.cuidadores.adapter.PerfilAdapter;
import br.com.softcare.cuidadores.dto.UsuarioAlteracao;
import br.com.softcare.cuidadores.enuns.Disponibilidade;
import br.com.softcare.cuidadores.enuns.Perfil;
import br.com.softcare.cuidadores.enuns.Periodo;
import br.com.softcare.cuidadores.exceptions.BusinessException;
import gp1.ihc.cuidadores.R;
import br.com.softcare.cuidadores.client.WebServices;

import static br.com.softcare.cuidadores.utils.Utils.getValorDaTextView;

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
