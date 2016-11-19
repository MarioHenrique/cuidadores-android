package br.com.softcare.cuidadores.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Set;
import br.com.softcare.cuidadores.adapter.PerfilAdapter;
import br.com.softcare.cuidadores.client.WebServices;
import br.com.softcare.cuidadores.dto.BuscaDeCuidadoresDTO;
import br.com.softcare.cuidadores.dto.Usuario;
import br.com.softcare.cuidadores.dto.UsuarioAlteracao;
import br.com.softcare.cuidadores.enuns.Disponibilidade;
import br.com.softcare.cuidadores.enuns.Perfil;
import br.com.softcare.cuidadores.enuns.Periodo;
import br.com.softcare.cuidadores.exceptions.BusinessException;
import static br.com.softcare.cuidadores.utils.Utils.getValorDaTextView;

import gp1.ihc.cuidadores.R;

public class UsuarioActivity extends Activity {

    private Usuario usuarioLogado;

    private UsuarioAlteracao usuarioAlteracao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        final Intent intent = getIntent();
        usuarioLogado = (Usuario) intent.getSerializableExtra("usuario");
        EditText campoCep = (EditText) findViewById(R.id.usuario_cep);
        campoCep.setText(usuarioLogado.getCep());
        EditText campoNome = (EditText) findViewById(R.id.usuario_nome);
        campoNome.setText(usuarioLogado.getNome());
        EditText campoTelefone = (EditText) findViewById(R.id.usuario_telefone);
        campoTelefone.setText(usuarioLogado.getContato());
        final Set<Disponibilidade> disponibilidade = usuarioLogado.getDisponibilidade();
        if(disponibilidade!=null && !disponibilidade.isEmpty()) {
            for (Disponibilidade disp : disponibilidade) {
                ativarCheckBoxDisponibilidade(disp);
            }
        }
        final Set<Periodo> periodo = usuarioLogado.getPeriodo();
        if(periodo!=null && !periodo.isEmpty()) {
            for (Periodo per : periodo) {
                ativarCheckBoxPeriodo(per);
            }
        }
        Spinner spinnerPerfil = (Spinner) findViewById(R.id.usuario_perfil);
        spinnerPerfil.setAdapter(new PerfilAdapter(this));
        Button button = (Button)findViewById(R.id.usuario_salvar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doInBackground(UsuarioActivity.this);
            }
        });
    }

    @Override
    protected void operation() throws Exception {
        usuarioAlteracao = new UsuarioAlteracao();
        usuarioAlteracao.setNome(getValorDaTextView(UsuarioActivity.this, R.id.usuario_nome));
        usuarioAlteracao.setCep(getValorDaTextView(UsuarioActivity.this,R.id.usuario_cep));
        usuarioAlteracao.setContato(getValorDaTextView(UsuarioActivity.this,R.id.usuario_telefone));
        Spinner spinner = (Spinner)findViewById(R.id.usuario_perfil);
        Perfil perfil= (Perfil)spinner.getSelectedItem();
        usuarioAlteracao.adicionarPerfil(perfil);
        setPeriodoAlteracao(usuarioAlteracao);
        setarDisponibilidateAlteracao(usuarioAlteracao);
        WebServices.cuidadores.atualizarUsuario(usuarioAlteracao);
    }

    @Override
    protected void onSuccess() {
        usuarioLogado.setNome(usuarioAlteracao.getNome());
        usuarioLogado.setContato(usuarioAlteracao.getContato());
        usuarioLogado.setDisponibilidade(usuarioAlteracao.getDisponibilidade());
        usuarioLogado.setPerfil(usuarioAlteracao.getPerfil());
        usuarioLogado.setPeriodo(usuarioAlteracao.getPeriodo());
        final Intent intent = new Intent();
        intent.putExtra("usuarioAlterado",usuarioLogado);
        setResult(RESULT_OK,intent);
        finish();
    }

    private void setPeriodoAlteracao(UsuarioAlteracao usuarioAlteracao) {
        if(isCheckBoxChecked(R.id.usuario_manha)){
            usuarioAlteracao.adicionarPeriodo(Periodo.MANHA);
        }
        if(isCheckBoxChecked(R.id.usuario_tarde)){
            usuarioAlteracao.adicionarPeriodo(Periodo.TARDE);
        }
        if(isCheckBoxChecked(R.id.usuario_noite)){
            usuarioAlteracao.adicionarPeriodo(Periodo.NOITE);
        }
    }

    private void setarDisponibilidateAlteracao(UsuarioAlteracao usuarioAlteracao) {
        if(isCheckBoxChecked(R.id.usuario_segunda)){
            usuarioAlteracao.adicionarDisponibilidade(Disponibilidade.SEGUNDA);
        }
        if(isCheckBoxChecked(R.id.usuario_terca)){
            usuarioAlteracao.adicionarDisponibilidade(Disponibilidade.TERCA);
        }
        if(isCheckBoxChecked(R.id.usuario_quarta)){
            usuarioAlteracao.adicionarDisponibilidade(Disponibilidade.QUARTA);
        }
        if(isCheckBoxChecked(R.id.usuario_quinta)){
            usuarioAlteracao.adicionarDisponibilidade(Disponibilidade.QUINTA);
        }
        if(isCheckBoxChecked(R.id.usuario_sexta)){
            usuarioAlteracao.adicionarDisponibilidade(Disponibilidade.SEXTA);
        }
        if(isCheckBoxChecked(R.id.usuario_sabado)){
            usuarioAlteracao.adicionarDisponibilidade(Disponibilidade.SABADO);
        }
        if(isCheckBoxChecked(R.id.usuario_domingo)){
            usuarioAlteracao.adicionarDisponibilidade(Disponibilidade.DOMINGO);
        }
    }

    private boolean isCheckBoxChecked(int resource){
        return ((CheckBox)findViewById(resource)).isChecked();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Spinner spinnerPerfil = (Spinner) findViewById(R.id.usuario_perfil);
        spinnerPerfil.setSelection(getItemPerfil(usuarioLogado.getPerfil()));
    }

    private int getItemPerfil(Set<Perfil> perfil) {
        for(Perfil per: perfil){
            if(per.equals(Perfil.CUIDADOR))
                return 0;
            else
                return 1;
        }
        return -1;
    }

    private void ativarCheckBoxPeriodo(Periodo per) {
        switch (per){
            case MANHA:
                check(R.id.usuario_manha);
                break;
            case TARDE:
                check(R.id.usuario_tarde);
                break;
            case NOITE:
                check(R.id.usuario_noite);
                break;
        }

    }

    private void ativarCheckBoxDisponibilidade(Disponibilidade disp){
        switch (disp){
            case SEGUNDA:
                check(R.id.usuario_segunda);
                break;
            case TERCA:
                check(R.id.usuario_terca);
                break;
            case QUARTA:
                check(R.id.usuario_quarta);
                break;
            case QUINTA:
                check(R.id.usuario_quinta);
                break;
            case SEXTA:
                check(R.id.usuario_sexta);
                break;
            case SABADO:
                check(R.id.usuario_sabado);
                break;
            case DOMINGO:
                check(R.id.usuario_domingo);
                break;
        }

    }

    private void check(int resource){
        CheckBox check = (CheckBox)findViewById(resource);
        check.setChecked(true);
    }

}
