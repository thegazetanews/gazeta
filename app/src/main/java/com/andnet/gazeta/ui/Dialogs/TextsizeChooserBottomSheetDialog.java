package com.andnet.gazeta.ui.Dialogs;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.andnet.gazeta.R;

public class TextsizeChooserBottomSheetDialog extends BottomSheetDialogFragment{


    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View mainView= LayoutInflater.from(getContext()).inflate(R.layout.text_sieze_chooser_bottom_sheet,null);
        dialog.setContentView(mainView);
    }
}
