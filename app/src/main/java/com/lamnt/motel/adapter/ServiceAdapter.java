package com.lamnt.motel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lamnt.motel.R;
import com.lamnt.motel.model.ServiceMotel;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {


    private Context context;
    private LayoutInflater inflater;
    private List<ServiceMotel> services;

    OnItemClickListenner onItemClickListenner;

    public ServiceAdapter(Context context,List<ServiceMotel> services) {
        this.context = context;
        this.services = services;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_service, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        ServiceMotel service = services.get(position);
        holder.txtTenDV.setText(service.getTenDV());
        holder.txtGiaDV.setText("Giá : " + service.getGiaDV() + " đ");
        holder.cvItemService.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemClickListenner.onItemClicked(holder.getAdapterPosition());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public void setOnItemClickListenner(ServiceAdapter.OnItemClickListenner onItemClickListenner) {
        this.onItemClickListenner = onItemClickListenner;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cvItemService;
        private TextView txtTenDV;
        private TextView txtGiaDV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvItemService = itemView.findViewById(R.id.cvItemService);
            txtTenDV = itemView.findViewById(R.id.txtTenDV);
            txtGiaDV = itemView.findViewById(R.id.txtGiaDV);
        }
    }

    public interface OnItemClickListenner {
        void onItemClicked(int position);
    }
}
