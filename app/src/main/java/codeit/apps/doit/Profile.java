package codeit.apps.doit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
    TextView shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


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
    }
}