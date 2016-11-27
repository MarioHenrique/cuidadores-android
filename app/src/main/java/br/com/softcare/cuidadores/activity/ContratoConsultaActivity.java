package br.com.softcare.cuidadores.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.LinkedHashMap;

import br.com.softcare.cuidadores.dto.LinkedMapTransferDTO;
import br.com.softcare.cuidadores.enuns.Status;
import gp1.ihc.cuidadores.R;

public class ContratoConsultaActivity extends AppCompatActivity {

    private LinkedMapTransferDTO contrato;
    private HashMap patient;
    private HashMap careGiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contrato_consulta);
        contrato = (LinkedMapTransferDTO)getIntent().getSerializableExtra("contrato");
        careGiver = (HashMap)contrato.getLinked().get("careGiver");
        patient = (HashMap)contrato.getLinked().get("patient");
        EditText nomeCuidador = (EditText)findViewById(R.id.contrato_cuidador_consulta_nome);
        nomeCuidador.setText((String)careGiver.get("name"));
        EditText nomePaciente = (EditText)findViewById(R.id.contrato_paciente_consulta_nome);
        nomePaciente.setText((String)patient.get("name"));
        EditText dataInicial = (EditText)findViewById(R.id.contrato_consulta_data_inicial);
        dataInicial.setText((String)contrato.getLinked().get("propostalInitialDate"));
        EditText dataFinal = (EditText)findViewById(R.id.contrato_consulta_data_final);
        dataFinal.setText((String)contrato.getLinked().get("propostalFinalDate"));
        EditText status = (EditText)findViewById(R.id.contrato_consulta_status);
        status.setText(Status.valueOf((String)contrato.getLinked().get("status")).getDescricao());
    }

    public void visualizarCuidador(View view){
        final Intent intent = new Intent(ContratoConsultaActivity.this, CuidadorContratoVisualizacaoActivity.class);
        intent.putExtra("cuidador",new LinkedMapTransferDTO(careGiver));
        startActivity(intent);
    }

    public void visualizarPaciente(View view){
        final Intent intent = new Intent(ContratoConsultaActivity.this, PacienteContratoVisualizacaoActivity.class);
        intent.putExtra("paciente",new LinkedMapTransferDTO(patient));
        startActivity(intent);
    }

    public void voltar(View view){
        finish();
    }

}
