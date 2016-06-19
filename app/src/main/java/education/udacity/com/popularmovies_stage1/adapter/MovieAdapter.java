package education.udacity.com.popularmovies_stage1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import education.udacity.com.popularmovies_stage1.MovieDetailsActivity;
import education.udacity.com.popularmovies_stage1.R;
import education.udacity.com.popularmovies_stage1.TheMovieDBUtils;
import education.udacity.com.popularmovies_stage1.domain.Movie;

/**
 * Created by dhiegoabrantes on 19/06/16.
 */
public class MovieAdapter extends BaseAdapter {

    private Context context;
    private final List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return movies.get(i).getId();
    }

    @Override
    public View getView(int i, View view, final ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridViewItem = inflater.inflate(R.layout.movie_item, null);
        ImageView imageView = (ImageView) gridViewItem.findViewById(R.id.grid_item_image);

        final Movie movie = movies.get(i);
        String path = movie.getPosterPath();
        Picasso.with(context).load(TheMovieDBUtils.buildUrl(movie.getPosterPath())).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = ((GridView)viewGroup).getPositionForView(view);
                Intent packEventIntent = new Intent(context, MovieDetailsActivity.class);
                packEventIntent.putExtra(Movie.PARCELABLE_KEY, movies.get(position));
                context.startActivity(packEventIntent);
            }
        });
        return gridViewItem;
    }
}
