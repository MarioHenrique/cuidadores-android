package br.com.softcare.cuidadores.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import br.com.softcare.cuidadores.client.WebServices;
import br.com.softcare.cuidadores.dto.Cuidador;
import br.com.softcare.cuidadores.dto.EspecialidadeDTO;
import gp1.ihc.cuidadores.R;

import static br.com.softcare.cuidadores.utils.Utils.getValorDaTextView;
import static br.com.softcare.cuidadores.utils.Utils.setValorDaTextView;

public class EspecialidadeEditActivity extends Activity {

    private EspecialidadeDTO especialidadeDTO;
    private Boolean isDeleteOperation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especialidade_edit);
        especialidadeDTO = (EspecialidadeDTO)getIntent().getSerializableExtra("especialidade");
        if(especialidadeDTO !=null){
            setValorDaTextView(this,R.id.especialidade_nome, especialidadeDTO.getNome());
            setValorDaTextView(this,R.id.especialidade_descricao, especialidadeDTO.getDescricao());
        }else{
            Button buttonOk = (Button)findViewById(R.id.especialidade_button_action);
            buttonOk.setText("Criar");
            Button button = (Button)findViewById(R.id.especialidade_button_deletar);
            button.setVisibility(View.GONE);
        }

        Cuidador cuidador = (Cuidador) getIntent().getSerializableExtra(CuidadorActivity.EXTRA_CUIDADOR);
        if (cuidador != null) {
            findViewById(R.id.especialidade_button_action).setVisibility(View.GONE);
            findViewById(R.id.especialidade_button_deletar).setVisibility(View.GONE);
            findViewById(R.id.especialidade_descricao).setEnabled(false);
            findViewById(R.id.especialidade_nome).setEnabled(false);
        }

    }

    public void salvarEspecialidade(View view){
        if(especialidadeDTO == null)
            especialidadeDTO = new EspecialidadeDTO();
        especialidadeDTO.setNome(getValorDaTextView(this,R.id.especialidade_nome));
        especialidadeDTO.setDescricao(getValorDaTextView(this,R.id.especialidade_descricao));
        doInBackground(this);
    }

    public void deletarEspecialidade(View view){
        new AlertDialog.Builder(this)
                .setMessage("Realmente deseja remover a especialidade ?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        isDeleteOperation = true;
                        doInBackground(EspecialidadeEditActivity.this);
                    }
                })
                .setNegativeButton("Não",null)
                .show();
    }

    @Override
    protected void operation() throws Exception {
        if(especialidadeDTO.getId()!=null){
            if (isDeleteOperation) {
                WebServices.cuidadores.removeEspecialidade(especialidadeDTO);
                return;
            }
            WebServices.cuidadores.atualizarEspecialidade(especialidadeDTO);
        }else{
            WebServices.cuidadores.cadastroDeEspecialidade(especialidadeDTO);
        }
    }

    @Override
    protected void onSuccess() {
        finish();
    }

}
