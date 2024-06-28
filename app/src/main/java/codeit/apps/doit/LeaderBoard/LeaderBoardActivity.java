package codeit.apps.doit.LeaderBoard;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import codeit.apps.doit.R;

public class LeaderBoardActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    List<ScoreData> list;
    ScoreAdapter adapter;
    FirebaseFirestore db;
    TextView myName, myScore, myRank;
    ImageButton chooseSection;
    Dialog dialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_leader_board);
        recyclerView = findViewById(R.id.leaderboard_recycler);
        progressBar = findViewById(R.id.leaderboard_progress);
        myName = findViewById(R.id.your_name);
        myScore = findViewById(R.id.your_score);
        myRank = findViewById(R.id.your_rank);
        db = FirebaseFirestore.getInstance();
        chooseSection = findViewById(R.id.btn_section_choose);

        dialog = new Dialog(LeaderBoardActivity.this);
        dialog.setContentView(R.layout.leaderboard_custom_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));

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
                    adapter = new ScoreAdapter(list, LeaderBoardActivity.this);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                })
                .addOnFailureListener(e -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LeaderBoardActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);


    }
}