package com.invoiceapp.android.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.invoiceapp.android.R;
import com.invoiceapp.android.adapter.NavigationAdapter;
import com.invoiceapp.android.databinding.ActivityHomeBinding;
import com.invoiceapp.android.listener.OnItemClickListener;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        setTitle();
        setNavigationList();
    }

    private void setTitle() {
        setSupportActionBar(binding.appBar.toolbar);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * set side navigation items
     */
    private void setNavigationList() {
        String[] navigation_array = getResources().getStringArray(R.array.navigation_array);
        NavigationAdapter navigationAdapter = new NavigationAdapter(HomeActivity.this, navigation_array);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        binding.recyclerView.setAdapter(navigationAdapter);
        navigationAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }
        });
    }
}
