package codeit.apps.doit;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;



import android.content.Intent;
import android.os.Bundle;


import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import codeit.apps.doit.Fragments.FocusFragment;
import codeit.apps.doit.Fragments.ToDoFragment;

import android.view.View;
import android.widget.Button;

import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity{

    private DrawerLayout drawerLayout;
    Button button;
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
                    Toast.makeText(MainActivity.this, "Calendar Selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.Score_nav) {
                    Toast.makeText(MainActivity.this, "Score Selected", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });


    }
}