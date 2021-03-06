package com.example.swp_dongnae;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter adapter; // 클럽네임과 상이함
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<CategorySub> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        recyclerView = findViewById(R.id.recyclerView); //TODO 리사이클 뷰 아이디 연
        recyclerView.setHasFixedSize(true); //리사이클러뷰 성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // 카테고리 액티비티 클래스의 객체를 담을 어레이 리스트

        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터 베이스 연동
        databaseReference = database.getReference().child("분과 종류"); //db 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는곳
                arrayList.clear(); //기존 배열리스트 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {  //반복문으로 데이터 리스트를 추출
                    CategorySub categorySub = new CategorySub();
                    categorySub.setId(snapshot.child("id").getValue().toString());
                    arrayList.add(categorySub);
                }
                adapter.notifyDataSetChanged();//리스트 저장 및 새로고침

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MainActivity", String.valueOf(databaseError.toException()));   //에러문 출력
            }
        });
        adapter = new CustomAdapter(arrayList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결
        adapter.setOnItemClickListener(new OnCategoryItemClickListener() {
            @Override
            public void onItemClick(CustomAdapter.CustomViewHolder holder, View view, int position) {
                //CategorySub item = adapter.getItem(position);
                int itemPosition = recyclerView.getChildAdapterPosition(view);

                String index;

                SwitchTool switchTool = new SwitchTool();
                index = switchTool.switchClub(itemPosition);
                String nickName = getIntent().getStringExtra("nickName");
                Intent intent = new Intent(CategoryActivity.this, ClubNameActivity.class);
                intent.putExtra("pos", index);
                intent.putExtra("posInt",itemPosition);
                intent.putExtra("nickName",nickName);
                Log.v("01077368247", index);
                startActivity(intent);//액티비티 이동
            }
        });

    }
}

