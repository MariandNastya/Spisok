    package com.example.spisok;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.ProgressBar;
    import android.widget.TextView;

    import androidx.appcompat.app.AppCompatActivity;
    import java.io.IOException;
    import okhttp3.ResponseBody;
    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    import java.util.List;

    public class ThirdActivity extends AppCompatActivity {
        private ProgressBar mProgressBar;
        private TextView mTextView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity3);

            mTextView = (TextView) findViewById(R.id.txtViewUser);
            mProgressBar = (ProgressBar) findViewById(R.id.progrBar);
            mProgressBar.setVisibility(View.INVISIBLE);

        }
        public void onClick (View view){
            mProgressBar.setVisibility(View.VISIBLE);
            GitHubService3 gitHubService = GitHubService3.retrofit.create(GitHubService3.class);
            final Call<List<Repos>> call = gitHubService.getRepos("alexanderklimov");

            call.enqueue(new Callback<List<Repos>>() {
                @Override
                public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {
                    if (response.isSuccessful()) {
                        mTextView.setText(response.body().toString() + "\n");
                        for (int i = 0; i < response.body().size(); i++) {
                            mTextView.append(response.body().get(i).getName() + "\n");
                        }
                        mProgressBar.setVisibility(View.INVISIBLE);
                    } else {
                        int statusCode = response.code();

                        ResponseBody errorBody = response.errorBody();
                        try {
                            mTextView.setText(errorBody.string());
                            mProgressBar.setVisibility(View.INVISIBLE);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<Repos>> call, Throwable throwable) {
                    mTextView.setText("Что-то пошло не так: " + throwable.getMessage());
                }
        });
    }
    public void Poisk(View view) {
        Intent intent = new Intent(getApplicationContext(), FourthActivity.class);
        startActivity(intent);
    }
    public void Back(View view) {
         Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
         startActivity(intent);
    }
}

