package com.example.swp_dongnae;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClubNameActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ClubNameSub> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_name);


        recyclerView = findViewById(R.id.recyclerView); //TODO 리사이클 뷰 아이디 연
        recyclerView.setHasFixedSize(true); //리사이클러뷰 성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // 카테고리 액티비티 클래스의 객체를 담을 어레이 리스트

        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터 베이스 연동
        databaseReference = database.getReference("CategoryActivity"); //db 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는곳
                arrayList.clear(); //기존 배열리스트 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){  //반복문으로 데이터 리스트를 추출
                    ClubNameSub clubNameSub = snapshot.getValue(ClubNameSub.class); //만들어둔 카테고리 액티비티 객체에 데이터를 담는다
                    arrayList.add(clubNameSub);

                }
                adapter.notifyDataSetChanged();//리스트 저장 및 새로고침

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MainActivity", String.valueOf(databaseError.toException()));   //에러문 출력
            }
        });
        adapter =new ClubNameAdapter(arrayList,this);
        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결
    }
}