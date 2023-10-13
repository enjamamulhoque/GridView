package com.enjamamulhoque.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GridViewAdapter extends ArrayAdapter<ModelUser> {


    public GridViewAdapter(@NonNull Context context, int resource, @NonNull List<ModelUser> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View gridItemView = convertView;

        if(gridItemView == null){
            gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_gridview_layout, parent, false);
        }


        ModelUser modelUser = getItem(position);

        TextView nameView = gridItemView.findViewById(R.id.item_gridview_name);
        TextView professionView = gridItemView.findViewById(R.id.item_gridview_profession);

        nameView.setText(modelUser.getName());
        professionView.setText(modelUser.getProfession());

        return gridItemView;
    }
}
