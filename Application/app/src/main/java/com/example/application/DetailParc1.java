package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.application.model.Commentaire;
import com.example.application.model.Parc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailParc1 extends AppCompatActivity {
    private static final String TAG = "DetailParc1";
    private  String nomParc = "";

    private ListView listView;
    private ArrayAdapter<String> adapter;

    private RecyclerView recyclerViewCommentaires;
    private List<Commentaire> commentairesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_parc1);

        getSupportActionBar().hide();

        ImageView imageViewSend = findViewById(R.id.imageViewSend);

        TextView titre = findViewById(R.id.titre);
        TextView descrption = findViewById(R.id.description);
        ImageView image1 = findViewById(R.id.imageViewId);

        SharedPreferences sharedPreferences = getSharedPreferences("SessionPrefs", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("user", "");
        Parc e = new Parc();
        e.getData(new Callback<List<Parc>>() {

            @Override
            public void onResponse(Call<List<Parc>> call, Response<List<Parc>> response) {
                if (response.isSuccessful()) {

                    List<Parc> parcs = response.body();
                    Log.e(TAG, "misy v izy aloooooooooo ???????????? " + parcs.size());
                    titre.setText(parcs.get(0).getNom()+" se trouve à " + parcs.get(0).getLieu());
                    //titre.setText(userId);
                    nomParc = parcs.get(0).getNom() ;
                    descrption.setText(parcs.get(0).getDescription());
                    int resId = getResources().getIdentifier(parcs.get(0).getPhoto(), "drawable", getPackageName());
                    Drawable drawable = getResources().getDrawable(resId);
                    image1.setImageDrawable(drawable);

                } else {
                    Log.e(TAG, "dans else");
                }
            }

            @Override
            public void onFailure(Call<List<Parc>> call, Throwable t) {

            }
        });
        Log.e(TAG, "onCreate: nomParc : Croc Farm");
        // Configurer la RecyclerView

        Commentaire com = new Commentaire();
        com.getCommentairesParc("Croc Farm", new Callback<List<Commentaire>>() {
            @Override
            public void onResponse(Call<List<Commentaire>> call, Response<List<Commentaire>> response) {
                if (response.isSuccessful()) {
                    Log.e(TAG, "taille commentaire ooooo ???????????? " + response.body().size());
                    List<Commentaire> commentaires = response.body();
                    for (int i = 0; i < commentaires.size(); i++) {
                        Commentaire commentaire = new Commentaire();

                        // Récupération des informations du commentaire
                        commentaire.setDate(commentaires.get(i).getDate());
                        commentaire.setText(commentaires.get(i).getText());
                        commentaire.setUser(commentaires.get(i).getUser());

                        // Affichage du contenu dans la console (Logcat)
                        Log.d("Commentaire", "Date: " + commentaire.getDate());
                        Log.d("Commentaire", "Texte: " + commentaire.getText());
                        Log.d("Commentaire", "Utilisateur: " + commentaire.getUser());
                    }
                    //commentaireAdapter.setCommentairesList(commentaires);
                    recyclerViewCommentaires = findViewById(R.id.recyclerViewCommentaires);
                    recyclerViewCommentaires.setLayoutManager(new LinearLayoutManager(DetailParc1.this));

                    // Créer et configurer l'adapter personnalisé pour la RecyclerView
                    CommentaireAdapter  commentaireAdapter = new CommentaireAdapter(commentaires);
                    recyclerViewCommentaires.setAdapter(commentaireAdapter);

                } else {
                    Log.e(TAG, "dans else commentaire");
                }
            }

            @Override
            public void onFailure(Call<List<Commentaire>> call, Throwable t) {

            }
        });

        imageViewSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentDate = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                String formattedDate = sdf.format(currentDate);
                Date date = convertStringToDate(formattedDate);
                // Récupérer le contenu du commentaire depuis l'EditText
                EditText editTextComment = findViewById(R.id.editTextComment);
                String commentaire = editTextComment.getText().toString();

                // Vérifier si le commentaire n'est pas vide
                if (!commentaire.isEmpty()) {
                    Log.e(TAG,"****************");
                    Log.e(TAG, "nomParc: " + nomParc );
                    Log.e(TAG, "date: " + date );
                    Log.e(TAG, "commentaire: " + commentaire );
                    Log.e(TAG, "userId: " + userId );
                    e.insertCommentaireParc(nomParc, date, commentaire, userId, new Parc.InsertCommentaire() {
                        @Override
                        public void onLoginResult(boolean isSuccess) {
                            if (isSuccess) {
                                editTextComment.setText("");
                            } else {
                                Log.e(TAG, "inscription: error");
                            }
                        }
                    });
                    recreate();
                } else {
                    // Afficher un toast ou une boîte de dialogue pour informer l'utilisateur que le commentaire est vide
                    Toast.makeText(DetailParc1.this, "Veuillez saisir un commentaire.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Date convertStringToDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Méthode pour convertir une chaîne de date en format normal
    public static String convertDateStringToNormalFormat(String dateString) {
        String[] inputPatterns = {
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                "EEE MMM dd HH:mm:ss zzz yyyy"
        };
        String outputPattern = "dd MMMM yyyy HH:mm"; // Format souhaité, par exemple : "08 août 2023 23:18"

        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.FRANCE);

        for (String inputPattern : inputPatterns) {
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
            try {
                Date date = inputFormat.parse(dateString);
                return outputFormat.format(date);
            } catch (ParseException e) {
                // Ignorer cette exception et essayer un autre format d'entrée
            }
        }

        return ""; // Gestion de l'erreur en cas d'échec de la conversion
    }

    // Classe Adapter personnalisée pour la RecyclerView
    private class CommentaireAdapter extends RecyclerView.Adapter<CommentaireAdapter.CommentaireViewHolder> {

        private List<Commentaire> commentairesList;

        CommentaireAdapter(List<Commentaire> commentairesList) {
            this.commentairesList = commentairesList;
        }

        public void setCommentairesList(List<Commentaire> commentairesList) {
            this.commentairesList = commentairesList;
            notifyDataSetChanged(); // Notifier la RecyclerView du changement de données
        }

        @NonNull
        @Override
        public CommentaireViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commentaire, parent, false);
            return new CommentaireViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CommentaireViewHolder holder, int position) {
            Commentaire commentaire = commentairesList.get(position);
            holder.textViewUser.setText(commentaire.getUser());
            holder.textViewDate.setText(convertDateStringToNormalFormat(commentaire.getDate()));
            holder.textViewText.setText(commentaire.getText());
        }

        @Override
        public int getItemCount() {
            return commentairesList.size();
        }

        // Classe ViewHolder pour les éléments de la RecyclerView
        class CommentaireViewHolder extends RecyclerView.ViewHolder {

            TextView textViewUser, textViewDate, textViewText;

            CommentaireViewHolder(View itemView) {
                super(itemView);
                textViewUser = itemView.findViewById(R.id.textViewUser);
                textViewDate = itemView.findViewById(R.id.textViewDate);
                textViewText = itemView.findViewById(R.id.textViewText);
            }
        }
    }

}