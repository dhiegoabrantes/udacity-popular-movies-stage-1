package education.udacity.com.popularmovies_stage1.service;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import education.udacity.com.popularmovies_stage1.TheMovieDBUtils;
import education.udacity.com.popularmovies_stage1.domain.Movie;
import education.udacity.com.popularmovies_stage1.processor.MovieProcessor;

/**
 * Created by dhiegoabrantes on 19/06/16.
 */
public class MovieService extends AsyncTask<Object, String, List<Movie>> {

    private AsyncTaskDelegate delegate = null;

    public MovieService(Context context, AsyncTaskDelegate responder){
        this.delegate = responder;
    }

    @Override
    protected List<Movie> doInBackground(Object... objects) {
        try {

            TheMovieDBUtils.SortBy sortBy = (TheMovieDBUtils.SortBy) objects[0];

            String urlAPI = "";

            switch (sortBy){
                case SORT_BY_POPULARITY:
                    urlAPI = TheMovieDBUtils.getPopularMoviesUrl();
                    break;
                case SORT_BY_RATE:
                    urlAPI = TheMovieDBUtils.getTopRatedMoviesUrl();
                    break;
            }

            URL url = new URL(urlAPI);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            InputStream is = connection.getInputStream();
            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                return MovieProcessor.process(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch(MalformedURLException ex){
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);
        if(delegate != null)
            delegate.processFinish(movies);
    }
}
