package codeit.apps.doit.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import Adapters.FriendsPagerAdapter;
import codeit.apps.doit.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FriendsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FriendsFragment extends Fragment {


    public FriendsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        TabLayout tabLayout = view.findViewById(R.id.friends_tab_layout);
        ViewPager viewPager = view.findViewById(R.id.friends_view_pager);

        FriendsPagerAdapter adapter = new FriendsPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new FriendsMyFragment(), "My Friends");
        adapter.addFragment(new FriendsAddFragment(), "Add Friends");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);




        return view;
    }
}