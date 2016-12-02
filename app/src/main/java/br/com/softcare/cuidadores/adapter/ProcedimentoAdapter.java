package br.com.softcare.cuidadores.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.softcare.cuidadores.dto.ProcedimentoDTO;
import br.com.softcare.cuidadores.dto.TratamentoDTO;
import gp1.ihc.cuidadores.R;

/**
 * Created by mario on 27/11/16.
 */

public class ProcedimentoAdapter extends BaseAdapter{

    private final Context context;
    private final List<ProcedimentoDTO> procedimentoDTO;

    public ProcedimentoAdapter(Context context, List<ProcedimentoDTO> procedimentoDTO){
        this.context = context;
        this.procedimentoDTO = procedimentoDTO;
    }


    @Override
    public int getCount() {
        return procedimentoDTO.size();
    }

    @Override
    public Object getItem(int position) {
        return procedimentoDTO.get(position);
    }

    @Override
    public long getItemId(int position) {
        return procedimentoDTO.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProcedimentoDTO procedimento = procedimentoDTO.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if(convertView == null){
            view = inflater.inflate(R.layout.item_procedimento, parent, false);
        }

        TextView pacienteNome = (TextView)view.findViewById(R.id.procedimento_nome);
        pacienteNome.setText(procedimento.getNome());

        return view;
    }

}
