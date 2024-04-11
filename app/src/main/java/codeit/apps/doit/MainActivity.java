package codeit.apps.doit;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;


import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import codeit.apps.doit.Fragments.FocusFragment;
import codeit.apps.doit.Fragments.FriendsFragment;
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
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrameLayout, new ToDoFragment()).commit();
                    return true;
                } else if (itemId == R.id.Focus_nav) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrameLayout, new FocusFragment()).commit();
                    return true;
                } else if (itemId == R.id.Calendar_nav) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrameLayout, new FriendsFragment()).commit();
                    Toast.makeText(MainActivity.this, "Calendar Selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.Score_nav) {
                    Toast.makeText(MainActivity.this, "Score Selected", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LeaderBoardActivity.class);
                    startActivity(intent);
                    return true;
                }else if (itemId == R.id.Friends_nav) {
                    Toast.makeText(MainActivity.this, "Friends Section Selected", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });


    }
}