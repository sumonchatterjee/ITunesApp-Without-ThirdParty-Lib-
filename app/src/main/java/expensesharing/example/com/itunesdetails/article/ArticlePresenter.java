package expensesharing.example.com.itunesdetails.article;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by sumon.chatterjee on 14/03/18.
 */

public class ArticlePresenter implements IArticleContract.Presenter {

    private final IArticleContract.View mView;
    Context mContext;

    public ArticlePresenter(IArticleContract.View view, Context mContext) {
        this.mView = view;
        this.mContext = mContext;
    }


    @Override
    public void start() {

    }

    @Override
    public void stop() {
        /*if (mInitialTask != null && mInitialTask.getStatus() == AsyncTask.Status.RUNNING) {*/
        /*    mInitialTask.cancel(true);*/
        /*}*/
    }

    @Override
    public void fetchFromRemoteSource() {
       /* if (mInitialTask == null || mInitialTask.getStatus() != AsyncTask.Status.RUNNING) {*/
       /*     mInitialTask = new InitialTask( this, mView);*/
       /*     mInitialTask.execute();*/
       /*    */
       /* }*/

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            if(obj!=null){
                JSONArray resultArr = obj.optJSONArray("results");
                if(resultArr!=null){
                    mView.loadArticleScene(resultArr);
                }
            }
        }catch (Exception ex){
            //exception
        }
    }


    public String loadJSONFromAsset() {
        String json = null;
         try {
             InputStream is = mContext.getAssets().open("data.json");
             int size = is.available();
             byte[] buffer = new byte[size];
             is.read(buffer);
             is.close();
             json = new String(buffer, "UTF-8");
         }catch (Exception ex){
             //exception
         }
        return  json;
    }




    public static class InitialTask extends AsyncTask<Void, Integer, JSONArray> {
        public static final int STATE_LOADING = 1;
        public static final int STATE_EMPTY = 2;
        public static final int STATE_SHOW_ARTICLE = 3;

        private final IArticleContract.Presenter presenter;
        private final IArticleContract.View view;

        public InitialTask(IArticleContract.Presenter presenter, IArticleContract.View view) {
           // this.id = id;
            this.presenter = presenter;
            this.view = view;
        }


        @Override
        protected JSONArray doInBackground(Void... voids) {
            publishProgress(STATE_LOADING);

            ArticleAPI api = new ArticleAPI();
            try {

                JSONObject response = api.fetchArticle("");
                response.optInt("resultCount");
                JSONArray datas = response.optJSONArray("results");


                if (datas!= null) {
                    return datas;

                } else {
                    publishProgress(STATE_EMPTY);
                }
            } catch (Exception e) {
                e.printStackTrace();
                publishProgress(STATE_EMPTY);//Or no Network scene
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            switch (values[0]) {
                case STATE_LOADING:
                    view.loadDownloadingScene();
                    break;
                case STATE_EMPTY:
                    view.loadEmptyScreen();
                    break;
            }
        }

        @Override
        protected void onPostExecute(JSONArray article) {
            super.onPostExecute(article);
            if (article != null) {
                view.loadArticleScene(article);
            }
        }
    }


}

