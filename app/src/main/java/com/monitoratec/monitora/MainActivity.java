package com.monitoratec.monitora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.monitoratec.monitora.domain.entity.GithubStatusApi;
import com.monitoratec.monitora.domain.entity.Status;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(GithubStatusApi.BASE_URL)
                .build();

        GithubStatusApi statusApiImpl = retrofit.create(GithubStatusApi.class);

        statusApiImpl.lastMessage().enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                if(response.isSuccessful()){
                    Status body = response.body();
                    Toast.makeText(MainActivity.this, body.status, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {

            }
        });
    }
}
