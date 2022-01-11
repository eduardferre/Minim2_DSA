package edu.upc.dsa;

import java.util.List;

import edu.upc.dsa.models.Repos;
import edu.upc.dsa.models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("users/{username}")
    Call<User> getInfoUser(@Path("username") String username);

    @GET("users/{username}/repos")
    Call<List<Repos>> getRepos(@Path("username") String username);
}
