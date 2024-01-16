package codeit.apps.doit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button button;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.btn_logout);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(getApplicationContext(), Signin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}