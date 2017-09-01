package com.invoiceapp.android.view.activity.createinvoice;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.jksiezni.permissive.PermissionsGrantedListener;
import com.github.jksiezni.permissive.PermissionsRefusedListener;
import com.github.jksiezni.permissive.Permissive;
import com.invoiceapp.android.R;
import com.invoiceapp.android.databinding.ActivityCreateInvoiceBinding;
import com.invoiceapp.android.listener.PermissionListener;
import com.invoiceapp.android.view.fragment.createinvoice.EditFragment;
import com.invoiceapp.android.view.fragment.createinvoice.HistoryInvoiceFragment;
import com.invoiceapp.android.view.fragment.createinvoice.PreviewInvoiceFragment;
import com.invoiceapp.android.view.model.BusinessDetailModel;

import java.io.File;

import static com.invoiceapp.android.util.Constant.PATH_PRODUCT_REPORT;
import static com.invoiceapp.android.util.Constant.reportName;
/*{"method":"businessDetail","userID":"1","businessName":"Mishra","vat":"20",
"phone":"9770789763","fax":"28350","website":"www.marvellousinstitute.com",
"address_1":"Indore","address_2":"Bhopal","address_3":"Gwalior","business_industry":"test",
"business_logo":"aa.png"}
*/
public class CreateInvoiceActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ActivityCreateInvoiceBinding binding;
    public BusinessDetailModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_invoice);
        setTitleActions();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        binding.container.setAdapter(mSectionsPagerAdapter);
        binding.tabs.setupWithViewPager(binding.container);

        binding.container.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setTitleActions() {
        setSupportActionBar(binding.toolbar);

        binding.toolbar.setNavigationIcon(R.drawable.back_icon_white);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_invoice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_email:
                sendEmail();
                break;
            case R.id.action_share:
                sharePdf();
                break;
            case R.id.action_openin:
                openIn();
                break;
        }
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    private void sharePdf() {
        getPdfFile(new PermissionListener() {
            @Override
            public void onPermissionAllow() {
                String mPath = Environment.getExternalStorageDirectory().toString()
                        + "/" + PATH_PRODUCT_REPORT
                        + "/" + reportName + ".pdf";
                File pdfFile = new File(mPath);
                Intent intentShareFile = new Intent(Intent.ACTION_SEND);
                if (pdfFile.exists()) {
                    intentShareFile.setType("application/pdf");
                    intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(pdfFile));
                    intentShareFile.putExtra(Intent.EXTRA_SUBJECT,
                            "Sharing File...");
                    intentShareFile.putExtra(Intent.EXTRA_TEXT, "Sharing File...");
                    startActivity(Intent.createChooser(intentShareFile, "Share File"));
                } else {
                    Toast.makeText(CreateInvoiceActivity.this, "Pdf not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendEmail() {
        getPdfFile(new PermissionListener() {
            @Override
            public void onPermissionAllow() {
                String mPath = Environment.getExternalStorageDirectory().toString()
                        + "/" + PATH_PRODUCT_REPORT
                        + "/" + reportName + ".pdf";
                File pdfFile = new File(mPath);
                Uri path = Uri.fromFile(pdfFile);
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
// set the type to 'email'
                emailIntent.setType("vnd.android.cursor.dir/email");
                String to[] = {"asd@gmail.com"};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
// the attachment
                emailIntent.putExtra(Intent.EXTRA_STREAM, path);
// the mail subject
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
    }

    private void openIn() {
        getPdfFile(new PermissionListener() {
            @Override
            public void onPermissionAllow() {
                String mPath = Environment.getExternalStorageDirectory().toString()
                        + "/" + PATH_PRODUCT_REPORT
                        + "/" + reportName + ".pdf";
                File pdfFile = new File(mPath);
                Intent target = new Intent(Intent.ACTION_VIEW);
                target.setDataAndType(Uri.fromFile(pdfFile), "application/pdf");
                target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                Intent intent = Intent.createChooser(target, "Open File");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Instruct the user to install a PDF reader here, or something
                }
            }
        });
    }

    private void getPdfFile(final PermissionListener permissionListener) {
        new Permissive.Request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .whenPermissionsGranted(new PermissionsGrantedListener() {
                    @Override
                    public void onPermissionsGranted(String[] permissions) throws SecurityException {
                        permissionListener.onPermissionAllow();
                    }
                })
                .whenPermissionsRefused(new PermissionsRefusedListener() {
                    @Override
                    public void onPermissionsRefused(String[] permissions) {
                        // given permissions are refused
                        finish();
                    }
                }).execute(CreateInvoiceActivity.this);
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
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return EditFragment.newInstance();
                case 1:
                    return PreviewInvoiceFragment.newInstance();
                case 2:
                    return HistoryInvoiceFragment.newInstance();
                default:
                    return EditFragment.newInstance();
            }

        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "EDIT";
                case 1:
                    return "PREVIEW";
                case 2:
                    return "HISTORY";
            }
            return null;
        }
    }
}
