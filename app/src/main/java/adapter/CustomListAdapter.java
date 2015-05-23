package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.technignite.biblebuddy_v1.R;

import java.util.List;

import app.AppController;
import model.Verse;

/**
 * Created by Arul Frances on 5/22/2015.
 */
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Verse> verses;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<Verse> verses)
    {
        this.activity = activity;
        this.verses = verses;
    }

    public int getCount()
    {
        return verses.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView verse_pic = (NetworkImageView) convertView.findViewById(R.id.verse_pic);
        TextView verse = (TextView) convertView.findViewById(R.id.verse);
        TextView chapter = (TextView) convertView.findViewById(R.id.chapter);
        TextView author = (TextView) convertView.findViewById(R.id.author);
        TextView date = (TextView) convertView.findViewById(R.id.date);

        Verse v = verses.get(position);

        verse_pic.setImageUrl(v.getVerseUrl(), imageLoader);

        verse.setText(v.getVerse());

        chapter.setText(v.getChapter());

        author.setText(v.getAuthor());

        date.setText(v.getDate());

        return convertView;


    }

    public Object getItem(int location)
    {
        return verses.get(location);
    }

    public long getItemId(int position)
    {
        return position;
    }
}
