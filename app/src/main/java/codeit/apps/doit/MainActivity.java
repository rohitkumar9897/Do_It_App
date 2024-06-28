package codeit.apps.doit;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;


import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import codeit.apps.doit.Fragments.FocusFragment;
import codeit.apps.doit.Fragments.FriendsFragment;
import codeit.apps.doit.Fragments.LeaderboardFragment;
import codeit.apps.doit.Fragments.ToDoFragment;
import codeit.apps.doit.LeaderBoard.LeaderBoardActivity;

import android.widget.Button;


public class MainActivity extends AppCompatActivity{
    
    ImageView profileBtn;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.inflateMenu(R.menu.bottom_navigation_menu);

        LinearLayout topLL = findViewById(R.id.topLinearLayoutMA);


        profileBtn = findViewById(R.id.profile_btn);
        profileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);

        });
        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrameLayout, new FocusFragment()).commit();





        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.Tasks_nav) {
                    topLL.setVisibility(View.VISIBLE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrameLayout, new ToDoFragment()).commit();
                    return true;
                } else if (itemId == R.id.Focus_nav) {
                    topLL.setVisibility(View.VISIBLE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrameLayout, new FocusFragment()).commit();
                    return true;
                }else if (itemId == R.id.Score_nav) {
                    topLL.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrameLayout, new LeaderboardFragment()).commit();
                    return true;
                }else if (itemId == R.id.Friends_nav) {
                    topLL.setVisibility(View.VISIBLE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrameLayout, new FriendsFragment()).commit();
                    return true;
                }
                return false;
            }
        });


    }
}