package edu.colorpreview.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import edu.colorpreview.R;
import edu.colorpreview.view.designer.DesignerActivity;
import edu.colorpreview.view.user.LoginActivity;
import edu.colorpreview.view.user.UserStatus;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navView;
    private NavController navController;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (UserStatus.sUser == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.bookmark,
                R.id.designer, R.id.account, R.id.home
                )
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        setNavView();


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DesignerActivity.class);
            startActivity(intent);
        });
    }

    private void setNavView() {
        navView.setOnNavigationItemSelectedListener(menuItem -> {
            navController.navigate(menuItem.getItemId());
            return true;
        });
    }

}
