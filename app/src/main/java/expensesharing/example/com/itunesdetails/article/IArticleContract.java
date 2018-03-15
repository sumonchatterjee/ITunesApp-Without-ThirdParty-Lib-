package expensesharing.example.com.itunesdetails.article;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by sumon.chatterjee on 14/03/18.
 */

public interface IArticleContract {

    public interface Presenter{
        public void start();
        public void stop();
        public void fetchFromRemoteSource();

    }

    public interface View{
        public void loadArticleScene(JSONArray object);
        public void loadEmptyScreen();
        public void loadDownloadingScene();
    }

}
