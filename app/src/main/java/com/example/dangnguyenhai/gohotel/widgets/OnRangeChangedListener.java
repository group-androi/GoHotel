package com.example.dangnguyenhai.gohotel.widgets;

public interface OnRangeChangedListener {
    void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser);

    void onStartTrackingTouch(RangeSeekBar view, boolean isLeft);

    void onStopTrackingTouch(RangeSeekBar view, boolean isLeft);
}
