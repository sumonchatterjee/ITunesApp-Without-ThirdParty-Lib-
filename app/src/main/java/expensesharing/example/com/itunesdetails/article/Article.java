package expensesharing.example.com.itunesdetails.article;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sumon.chatterjee on 15/03/18.
 */

public class Article implements Parcelable {

    String trackId;
    String collectionName;
    String trackName;
    double collectionPrice;

    public String getImggUrl() {
        return imggUrl;
    }

    String imggUrl;


    public Article(String trackId, String collectionName, String trackName, double collectionPrice,String imggUrl) {
        this.trackId = trackId;
        this.collectionName = collectionName;
        this.trackName = trackName;
        this.collectionPrice = collectionPrice;
        this.imggUrl = imggUrl;
    }


    /**
     * Use when reconstructing User object from parcel
     * This will be used only by the 'CREATOR'
     * @param in a parcel to read this object
     */
    public Article(Parcel in) {
        this.trackId = in.readString();
        this.collectionName = in.readString();
        this.trackName = in.readString();
        this.collectionPrice = in.readFloat();
        this.imggUrl = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(trackId);
        dest.writeString(collectionName);
        dest.writeString(trackName);
        dest.writeDouble(collectionPrice);
        dest.writeString(imggUrl);
    }


    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {

        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        public Article[] newArray(int size) {
            return new Article[size];
        }
    };


    public String getId() {
        return trackId;
    }

    public void setId(String id) {
        this.trackId = id;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public double getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(double collectionPrice) {
        this.collectionPrice = collectionPrice;
    }



    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Article) {
            Article toCompare = (Article) obj;
            return (this.trackId.equalsIgnoreCase(toCompare.getId()));
        }

        return false;
    }

    @Override
    public int hashCode() {
        return (this.getId()).hashCode();
    }

}
