package codeit.apps.doit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;

import Adapters.Onboarding_Viewpager_adapter;

public class Onboarding extends AppCompatActivity {
    ViewPager viewPager;
    ArrayList<Integer> images;
    ArrayList<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));
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

        Onboarding_Viewpager_adapter onboardingViewpagerAdapter = new Onboarding_Viewpager_adapter(this, images, strings);
        viewPager.setAdapter(onboardingViewpagerAdapter);
        onboardingViewpagerAdapter.notifyDataSetChanged();



    }
}