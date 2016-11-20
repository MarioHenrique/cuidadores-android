package br.com.softcare.cuidadores.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.softcare.cuidadores.client.WebServices;
import br.com.softcare.cuidadores.dto.PacienteDTO;
import gp1.ihc.cuidadores.R;

import static br.com.softcare.cuidadores.utils.Utils.getValorDaTextView;
import static br.com.softcare.cuidadores.utils.Utils.setValorDaTextView;

public class PacienteEditActivity extends Activity {

    private PacienteDTO paciente;
    private Boolean isDeletion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_edit);
        paciente = (PacienteDTO)getIntent().getSerializableExtra("paciente");
        LinearLayout linear = (LinearLayout)findViewById(R.id.paciente_tratamento);
        if(paciente != null) {
            setValorDaTextView(this, R.id.paciente_nome, paciente.getNome());
            setValorDaTextView(this, R.id.paciente_idade, paciente.getDataNascimento());
            setValorDaTextView(this, R.id.paciente_contato, paciente.getContato());
            setValorDaTextView(this, R.id.paciente_saude, paciente.getEstadoDeSaude());
        }else{
            Button button = (Button)findViewById(R.id.paciente_button_action);
            button.setText("Criar");
            Button buttonDeletar = (Button)findViewById(R.id.paciente_button_deletar);
            buttonDeletar.setVisibility(View.GONE);
            linear.setVisibility(View.GONE);
        }
    }

    public void adicionarTratamentos(View view){
        final Intent intent = new Intent(this, TratamentoActivity.class);
        intent.putExtra("paciente",paciente);
        startActivity(intent);
    }


    public void salvarPaciente(View view){
        doInBackground(this);
    }

    public void deletarPaciente(View view){
        new AlertDialog.Builder(this)
                .setMessage("Realmente deseja remover o paciente ?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        isDeletion = true;
                        doInBackground(PacienteEditActivity.this);
                    }
                })
                .setNegativeButton("Não",null)
                .show();
    }

    public void mostrarPicker(View view){
        DatePickerFragment datePicker = new DatePickerFragment();
        Date dataNasc = new Date();
        if(paciente!=null){
            dataNasc = paciente.getDate();
        }
        datePicker.setDataNascimento(dataNasc);
        datePicker.setActivity(this);
        datePicker.show(getSupportFragmentManager(),"Data de nascimento");
    }

    @Override
    protected void operation() throws Exception {
        if(isDeletion){
            WebServices.cuidadores.deletarPaciente(paciente);
            return;
        }

        if(paciente == null)
            paciente = new PacienteDTO();
        paciente.setNome(getValorDaTextView(PacienteEditActivity.this,R.id.paciente_nome));
        paciente.setContato(getValorDaTextView(PacienteEditActivity.this,R.id.paciente_contato));
        paciente.setDataNascimento(getValorDaTextView(PacienteEditActivity.this,R.id.paciente_idade));
        paciente.setEstadoDeSaude(getValorDaTextView(PacienteEditActivity.this,R.id.paciente_saude));
        if(paciente.getId()!=null)
            WebServices.cuidadores.atualizarPaciente(paciente);
        else
            WebServices.cuidadores.criarPaciente(paciente);
    }

    @Override
    protected void onSuccess() {
        finish();
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener  {

        private Date dataNascimento;

        private AppCompatActivity activity;
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
            EditText editText = (EditText)activity.findViewById(R.id.paciente_idade);
            editText.setText(getFormatedDate(year,month,dayOfMonth));
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

        public void setActivity(AppCompatActivity activity) {
            this.activity = activity;
        }

    }

}
