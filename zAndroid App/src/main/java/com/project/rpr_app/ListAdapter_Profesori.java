package com.project.rpr_app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter_Profesori extends ArrayAdapter {

    private Activity mContext;
    List<Profesor> profesoriList;

    public ListAdapter_Profesori(Activity mContext, List<Profesor> profesoriList)
    {
        super(mContext,R.layout.list_item_profesori,profesoriList);
        this.mContext = mContext;
        this.profesoriList = profesoriList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item_profesori,null,true);

        TextView mNume = listItemView.findViewById(R.id.nume_item_profesor);
        TextView mScoala = listItemView.findViewById(R.id.scoala_item_profesor);
        TextView mMaterie = listItemView.findViewById(R.id.materie_item_profesor);

        Profesor profesor = profesoriList.get(position);

        mNume.setText(profesor.getNume());
        mScoala.setText(profesor.getScoala());
        mMaterie.setText(profesor.getMaterie());

        return listItemView;
    }
}
