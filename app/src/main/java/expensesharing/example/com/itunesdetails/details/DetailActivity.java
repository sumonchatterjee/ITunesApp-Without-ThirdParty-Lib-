package expensesharing.example.com.itunesdetails.details;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import expensesharing.example.com.itunesdetails.R;
import expensesharing.example.com.itunesdetails.article.Article;


/**
 * Created by sumon.chatterjee on 15/03/18.
 */

public class DetailActivity extends AppCompatActivity implements IDetailContract.View {

    Article article;
    DetailPresenter mPresenter;
    ProgressBar bar;
    ImageView imgVw;
    String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getBundleData();
        initializeViews();
    }


    private void getBundleData(){
        Bundle bundle = getIntent().getExtras();
        article =(Article) bundle.getParcelable("article_data");

    }


    private void initializeViews(){
        ((TextView)findViewById(R.id.collection_name)).setText("Collection :" + article.getCollectionName());
        ((TextView)findViewById(R.id.track_name)).setText(article.getTrackName());

        ((TextView)findViewById(R.id.colletion_price)).setText(Double.toString(article.getCollectionPrice()));
        imgVw =  ((ImageView)findViewById(R.id.detail_imgvw));

        bar = (ProgressBar)findViewById(R.id.progress_bar);

       // mPresenter=new DetailPresenter(this,this);
       // mPresenter.fetchImageFromRemoteSource(url);
    }


    @Override
    public void loadDownloadingScene() {
        bar.setVisibility(View.VISIBLE);
    }


    @Override
    public void loadEmptyScreen() {

    }


    @Override
    public void showImage(Bitmap object) {
        bar.setVisibility(View.GONE);
        imgVw.setImageBitmap(object);
    }
}
