package education.udacity.com.popularmovies_stage1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import education.udacity.com.popularmovies_stage1.adapter.MovieAdapter;
import education.udacity.com.popularmovies_stage1.domain.Movie;
import education.udacity.com.popularmovies_stage1.service.AsyncTaskDelegate;
import education.udacity.com.popularmovies_stage1.service.MovieService;

public class MainActivity extends AppCompatActivity implements AsyncTaskDelegate{

    private GridView gridView;
    TextView labelSortBy;

    TheMovieDBUtils.SortBy sortByClause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //By default, the movies will be sorted by popularity
        performSort(TheMovieDBUtils.SortBy.SORT_BY_POPULARITY);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(TheMovieDBUtils.SORT_BY_CLAUSE_INTANCE_NAME, sortByClause);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        sortByClause = (TheMovieDBUtils.SortBy) savedInstanceState.getSerializable(TheMovieDBUtils.SORT_BY_CLAUSE_INTANCE_NAME);
        performSort(sortByClause);
    }

    private void performSort(TheMovieDBUtils.SortBy sortByClause){
        labelSortBy = (TextView) findViewById(R.id.labelSortBy);
        switch (sortByClause){
            case SORT_BY_POPULARITY:
                this.sortByClause = TheMovieDBUtils.SortBy.SORT_BY_POPULARITY;
                labelSortBy.setText( R.string.label_sorting_by_popular_movies );
                new MovieService(MainActivity.this, MainActivity.this).execute(sortByClause);
                break;
            case SORT_BY_RATE:
                this.sortByClause = TheMovieDBUtils.SortBy.SORT_BY_RATE;
                labelSortBy.setText( R.string.label_sorting_by_top_rated_movies );
                new MovieService(MainActivity.this, MainActivity.this).execute(sortByClause);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.sortByPopularity:
                performSort(TheMovieDBUtils.SortBy.SORT_BY_POPULARITY);
                break;
            case R.id.sortByRate:
                performSort(TheMovieDBUtils.SortBy.SORT_BY_RATE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void processFinish(Object output) {
        List<Movie> movies = (List<Movie>) output;
        gridView = (GridView) findViewById(R.id. gridViewMovies);
        gridView.setAdapter(new MovieAdapter(this, movies));
    }
}
