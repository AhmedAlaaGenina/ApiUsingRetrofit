package com.ahmedg.apiusingretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnNext, btnPrevious;
    TextView txtId, txtUserId, txtBody, txtTitle;
    PostsModelPojo[] postsModelPojo;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        txtId = findViewById(R.id.txtID);
        txtUserId = findViewById(R.id.txtUserID);
        txtTitle = findViewById(R.id.txtTitle);
        txtBody = findViewById(R.id.txtBody);
        APIPostsInterface postsInterface = APIClient.getClient().create(APIPostsInterface.class);
        Call<PostsModelPojo[]> call = postsInterface.getPost();
        call.enqueue(new Callback<PostsModelPojo[]>() {
            @Override
            public void onResponse(Call<PostsModelPojo[]> call, Response<PostsModelPojo[]> response) {
                Log.i("TAG", "onResponse: Success");
                postsModelPojo = response.body();
            }

            @Override
            public void onFailure(Call<PostsModelPojo[]> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.getMessage());
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                setLayout(postsModelPojo[index]);
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index--;
                if (index == -1) {
                    index = 0;
                }
                setLayout(postsModelPojo[index]);
            }
        });

    }

    public void setLayout(PostsModelPojo pojo) {
        txtId.setText("" + pojo.getId());
        txtUserId.setText("" + pojo.getUserId());
        txtTitle.setText(pojo.getTitle());
        txtBody.setText(pojo.getBody());
    }
}