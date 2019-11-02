package com.example.js;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.js.Model.Post;
import com.example.js.Service.RetrofitInstance;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


// Raw data https://jsonplaceholder.typicode.com/posts/

public class MainActivity extends AppCompatActivity {


    private RecyclerViewPostAdapter mRecyclerViewPostAdapter;
    private ArrayList<Post> mListPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerViewPostAdapter = new RecyclerViewPostAdapter(new ArrayList<Post>(0)); //initialize a new item => avoid null


        // initialize recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mRecyclerViewPostAdapter);
        recyclerView.setHasFixedSize(true);

        // get drawable - divider
        Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.custom_divider);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(dividerDrawable);

//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.ClickListener() {
            @Override
            public void onClick(View view, int childPosition) {
                Toast.makeText(MainActivity.this, "Position " + childPosition, Toast.LENGTH_SHORT).show();
            }
        }));
//        loadPosts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "loadPosts: starts");

        Call<ArrayList<Post>> mCall = RetrofitInstance.getClient().getPosts();
        mCall.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                // successful -> load data
                if (response.isSuccessful()) {
                    mListPost = response.body();
                    // load data from response into recycler view
                    Log.d("loadPosts", "onResponse: data size = " + mListPost.size());
                    mRecyclerViewPostAdapter.updateListData(mListPost);
                    Toast.makeText(MainActivity.this, "Server successed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Server returned an error", Toast.LENGTH_SHORT).show();
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                Log.d("MainActivity", "onFailure: failed");
                Toast.makeText(MainActivity.this, "Server Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }

//    // this method will lost post from json api
//    private void loadPosts() {
//
//    }


}
