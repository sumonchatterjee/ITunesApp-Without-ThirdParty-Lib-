package expensesharing.example.com.itunesdetails.details;

import android.content.Context;
import android.view.InputDevice;

import expensesharing.example.com.itunesdetails.imageDownloading.DownloadImageTask;

/**
 * Created by sumon.chatterjee on 15/03/18.
 */

public class DetailPresenter implements IDetailContract.Presenter {

    private final IDetailContract.View mView;
    Context mContext;

    public DetailPresenter(IDetailContract.View view, Context mContext) {
        this.mView = view;
        this.mContext = mContext;
    }

    @Override
    public void fetchImageFromRemoteSource(String url) {
        new DownloadImageTask(mContext, url,mView).execute();
    }
}
