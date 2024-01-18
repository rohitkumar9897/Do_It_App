package codeit.apps.doit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    FirebaseAuth auth;
    String email;
    EditText txtEmail;
    Button forgetbtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        TextView btn=findViewById(R.id.btn_signinpage);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPassword.this, Signin.class));
                finish();
            }
        });
        auth= FirebaseAuth.getInstance();
        forgetbtn=findViewById(R.id.btn_send_email);
        txtEmail=findViewById(R.id.forgotEmail);

        forgetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email= String.valueOf(txtEmail.getText());
                if(email.isEmpty()){
                    txtEmail.setError("Email Required");
                }else{
                    auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgotPassword.this, "Check your email", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(ForgotPassword.this, Signin.class));
                                finish();
                            }else{
                                Toast.makeText(ForgotPassword.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}