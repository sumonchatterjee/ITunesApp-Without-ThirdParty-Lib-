package expensesharing.example.com.itunesdetails.article;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import expensesharing.example.com.itunesdetails.R;
import expensesharing.example.com.itunesdetails.details.DetailActivity;

public class ArticleActivity extends AppCompatActivity implements IArticleContract.View,ArticleAdapter.ClickListener {

    ArticleAdapter mAdapter;
    private ArticlePresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();


       /* initialize presenter*/
        mPresenter=new ArticlePresenter(this,this);
        mPresenter.start();
        mPresenter.fetchFromRemoteSource();
    }


    /*initialize views*/
    private void initializeViews(){
        mAdapter =  new ArticleAdapter(this);
        mAdapter.setClickListener(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }


    @Override
    public void loadArticleScene(JSONArray object) {
      if(mAdapter!=null){
          mAdapter.setData(object);
          mAdapter.notifyDataSetChanged();
      }
    }


    @Override
    public void loadEmptyScreen() {

    }

    @Override
    public void loadDownloadingScene() {

    }

    @Override
    public void onClickItem(JSONObject object) {
       Article article = new Article(object.optString("trackId"),object.optString("collectionName"),
               object.optString("trackName"),object.optDouble("collectionPrice"),object.optString("artworkUrl100"));


       Intent intent = new Intent();
       intent.putExtra("article_data",article);
       intent.setClass(this, DetailActivity.class);
       startActivity(intent);
    }
}
