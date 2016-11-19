package br.com.softcare.cuidadores.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Set;

import br.com.softcare.cuidadores.dto.Cuidador;
import br.com.softcare.cuidadores.enuns.Disponibilidade;
import br.com.softcare.cuidadores.enuns.Periodo;
import gp1.ihc.cuidadores.R;

public class CuidadorActivity extends AppCompatActivity {

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

        final Cuidador cuidador = (Cuidador)getIntent().getSerializableExtra("cuidador");
        TextView campoCep = (TextView) findViewById(R.id.cuidador_cep);
        campoCep.setText(cuidador.getCep());
        TextView campoNome = (TextView) findViewById(R.id.cuidador_nome);
        campoNome.setText(cuidador.getNome());
        TextView campoTelefone = (TextView) findViewById(R.id.cuidador_telefone);
        campoTelefone.setText(cuidador.getContato());

        TextView campoEmail = (TextView) findViewById(R.id.cuidador_email);
        campoEmail.setText(cuidador.getEmail());

        TextView campoBairro = (TextView) findViewById(R.id.cuidador_bairro);
        campoBairro.setText(cuidador.getBairro());

        TextView campoCidade = (TextView) findViewById(R.id.cuidador_cidade);
        campoCidade.setText(cuidador.getCidade());

        TextView campoEstado = (TextView) findViewById(R.id.cuidador_estado);
        campoEstado.setText(cuidador.getEstado());

        TextView campoRua = (TextView) findViewById(R.id.cuidador_rua);
        campoRua.setText(cuidador.getRua());

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


    private void ativarCheckBoxDisponibilidade(Disponibilidade disp){
        switch (disp){
            case SEGUNDA:
                check(R.id.cuidador_segunda);
                break;
            case TERCA:
                check(R.id.cuidador_terca);
                break;
            case QUARTA:
                check(R.id.cuidador_quarta);
                break;
            case QUINTA:
                check(R.id.cuidador_quinta);
                break;
            case SEXTA:
                check(R.id.cuidador_sexta);
                break;
            case SABADO:
                check(R.id.cuidador_sabado);
                break;
            case DOMINGO:
                check(R.id.cuidador_domingo);
                break;
        }

    }

    private void check(int resource){
        CheckBox check = (CheckBox)findViewById(resource);
        check.setChecked(true);
    }

    private void ativarCheckBoxPeriodo(Periodo per) {
        switch (per){
            case MANHA:
                check(R.id.cuidador_manha);
                break;
            case TARDE:
                check(R.id.cuidador_tarde);
                break;
            case NOITE:
                check(R.id.cuidador_noite);
                break;
        }

    }

}
