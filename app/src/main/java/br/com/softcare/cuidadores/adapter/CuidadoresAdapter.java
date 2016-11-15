package br.com.softcare.cuidadores.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import java.util.zip.Inflater;
import br.com.softcare.cuidadores.activity.ListaActivity;
import br.com.softcare.cuidadores.dto.Cuidador;
import br.com.softcare.cuidadores.dto.ListaDeCuidadores;
import gp1.ihc.cuidadores.R;

/**
 * Created by mario on 14/11/16.
 */

public class CuidadoresAdapter extends BaseAdapter{

    private Context context;
    private List<Cuidador> cuidadores;

    public CuidadoresAdapter(Context context, List<Cuidador> cuidadores) {
        this.context = context;
        this.cuidadores = cuidadores;
    }

    @Override
    public int getCount() {
        return cuidadores.size();
    }

    @Override
    public Object getItem(int position) {
        return cuidadores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cuidadores.get(position).getId();
    }

    @Override
    public View getView(int position, View viewConverter, ViewGroup parent) {
        Cuidador cuidador = cuidadores.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = viewConverter;
        if(viewConverter == null){
            view = inflater.inflate(R.layout.item_list,parent,false);
        }
        TextView nome = (TextView)view.findViewById(R.id.item_nome);
        nome.setText(cuidador.getNome());
        TextView telefone = (TextView)view.findViewById(R.id.item_telefone);
        telefone.setText(cuidador.getContato());
        return view;
    }

}
