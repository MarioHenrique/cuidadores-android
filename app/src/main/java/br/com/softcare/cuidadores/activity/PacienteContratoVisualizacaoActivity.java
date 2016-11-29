package br.com.softcare.cuidadores.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static br.com.softcare.cuidadores.utils.Utils.*;
import br.com.softcare.cuidadores.dto.LinkedMapTransferDTO;
import br.com.softcare.cuidadores.dto.PacienteDTO;
import br.com.softcare.cuidadores.dto.TratamentoDTO;
import br.com.softcare.cuidadores.dto.TratamentosTransferDTO;
import gp1.ihc.cuidadores.R;

public class PacienteContratoVisualizacaoActivity extends AppCompatActivity {

    private List<TratamentoDTO> tratamentos = new ArrayList<>();
    private PacienteDTO pacienteDTO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_contrato_visualizacao);
        LinkedMapTransferDTO paciente = (LinkedMapTransferDTO)getIntent().getSerializableExtra("paciente");
        setValorDaTextView(this,R.id.contrato_paciente_visualizar_nome,(String)paciente.getLinked().get("name"));
        setValorDaTextView(this,R.id.contrato_paciente_visualizar_idade,(String)paciente.getLinked().get("dateOfBirth"));
        setValorDaTextView(this,R.id.contrato_paciente_visualizar_contato,(String)paciente.getLinked().get("contact"));
        setValorDaTextView(this,R.id.contrato_paciente_visualizar_saude,(String)paciente.getLinked().get("healthStatus"));
        pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(Long.valueOf(String.valueOf(paciente.getLinked().get("id"))));
        pacienteDTO.setEstadoDeSaude((String)paciente.getLinked().get("healthStatus"));
        pacienteDTO.setDataNascimento((String)paciente.getLinked().get("dateOfBirth"));
        pacienteDTO.setContato((String)paciente.getLinked().get("contact"));
        pacienteDTO.setNome((String)paciente.getLinked().get("name"));
        List<HashMap> treatments = (List)paciente.getLinked().get("treatments");
        for(HashMap item : treatments){
            final TratamentoDTO tratamento = new TratamentoDTO();
            tratamento.setId(Long.valueOf(String.valueOf(item.get("id"))));
            tratamento.setDescricao((String)item.get("description"));
            tratamento.setNome((String)item.get("name"));
            tratamentos.add(tratamento);
        }
    }

    public void verTratamentos(View view){
        final Intent intent = new Intent(this, TratamentoActivity.class);
        intent.putExtra("paciente",pacienteDTO);
        intent.putExtra("verApenas",true);
        final TratamentosTransferDTO tratamentoTransfer = new TratamentosTransferDTO();
        tratamentoTransfer.setTratamentos(tratamentos);
        intent.putExtra("tratamentos",tratamentoTransfer);
        startActivity(intent);
    }


    public void voltar(View view){
        finish();
    }
}
