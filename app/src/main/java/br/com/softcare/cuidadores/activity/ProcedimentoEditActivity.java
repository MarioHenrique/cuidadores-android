package br.com.softcare.cuidadores.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.softcare.cuidadores.client.WebServices;
import br.com.softcare.cuidadores.dto.ProcedimentoDTO;
import br.com.softcare.cuidadores.dto.Usuario;
import br.com.softcare.cuidadores.enuns.Perfil;
import gp1.ihc.cuidadores.R;
import static br.com.softcare.cuidadores.utils.Utils.*;
import static br.com.softcare.cuidadores.utils.Utils.getValorDaTextView;


public class ProcedimentoEditActivity extends Activity {

    private Long contratoId = null;
    private Usuario usuarioLogado;
    private ProcedimentoDTO procedimento = null;
    private Boolean deleteOperation = Boolean.FALSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedimento_edit);
        contratoId = getIntent().getLongExtra("contratoId", 0L);
        usuarioLogado = (Usuario)getIntent().getSerializableExtra("usuario");
        procedimento = (ProcedimentoDTO)getIntent().getSerializableExtra("procedimento");
        verificarPermissao(usuarioLogado);
        if(procedimento!=null){
            setValorDaTextView(this,R.id.procedimento_nome,procedimento.getNome());
            setValorDaTextView(this,R.id.procedimento_descricao,procedimento.getDescricao());
            setValorDaTextView(this,R.id.procedimento_localizacao,procedimento.getLocal());
            final SimpleDateFormat sdfStr = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            try{
                final Date dataInicial = sdfStr.parse(procedimento.getHorarioInicial());
                final Date dataFinal = sdfStr.parse(procedimento.getHorarioFinal());
                setValorDaTextView(this,R.id.procedimento_inicio,TimePickerFragment.getTwoDecimal(String.valueOf(dataInicial.getHours()))+":"+TimePickerFragment.getTwoDecimal(String.valueOf(dataInicial.getMinutes())));
                setValorDaTextView(this,R.id.procedimento_fim,TimePickerFragment.getTwoDecimal(String.valueOf(dataFinal.getHours()))+":"+TimePickerFragment.getTwoDecimal(String.valueOf(dataFinal.getMinutes())));
            }catch(ParseException e){}
        }else{
            Button delete= (Button)findViewById(R.id.procedimento_button_deletar);
            delete.setVisibility(View.GONE);
        }
    }

    private void verificarPermissao(Usuario usuarioLogado) {
        if(usuarioLogado.getPerfil().contains(Perfil.RESPONSAVEL)){
            EditText nome = (EditText)findViewById(R.id.procedimento_nome);
            nome.setEnabled(false);
            EditText desc = (EditText)findViewById(R.id.procedimento_descricao);
            desc.setEnabled(false);
            EditText local = (EditText)findViewById(R.id.procedimento_localizacao);
            local.setEnabled(false);
            EditText inicio = (EditText)findViewById(R.id.procedimento_inicio);
            inicio.setEnabled(false);
            EditText fim = (EditText)findViewById(R.id.procedimento_fim);
            fim.setEnabled(false);
            Button deletar = (Button)findViewById(R.id.procedimento_button_deletar);
            deletar.setVisibility(View.GONE);
            Button salvar = (Button)findViewById(R.id.procedimento_button_action);
            salvar.setVisibility(View.GONE);
            ImageButton btInicio = (ImageButton)findViewById(R.id.procedimento_button_inicio);
            btInicio.setVisibility(View.GONE);
            ImageButton btFim = (ImageButton)findViewById(R.id.procedimento_button_fim);
            btFim.setVisibility(View.GONE);
        }
    }

    public void horarioInicial(View view){
        final TimePickerFragment timePicker = new TimePickerFragment();
        timePicker.setCampo((EditText)findViewById(R.id.procedimento_inicio));
        final Date agora = new Date();
        timePicker.setHorario(agora.getHours(),agora.getMinutes());
        timePicker.show(getSupportFragmentManager(),"Inicio do procedimento");
    }

    public void horarioFinal(View view){
        final TimePickerFragment timePicker = new TimePickerFragment();
        timePicker.setCampo((EditText)findViewById(R.id.procedimento_fim));
        final Date agora = new Date();
        timePicker.setHorario(agora.getHours(),agora.getMinutes());
        timePicker.show(getSupportFragmentManager(),"Final do procedimento");
    }


    public void salvar(View view){
        doInBackground(this);
    }

    public void deletar(View view){
        new AlertDialog.Builder(this)
                .setMessage("Realmente deseja remover o procedimento ?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteOperation = Boolean.TRUE;
                        doInBackground(ProcedimentoEditActivity.this);
                    }
                })
                .setNegativeButton("Não",null)
                .show();
    }

    @Override
    protected void operation() throws Exception {
        if(deleteOperation){
            WebServices.cuidadores.deletarProcedimento(contratoId,procedimento.getId());
        }else {
            ProcedimentoDTO procedimentoNovo = new ProcedimentoDTO();
            if (procedimento != null) {
                procedimentoNovo = procedimento;
            }
            procedimentoNovo.setNome(getValorDaTextView(this, R.id.procedimento_nome));
            procedimentoNovo.setDescricao(getValorDaTextView(this, R.id.procedimento_descricao));
            procedimentoNovo.setLocal(getValorDaTextView(this, R.id.procedimento_localizacao));
            Date agora = new Date();
            final Calendar calc = Calendar.getInstance();
            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            final SimpleDateFormat sdfStr = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            try {
                final Date dataInitial = sdf.parse(getValorDaTextView(this, R.id.procedimento_inicio));
                final Date dataFinal = sdf.parse(getValorDaTextView(this, R.id.procedimento_fim));
                if (dataInitial.after(dataFinal)) {
                    calc.setTime(dataInitial);
                    calc.add(Calendar.DAY_OF_MONTH, 1);
                }
                dataInitial.setYear(agora.getYear());
                dataInitial.setMonth(agora.getMonth());
                dataInitial.setDate(agora.getDate());
                dataFinal.setYear(agora.getYear());
                dataFinal.setMonth(agora.getMonth());
                dataFinal.setDate(agora.getDate());
                procedimentoNovo.setHorarioInicial(sdfStr.format(dataInitial));
                procedimentoNovo.setHorarioFinal(sdfStr.format(dataFinal));
            } catch (ParseException e) {
            }
            if (procedimento == null) {
                WebServices.cuidadores.adicionarProcedimento(contratoId, procedimentoNovo);
            } else {
                WebServices.cuidadores.atualizarProcedimento(contratoId, procedimentoNovo);
            }
        }
    }

    @Override
    protected void onSuccess() {
        finish();
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener  {

        private EditText campo;
        private int hour;
        private int minute;

        public void setHorario(int hour,int minute) {
            this.hour = hour;
            this.minute = minute;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new TimePickerDialog(getActivity(),this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }

        private String getFormatedDate(int hourOfDay,int minute){
            String hourStr = getTwoDecimal(String.valueOf(hourOfDay));
            String minutStr = getTwoDecimal(String.valueOf(minute));
            return hourStr+":"+minutStr;
        }

        public static String getTwoDecimal(String decimal){
            if(decimal.length() == 1)
                decimal = "0"+decimal;
            return decimal;
        }

        public void setCampo(EditText campo) {
            this.campo = campo;
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            campo.setText(getFormatedDate(hourOfDay,minute));
        }
    }

}
