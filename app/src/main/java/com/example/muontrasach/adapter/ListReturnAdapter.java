package com.example.muontrasach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muontrasach.R;
import com.example.muontrasach.model.PhieuMuon;

import java.util.List;

public class ListReturnAdapter extends RecyclerView.Adapter<ListReturnAdapter.ViewHolder>  {

    private List<PhieuMuon> mPhieuMuon;
    private Context mContext;
    private onClickItem onClickItem;
    String user;
    int stt;

    public ListReturnAdapter(Context mContext, onClickItem listener) {
        this.mContext = mContext;
        this.onClickItem = listener;
    }

    public void setListItem(List<PhieuMuon> pm) {
        this.mPhieuMuon = pm;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListReturnAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.borrow_item, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        inflate.setLayoutParams(lp);
        return new ListReturnAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ListReturnAdapter.ViewHolder holder, int position) {
        PhieuMuon pm = mPhieuMuon.get(position);
        holder.nameBook.setText("Book: "+pm.getBook_Muon());
        holder.idpm.setText("ID: "+pm.getId_Muon());
        holder.person.setText("Borrower: "+pm.getPerson_Muon());
        holder.date.setText("Registration Date: "+pm.getDate());
        if(pm.getTrangThai() == 1){
            holder.status.setText("Đã Trả");
        }else{
            holder.status.setText("Chưa Trả");
        }

        holder.btnConfirm.setOnClickListener(v -> {
            onClickItem.onClickConfirm(0, pm);
        });

        holder.btnDel.setOnClickListener(v -> {
            onClickItem.onClickConfirm(1, pm);
        });

    }

    @Override
    public int getItemCount() {
        return mPhieuMuon.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameBook, idpm,person,date,status;
        private Button btnConfirm, btnDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameBook = itemView.findViewById(R.id.tvBook);
            idpm = itemView.findViewById(R.id.tvID);

            person = itemView.findViewById(R.id.tv_person);
            date = itemView.findViewById(R.id.tv_date);
            status = itemView.findViewById(R.id.tv_status);
            btnConfirm = itemView.findViewById(R.id.btn_confirm);
            btnDel = itemView.findViewById(R.id.btn_del);
        }
    }

    public interface onClickItem {
        void onClickConfirm(int fun, PhieuMuon pm);

    }
}

