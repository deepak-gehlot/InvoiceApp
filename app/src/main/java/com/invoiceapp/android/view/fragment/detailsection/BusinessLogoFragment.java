package com.invoiceapp.android.view.fragment.detailsection;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.androidquery.AQuery;
import com.github.jksiezni.permissive.PermissionsGrantedListener;
import com.github.jksiezni.permissive.PermissionsRefusedListener;
import com.github.jksiezni.permissive.Permissive;
import com.invoiceapp.android.R;
import com.invoiceapp.android.databinding.FragmentBusinessLogoBinding;
import com.invoiceapp.android.util.Utility;
import com.invoiceapp.android.view.model.BusinessDetailModel;

import java.io.File;
import java.io.IOException;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class BusinessLogoFragment extends Fragment {

    public BusinessLogoFragment() {
        // Required empty public constructor
    }

    private BusinessDetailModel businessDetailModel;

    public static BusinessLogoFragment newInstance(BusinessDetailModel businessDetailModel) {
        BusinessLogoFragment fragment = new BusinessLogoFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("item", businessDetailModel);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        businessDetailModel = bundle.getParcelable("item");
    }

    private FragmentBusinessLogoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_business_logo, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setFragment(this);

        if (!businessDetailModel.getLogo().isEmpty()) {
            AQuery aQuery = new AQuery(getActivity());
            if (businessDetailModel.getLogo().contains("http")) {
                aQuery.id(binding.imageLogo).image(businessDetailModel.getLogo(), true, true, 200, R.drawable.ic_client);
            } else {
                aQuery.id(binding.imageLogo).image(Utility.decodeImage(businessDetailModel.getLogo()));
            }
        }
    }

    public void onSelectLogoClick() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.camer_gallery_popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        dialog.findViewById(R.id.cameraTxt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                openCamera();
            }
        });

        dialog.findViewById(R.id.galleryTxt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                openGallery();
            }
        });

        dialog.findViewById(R.id.cancel_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void openCamera() {
        new Permissive.Request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .whenPermissionsGranted(new PermissionsGrantedListener() {
                    @Override
                    public void onPermissionsGranted(String[] permissions) throws SecurityException {
                        // given permissions are granted
                        EasyImage.openCamera(BusinessLogoFragment.this, 1);
                    }
                }).whenPermissionsRefused(new PermissionsRefusedListener() {
            @Override
            public void onPermissionsRefused(String[] permissions) {
                // given permissions are refused
            }
        }).execute(getActivity());
    }

    private void openGallery() {
        new Permissive.Request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .whenPermissionsGranted(new PermissionsGrantedListener() {
                    @Override
                    public void onPermissionsGranted(String[] permissions) throws SecurityException {
                        // given permissions are granted
                        EasyImage.openGallery(BusinessLogoFragment.this, 1);
                    }
                }).whenPermissionsRefused(new PermissionsRefusedListener() {
            @Override
            public void onPermissionsRefused(String[] permissions) {
                // given permissions are refused
            }
        }).execute(getActivity());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePicked(final File imageFile, EasyImage.ImageSource source, int type) {
                final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "", "loading image...", false, false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final Bitmap bitmap = Utility.handleSamplingAndRotationBitmap(getActivity(), Uri.fromFile(imageFile));
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    binding.imageLogo.setImageBitmap(bitmap);
                                    progressDialog.dismiss();
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            businessDetailModel.setLogo(Utility.encodeImage(bitmap));
                                        }
                                    }).start();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
}
