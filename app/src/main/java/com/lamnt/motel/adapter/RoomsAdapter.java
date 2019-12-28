package com.lamnt.motel.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.lamnt.motel.R;
import com.lamnt.motel.model.Room;
import com.lamnt.motel.webservice.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Room> rooms;

    private OnItemClickListenner onItemClickListenner;

    public RoomsAdapter(Context context, List<Room> rooms) {
        this.rooms = rooms;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = inflater.inflate(R.layout.item_room, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Room room = rooms.get(position);
        holder.txtName.setText(room.getMaPhong());
        int soNguoi = room.getSoNguoi();
        if(soNguoi == 0){
            holder.txtState.setText("Còn trống");
            holder.txtState.setTextColor(Color.RED);
        }
        else {
            holder.txtState.setText("Đã thuê");
            holder.txtState.setTextColor(Color.GREEN);
        }

        holder.txtPrice.setText(room.getGiaPhong() + " đ");

        holder.swipeMenu.setShowMode(SwipeLayout.ShowMode.PullOut);
        holder.swipeMenu.addDrag(SwipeLayout.DragEdge.Right, holder.swipeMenu.findViewById(R.id.swipeRTL));
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa phòng");
                builder.setMessage("Bạn có muốn xóa phòng " +
                        rooms.get(holder.getAdapterPosition()).getMaPhong() + " không?").setCancelable(false);
                builder.setPositiveButton("Có", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Client.getService().deleteRoom(rooms.get(holder.getAdapterPosition()).getMaPhong()).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
                        notifyItemRemoved(holder.getAdapterPosition());
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        holder.swipeMenu.close();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListenner.onItemClicked(holder.getAdapterPosition(), v);
                holder.swipeMenu.close();
            }
        });

        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListenner.onItemClicked(holder.getAdapterPosition(), v);
                holder.swipeMenu.close();
            }
        });

        holder.cvRoomDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListenner.onItemClicked(holder.getAdapterPosition(), v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public void setOnItemClickListenner(OnItemClickListenner onItemClickListenner) {
        this.onItemClickListenner = onItemClickListenner;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgAvt;
        private TextView txtName;
        private TextView txtState;
        private TextView txtPrice;
        private ImageView imgEdit;
        private ImageView imgDelete;
        private ImageView imgView;
        private SwipeLayout swipeMenu;
        private CardView cvRoomDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvt = itemView.findViewById(R.id.imgAvt);
            txtName = itemView.findViewById(R.id.txtName);
            txtState = itemView.findViewById(R.id.txtState);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            imgView = itemView.findViewById(R.id.imgView);
            imgEdit = itemView.findViewById(R.id.imgEdit);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            swipeMenu = itemView.findViewById(R.id.swipeMenu);
            cvRoomDetail = itemView.findViewById(R.id.cvRoomDetail);
        }
    }

    public interface OnItemClickListenner {
        void onItemClicked(int position, View view);
    }
}
