package education.udacity.com.popularmovies_stage1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import education.udacity.com.popularmovies_stage1.domain.Movie;

public class MovieDetailsActivity extends AppCompatActivity {

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            this.movie = (Movie) bundle.getParcelable(Movie.PARCELABLE_KEY);
            fillFields();
        }
    }

    private void fillFields() {
        ImageView imageView = (ImageView) findViewById(R.id.imageViewMovie);
        Picasso.with(MovieDetailsActivity.this).load(TheMovieDBUtils.buildUrl(movie.getPosterPath())).into(imageView);

        TextView title = (TextView) findViewById(R.id.textViewMovieTitle);
        title.setText( this.movie.getOriginalTitle() );

        TextView overview = (TextView) findViewById(R.id.textViewMovieOverview);
        overview.setText( this.movie.getOverview() );

        TextView releaseDate = (TextView) findViewById(R.id.textViewMovieReleaseDate);
        releaseDate.setText( this.movie.getReleaseDate() );

        TextView rating = (TextView) findViewById(R.id.textViewMovieRating);
        rating.setText( this.movie.getRating() );
    }
}
