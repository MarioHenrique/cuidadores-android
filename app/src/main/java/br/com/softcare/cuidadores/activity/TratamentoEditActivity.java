package br.com.softcare.cuidadores.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.softcare.cuidadores.client.WebServices;
import br.com.softcare.cuidadores.dto.PacienteDTO;
import br.com.softcare.cuidadores.dto.TratamentoDTO;
import gp1.ihc.cuidadores.R;
import static br.com.softcare.cuidadores.utils.Utils.*;

public class TratamentoEditActivity extends Activity {

    private TratamentoDTO tratamentoDTO;
    private PacienteDTO pacienteDTO;
    private Boolean isDeleteOperation = false;
    private boolean verApenas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamento_edit);
        tratamentoDTO = (TratamentoDTO)getIntent().getSerializableExtra("tratamento");
        pacienteDTO = (PacienteDTO)getIntent().getSerializableExtra("paciente");
        verApenas = getIntent().getBooleanExtra("verApenas",false);
        if(verApenas){
            Button action =  (Button)findViewById(R.id.tratamento_button_action);
            action.setVisibility(View.GONE);
            Button delete =  (Button)findViewById(R.id.tratamento_button_deletar);
            delete.setVisibility(View.GONE);
            EditText nome = (EditText)findViewById(R.id.tratamento_nome);
            nome.setEnabled(false);
            EditText descricao = (EditText)findViewById(R.id.tratamento_descricao);
            descricao.setEnabled(false);
        }
        if(tratamentoDTO !=null){
            setValorDaTextView(this,R.id.tratamento_nome,tratamentoDTO.getNome());
            setValorDaTextView(this,R.id.tratamento_descricao,tratamentoDTO.getDescricao());
        }else{
            Button buttonOk = (Button)findViewById(R.id.tratamento_button_action);
            buttonOk.setText("Criar");
            Button button = (Button)findViewById(R.id.tratamento_button_deletar);
            button.setVisibility(View.GONE);
        }
    }

    public void salvarTratamento(View view){
        if(tratamentoDTO == null)
            tratamentoDTO = new TratamentoDTO();
        tratamentoDTO.setNome(getValorDaTextView(this,R.id.tratamento_nome));
        tratamentoDTO.setDescricao(getValorDaTextView(this,R.id.tratamento_descricao));
        doInBackground(this);
    }

    public void deletarTratamento(View view){
        new AlertDialog.Builder(this)
                .setMessage("Realmente deseja remover o tratamento ?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        isDeleteOperation = true;
                        doInBackground(TratamentoEditActivity.this);
                    }
                })
                .setNegativeButton("Não",null)
                .show();
    }

    @Override
    protected void operation() throws Exception {
        if(tratamentoDTO.getId()!=null){
            if (isDeleteOperation) {
                WebServices.cuidadores.deletarTratamento(pacienteDTO.getId(),tratamentoDTO.getId());
                return;
            }
            WebServices.cuidadores.atualizarTratamento(tratamentoDTO,pacienteDTO.getId(),tratamentoDTO.getId());
        }else{
            WebServices.cuidadores.criarTratamento(tratamentoDTO,pacienteDTO.getId());
        }
    }

    @Override
    protected void onSuccess() {
        finish();
    }

}
