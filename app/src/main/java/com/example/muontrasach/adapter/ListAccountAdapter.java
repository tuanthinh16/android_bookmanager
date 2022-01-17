package com.example.muontrasach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muontrasach.MainActivity;
import com.example.muontrasach.R;
import com.example.muontrasach.model.Account;
import com.example.muontrasach.model.Book;

import java.util.List;

public class ListAccountAdapter extends RecyclerView.Adapter<ListAccountAdapter.ViewHolder>  {

    private List<Account> mAccountList;
    private Context mContext;
    private onClickItem onClickItem;
    String user;
    int stt;

    public ListAccountAdapter(Context mContext, onClickItem listener) {
        this.mContext = mContext;
        this.onClickItem = listener;
    }

    public void setListItem(List<Account> accountList) {
        this.mAccountList = accountList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListAccountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.account_item, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        inflate.setLayoutParams(lp);
        return new ListAccountAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAccountAdapter.ViewHolder holder, int position) {
        Account acc = mAccountList.get(position);

        holder.name.setText("Name: "+acc.getName());
        holder.username.setText("Username: "+acc.getUsername());
        holder.email.setText("Email: "+acc.getEmail());
        holder.diachi.setText("Địa Chỉ: "+acc.getAddress());
        holder.count.setText("ID: "+acc.getId());
        holder.btnEdit.setOnClickListener(v -> {
            onClickItem.onClickAccount(0, acc);

        });

        holder.btnDel.setOnClickListener(v -> {
            onClickItem.onClickAccount(1, acc);
        });


    }

    @Override
    public int getItemCount() {
        return mAccountList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, username,email,diachi,count;
        private Button btnDel,btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvHoten);
            username = itemView.findViewById(R.id.tvUsr);
            email = itemView.findViewById(R.id.tvEmail);
            diachi = itemView.findViewById(R.id.tv_diachi);
            count = itemView.findViewById(R.id.tv_countsach);

            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDel = itemView.findViewById(R.id.btn_del);
        }
    }

    public interface onClickItem {
        void onClickAccount(int fun, Account account);

    }
}

