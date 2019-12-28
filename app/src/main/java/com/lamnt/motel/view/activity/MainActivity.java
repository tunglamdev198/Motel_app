package com.lamnt.motel.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.lamnt.motel.view.fragment.ContactFragment;
import com.lamnt.motel.view.fragment.HomePageFragment;
import com.lamnt.motel.R;
import com.lamnt.motel.view.fragment.SettingFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new HomePageFragment());
    }

    private void loadFragment(Fragment frag) {
        if (frag != null) {
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, frag).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomePageFragment();
                break;

            case R.id.navigation_contact:
                fragment = new ContactFragment();
                break;

            case R.id.navigation_setting:
                fragment = new SettingFragment();
                break;
            default:
                return false;

        }
        loadFragment(fragment);
        return true;
    }
}
