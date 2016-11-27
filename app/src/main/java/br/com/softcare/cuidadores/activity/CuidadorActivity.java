package br.com.softcare.cuidadores.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Set;

import br.com.softcare.cuidadores.dto.Cuidador;
import br.com.softcare.cuidadores.enuns.Disponibilidade;
import br.com.softcare.cuidadores.enuns.Periodo;
import gp1.ihc.cuidadores.R;

import static br.com.softcare.cuidadores.utils.Utils.activiCheckBox;
import static br.com.softcare.cuidadores.utils.Utils.setValorDaTextView;

public class CuidadorActivity extends AppCompatActivity {

    private Cuidador cuidador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuidador);
        Button voltar = (Button)findViewById(R.id.cuidador_voltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cuidador = (Cuidador)getIntent().getSerializableExtra("cuidador");
        setValorDaTextView(CuidadorActivity.this,R.id.cuidador_cep,cuidador.getCep());
        setValorDaTextView(CuidadorActivity.this,R.id.cuidador_nome,cuidador.getNome());
        setValorDaTextView(CuidadorActivity.this,R.id.cuidador_telefone,cuidador.getContato());
        setValorDaTextView(CuidadorActivity.this,R.id.cuidador_email,cuidador.getEmail());
        setValorDaTextView(CuidadorActivity.this,R.id.cuidador_bairro,cuidador.getBairro());
        setValorDaTextView(CuidadorActivity.this,R.id.cuidador_cidade,cuidador.getCidade());
        setValorDaTextView(CuidadorActivity.this,R.id.cuidador_estado,cuidador.getEstado());
        setValorDaTextView(CuidadorActivity.this,R.id.cuidador_rua,cuidador.getRua());

        final Set<Disponibilidade> disponibilidade = cuidador.getDisponibilidade();
        if(disponibilidade!=null && !disponibilidade.isEmpty()) {
            for (Disponibilidade disp : disponibilidade) {
                ativarCheckBoxDisponibilidade(disp);
            }
        }
        final Set<Periodo> periodo = cuidador.getPeriodo();
        if(periodo!=null && !periodo.isEmpty()) {
            for (Periodo per : periodo) {
                ativarCheckBoxPeriodo(per);
            }
        }
    }

    public void novoContrato(View view){
        Intent novoContrato = new Intent(CuidadorActivity.this,NovoContratoActivity.class);
        novoContrato.putExtra("cuidador",cuidador);
        startActivity(novoContrato);
    }


    private void ativarCheckBoxDisponibilidade(Disponibilidade disp){
        switch (disp){
            case SEGUNDA:
                activiCheckBox(this,R.id.cuidador_segunda);
                break;
            case TERCA:
                activiCheckBox(this,R.id.cuidador_terca);
                break;
            case QUARTA:
                activiCheckBox(this,R.id.cuidador_quarta);
                break;
            case QUINTA:
                activiCheckBox(this,R.id.cuidador_quinta);
                break;
            case SEXTA:
                activiCheckBox(this,R.id.cuidador_sexta);
                break;
            case SABADO:
                activiCheckBox(this,R.id.cuidador_sabado);
                break;
            case DOMINGO:
                activiCheckBox(this,R.id.cuidador_domingo);
                break;
        }

    }

    private void ativarCheckBoxPeriodo(Periodo per) {
        switch (per){
            case MANHA:
                activiCheckBox(this,R.id.cuidador_manha);
                break;
            case TARDE:
                activiCheckBox(this,R.id.cuidador_tarde);
                break;
            case NOITE:
                activiCheckBox(this,R.id.cuidador_noite);
                break;
        }
    }

}
