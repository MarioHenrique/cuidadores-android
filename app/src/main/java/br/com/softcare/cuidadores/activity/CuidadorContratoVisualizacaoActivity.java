package br.com.softcare.cuidadores.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;
import java.util.Set;

import static br.com.softcare.cuidadores.utils.Utils.*;
import br.com.softcare.cuidadores.dto.LinkedMapTransferDTO;
import br.com.softcare.cuidadores.enuns.Disponibilidade;
import br.com.softcare.cuidadores.enuns.Periodo;
import gp1.ihc.cuidadores.R;

public class CuidadorContratoVisualizacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuidador_contrato_visualizacao);
        final LinkedMapTransferDTO cuidador = (LinkedMapTransferDTO)getIntent().getSerializableExtra("cuidador");
        setValorDaTextView(this,R.id.contrato_cuidador_visualizar_nome,(String)cuidador.getLinked().get("name"));
        setValorDaTextView(this,R.id.contrato_cuidador_visualizar_telefone,(String)cuidador.getLinked().get("contact"));
        setValorDaTextView(this,R.id.contrato_cuidador_visualizar_cep,(String)cuidador.getLinked().get("zipCode"));

        List<String> disponibilidade = (List)cuidador.getLinked().get("availability");
        if(disponibilidade!=null && !disponibilidade.isEmpty()) {
            for (String disp : disponibilidade) {
                ativarCheckBoxDisponibilidade(Disponibilidade.getByName(disp));
            }
        }
        List<String> periodo = (List)cuidador.getLinked().get("period");
        if(periodo!=null && !periodo.isEmpty()) {
            for (String per : periodo) {
                ativarCheckBoxPeriodo(Periodo.getByName(per));
            }
        }
    }

    public void voltar(View view){
        finish();
    }

    private void ativarCheckBoxDisponibilidade(Disponibilidade disp){
        switch (disp){
            case SEGUNDA:
                activiCheckBox(this,R.id.contrato_cuidador_visualizar_segunda);
                break;
            case TERCA:
                activiCheckBox(this,R.id.contrato_cuidador_visualizar_terca);
                break;
            case QUARTA:
                activiCheckBox(this,R.id.contrato_cuidador_visualizar_quarta);
                break;
            case QUINTA:
                activiCheckBox(this,R.id.contrato_cuidador_visualizar_quinta);
                break;
            case SEXTA:
                activiCheckBox(this,R.id.contrato_cuidador_visualizar_sexta);
                break;
            case SABADO:
                activiCheckBox(this,R.id.contrato_cuidador_visualizar_sabado);
                break;
            case DOMINGO:
                activiCheckBox(this,R.id.contrato_cuidador_visualizar_domingo);
                break;
        }

    }

    private void ativarCheckBoxPeriodo(Periodo per) {
        switch (per){
            case MANHA:
                activiCheckBox(this,R.id.contrato_cuidador_visualizar_manha);
                break;
            case TARDE:
                activiCheckBox(this,R.id.contrato_cuidador_visualizar_tarde);
                break;
            case NOITE:
                activiCheckBox(this,R.id.contrato_cuidador_visualizar_noite);
                break;
        }

    }

}
