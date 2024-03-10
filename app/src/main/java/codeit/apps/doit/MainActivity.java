package codeit.apps.doit;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;


import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import codeit.apps.doit.Fragments.FocusFragment;
import android.view.View;
import android.widget.Button;

import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    Button button;
    ImageButton profileBtn;
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
                    Toast.makeText(MainActivity.this, "Tasks Selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.Focus_nav) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrameLayout, new FocusFragment()).commit();
                    return true;
                } else if (itemId == R.id.Profile_nav) {
                    Toast.makeText(MainActivity.this, "Profile Selected", Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();

        if(id==R.id.nav_home){
            Intent intent= new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else if(id==R.id.nav_about){
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new AboutFragment()).commit();
        }else if(id==R.id.nav_settings){
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
        }else if(id==R.id.nav_share){
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String shareMessage = "Hey There! Let's make our days more productive with this amazing app";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            Intent chooserIntent = Intent.createChooser(shareIntent, "Share via");
            if (shareIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooserIntent);
            } else {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
            return true;

            //Toast.makeText(this, "Share your Profile", Toast.LENGTH_SHORT).show();
        }else if(id== R.id.nav_logout){
            Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            Intent intent= new Intent(MainActivity.this, Signin.class);
            startActivity(intent);
            finish();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}