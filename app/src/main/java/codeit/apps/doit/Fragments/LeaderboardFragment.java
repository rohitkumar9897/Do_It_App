package codeit.apps.doit.Fragments;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import codeit.apps.doit.LeaderBoard.ScoreAdapter;
import codeit.apps.doit.LeaderBoard.ScoreData;
import codeit.apps.doit.R;

public class LeaderboardFragment extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    List<ScoreData> list;
    ScoreAdapter adapter;
    FirebaseFirestore db;
    TextView myName, myScore, myRank;
    ImageButton chooseSection;
    Dialog dialog;

    public LeaderboardFragment() {
        // Required empty public constructor
    }
    public static LeaderboardFragment newInstance(String param1, String param2) {
        LeaderboardFragment fragment = new LeaderboardFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_leaderboard, container, false);


        recyclerView = view.findViewById(R.id.leaderboard_recycler);
        progressBar = view.findViewById(R.id.leaderboard_progress);
        myName = view.findViewById(R.id.your_name);
        myScore = view.findViewById(R.id.your_score);
        myRank = view.findViewById(R.id.your_rank);
        db = FirebaseFirestore.getInstance();
        chooseSection = view.findViewById(R.id.btn_section_choose);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.leaderboard_custom_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //dialog.getWindow().setBackgroundDrawable((Drawable) R.drawable.custom_dialog_bg);

        chooseSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        list= new ArrayList<>();


        db.collection("users")
                .orderBy("dailyScore")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        ScoreData data = documentSnapshot.toObject(ScoreData.class);
                        list.add(data);
                    }
                    adapter = new ScoreAdapter(list, getContext());
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                })
                .addOnFailureListener(e -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);






        return view;
    }
}