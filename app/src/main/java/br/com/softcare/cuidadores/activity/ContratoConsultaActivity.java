package br.com.softcare.cuidadores.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import br.com.softcare.cuidadores.client.WebServices;
import br.com.softcare.cuidadores.dto.LinkedMapTransferDTO;
import br.com.softcare.cuidadores.dto.Usuario;
import br.com.softcare.cuidadores.enuns.Disponibilidade;
import br.com.softcare.cuidadores.enuns.Perfil;
import br.com.softcare.cuidadores.enuns.Periodo;
import br.com.softcare.cuidadores.enuns.Status;
import gp1.ihc.cuidadores.R;

import static android.R.attr.id;
import static br.com.softcare.cuidadores.utils.Utils.activiCheckBox;

public class ContratoConsultaActivity extends Activity {

    private LinkedMapTransferDTO contrato;
    private HashMap patient;
    private HashMap careGiver;
    private Usuario usuarioLogado;
    private Status statusOperation;
    private Status statusAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contrato_consulta);
        usuarioLogado = (Usuario)getIntent().getSerializableExtra("usuario");
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
        statusAtual = Status.valueOf((String)contrato.getLinked().get("status"));
        List<String> disponibilidade = (List)contrato.getLinked().get("availability");
        if(disponibilidade!=null && !disponibilidade.isEmpty()) {
            for (String disp : disponibilidade) {
                ativarCheckBoxDisponibilidade(Disponibilidade.getByName(disp));
            }
        }
        List<String> periodo = (List)contrato.getLinked().get("period");
        if(periodo!=null && !periodo.isEmpty()) {
            for (String per : periodo) {
                ativarCheckBoxPeriodo(Periodo.getByName(per));
            }
        }
    }

    private void ativarCheckBoxDisponibilidade(Disponibilidade disp){
        switch (disp){
            case SEGUNDA:
                activiCheckBox(this,R.id.contrato_visualizar_segunda);
                break;
            case TERCA:
                activiCheckBox(this,R.id.contrato_visualizar_terca);
                break;
            case QUARTA:
                activiCheckBox(this,R.id.contrato_visualizar_quarta);
                break;
            case QUINTA:
                activiCheckBox(this,R.id.contrato_visualizar_quinta);
                break;
            case SEXTA:
                activiCheckBox(this,R.id.contrato_visualizar_sexta);
                break;
            case SABADO:
                activiCheckBox(this,R.id.contrato_visualizar_sabado);
                break;
            case DOMINGO:
                activiCheckBox(this,R.id.contrato_visualizar_domingo);
                break;
        }

    }

    private void ativarCheckBoxPeriodo(Periodo per) {
        switch (per){
            case MANHA:
                activiCheckBox(this,R.id.contrato_visualizar_manha);
                break;
            case TARDE:
                activiCheckBox(this,R.id.contrato_visualizar_tarde);
                break;
            case NOITE:
                activiCheckBox(this,R.id.contrato_visualizar_noite);
                break;
        }

    }

    public void adicionarProcedimentos(View view){
        final Intent intent = new Intent(this, ProcedimentoActivity.class);
        intent.putExtra("usuario",usuarioLogado);
        intent.putExtra("contratoId",Long.valueOf(String.valueOf(contrato.getLinked().get("id"))));
        startActivity(intent);
    }

    public void adicionarSintomas(View view){
        final Intent intent = new Intent(this, SintomesActivity.class);
        intent.putExtra("usuario",usuarioLogado);
        intent.putExtra("contratoId",Long.valueOf(String.valueOf(contrato.getLinked().get("id"))));
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText status = (EditText)findViewById(R.id.contrato_consulta_status);
        status.setText(statusAtual.getDescricao());
        validarCicloDeVicaDoContrato(statusAtual);
    }

    private void desabilitarBotao(int resource){
        ((Button)findViewById(resource)).setVisibility(View.GONE);
    }

    private void habilitarBotao(int resource){
        ((Button)findViewById(resource)).setVisibility(View.VISIBLE);
    }

    private void validarCicloDeVicaDoContrato(Status statusContrato) {
        desabilitarBotao(R.id.contrato_button_status_aprovar);
        desabilitarBotao(R.id.contrato_button_status_negar);
        desabilitarBotao(R.id.contrato_button_status_cancelar);
        desabilitarBotao(R.id.contrato_button_status_iniciar);
        desabilitarBotao(R.id.contrato_button_status_finalizar);
        LinearLayout sintomas = (LinearLayout)findViewById(R.id.contrato_sintomas);
        LinearLayout procedimentos = (LinearLayout)findViewById(R.id.contrato_procedimento);
        sintomas.setVisibility(View.GONE);
        procedimentos.setVisibility(View.GONE);

        if(usuarioLogado.getPerfil().contains(Perfil.RESPONSAVEL)){
            cicloDoResponsavel(statusContrato);
        }else{
            cicloDoCuidador(statusContrato);
        }

        if(statusContrato.equals(Status.INITIALIZED) || (usuarioLogado.getPerfil().contains(Perfil.RESPONSAVEL) && statusContrato.equals(Status.CANCELED))){
            sintomas.setVisibility(View.VISIBLE);
            procedimentos.setVisibility(View.VISIBLE);
        }

    }

    private void cicloDoCuidador(Status statusContrato) {
        if(statusContrato.equals(Status.PENDING)){
            habilitarBotao(R.id.contrato_button_status_aprovar);
            habilitarBotao(R.id.contrato_button_status_negar);
        }else if(statusContrato.equals(Status.INITIALIZED)){
            habilitarBotao(R.id.contrato_button_status_cancelar);
        }
    }


    private void cicloDoResponsavel(Status statusContrato) {
        if(statusContrato.equals(Status.ACEPTED)){
            habilitarBotao(R.id.contrato_button_status_iniciar);
            habilitarBotao(R.id.contrato_button_status_cancelar);
        }else if(statusContrato.equals(Status.INITIALIZED)){
            habilitarBotao(R.id.contrato_button_status_finalizar);
        }
    }

    public void mudar(String mensagem, final Status status){
        new AlertDialog.Builder(this)
                .setMessage(mensagem)
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        statusOperation = status;
                        doInBackground(ContratoConsultaActivity.this);
                    }
                })
                .setNegativeButton("Não",null)
                .show();
    }

    public void aprovar(View view){
        mudar("Deseja realmente aprovar o contrato? ",Status.ACEPTED);
    }

    public void negar(View view){
        mudar("Deseja realmente reprovar o contrato? ",Status.DENIED);
    }

    public void cancelar(View view){
        mudar("Deseja realmente cancelar o contrato? ",Status.CANCELED);
    }

    public void iniciar(View view){
        mudar("Deseja realmente iniciar o contrato? ",Status.INITIALIZED);
    }

    public void finalizar(View view){
        mudar("Deseja realmente finalizar o contrato? ",Status.FINISHED);
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

    @Override
    protected void operation() throws Exception {
        WebServices.cuidadores.mudarStatusDoContrato(Long.valueOf(String.valueOf(contrato.getLinked().get("id"))),statusOperation.toString());
    }

    @Override
    protected void onSuccess() {
        Toast.makeText(this,"Mudança do status do contrato realizado com sucesso!",Toast.LENGTH_LONG).show();
        statusAtual = statusOperation;
       ContratoConsultaActivity.this.onResume();
    }
}
