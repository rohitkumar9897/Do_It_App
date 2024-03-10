package codeit.apps.doit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    TextView shareBtn, logoutBtn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logoutBtn= findViewById(R.id.btn_logout);

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