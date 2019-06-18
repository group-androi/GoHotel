package com.example.dangnguyenhai.gohotel.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.dangnguyenhai.gohotel.GoHotelApplication;
import com.example.dangnguyenhai.gohotel.R;
import com.example.dangnguyenhai.gohotel.activity.SearchActivity;
import com.example.dangnguyenhai.gohotel.adapter.SearchedAdapter;
import com.example.dangnguyenhai.gohotel.model.SearchForm;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((Activity)context).getWindow().setStatusBarColor(context.getResources().getColor(R.color.colorWhite));
            }
        }

        btnSearch = rootView.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
        rcvSearchedHotel = rootView.findViewById(R.id.rcvSearchedHotel);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(context);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        rcvSearchedHotel.setLayoutManager(layoutManager);
        getKeyWord();
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearch:
                gotoSearch("");
                break;
        }
    }

    private void gotoSearch(String key) {
        Intent intent = new Intent(context, SearchActivity.class);
        if (key != null && !key.isEmpty())
            intent.putExtra("keyWord", key);
        startActivity(intent);
    }

    private void getKeyWord() {
        GoHotelApplication.serviceApi.getKeySearch().enqueue(new Callback<List<SearchForm>>() {
            @Override
            public void onResponse(Call<List<SearchForm>> call, Response<List<SearchForm>> response) {
                if (response.code() == 200) {
                    searchForms = response.body();
                    if (searchForms != null && searchForms.size() > 0) {
                        handleSearch();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SearchForm>> call, Throwable t) {

            }
        });
    }

    private void handleSearch() {
        rcvSearchedHotel.setAdapter(new SearchedAdapter(context, searchForms, new SearchedAdapter.OnclickListener() {
            @Override
            public void onClick(String key) {
                gotoSearch(key);
            }
        }));

    }
}
