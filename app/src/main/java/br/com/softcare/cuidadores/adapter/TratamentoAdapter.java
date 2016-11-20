package br.com.softcare.cuidadores.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.softcare.cuidadores.dto.PacienteDTO;
import br.com.softcare.cuidadores.dto.TratamentoDTO;
import gp1.ihc.cuidadores.R;

/**
 * Created by mario on 20/11/16.
 */

public class TratamentoAdapter extends BaseAdapter{


    private final Context context;
    private final List<TratamentoDTO> tratamentos;

    public TratamentoAdapter(Context context, List<TratamentoDTO> tratamentos){
        this.context = context;
        this.tratamentos = tratamentos;
    }


    @Override
    public int getCount() {
        return tratamentos.size();
    }

    @Override
    public Object getItem(int position) {
        return tratamentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tratamentos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TratamentoDTO tratamento = tratamentos.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if(convertView == null){
            view = inflater.inflate(R.layout.item_tratamento, parent, false);
        }

        TextView pacienteNome = (TextView)view.findViewById(R.id.tratamento_nome);
        pacienteNome.setText(tratamento.getNome());

        return view;
    }
}
