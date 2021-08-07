package sg.edu.rp.c346.id20046765.problemstatementsolution;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Song> versionList;

    public CustomAdapter(Context context, int resource, ArrayList<Song> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        versionList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvRowSongTitle = rowView.findViewById(R.id.tvRowSongTitle);
        TextView tvRowYears = rowView.findViewById(R.id.tvRowYears);
        TextView tvRowSingers = rowView.findViewById(R.id.tvRowSingers);
        ImageView ivImage = rowView.findViewById(R.id.imageViewNews);
        RatingBar ratingBarStar = rowView.findViewById(R.id.ratingBarStar);

        // Obtain the Android Version information based on the position
        Song currentVersion = versionList.get(position);

        // Set values to the TextView to display the corresponding information
        tvRowSongTitle.setText(currentVersion.getTitle());
        tvRowYears.setText(currentVersion.getYearString());
        tvRowSingers.setText(currentVersion.getSingers());
        if(currentVersion.getYear() >= 2019){
            ivImage.setImageResource(R.drawable.newsong);
        }else{
            ivImage.setVisibility(View.INVISIBLE);
        }
        ratingBarStar.setRating(currentVersion.getStars());
        ratingBarStar.setEnabled(false);

        return rowView;
    }
}