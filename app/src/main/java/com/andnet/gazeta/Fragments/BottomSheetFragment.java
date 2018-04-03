package com.andnet.gazeta.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.andnet.gazeta.R;

public class BottomSheetFragment extends Fragment {

    private BottomSheetBehavior sheetBehavior;

    public BottomSheetFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FrameLayout bottomSheet = (FrameLayout) getActivity().findViewById(R.id.content);
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);
    }

    private void setBottomSheet() {
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
                } else {
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    public void expandBottomSheet() {
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}