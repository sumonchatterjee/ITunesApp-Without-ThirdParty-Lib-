package expensesharing.example.com.itunesdetails.details;

import android.graphics.Bitmap;

import org.json.JSONArray;

/**
 * Created by sumon.chatterjee on 15/03/18.
 */

public class IDetailContract {

    public interface Presenter{
        public void fetchImageFromRemoteSource(String url);

    }

    public interface View{
        public void showImage(Bitmap object);
        public void loadEmptyScreen();
        public void loadDownloadingScene();
    }

}
