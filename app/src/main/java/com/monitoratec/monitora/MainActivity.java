package com.monitoratec.monitora;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.monitoratec.monitora.domain.entity.GithubStatusApi;
import com.monitoratec.monitora.domain.entity.Status;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView gitText;
    private ImageView gitImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gitText = (TextView) findViewById(R.id.git_text_status);
        gitImage = (ImageView) findViewById(R.id.git_image);

        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(GithubStatusApi.BASE_URL)
                .build();

        GithubStatusApi statusApiImpl = retrofit.create(GithubStatusApi.class);

        statusApiImpl.lastMessage().enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                if(response.isSuccessful()){
                    Status.Type status = response.body().status;
                    switch (status){
                        case GOOD:
                            setImageAndTextColor(status);
                            break;
                        case MINOR:
                            setImageAndTextColor(status);
                            break;
                        case MAJOR:
                            setImageAndTextColor(status);
                            break;
                    }

                /*    Status body = response.body();
                    Toast.makeText(MainActivity.this, body.status, Toast.LENGTH_LONG).show();*/
                }else{
                    try {
                        String errorBody = response.errorBody().string();
                        Toast.makeText(MainActivity.this, errorBody, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {

            }
        });
    }


    private void setImageAndTextColor(Status.Type status){
                int color = getResources().getColor(status.getColor());
                gitText.setText(status.getStatusName());
                gitText.setTextColor(color);
                gitImage.setBackgroundColor(color);

    }
}
