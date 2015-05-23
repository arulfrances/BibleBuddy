package com.technignite.biblebuddy_v1;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.CustomListAdapter;
import app.AppController;
import model.Verse;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String url = "http://www.kerapumps.com/technignite/bible_buddy.json";
    private ProgressDialog progressDialog;
    private List<Verse> verseList = new ArrayList<Verse>();
    private ListView listView;
    private CustomListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_bb);
        adapter = new CustomListAdapter(this, verseList);
        listView.setAdapter(adapter);


        // Showing the HTTP Request Progress
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Blessing on the Way..");
        progressDialog.show();


    // Volley HTTP Request for Object from JSON

    JsonArrayRequest verse_Request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            Log.d(TAG, response.toString());
            hidePDialog();


            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject obj = response.getJSONObject(i);
                    Verse verse = new Verse();
                    verse.setVerse(obj.getString("verse"));
                    verse.setVerseUrl(obj.getString("verse_img"));
                    verse.setAuthor(obj.getString("author"));
                    verse.setChapter(obj.getString("chapter"));
                    verse.setDate(obj.getString("date"));
                    //Adding Verse to Verse List Array
                    verseList.add(verse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            adapter.notifyDataSetChanged();
        }

    }, new Response.ErrorListener() {

        public void onErrorResponse(VolleyError error) {
            VolleyLog.d(TAG, "Error Message: " + error.getMessage());
            hidePDialog();
        }

    });

  //Adding Request to Request Queue

    AppController.getInstance().addToRequestQueue(verse_Request);
}

public void onDestroy() {
    super.onDestroy();
    hidePDialog();
}
    private void hidePDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
