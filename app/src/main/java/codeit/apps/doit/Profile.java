package codeit.apps.doit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    TextView shareBtn, logoutBtn, profileSettings, profileHey;
    AppCompatButton backBtn;
    SharedPreferences sharedPreferences;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);

        profileSettings= findViewById(R.id.profile_settings_btn);
        logoutBtn= findViewById(R.id.btn_logout);
        backBtn= findViewById(R.id.btn_back_profile);
        profileHey = findViewById(R.id.profile_hey);

        shareBtn = findViewById(R.id.profile_share);
        shareBtn.setOnClickListener(v -> {

            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String shareMessage = "Hey There! Let's make our days more productive with this amazing app : LINK HERE";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            Intent chooserIntent = Intent.createChooser(shareIntent, "Share via");
            if (shareIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooserIntent);
            } else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }


        });

        profileHey.setText("Hey, "+ sharedPreferences.getString("spname", null));

        profileSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Profile.this, ProfileSettings.class);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Profile.this, "Logout", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            Intent intent= new Intent(Profile.this, Signin.class);
            startActivity(intent);
            finish();
            }
        });

    }
}