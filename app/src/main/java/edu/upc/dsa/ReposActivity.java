package edu.upc.dsa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.upc.dsa.models.Repos;
import edu.upc.dsa.models.User;
import edu.upc.dsa.ui.main.ListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReposActivity extends AppCompatActivity {

    ImageView profileImage;
    TextView userName, followers, following, repositories;
    ProgressBar circularProgress;

    RecyclerView recyclerView;
    ApiInterface apiInterface;

    public static final String API_URL = "https://api.github.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        this.profileImage = findViewById(R.id.profilePic);
        this.userName = findViewById(R.id.userNameText);
        this.followers = findViewById(R.id.followersNumber);
        this.following = findViewById(R.id.followingNumber);
        this.circularProgress = findViewById(R.id.progressBarRepos);
        this.recyclerView = findViewById(R.id.recyclerViewID);

        circularProgress.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

        SharedPreferences sharedPrefer = getSharedPreferences("userName", Context.MODE_PRIVATE);
        String userNamePass = sharedPrefer.getString("User", null);

        getUserByID(userNamePass);
        getAllRepos(userNamePass);
    }

    public void getUserByID (String userID){
        Call<User> call = apiInterface.getInfoUser(userID);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(), ErrorActivity.class);
                    startActivity(intent);
                }

                User user = response.body();
                String numFollowers = user.getFollowers();
                String numFollowing = user.getFollowing();
                //Picasso.get().load(user.getAvatar_url()).into(avatar);
                userName.setText(userID);
                followers.setText(numFollowers);
                following.setText(numFollowing);
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Intent intent = new Intent(getApplicationContext(), ErrorActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getAllRepos (String userID) {
        Call<List<Repos>> call = apiInterface.getRepos(userID);
        call.enqueue(new Callback<List<Repos>>() {

            @Override
            public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {
                if(!response.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(), ErrorActivity.class);
                    startActivity(intent);
                }

                List<Repos> reposList = response.body();
                ListAdapter myAdapter = new ListAdapter(reposList, ReposActivity.this);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(ReposActivity.this));
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Call<List<Repos>> call, Throwable t) {
                Intent intent = new Intent (getApplicationContext(), ErrorActivity.class);
                startActivity(intent);
            }
        });
    }


    public void returnToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
