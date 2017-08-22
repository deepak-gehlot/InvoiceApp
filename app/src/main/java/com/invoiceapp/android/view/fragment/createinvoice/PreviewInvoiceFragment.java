package com.invoiceapp.android.view.fragment.createinvoice;


import android.Manifest;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.jksiezni.permissive.PermissionsGrantedListener;
import com.github.jksiezni.permissive.PermissionsRefusedListener;
import com.github.jksiezni.permissive.Permissive;
import com.invoiceapp.android.R;
import com.invoiceapp.android.databinding.FragmentPreviewInvoiceBinding;

import java.io.File;

public class PreviewInvoiceFragment extends Fragment {

    String PATH_PRODUCT_REPORT = "ppp";
    String reportName = "onetwo";

    public PreviewInvoiceFragment() {
        // Required empty public constructor
    }

    private FragmentPreviewInvoiceBinding binding;

    public static PreviewInvoiceFragment newInstance() {
        PreviewInvoiceFragment fragment = new PreviewInvoiceFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_preview_invoice, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Permissive.Request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .whenPermissionsGranted(new PermissionsGrantedListener() {
                    @Override
                    public void onPermissionsGranted(String[] permissions) throws SecurityException {
                        drawPdf();
                    }
                })
                .whenPermissionsRefused(new PermissionsRefusedListener() {
                    @Override
                    public void onPermissionsRefused(String[] permissions) {
                        // given permissions are refused
                        getActivity().finish();
                    }
                }).execute(getActivity());


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser){
            drawPdf();
        }
    }

    private void drawPdf() {
        //getting the full path of the PDF report name
        String mPath = Environment.getExternalStorageDirectory().toString()
                + "/" + PATH_PRODUCT_REPORT //PATH_PRODUCT_REPORT="/SIAS/REPORT_PRODUCT/"
                + "/" + reportName + ".pdf"; //reportName could be any name

        //constructing the PDF file
        File pdfFile = new File(mPath);
        if (pdfFile.exists()) {
            binding.pdfView.fromFile(pdfFile).load();
        } else {
            Toast.makeText(getActivity(), "Pdf file not found.", Toast.LENGTH_SHORT).show();
        }
    }
}
