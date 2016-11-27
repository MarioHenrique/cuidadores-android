package br.com.softcare.cuidadores.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import static br.com.softcare.cuidadores.utils.Utils.*;
import br.com.softcare.cuidadores.client.WebServices;
import br.com.softcare.cuidadores.dto.SintomaDTO;
import br.com.softcare.cuidadores.dto.Usuario;
import br.com.softcare.cuidadores.enuns.Perfil;
import gp1.ihc.cuidadores.R;

public class SintomasEditActivity extends Activity {

    private SintomaDTO sintoma;
    private Long contratoId = null;
    private boolean deleteOperation = false;
    private Usuario usuarioLogado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas_edit);
        contratoId = getIntent().getLongExtra("contratoId",0L);
        sintoma = (SintomaDTO)getIntent().getSerializableExtra("sintoma");
        usuarioLogado = (Usuario)getIntent().getSerializableExtra("usuario");
        verificarPermissao(usuarioLogado);
        if(sintoma == null){
            Button delete = (Button)findViewById(R.id.sintomas_button_deletar);
            delete.setVisibility(View.GONE);
        }else{
            setValorDaTextView(this,R.id.sintomas_descricao,sintoma.getDescricao());
        }
    }

    private void verificarPermissao(Usuario usuarioLogado) {
        if(usuarioLogado.getPerfil().contains(Perfil.RESPONSAVEL)){
            EditText desc = (EditText)findViewById(R.id.sintomas_descricao);
            desc.setEnabled(false);
            Button deletar = (Button)findViewById(R.id.sintomas_button_deletar);
            deletar.setVisibility(View.GONE);
            Button salvar = (Button)findViewById(R.id.sintomas_button_action);
            salvar.setVisibility(View.GONE);
        }
    }

    public void salvar(View view){
        doInBackground(this);
    }

    public void delete(View view){
        new AlertDialog.Builder(this)
                .setMessage("Realmente deseja remover o sintoma ?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteOperation = true;
                        doInBackground(SintomasEditActivity.this);
                    }
                })
                .setNegativeButton("Não",null)
                .show();

    }

    @Override
    protected void operation() throws Exception {
        EditText descricao = (EditText) findViewById(R.id.sintomas_descricao);
        String descricaoSintoma = descricao.getText().toString();
        if(sintoma != null){
            if(deleteOperation){
                WebServices.cuidadores.deletarSintoma(contratoId,sintoma.getId());
            }else{
                sintoma.setDescricao(descricaoSintoma);
                WebServices.cuidadores.atualizarSintoma(contratoId,sintoma);
            }
        }else {
            WebServices.cuidadores.adicionarSintoma(contratoId, descricaoSintoma);
        }
    }

    @Override
    protected void onSuccess() {
        finish();
    }
}
