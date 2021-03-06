package com.example.swp_dongnae;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> { // qna, 협업 게시글 어댑터 아래 내용은 다른 어댑터와 비슷
    final ArrayList<NoticeSub> arrayList;
    final Context context;
    OnNoticeItemClickListener listener;


    public NoticeAdapter(ArrayList<NoticeSub> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_notice_adapter, parent, false);
        NoticeViewHolder holder = new NoticeViewHolder(view, this.listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {

        holder.title.setText(arrayList.get(position).getTitle());

        holder.user.setText(arrayList.get(position).getUser());

        holder.date.setText(arrayList.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public void setOnItemClickListener(OnNoticeItemClickListener listener) {
        this.listener = listener;
    }

    public void onItemClick(NoticeViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }


    public static class NoticeViewHolder extends RecyclerView.ViewHolder {


        TextView user;
        TextView date;
        TextView title;

        public NoticeViewHolder(@NonNull View itemView, final OnNoticeItemClickListener listener) {
            super(itemView);
            this.title = itemView.findViewById(R.id.tv_title);
            this.user = itemView.findViewById(R.id.tv_user);
            this.date = itemView.findViewById(R.id.tv_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(NoticeViewHolder.this, v, position);
                    }

                }
            });

        }

    }


    public NoticeSub getItem(int position) {
        return arrayList.get(position);
    }


}
