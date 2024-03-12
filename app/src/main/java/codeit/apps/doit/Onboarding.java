package codeit.apps.doit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import Adapters.Onboarding_Viewpager_adapter;

public class Onboarding extends AppCompatActivity {
    ViewPager viewPager;
    ArrayList<Integer> images;
    ArrayList<String> strings;
    TextView skipBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.onboardingViewPager);

        images = new ArrayList<Integer>();
        strings = new ArrayList<String>();


        images.add(R.drawable.onboardingimg1);
        images.add(R.drawable.onboardingimg2);
        images.add(R.drawable.onboardingimg3);
        images.add(R.drawable.onboardingimg4);

        strings.add("Timer");
        strings.add("Leaderboard");
        strings.add("To Do list");
        strings.add("Calendar");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black_light));
        }




        Onboarding_Viewpager_adapter onboardingViewpagerAdapter = new Onboarding_Viewpager_adapter(this, images, strings, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Onboarding.this, WelcomeActivity.class));
            }
        });
        viewPager.setAdapter(onboardingViewpagerAdapter);
        onboardingViewpagerAdapter.notifyDataSetChanged();






    }
}