package br.com.softcare.cuidadores.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.softcare.cuidadores.activity.MenuActivity;
import gp1.ihc.cuidadores.R;

/**
 * Created by mario on 13/11/16.
 */

public class ImageButtonAdapter extends BaseAdapter{

    private List<Item> mItems = new ArrayList<Item>();
    private final LayoutInflater mInflater;

    public ImageButtonAdapter(Context context,List<Item> mItems) {
        mInflater = LayoutInflater.from(context);
        this.mItems = mItems;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if (v == null) {
            v = mInflater.inflate(R.layout.griditem, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.getTag(R.id.picture);

        Item item = getItem(i);

        picture.setImageResource(item.drawableId);

        return v;
    }

    public static class Item {
        public final int drawableId;

        public Item(int drawableId) {
            this.drawableId = drawableId;
        }
    }

}
