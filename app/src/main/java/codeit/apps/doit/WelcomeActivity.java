package codeit.apps.doit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity {

    Button login, signup;
    ImageView welcomeLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        login=findViewById(R.id.btn_login);
        signup=findViewById(R.id.btn_signup);
        welcomeLogo = findViewById(R.id.welcome_logo);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(WelcomeActivity.this, Signin.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(WelcomeActivity.this, Signup.class);
                startActivity(intent);
            }
        });
    }
}