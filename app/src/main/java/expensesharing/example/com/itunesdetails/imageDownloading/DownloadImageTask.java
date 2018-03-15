package expensesharing.example.com.itunesdetails.imageDownloading;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import expensesharing.example.com.itunesdetails.details.IDetailContract;


/**
 * Created by sumon.chatterjee on 15/03/18.
 */

public class DownloadImageTask
    extends AsyncTask<String, Void, Bitmap>
    {

        private final String mUrl;
        private IDetailContract.View mView;

    public DownloadImageTask(Context context, String url,IDetailContract.View view)
    {
            mUrl = url;
            mView =view;

        }

    protected Bitmap doInBackground(String... urls)
    {
        String urlDisplay = mUrl;
        Bitmap bitmap = null;
        try {
            ImageDownload download = new ImageDownload();
            bitmap = download.downloadBitmap(urlDisplay);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap result)
    {

        if (result != null) {
            mView.showImage(result);
        }

    }
   /* public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = mDestination.getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
*/
}
