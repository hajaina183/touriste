package com.example.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> labels;
    private List<Integer> images;

    public CustomListAdapter(Context context, List<String> labels, List<Integer> images) {
        super(context, R.layout.list_item_layout, labels);
        this.context = context;
        this.labels = labels;
        this.images = images;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);
        }

        ImageView imageView = listItemView.findViewById(R.id.imageView);
        TextView textView = listItemView.findViewById(R.id.textView);

        imageView.setImageResource(images.get(position));
        textView.setText(labels.get(position));

        return listItemView;
    }
}
