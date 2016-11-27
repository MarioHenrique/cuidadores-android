package br.com.softcare.cuidadores.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.softcare.cuidadores.dto.EspecialidadeDTO;
import gp1.ihc.cuidadores.R;

/**
 * Adapter para a lista de especialidades.
 * @author Izaac Silva
 * @since 26/11/2016
 */
public class EspecialidadeAdapter extends BaseAdapter{


    private final Context context;
    private final List<EspecialidadeDTO> especialidades;

    public EspecialidadeAdapter(Context context, List<EspecialidadeDTO> especialidades){
        this.context = context;
        this.especialidades = especialidades;
    }


    @Override
    public int getCount() {
        return especialidades.size();
    }

    @Override
    public Object getItem(int position) {
        return especialidades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return especialidades.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EspecialidadeDTO especialidade = especialidades.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if(convertView == null){
            view = inflater.inflate(R.layout.item_especialidade, parent, false);
        }

        TextView especialidadeNome = (TextView)view.findViewById(R.id.especialidade_nome);
        especialidadeNome.setText(especialidade.getNome());

        return view;
    }
}
