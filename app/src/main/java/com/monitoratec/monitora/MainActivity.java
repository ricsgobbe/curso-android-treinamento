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

import com.jakewharton.rxbinding.widget.RxTextView;
import com.monitoratec.monitora.domain.entity.AccessToken;
import com.monitoratec.monitora.domain.entity.GitHubApi;
import com.monitoratec.monitora.domain.entity.GitHubOAuthApi;
import com.monitoratec.monitora.domain.entity.GithubStatusApi;
import com.monitoratec.monitora.domain.entity.Status;
import com.monitoratec.monitora.domain.entity.User;
import com.monitoratec.monitora.util.AppUtils;
import com.monitoratec.monitora.util.MySubscribe;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.monitoratec.monitora.domain.entity.Status.Type.GOOD;
import static com.monitoratec.monitora.domain.entity.Status.Type.MAJOR;
import static com.monitoratec.monitora.domain.entity.Status.Type.MINOR;

public class MainActivity extends BaseActivity {

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
    @Inject GitHubApi githubApi;
    @Inject SharedPreferences sharedPreferences;
    @Inject GitHubOAuthApi githubOAuthApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDaggerDiComponent().inject(this);
        ButterKnife.bind(this);

        gitBtnLogin.setOnClickListener(view -> {
           if(AppUtils.vailidateRequiredField(MainActivity.this, gitLogin, gitPassword)){
                String userName = gitLogin.getEditText().getText().toString();
                String password = gitPassword.getEditText().getText().toString();
                final String credential = Credentials.basic(userName, password);
               githubApi.basicAuth(credential)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new MySubscribe<User>() {
                           @Override
                           public void onCompleted() {

                           }

                            @Override
                            public void onError(String message) {

                            }

                            @Override
                           public void onNext(User user) {
                               sharedPreferences.edit()
                                       .putString(getString(R.string.sp_credential_key), credential)
                                       .apply();
                               Snackbar.make(view, user.login, Snackbar.LENGTH_LONG).show();
                               sharedPreferences.getString(credential, "");
                           }
                       });
               processOAuthRedirectUri();

               RxTextView.textChanges(gitLogin.getEditText()).skip(1).subscribe(text ->{
                  AppUtils.vailidateRequiredField(this, gitLogin);
               });
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
                githubOAuthApi.accessToken(clientId, clientSecret, code)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<AccessToken>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, e.getMessage());
                            }

                            @Override
                            public void onNext(AccessToken accessToken) {
                                sharedPreferences.edit()
                                        .putString(getString(R.string.sp_credential_key), accessToken.getAuthCredential())
                                        .apply();
                                Snackbar.make(gitOAuth, accessToken.access_token, Snackbar.LENGTH_LONG).show();
                                sharedPreferences.getString(accessToken.access_token, "");
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
    }

    private void setImageAndTextColor(Status status){

                int color = getResources().getColor(status.getStatusName().getColor());
                gitText.setText(status.getStatusName().getStatusName());
                gitText.setTextColor(color);
                gitImage.setBackgroundColor(color);

    }
}
