package com.invoiceapp.android.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.invoiceapp.android.util.Utility;
import com.invoiceapp.android.view.fragment.account.MyAccountFragment;
import com.invoiceapp.android.view.fragment.estimates.EstimateMainFragment;
import com.invoiceapp.android.view.fragment.invoice.InvoiceMainFragment;
import com.invoiceapp.android.view.fragment.myClients.MyClientListFragment;
import com.invoiceapp.android.view.fragment.myItems.MyItemsListFragment;
import com.invoiceapp.android.view.fragment.settings.SettingsMainFragment;

public class HomeActivity extends AppCompatActivity {

    private int navigationIcons[] = {R.drawable.invoice_icon, R.drawable.estimates_icon, R.drawable.reports_icon, R.drawable.my_items,
            R.drawable.clients_icon, R.drawable.account_icon, R.drawable.support_icon, R.drawable.setting_icon};
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        setNavigationList();
        Utility.addFragment(HomeActivity.this, InvoiceMainFragment.newInstance(),
                "InvoiceMainFragment",
                binding.container.getId(), "InvoiceMainFragment");

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

    public void openDrawer() {
        binding.drawerLayout.openDrawer(Gravity.START);
    }

    /**
     * set side navigation items
     */
    private void setNavigationList() {
        String[] navigation_array = getResources().getStringArray(R.array.navigation_array);
        NavigationAdapter navigationAdapter = new NavigationAdapter(HomeActivity.this, navigation_array, navigationIcons);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        binding.recyclerView.setAdapter(navigationAdapter);
        navigationAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                binding.drawerLayout.closeDrawer(Gravity.START);
                switch (position) {
                    case 0:
                        addFragment(InvoiceMainFragment.newInstance(), "InvoiceMainFragment");
                        break;
                    case 1:
                        addFragment(EstimateMainFragment.newInstance(), "EstimateMainFragment");
                        break;
                    case 2:
                        break;
                    case 3:
                        addFragment(MyItemsListFragment.newInstance(), "MyItemsListFragment");
                        break;
                    case 4:
                        addFragment(MyClientListFragment.newInstance(), "MyClientListFragment");
                        break;
                    case 5:
                        addFragment(MyAccountFragment.newInstance(), "MyAccountFragment");
                        break;
                    case 6:
                        break;
                    case 7:
                        addFragment(SettingsMainFragment.newInstance(), "SettingsMainFragment");
                        break;
                }
            }
        });
    }

    private void addFragment(Fragment fragment, String tag) {
        Utility.addFragment(HomeActivity.this, fragment,
                tag,
                binding.container.getId(), tag);
    }
}
