package br.com.softcare.cuidadores.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.List;

import br.com.softcare.cuidadores.dto.PropostaDTO;
import br.com.softcare.cuidadores.dto.PropostaResponseDTO;
import br.com.softcare.cuidadores.dto.TratamentoDTO;
import gp1.ihc.cuidadores.R;

/**
 * Created by mario on 26/11/16.
 */

public class ContratosAdapter extends BaseAdapter {

    private final Context context;
    private final List<LinkedHashMap> propostas;

    public ContratosAdapter(Context context, List<LinkedHashMap> propostas){
        this.context = context;
        this.propostas = propostas;
    }


    @Override
    public int getCount() {
        return propostas.size();
    }

    @Override
    public Object getItem(int position) {
        return propostas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((Integer)((LinkedHashMap)propostas.get(position)).get("id")).longValue();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinkedHashMap proposta = propostas.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if(convertView == null){
            view = inflater.inflate(R.layout.item_contrato,parent, false);
        }

        TextView cuidadorNome = (TextView)view.findViewById(R.id.contrato_cuidador_nome);
        LinkedHashMap careGiver = (LinkedHashMap)proposta.get("careGiver");
        String name =(String)careGiver.get("name");
        cuidadorNome.setText(name);

        TextView pacienteNome = (TextView)view.findViewById(R.id.contrato_paciente_nome);
        LinkedHashMap patient = (LinkedHashMap)proposta.get("patient");
        String namePatient =(String)patient.get("name");
        pacienteNome.setText(namePatient);


        return view;
    }
}
