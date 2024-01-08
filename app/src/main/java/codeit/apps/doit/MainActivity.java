package codeit.apps.doit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        //bottomNavigationView.setItemHorizontalTranslationEnabled(true);
        //bottomNavigationView.setActivated(true);
        bottomNavigationView.inflateMenu(R.menu.bottom_navigation_menu);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.Tasks_nav) {
                    Toast.makeText(MainActivity.this, "Tasks Selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.Focus_nav) {
                    Toast.makeText(MainActivity.this, "Focus Selected", Toast.LENGTH_SHORT).show();
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
}