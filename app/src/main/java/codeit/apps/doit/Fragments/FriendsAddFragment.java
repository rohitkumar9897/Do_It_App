package codeit.apps.doit.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import codeit.apps.doit.R;

public class FriendsAddFragment extends Fragment {

    private Button searchBtn, addFriendButton;
    TextInputEditText usernameET;
    FirebaseFirestore db;
    CardView friendCard;
    String searchUsername;
    TextView searchFullName, searchUserName, searchCountry;

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


        searchBtn.setOnClickListener(v -> {

            if(usernameET.getText().toString() == null){
                Toast.makeText(getContext(), "Please enter valid username", Toast.LENGTH_SHORT).show();
            }else{
                searchUsername = usernameET.getText().toString();
                if(searchIfUsernameExists(searchUsername)){
                    friendCard.setVisibility(View.VISIBLE);
                }else{
                    friendCard.setVisibility(View.GONE);
                }
            }
        });





        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_data", Context.MODE_PRIVATE);
                String userName = sharedPreferences.getString("spusername", null);
                Map<String, Object> friendData = new HashMap<>();
                friendData.put("friendsList", Arrays.asList(searchUsername));


                DocumentReference docRef = db.collection("friends").document(userName);
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        List<String> friendsList = new ArrayList<>();
                        if (documentSnapshot.exists()) {
                            // If document exists, get the current friends list
                            friendsList = (List<String>) documentSnapshot.get("friendsList");
                        }
                        // Add the new friend to the list
                        friendsList.add(searchUsername);

                        // Update the friends list in Firestore
                        Map<String, Object> friendData = new HashMap<>();
                        friendData.put("friendsList", friendsList);

                        docRef.set(friendData) // Overwrite the entire document with the updated friends list
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getContext(), "Friend added", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e("Firestore", "Error adding friend", e);
                                        Toast.makeText(getContext(), "Error adding friend", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
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
                        setOnCard(searchUsername);
                        friendCard.setVisibility(View.VISIBLE);
                    }else{
                        exists[0] = false;
                        Toast.makeText(getContext(), "Username doesn't exist", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return exists[0];



    }

    private void setOnCard(String searchUsername) {
        db.collection("users").document(searchUsername).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){


                }
            }
        });
    }
}