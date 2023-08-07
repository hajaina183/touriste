package com.example.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> labels;
    private List<Integer> images;
    boolean nightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

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
        Switch switcher = listItemView.findViewById(R.id.switcher);
        LinearLayout switcherContainer = listItemView.findViewById(R.id.switcherContainer);

        sharedPreferences = context.getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false); // light mode default

        if(nightMode){
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nightMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);
                }
                editor.apply();
            }
        });

        imageView.setImageResource(images.get(position));

        // Afficher le switcher uniquement pour la deuxième ligne (position == 1)
        if (position == 0) {
            switcherContainer.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE); // Masquer le libellé pour la deuxième ligne
        } else {
            switcherContainer.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE); // Afficher le libellé pour les autres lignes
            textView.setText(labels.get(position));
        }

        // Vous pouvez ajouter un écouteur au switcher ici pour traiter son état si nécessaire

        return listItemView;
    }
}
