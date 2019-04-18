package com.example.dangnguyenhai.gohotel.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.dangnguyenhai.gohotel.R;
import com.example.dangnguyenhai.gohotel.SearchActivity;
import com.example.dangnguyenhai.gohotel.adapter.SearchedAdapter;
import com.example.dangnguyenhai.gohotel.model.SearchForm;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.List;

public class SearchFragment extends Fragment implements View.OnClickListener {
    private RecyclerView rcvSearchedHotel;
    private LinearLayout btnSearch;
    private Context context;
    private List<SearchForm> searchForms;
    public static SearchFragment newInstance() {
        SearchFragment myFragment = new SearchFragment();

        Bundle args = new Bundle();
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fragment, container, false);
        btnSearch = rootView.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
        rcvSearchedHotel = rootView.findViewById(R.id.rcvSearchedHotel);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(context);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        rcvSearchedHotel.setLayoutManager(layoutManager);
        rcvSearchedHotel.setAdapter(new SearchedAdapter(context,searchForms));
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearch:
                gotoSearch();
                break;
        }
    }

    private void gotoSearch() {
        Intent intent = new Intent(context, SearchActivity.class);
        startActivity(intent);
    }
}
