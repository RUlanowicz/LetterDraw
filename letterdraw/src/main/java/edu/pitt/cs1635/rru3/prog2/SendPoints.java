package edu.pitt.cs1635.rru3.prog2;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ulanowicz on 2/2/14.
 */
public class SendPoints extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        final String key = "11773edfd643f813c18d82f56a8104ed";
        final String url = "http://cwritepad.appspot.com/reco/usen";
        String answer = null;

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> data = new ArrayList<NameValuePair>();
        data.add(new BasicNameValuePair("key",key));
        data.add(new BasicNameValuePair("q",params[0]));
        try{
            post.setEntity(new UrlEncodedFormEntity(data));
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            answer = EntityUtils.toString(entity);
        }
        catch(Exception e){
            Log.i("SendPoints","broken");
        }
        return answer;
    }

    @Override
    protected void onPostExecute(String result){

    }

}
