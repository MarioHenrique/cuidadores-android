package br.com.softcare.cuidadores.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static br.com.softcare.cuidadores.utils.Utils.*;
import java.util.Date;

import br.com.softcare.cuidadores.client.WebServices;
import br.com.softcare.cuidadores.dto.Cuidador;
import br.com.softcare.cuidadores.dto.PacienteDTO;
import br.com.softcare.cuidadores.dto.PropostaDTO;
import br.com.softcare.cuidadores.dto.Usuario;
import br.com.softcare.cuidadores.dto.UsuarioAlteracao;
import br.com.softcare.cuidadores.enuns.Disponibilidade;
import br.com.softcare.cuidadores.enuns.Periodo;
import gp1.ihc.cuidadores.R;

public class NovoContratoActivity extends Activity {

    private PacienteDTO paciente = null;
    private Cuidador cuidador = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_contrato);
        cuidador = (Cuidador)getIntent().getSerializableExtra("cuidador");
    }

    public void entrataDataInicial(View view){
        DatePickerFragment datePicker = new DatePickerFragment();
        Date dataNasc = new Date();
        datePicker.setDataNascimento(dataNasc);
        EditText text = (EditText)findViewById(R.id.contrato_data_inicio);
        datePicker.setCampo(text);
        datePicker.show(getSupportFragmentManager(),"Data de nascimento");
    }

    public void entradaDataFinal(View view){
        DatePickerFragment datePicker = new DatePickerFragment();
        Date dataNasc = new Date();
        datePicker.setDataNascimento(dataNasc);
        EditText text = (EditText)findViewById(R.id.contrato_data_final);
        datePicker.setCampo(text);
        datePicker.show(getSupportFragmentManager(),"Data de nascimento");
    }

    public void adicionarPaciente(View view){
        Intent escolhaPaciente = new Intent(this,EscolhaPacienteActivity.class);
        startActivityForResult(escolhaPaciente,1);
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener  {

        private Date dataNascimento;

        private EditText campo;
        public void setDataNascimento(Date dataNascimento) {
            this.dataNascimento = dataNascimento;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new DatePickerDialog(getActivity(), this, dataNascimento.getYear()+1900,dataNascimento.getMonth(),dataNascimento.getDate());
        }

        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
            campo.setText(getFormatedDate(year,month,dayOfMonth));
        }

        private String getFormatedDate(int year,int month,int dayOfMonth){
            String yearStr = String.valueOf(year);
            String monthStr = getTwoDecimal(String.valueOf(month+1));
            String dayOfMonthStr = getTwoDecimal(String.valueOf(dayOfMonth));
            return dayOfMonthStr+"/"+monthStr+"/"+yearStr;
        }

        private String getTwoDecimal(String decimal){
            if(decimal.length() == 1)
                decimal = "0"+decimal;
            return decimal;
        }

        public void setCampo(EditText campo) {
            this.campo = campo;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                PacienteDTO paciente= (PacienteDTO)data.getSerializableExtra("pacienteAdicionado");
                this.paciente = paciente;
                setValorDaTextView(this,R.id.contrato_paciente_nome,paciente.getNome());
            }
        }

    }

    private void setPeriodo(PropostaDTO proposta) {
        if(isCheckBoxChecked(this,R.id.contrato_manha)){
            proposta.adicionarPeriodo(Periodo.MANHA);
        }
        if(isCheckBoxChecked(this,R.id.contrato_tarde)){
            proposta.adicionarPeriodo(Periodo.TARDE);
        }
        if(isCheckBoxChecked(this,R.id.contrato_noite)){
            proposta.adicionarPeriodo(Periodo.NOITE);
        }
    }

    private void setarDisponibilidate(PropostaDTO proposta) {
        if(isCheckBoxChecked(this,R.id.contrato_segunda)){
            proposta.adicionarDisponibilidade(Disponibilidade.SEGUNDA);
        }
        if(isCheckBoxChecked(this,R.id.contrato_terca)){
            proposta.adicionarDisponibilidade(Disponibilidade.TERCA);
        }
        if(isCheckBoxChecked(this,R.id.contrato_quarta)){
            proposta.adicionarDisponibilidade(Disponibilidade.QUARTA);
        }
        if(isCheckBoxChecked(this,R.id.contrato_quinta)){
            proposta.adicionarDisponibilidade(Disponibilidade.QUINTA);
        }
        if(isCheckBoxChecked(this,R.id.contrato_sexta)){
            proposta.adicionarDisponibilidade(Disponibilidade.SEXTA);
        }
        if(isCheckBoxChecked(this,R.id.contrato_sabado)){
            proposta.adicionarDisponibilidade(Disponibilidade.SABADO);
        }
        if(isCheckBoxChecked(this,R.id.contrato_domingo)){
            proposta.adicionarDisponibilidade(Disponibilidade.DOMINGO);
        }
    }

    public void contratar(View view){
        doInBackground(this);
    }

    public void cancelar(View view){
        finish();
    }

    @Override
    protected void operation() throws Exception {
        final PropostaDTO proposta = new PropostaDTO();
        setPeriodo(proposta);
        setarDisponibilidate(proposta);
        proposta.setDataInicial(getValorDaTextView(this,R.id.contrato_data_inicio));
        proposta.setDataFinal(getValorDaTextView(this,R.id.contrato_data_final));
        proposta.setCuidadorId(cuidador.getId());
        proposta.setPacienteId(paciente.getId());
        WebServices.cuidadores.adicionarProposta(proposta);
    }


    @Override
    protected void onSuccess()
    {
        Toast.makeText(this,"Contrato criado com sucesso!",Toast.LENGTH_LONG);
        finish();
    }

}
