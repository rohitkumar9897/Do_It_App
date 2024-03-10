package codeit.apps.doit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;

import Adapters.Onboarding_Viewpager_adapter;

public class Onboarding extends AppCompatActivity {
    ViewPager viewPager;
    ArrayList<Integer> images;
    ArrayList<String> strings;
    private Button nextButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.onboardingViewPager);

        images = new ArrayList<Integer>();
        strings = new ArrayList<String>();
        nextButton = findViewById(R.id.onboardingNextBtn);

        images.add(R.drawable.onboardingimg1);
        images.add(R.drawable.onboardingimg2);
        images.add(R.drawable.onboardingimg3);
        images.add(R.drawable.onboardingimg4);

        strings.add("Timer");
        strings.add("Leaderboard");
        strings.add("To Do list");
        strings.add("Calendar");

        Onboarding_Viewpager_adapter onboardingViewpagerAdapter = new Onboarding_Viewpager_adapter(this, images, strings);
        viewPager.setAdapter(onboardingViewpagerAdapter);
        onboardingViewpagerAdapter.notifyDataSetChanged();


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Onboarding.this, WelcomeActivity.class));
                finish();
            }
        });



    }
}