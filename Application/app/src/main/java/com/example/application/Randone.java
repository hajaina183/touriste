package com.example.application;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Randone#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Randone extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "VotreFragment";
    private VideoView videoView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Randone() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Randone.
     */
    // TODO: Rename and change types and number of parameters
    public static Randone newInstance(String param1, String param2) {
        Randone fragment = new Randone();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_randone, container, false);

        // Récupération du VideoView depuis le layout du fragment
        //videoView = rootView.findViewById(R.id.videoView);

        // Obtenir le chemin ou l'URL de la vidéo que vous voulez lire
        //String videoPath = "android.resource://" + requireContext().getPackageName() + "/" + R.raw.robe;

        //Uri uri = Uri.parse(videoPath);

        // Définir la source de la vidéo pour le VideoView
        //videoView.setVideoURI(uri);

        // Créer un MediaController en utilisant le contexte du fragment
        //MediaController mediaController = new MediaController(requireContext());

        // Attacher le MediaController au VideoView
        //videoView.setMediaController(mediaController);

        // Démarrer la lecture de la vidéo
        //videoView.start();

        return rootView;
    }
}