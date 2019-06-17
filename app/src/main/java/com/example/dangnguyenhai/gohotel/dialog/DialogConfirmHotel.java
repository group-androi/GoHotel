package com.example.dangnguyenhai.gohotel.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.dangnguyenhai.gohotel.R;

public class DialogConfirmHotel {
    public void showConfirmHotel(final Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            final Dialog dialog = new Dialog(activity, R.style.dialog_full_transparent_background);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.confirm_hotel_dialog);
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.CENTER;
                window.setAttributes(wlp);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                dialog.show();
            }
            TextView btnSubmit = dialog.findViewById(R.id.btnSubmit);
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

        }
    }
}
