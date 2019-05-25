package com.example.dangnguyenhai.gohotel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dangnguyenhai.gohotel.R;
import com.example.dangnguyenhai.gohotel.model.api.CityForm;

import java.util.List;

public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.ViewHolder> {
    private Context context;
    private List<CityForm> cityForms;

    public ProvinceAdapter(Context context, List<CityForm> cityForms) {
        this.context = context;
        this.cityForms = cityForms;
    }

    @NonNull
    @Override
    public ProvinceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.province_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProvinceAdapter.ViewHolder holder, int position) {
        holder.tvProvinceName.setText(cityForms.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return cityForms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProvinceName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvProvinceName = itemView.findViewById(R.id.tvProvinceName);
        }
    }
}
