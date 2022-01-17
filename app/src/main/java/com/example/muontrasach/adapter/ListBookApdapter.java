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
import com.example.muontrasach.model.Book;

import java.util.List;

public class ListBookApdapter extends RecyclerView.Adapter<ListBookApdapter.ViewHolder>  {

    private List<Book> mBookList;
    private Context mContext;
    private onClickItem onClickItem;
    String user;
    int stt;

    public ListBookApdapter(Context mContext, onClickItem listener) {
        this.mContext = mContext;
        this.onClickItem = listener;
    }

    public void setListItem(List<Book> bookList) {
        this.mBookList = bookList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListBookApdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.example_item, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        inflate.setLayoutParams(lp);
        return new ListBookApdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBookApdapter.ViewHolder holder, int position) {
        Book book = mBookList.get(position);
        holder.name.setText(book.getName());
        holder.category.setText(book.getType());

        holder.btnBorrow.setOnClickListener(v -> {
          onClickItem.onClickBook(0, book);
          onClickItem.getIDtoBorrow(book);
        });

        holder.btnDel.setOnClickListener(v -> {
            onClickItem.onClickBook(1, book);
        });
        holder.btnEdit.setOnClickListener(v -> {
            onClickItem.onClickBook(2, book);
        });

    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, category;
        private Button btnDel, btnEdit,btnBorrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            category = itemView.findViewById(R.id.tv_category);

            btnBorrow = itemView.findViewById(R.id.btn_borrow);
            btnDel = itemView.findViewById(R.id.btn_del);
            btnEdit = itemView.findViewById(R.id.btn_edit);
        }
    }

    public interface onClickItem {
        void onClickBook(int fun, Book book);
        void getIDtoBorrow(Book book);

    }
}

