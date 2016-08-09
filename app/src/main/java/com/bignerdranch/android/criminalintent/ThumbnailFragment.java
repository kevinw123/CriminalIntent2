package com.bignerdranch.android.criminalintent;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class ThumbnailFragment extends DialogFragment {
    private File mPhotoFile;
    private ImageView mImage;
    private Crime mCrime;

    public static final String EXTRA_PATH =
            "com.bignerdranch.android.criminalintent.path";

    private static final String ARG_SUSPECT_IMAGE = "suspect_image";

    public static ThumbnailFragment newInstance(File photoFile) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SUSPECT_IMAGE, photoFile);

        ThumbnailFragment fragment = new ThumbnailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_thumbnail, container, false);
        String name  = (String) getArguments().getSerializable(ARG_PATH);

        //Bitmap bitmap = PictureUtils.getScaledBitmap(path, 100, 100);
        //mImage.setImageBitmap(bitmap);
        //mImage.setImageURI();
        return v;
    }*/
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        super.onCreateDialog(savedInstanceState);

        File photoFile = (File) getArguments().getSerializable(ARG_SUSPECT_IMAGE);

        Bitmap image = PictureUtils.getScaledBitmap(photoFile.getPath(), getActivity());

        View v =  LayoutInflater.from(getActivity()).inflate(R.layout.fragment_thumbnail, null);

        ImageView imageView = (ImageView) v.findViewById(R.id.zoom_thumbnail_image);
        imageView.setImageBitmap(image);

        return new AlertDialog.Builder(getActivity()).setView(imageView).create();
    }
}
