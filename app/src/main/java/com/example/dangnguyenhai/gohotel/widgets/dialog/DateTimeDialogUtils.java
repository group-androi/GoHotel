package com.example.dangnguyenhai.gohotel.widgets.dialog;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.dangnguyenhai.gohotel.R;

import java.util.Calendar;

public class DateTimeDialogUtils {
    public static void showTimePickerDialogEdit(Context context, final TextView tvTime, String timeLimit) {
        String time = tvTime.getText().toString();
        String[] timeArray = time.split(":");
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        newCalendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));

        String[] timeLimitArray = timeLimit.split(":");
        Calendar currentTime = Calendar.getInstance();
        currentTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeLimitArray[0]));
        currentTime.set(Calendar.MINUTE, Integer.parseInt(timeLimitArray[1]));

        CustomTimePickerDialog customTimePickerDialog = new CustomTimePickerDialog(context, (view, hourOfDay, minute) -> tvTime.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute)), newCalendar.get(Calendar.HOUR_OF_DAY), newCalendar.get(Calendar.MINUTE), currentTime.get(Calendar.HOUR_OF_DAY), currentTime.get(Calendar.MINUTE));

        customTimePickerDialog.show();
    }

    public static void showDatePickerDialog(Context context, final TextView tvTime, Calendar minYear, Calendar maxYear) {

        String[] d;

        if (tvTime.getText().toString().equals("")) {
            d = context.getString(R.string.default_birthday).split("/");
        } else {
            d = tvTime.getText().toString().split("/");
        }

        CustomDialogDatePicker customDialogDatePicker=new CustomDialogDatePicker(context, new CustomDialogDatePicker.onSelectedDay() {
            @Override
            public void getDay(String s) {
                tvTime.setText(s);
            }
        });
        customDialogDatePicker.show();

    }
}
