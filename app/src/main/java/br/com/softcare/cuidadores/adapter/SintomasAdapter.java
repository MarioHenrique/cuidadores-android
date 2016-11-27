package br.com.softcare.cuidadores.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.List;

import br.com.softcare.cuidadores.dto.SintomaDTO;
import gp1.ihc.cuidadores.R;

/**
 * Created by mario on 27/11/16.
 */

public class SintomasAdapter extends BaseAdapter {

    private final Context context;
    private final List<SintomaDTO> sintomas;

    public SintomasAdapter(Context context, List<SintomaDTO> sintomas){
        this.context = context;
        this.sintomas = sintomas;
    }


    @Override
    public int getCount() {
        return sintomas.size();
    }

    @Override
    public Object getItem(int position) {
        return sintomas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return sintomas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SintomaDTO proposta = sintomas.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if(convertView == null){
            view = inflater.inflate(R.layout.item_sintoma,parent, false);
        }

        TextView cuidadorNome = (TextView)view.findViewById(R.id.sintomas_data);
        cuidadorNome.setText(proposta.getDataEvento());
        return view;
    }

}
