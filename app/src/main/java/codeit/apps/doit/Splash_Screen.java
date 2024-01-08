package codeit.apps.doit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

public class Splash_Screen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.activity_splash_screen);

        imageView =  findViewById(R.id.Splash_Image_View);
        //imageView.setTranslationX(2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Onboarding.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);



    }

}