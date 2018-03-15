package expensesharing.example.com.itunesdetails.article;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import expensesharing.example.com.itunesdetails.R;
import expensesharing.example.com.itunesdetails.imageDownloading.ImageLoader;

/**
 * Created by sumon.chatterjee on 14/03/18.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> implements View.OnClickListener{

    private Context mContext;
    private JSONArray detailArr;
    private ClickListener mListener;


    ArticleAdapter(Context mContext){
        this.mContext=mContext;
    }


    public void setData(JSONArray dataArr){
     this.detailArr = dataArr;
    }

    public void setClickListener(ClickListener listener){
        this.mListener = listener;
    }


    @Override
    public int getItemCount() {
        return detailArr.length();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      JSONObject obj = detailArr.optJSONObject(position);
      if(obj!=null){
         holder.text.setText(obj.optString("trackName"));
          String image_url = obj.optString("artworkUrl100");


          int loader = R.drawable.loader;
          ImageLoader imgLoader = new ImageLoader(mContext);
          imgLoader.DisplayImage(image_url, loader, holder.image);


          holder.mainContainer.setTag(obj);
          holder.mainContainer.setOnClickListener(this);

         // new DownloadImageTask(mContext, holder.image, image_url).execute();
      }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView text;
        private RelativeLayout mainContainer;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            text = (TextView) itemView.findViewById(R.id.text);
            mainContainer = (RelativeLayout)itemView.findViewById(R.id.main_container);
        }
    }

    @Override
    public void onClick(View view) {
        JSONObject obj = (JSONObject) view.getTag();
        mListener.onClickItem(obj);
    }


    interface ClickListener{
        void onClickItem(JSONObject object);
    }
}
