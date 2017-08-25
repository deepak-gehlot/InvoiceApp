package com.invoiceapp.android.view.activity;

import android.Manifest;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;

import com.github.jksiezni.permissive.PermissionsGrantedListener;
import com.github.jksiezni.permissive.PermissionsRefusedListener;
import com.github.jksiezni.permissive.Permissive;
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

    private int navigationIcons[] = {R.drawable.ic_invoices, R.drawable.ic_estimates, R.drawable.ic_report, R.drawable.ic_my_items,
            R.drawable.ic_client, R.drawable.ic_account, R.drawable.ic_support, R.drawable.ic_setting};
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        setNavigationList();
        Utility.addFragment(HomeActivity.this, InvoiceMainFragment.newInstance(),
                "InvoiceMainFragment",
                binding.container.getId(), "InvoiceMainFragment");

        new Permissive.Request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .whenPermissionsGranted(new PermissionsGrantedListener() {
                    @Override
                    public void onPermissionsGranted(String[] permissions) throws SecurityException {
                        // given permissions are granted

                    }
                })
                .whenPermissionsRefused(new PermissionsRefusedListener() {
                    @Override
                    public void onPermissionsRefused(String[] permissions) {
                        // given permissions are refused
                        finish();
                    }
                }).execute(HomeActivity.this);

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
