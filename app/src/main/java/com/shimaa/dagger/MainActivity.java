package com.shimaa.dagger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.shimaa.dagger.adaptors.AdapterRepos;
import com.shimaa.dagger.components.DaggerMainActivityComponent;
import com.shimaa.dagger.components.MainActivityComponent;
import com.shimaa.dagger.controls.MyApplicationClass;
import com.shimaa.dagger.models.GithubRepo;
import com.shimaa.dagger.modules.MainActivityModule;
import com.shimaa.dagger.network.GithubService;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.repo_home_list)
    ListView listView;

    @Inject
    GithubService githubService;
    @Inject
    Picasso mPicasso;

    Call<List<GithubRepo>> reposCall;

    // tell the Dagger to populate this field
    @Inject
    AdapterRepos adapterRepos;
    MainActivityComponent component ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Replaced by HomeActivityComponent version
        //githubService = GithubApplication.get(this).getGithubService();
        // replaced by @Inject
        // mPicasso = GithubApplication.get(this).getPicasso();

        MainActivityComponent component= DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .componentInterFace(MyApplicationClass.get(this).getComponent())
                .build();

//                .homeActivityModule(new MainActivityModule(this))
//                .githubApplicationComponent(MyApplicationClass.get(this).getComponent()) /
//                .build();

        // Inject something here
        component.injectHomeActivity(this);

        // old way
//        adapterRepos = new AdapterRepos(this, mPicasso);
        // replaced by @Inject
//        adapterRepos=component.getAdapterRepos();
        listView.setAdapter(adapterRepos);

        // replaced by @Inject
        // we can also retrieve GithubService through our HomeActivityComponent instead of Application
//        githubService = component.getGithubService();

        reposCall = githubService.getReposForUser("ShiryAbdo");
        reposCall.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                adapterRepos.swapData(response.body());
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this,   t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (reposCall != null) {
            reposCall.cancel();
        }
    }
}
