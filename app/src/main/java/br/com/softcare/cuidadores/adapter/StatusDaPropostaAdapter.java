package br.com.softcare.cuidadores.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import br.com.softcare.cuidadores.enuns.Perfil;
import br.com.softcare.cuidadores.enuns.Status;
import gp1.ihc.cuidadores.R;

/**
 * Created by mario on 27/11/16.
 */

public class StatusDaPropostaAdapter extends BaseAdapter{
    List<Status> status = Arrays.asList(Status.INITIALIZED,Status.PENDING,Status.ACEPTED,Status.CANCELED,Status.DENIED,Status.FINISHED);
    private Context context;

    public StatusDaPropostaAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return status.size();
    }

    @Override
    public Object getItem(int position) {
        return status.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Status sta = status.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if(convertView == null){
            view = inflater.inflate(R.layout.item_status_proposta, parent, false);
        }
        TextView perfilName = (TextView)view.findViewById(R.id.contrato_status);
        perfilName.setText(sta.getPortName());

        return view;
    }
}
