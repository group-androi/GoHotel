package com.example.dangnguyenhai.gohotel.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.dangnguyenhai.gohotel.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

class CustomDialogDatePicker extends Dialog implements View.OnClickListener {
private DatePicker datePicker;
private onSelectedDay onSelectedDay;
private Context context;
private SimpleDateFormat dateFormatter;
private String birthday = "";

@Override
public void onClick(View v) {
        if (!birthday.isEmpty()) {
        Calendar calendarChoose=Calendar.getInstance();
        try {
        calendarChoose.setTime(dateFormatter.parse(birthday));
        } catch (ParseException e) {
        e.printStackTrace();
        }
        Calendar currentCalendar=Calendar.getInstance();
        int year = currentCalendar.get(Calendar.YEAR) - 18;
        int date = currentCalendar.get(Calendar.DATE);
        int month = currentCalendar.get(Calendar.MONTH);
        currentCalendar.set(year,month,date);

        if(calendarChoose.compareTo(currentCalendar) > 0){
        datePicker.updateDate(year,month,date);
        }else {
        onSelectedDay.getDay(birthday);
        dismiss();
        }
        }else {
        dismiss();
        }
        }

public interface onSelectedDay {
    void getDay(String s);
}


    public CustomDialogDatePicker(@NonNull Context context, onSelectedDay onSelectedDay) {
        super(context);
        this.onSelectedDay = onSelectedDay;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Calendar calendar = Calendar.getInstance();
        dateFormatter = new SimpleDateFormat(context.getString(R.string.date_format_view), Locale.ENGLISH);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_date_picker);
        TextView tvDone = findViewById(R.id.tvDone);

        datePicker = findViewById(R.id.dateTimePicker);
        int year = calendar.get(Calendar.YEAR) - 18;
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        calendar.set(year,month,date);
        birthday = dateFormatter.format(calendar.getTime());

        datePicker.updateDate(year,month,date);
        datePicker.setSelectionDivider(new ColorDrawable(0xFFFF6400));
        datePicker.setSelectionDividerHeight(4);
        datePicker.setOnDateChangedListener((view, year1, monthOfYear, dayOfMonth) -> {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year1, monthOfYear, dayOfMonth);
            birthday = dateFormatter.format(newDate.getTime());
        });

        tvDone.setOnClickListener(this);
    }
}
