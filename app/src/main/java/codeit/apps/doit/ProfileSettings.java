package codeit.apps.doit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
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
        boolean isSignUp = false;

        sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);

        if(!isSignUp){
            setValues();
            profileSettingsUserNameET.setEnabled(false);
            profileSettingsUserNameET.setFocusable(false);


        }





        btnUpdateProfile.setOnClickListener(v -> {
            String name = String.valueOf(profileSettingsNameET.getText());
            String userName = String.valueOf(profileSettingsUserNameET.getText());
            String age = String.valueOf(profileSettingsAgeET.getText());
            String country = String.valueOf(profileSettingsCountryET.getText());

            if(isSignUp){
                createAccount(name, userName, age, country);
            }else{

                pushToFireBase(name, userName, age, country);
            }
            Intent intent = new Intent(ProfileSettings.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        SelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickImageFromGallery();
            }

        });
    }

    private void pushToFireBase (String name, String userName, String age, String country) {
        updateSharedPreference(name, userName, age, country);

        db.collection("users").document(userName).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                Map<String, Object> userUpdates = new HashMap<>();
                userUpdates.put("name", name);
                userUpdates.put("age", age);
                userUpdates.put("country", country);
                db.collection("users").document(userName).update(userUpdates).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ProfileSettings.this, "success", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProfileSettings.this, "some error occurred", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    private void createAccount(String name, String userName, String age, String country) {



        db.collection("users").document(userName).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
                } else {
                    Map<String, Object> user = new HashMap<>();
                    user.put("username", userName);
                    user.put("name", name);
                    user.put("age",age);
                    user.put("country", country);
                    user.put("score", 0);
                    db.collection("users").document(userName).set(user)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                                updateSharedPreference(name, userName, age, country);
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




    private void setValues(){

        profileSettingsUserNameET.setText(sharedPreferences.getString("spusername", null));
        profileSettingsNameET.setText(sharedPreferences.getString("spname", null));
        profileSettingsAgeET.setText(sharedPreferences.getString("spage", null));
        profileSettingsCountryET.setText(sharedPreferences.getString("spcountry", null));

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