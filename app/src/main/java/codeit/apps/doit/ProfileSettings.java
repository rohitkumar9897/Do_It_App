package codeit.apps.doit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProfileSettings extends AppCompatActivity {
    private final int GALLARY_REQ_CODE = 1000;
    private MaterialCardView SelectPhoto;
    private Uri ImageUri;
    ImageView imgProfile;
    private Button btnUpdateProfile;
    FirebaseFirestore db;
    TextInputEditText profileSettingsNameET, profileSettingsAgeET, profileSettingsUserNameET, profileSettingsCountryET;
    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        SelectPhoto= findViewById(R.id.profileimg);
        imgProfile= findViewById(R.id.imgprofile);
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile);
        profileSettingsNameET = findViewById(R.id.edit_text_name);
        profileSettingsAgeET = findViewById(R.id.edit_text_age);
        profileSettingsUserNameET = findViewById(R.id.edit_text_username);
        profileSettingsCountryET= findViewById(R.id.edit_text_country);
        db = FirebaseFirestore.getInstance();

        sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);



        btnUpdateProfile.setOnClickListener(v -> {
            String name = String.valueOf(profileSettingsNameET.getText());
            String userName = String.valueOf(profileSettingsUserNameET.getText());
            String age = String.valueOf(profileSettingsAgeET.getText());
            String country = String.valueOf(profileSettingsCountryET.getText());

            updateSharedPreference(name, userName, age, country);
            pushToFireBase(name, userName, age, country);

        });
        SelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickImageFromGallery();
            }

        });
    }

    private void pushToFireBase(String name, String userName, String age, String country) {
        Log.d("priyanshu","-1");



        db.collection("users").document(userName).get().addOnCompleteListener(task -> {
            Log.d("priyanshu","1");
            if (task.isSuccessful()) {
                Log.d("priyanshu","2");
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Log.d("priyanshu","3");
                    Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("priyanshu","4");
                    Map<String, Object> user = new HashMap<>();
                    user.put("username", userName);
                    user.put("name", name);
                    user.put("age",age);
                    user.put("country", country);
                    user.put("score", 0);
                    db.collection("users").document(userName).set(user)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                                // User added successfully
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                                // Handle failure
                            });
                }
            } else {
                Toast.makeText(this, "some error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateSharedPreference(String name, String userName, String age, String country) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("spname", name);
        editor.putString("spusername", userName);
        editor.putString("spage",age);
        editor.putString("spcountry", country);
        editor.apply();
    }

    private void PickImageFromGallery() {
        Intent iGallery= new Intent(Intent.ACTION_PICK);
        iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(iGallery, GALLARY_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== RESULT_OK){

            if(requestCode==GALLARY_REQ_CODE){
                // for Gallery

                imgProfile.setImageURI(data.getData());
            }
        }
    }
}