package codeit.apps.doit.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import codeit.apps.doit.R;

public class FriendsAddFragment extends Fragment {

    private Button searchBtn, addFriendButton;
    TextInputEditText usernameET;
    FirebaseFirestore db;
    CardView friendCard;

    public FriendsAddFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friends_add, container, false);
        searchBtn = view.findViewById(R.id.add_friend_search_btn);
        usernameET = view.findViewById(R.id.add_friend_ET);
        friendCard = view.findViewById(R.id.add_friend_card);
        addFriendButton = view.findViewById(R.id.add_friend_add_btn);
        db = FirebaseFirestore.getInstance();


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(usernameET.getText().toString() == null){
                    Toast.makeText(getContext(), "Please enter valid username", Toast.LENGTH_SHORT).show();

                }else{
                    String searchUsername = usernameET.getText().toString();

                    if(searchIfUsernameExists(searchUsername)){
                        friendCard.setVisibility(View.VISIBLE);


                    }else{
                        friendCard.setVisibility(View.GONE);

                    }

                }



            }
        });





        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });





        return view;
    }

    private Boolean searchIfUsernameExists(String searchUsername) {

        Boolean[] exists = {false};

        db.collection("users").document(searchUsername).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()){
                        exists[0] = true;
                    }else{
                        exists[0] = false;
                    }
                }else{
                    Toast.makeText(getContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return exists[0];



    }
}