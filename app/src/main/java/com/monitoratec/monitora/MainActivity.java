package com.monitoratec.monitora;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.monitoratec.monitora.domain.entity.AccessToken;
import com.monitoratec.monitora.domain.entity.GitHubApi;
import com.monitoratec.monitora.domain.entity.GitHubOAuthApi;
import com.monitoratec.monitora.domain.entity.GithubStatusApi;
import com.monitoratec.monitora.domain.entity.Status;
import com.monitoratec.monitora.domain.entity.User;
import com.monitoratec.monitora.util.AppUtils;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.git_text_status)
     TextView gitText;
    @BindView(R.id.git_image)
     ImageView gitImage;
    private GithubStatusApi statusApiImpl;
    @BindView(R.id.tilUsername)
     TextInputLayout gitLogin;
    @BindView(R.id.tilPassword)
     TextInputLayout gitPassword;
    @BindView(R.id.git_button)
     Button gitBtnLogin;
    @BindView(R.id.git_oauth)
     Button gitOAuth;
    private boolean isFieldsOk;
    private GitHubApi githubApi;
    private SharedPreferences sharedPreferences;
    private GitHubOAuthApi githubOAuthApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gitText = (TextView) findViewById(R.id.git_text_status);
        gitImage = (ImageView) findViewById(R.id.git_image);
        gitLogin = (TextInputLayout) findViewById(R.id.tilUsername);
        gitPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        gitBtnLogin = (Button) findViewById(R.id.git_button);
        gitOAuth = (Button) findViewById(R.id.git_oauth);

        gitBtnLogin.setOnClickListener(view -> {
           if(AppUtils.vailidateRequiredField(MainActivity.this, gitLogin, gitPassword)){
                String userName = gitLogin.getEditText().getText().toString();
                String password = gitPassword.getEditText().getText().toString();
                final String credential = Credentials.basic(userName, password);
               githubApi.basicAuth(credential).enqueue(new Callback<User>() {
                   @Override
                   public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            String login = response.body().login;
                            sharedPreferences.edit()
                                    .putString(getString(R.string.sp_credential_key), credential)
                                    .apply();
                            Snackbar.make(view, login, Snackbar.LENGTH_LONG).show();
                            sharedPreferences.getString(credential, "");
                        }else{
                            try {
                                String error = response.errorBody().string();
                            } catch (IOException e) {
                               Log.e(TAG, e.getMessage());
                            }
                        }
                   }

                   @Override
                   public void onFailure(Call<User> call, Throwable t) {

                   }
               });
               processOAuthRedirectUri();
           }
        });



        gitOAuth.setOnClickListener(view -> {
            final String baseUrl = GitHubOAuthApi.BASE_URL + "authorize";
            final String clientId = getString(R.string.oauth_client_id);
            final String redirectUri = getOAuthRedirectUri();
            final Uri uri = Uri.parse(baseUrl + "?client_id=" + clientId + "&redirect_uri=" + redirectUri);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });


        statusApiImpl = GithubStatusApi.RETROFIT.create(GithubStatusApi.class);
        githubApi = GitHubApi.RETROFIT.create(GitHubApi.class);
        githubOAuthApi = GitHubApi.RETROFIT.create(GitHubOAuthApi.class);
        sharedPreferences = getSharedPreferences(getString(R.string.shared_key_file), MODE_PRIVATE);




    }

    private String getOAuthRedirectUri() {
        return getString(R.string.oauth_schema) + "://" + getString(R.string.oauth_host);
    }

    private void processOAuthRedirectUri() {
        // Os intent-filter's permitem a interação com o ACTION_VIEW
        final Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(this.getOAuthRedirectUri())) {
            String code = uri.getQueryParameter("code");
            if (code != null) {
                //TODO Pegar o access token (Client ID, Client Secret e Code)
                String clientId = getString(R.string.oauth_client_id);
                String clientSecret = getString(R.string.oauth_client_secret);
                githubOAuthApi.accessToken(clientId, clientSecret, code).enqueue(new Callback<AccessToken>() {
                    @Override
                    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                        if(response.isSuccessful()){
                            AccessToken accessToken = response.body();
                            sharedPreferences.edit()
                                    .putString(getString(R.string.sp_credential_key), accessToken.getAuthCredential())
                                    .apply();
                            Snackbar.make(gitOAuth, accessToken.access_token, Snackbar.LENGTH_LONG).show();
                            sharedPreferences.getString(accessToken.access_token, "");
                        }else{
                            try {
                                String error = response.errorBody().string();
                            } catch (IOException e) {
                                Log.e(TAG, e.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AccessToken> call, Throwable t) {

                    }
                });
            } else if (uri.getQueryParameter("error") != null) {
                //TODO Tratar erro11
            }
            // Limpar os dados para evitar chamadas múltiplas
            getIntent().setData(null);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
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
