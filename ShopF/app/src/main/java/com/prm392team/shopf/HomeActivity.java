package com.prm392team.shopf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.prm392team.shopf.Adapter.ViewPagerAdapter;
import com.prm392team.shopf.Entity.Category;
import com.prm392team.shopf.Entity.Order;
import com.prm392team.shopf.Entity.Product;
import com.prm392team.shopf.Entity.User;
import com.prm392team.shopf.Fragment.HomeFragment;
import com.prm392team.shopf.R;
import com.prm392team.shopf.RoomDB.FFoodDB;
import com.prm392team.shopf.RoomDB.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ViewPager vpHome;
    private BottomNavigationView bnvHome;
    private FloatingActionButton floatingActionButton;
    private BottomAppBar babHome;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USER = "ducw";
    private static final String KEY_PASS = "pass";

    FFoodDB db;
    UserDAO userDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        bindingView();
        bindingAction();

        String user = sharedPreferences.getString(KEY_USER, null);
        if (user != null){
        }
    }

    private void bindingView() {
        vpHome = findViewById(R.id.vpHome);
        bnvHome = findViewById(R.id.bnvHome);
        floatingActionButton = findViewById(R.id.fabCart);
        babHome = findViewById(R.id.babHome);
        db = FFoodDB.getInstance(this);
        userDAO= db.userDAO();
    }

    private void bindingAction() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,CartListActivity.class));
            }
        });

        babHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,HomeActivity.class));
            }
        });

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpHome.setAdapter(viewPagerAdapter);
        vpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bnvHome.getMenu().findItem(R.id.mnHome).setChecked(true);
                        break;
                    case 1:
                        bnvHome.getMenu().findItem(R.id.mnfood).setChecked(true);
                        break;
                    case 2:
                        bnvHome.getMenu().findItem(R.id.mnAccount).setChecked(true);
                        break;
                    case 3:
                        bnvHome.getMenu().findItem(R.id.mnSetting).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bnvHome.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mnHome:
                        vpHome.setCurrentItem(0);
                        break;
                    case R.id.mnfood:
                        vpHome.setCurrentItem(1);
                        break;
                    case R.id.mnAccount:
                        vpHome.setCurrentItem(2);
                        break;
                    case R.id.mnSetting:
                        vpHome.setCurrentItem(3);
                        break;
                }
            }
        });
    }

}