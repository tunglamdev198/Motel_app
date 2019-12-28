package com.lamnt.motel.view.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamnt.motel.R;
import com.lamnt.motel.model.Customer;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Customer> customers;

    private OnItemClickListener onItemClickListener;

    public CustomerAdapter(Context context, List<Customer> customers) {
        this.customers = customers;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.item_customer, viewGroup, false);

        return new ViewHolder(view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Customer customer = customers.get(i);

        viewHolder.txtHoTen.setText(customer.getHoTen());
        viewHolder.txtSDT.setText(customer.getSdt());

        viewHolder.cvCustomer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemClickListener.onItemClicked(viewHolder.getAdapterPosition());
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtHoTen;
        private TextView txtSDT;
        private CardView cvCustomer;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtSDT = itemView.findViewById(R.id.txtSDT);
            cvCustomer = itemView.findViewById(R.id.cvCustomer);

        }
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

}
