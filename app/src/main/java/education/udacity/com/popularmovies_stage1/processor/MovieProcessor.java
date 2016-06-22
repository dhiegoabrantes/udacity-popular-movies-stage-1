package education.udacity.com.popularmovies_stage1.processor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import education.udacity.com.popularmovies_stage1.domain.Movie;

/**
 * Created by dhiegoabrantes on 19/06/16.
 */
public class MovieProcessor {

    public static List<Movie> process(String input) {
        List<Movie> movies = new ArrayList<Movie>();
        try {
            if(input != null && input.startsWith("{")){
                JSONObject parentObject = new JSONObject(input);
                JSONArray jsonArray = (JSONArray) parentObject.get("results");

                if( jsonArray.length() > 0 ){
                    movies = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String originalTitle = jsonObject.optString("original_title").toString();
                        String posterPath = jsonObject.optString("poster_path").toString();
                        String overview = jsonObject.optString("overview").toString();
                        String rating = jsonObject.optString("vote_average").toString();
                        String releaseDate = jsonObject.optString("release_date").toString();

                        Movie movie = new Movie(i, originalTitle, posterPath, overview, rating, releaseDate);
                        movies.add(movie);

                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
