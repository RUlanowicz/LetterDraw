package edu.pitt.cs1635.rru3.prog2;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Text;

import java.util.List;

import butterknife.InjectView;

public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";
    private String inMain;
    private String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final myCanvas myC = (myCanvas)findViewById(R.id.myCustom);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        final Button subBut = (Button)findViewById(R.id.submit_but);
        subBut.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                inMain = myC.toServer.toString()+ ", 255, 255]";
                try{
                    answer = new SendPoints().execute(inMain).get();
                    TextView op = (TextView)findViewById(R.id.answer);
                    op.setText(answer);
                }
                catch(Exception E){
                    Log.i(TAG,"Concurrent");
                }
                Log.i(TAG,inMain);
            }
        });

        final Button clearBut = (Button)findViewById(R.id.clear_but);
        clearBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                myCanvas myCan = (myCanvas)findViewById(R.id.myCustom);
                myCan.reset();
                TextView answerView = (TextView)findViewById(R.id.answer);
                answerView.setText("");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
