package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application.model.Commentaire;
import com.example.application.model.Parc;
import com.example.application.model.Site;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSite3 extends AppCompatActivity {

    private  String nomParc = "";
    private RecyclerView recyclerViewCommentaires;
    private static final String TAG = "DetailSite1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_site3);

        getSupportActionBar().hide();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.footerFragment, new FooterFragment())
                    .commit();
        }

        ImageView imageViewSend = findViewById(R.id.imageViewSend);

        TextView titre = findViewById(R.id.titre);
        TextView descrption = findViewById(R.id.description);
        ImageView image1 = findViewById(R.id.imageViewId);

        SharedPreferences sharedPreferences = getSharedPreferences("SessionPrefs", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("user", "");
        Site s = new Site();
        s.getData(new Callback<List<Site>>() {
            @Override
            public void onResponse(Call<List<Site>> call, Response<List<Site>> response) {
                if (response.isSuccessful()) {

                    List<Site> sites = response.body();
                    titre.setText(sites.get(2).getNom()+" se trouve à " + sites.get(2).getLieu());
                    //titre.setText(userId);
                    nomParc = sites.get(2).getNom() ;
                    descrption.setText(sites.get(2).getDescription());
                    int resId = getResources().getIdentifier(sites.get(2).getPhoto(), "drawable", getPackageName());
                    Drawable drawable = getResources().getDrawable(resId);
                    image1.setImageDrawable(drawable);


                } else {
                    Log.e(TAG, "dans else");
                }

            }

            @Override
            public void onFailure(Call<List<Site>> call, Throwable t) {

            }
        });

        Commentaire com = new Commentaire();
        com.getCommentairesSite("Le palais royal de Manjakamiadana", new Callback<List<Commentaire>>() {
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
                    }
                    //commentaireAdapter.setCommentairesList(commentaires);
                    recyclerViewCommentaires = findViewById(R.id.recyclerViewCommentaires);
                    recyclerViewCommentaires.setLayoutManager(new LinearLayoutManager(DetailSite3.this));

                    // Créer et configurer l'adapter personnalisé pour la RecyclerView
                    DetailSite3.CommentaireAdapter commentaireAdapter = new DetailSite3.CommentaireAdapter(commentaires);
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
                    s.insertCommentaireSite(nomParc, date, commentaire, userId, new Site.InsertCommentaire() {
                        @Override
                        public void onLoginResult(boolean isSuccess) {
                            if (isSuccess) {
                                editTextComment.setText(" ");
                            } else {
                                Log.e(TAG, "inscription: error");
                            }
                        }
                    });
                } else {
                    // Afficher un toast ou une boîte de dialogue pour informer l'utilisateur que le commentaire est vide
                    Toast.makeText(DetailSite3.this, "Veuillez saisir un commentaire.", Toast.LENGTH_SHORT).show();
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
    private class CommentaireAdapter extends RecyclerView.Adapter<DetailSite3.CommentaireAdapter.CommentaireViewHolder> {

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
        public DetailSite3.CommentaireAdapter.CommentaireViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commentaire, parent, false);
            return new DetailSite3.CommentaireAdapter.CommentaireViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DetailSite3.CommentaireAdapter.CommentaireViewHolder holder, int position) {
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