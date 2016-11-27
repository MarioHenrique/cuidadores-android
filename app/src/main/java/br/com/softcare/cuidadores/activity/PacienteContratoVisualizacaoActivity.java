package br.com.softcare.cuidadores.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import static br.com.softcare.cuidadores.utils.Utils.*;
import br.com.softcare.cuidadores.dto.LinkedMapTransferDTO;
import gp1.ihc.cuidadores.R;

public class PacienteContratoVisualizacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_contrato_visualizacao);
        LinkedMapTransferDTO paciente = (LinkedMapTransferDTO)getIntent().getSerializableExtra("paciente");
        setValorDaTextView(this,R.id.contrato_paciente_visualizar_nome,(String)paciente.getLinked().get("name"));
        setValorDaTextView(this,R.id.contrato_paciente_visualizar_idade,(String)paciente.getLinked().get("dateOfBirth"));
        setValorDaTextView(this,R.id.contrato_paciente_visualizar_contato,(String)paciente.getLinked().get("contact"));
        setValorDaTextView(this,R.id.contrato_paciente_visualizar_saude,(String)paciente.getLinked().get("healthStatus"));
    }



    public void voltar(View view){
        finish();
    }
}
