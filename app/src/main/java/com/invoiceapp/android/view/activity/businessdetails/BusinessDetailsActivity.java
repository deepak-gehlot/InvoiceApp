package com.invoiceapp.android.view.activity.businessdetails;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.invoiceapp.android.R;
import com.invoiceapp.android.dao.QueryManager;
import com.invoiceapp.android.dao.Response;
import com.invoiceapp.android.databinding.ActivityBusinessDetailsBinding;
import com.invoiceapp.android.listener.CallbackListener;
import com.invoiceapp.android.util.PreferenceConnector;
import com.invoiceapp.android.view.fragment.businessdetails.ContactFragment;
import com.invoiceapp.android.view.fragment.businessdetails.GeneralDetailFragment;
import com.invoiceapp.android.view.fragment.businessdetails.LogoFragment;
import com.invoiceapp.android.view.model.BusinessDetailModel;

import io.fabric.sdk.android.Fabric;


public class BusinessDetailsActivity extends AppCompatActivity {

    private ActivityBusinessDetailsBinding binding;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private BusinessDetailModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_business_details);

        setSupportActionBar(binding.toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(mSectionsPagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewPager);

        String details = PreferenceConnector.readString(BusinessDetailsActivity.this, PreferenceConnector.BUSINESS_DETAILS, "");
        if (details.isEmpty()) {
            model = new BusinessDetailModel();
        } else {
            model = new Gson().fromJson(details, BusinessDetailModel.class);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_business_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        String json = new Gson().toJson(model);
        final ProgressDialog progressDialog = ProgressDialog.show(BusinessDetailsActivity.this, "", "", false, false);
        PreferenceConnector.writeString(BusinessDetailsActivity.this, PreferenceConnector.BUSINESS_DETAILS, json);
        QueryManager.getInstance().postRequest(BusinessDetailsActivity.this, json, new CallbackListener() {
            @Override
            public void onResult(Exception e, String result) {
                progressDialog.dismiss();
                if (e == null && result != null && !result.isEmpty()) {
                    try {
                        Response response = new Gson().fromJson(result, Response.class);
                        if (response.status.equals("200")) {
                            finish();
                        } else {
                            Toast.makeText(BusinessDetailsActivity.this, getString(R.string.wrong), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } catch (JsonSyntaxException e1) {
                        e1.printStackTrace();
                        Toast.makeText(BusinessDetailsActivity.this, getString(R.string.wrong), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    finish();
                    Toast.makeText(BusinessDetailsActivity.this, getString(R.string.wrong), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_business_details2, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return GeneralDetailFragment.newInstance(model);
                case 1:
                    return ContactFragment.newInstance(model);
                case 2:
                    return LogoFragment.newInstance(model);
                default:
                    return GeneralDetailFragment.newInstance(model);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "General";
                case 1:
                    return "Contact";
                case 2:
                    return "Logo";
            }
            return null;
        }
    }
}
