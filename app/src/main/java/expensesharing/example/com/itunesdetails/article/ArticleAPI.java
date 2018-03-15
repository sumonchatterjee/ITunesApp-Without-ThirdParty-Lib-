package expensesharing.example.com.itunesdetails.article;

import android.text.TextUtils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sumon.chatterjee on 14/03/18.
 */

public class ArticleAPI {
    private static final String REQUEST_METHOD = "GET";
    private static final int READ_TIMEOUT = 100000;

    public JSONObject fetchArticle(String url){
        String result="";
        String inputLine;
        try {

            //Create a URL object holding our url
            URL myUrl = new URL(url);
            //Create a connection
            HttpURLConnection connection =(HttpURLConnection)
                    myUrl.openConnection();
            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(READ_TIMEOUT);

            //Connect to our url
            connection.connect();

            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());
            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            //Check if the line we are reading is not null
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            //Close our InputStream and Buffered reader
            reader.close();
            streamReader.close();
            //Set our result equal to our stringBuilder
            result = stringBuilder.toString();
            JSONObject obj = new JSONObject(result);
            return  obj;

        }catch (Exception ex){

        }
       return null;
    }
}
