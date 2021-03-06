package com.example.swp_dongnae;

import android.content.Context;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ClubViewHolder> {
    final ArrayList<Bs> arrayList; // 어레이 리스트 변수
    final Context context;

    public MediaAdapter(ArrayList<Bs> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public ClubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_item,parent,false);
        MediaAdapter.ClubViewHolder holder = new MediaAdapter.ClubViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClubViewHolder holder, int position) {
        Bs media_url = arrayList.get(position);
        Glide.with(holder.itemView) // 어레이 리스트의 이미지 가져오기
                .load(media_url.getMedia())
                .into(holder.tv_image);
    }

    @Override
    public int getItemCount() {
        return (arrayList !=null? arrayList.size():0);
    }

    public class ClubViewHolder extends RecyclerView.ViewHolder {
        ImageView tv_image; // 이미지 뷰

        public ClubViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_image = itemView.findViewById(R.id.tv_image); // 이미지뷰 아이디로 찾기
        }
    }

    public Bs getItem(int position) {return arrayList.get(position);}
}
