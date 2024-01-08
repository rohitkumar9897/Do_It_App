package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

import codeit.apps.doit.R;

public class Onboarding_Viewpager_adapter extends PagerAdapter {

    private final Context context;
    private final ArrayList<String> strings;
    private final ArrayList<Integer> images;

    public Onboarding_Viewpager_adapter(Context context, ArrayList<Integer> ImageList, ArrayList<String> Strings){
        this.context = context;
        this.images = ImageList;
        this.strings = Strings;

    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.onboarding_viewpager_layout, container, false);

        ImageView imageView = layout.findViewById(R.id.onboardingImage);
        TextView textView = layout.findViewById(R.id.onboardingString);

        imageView.setImageResource(images.get(position));
        textView.setText(strings.get(position));

        container.addView(layout);
        return layout;
    }
    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
