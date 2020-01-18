package com.servicenow.exercise_java.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.servicenow.exercise.R;
import com.servicenow.exercise_java.Constants;
import com.servicenow.exercise_java.apiRequest.ApiRequestTask;
import com.servicenow.exercise_java.apiRequestRetroFit.ApiClient;
import com.servicenow.exercise_java.apiRequestRetroFit.GamesObject;
import com.servicenow.exercise_java.apiRequestRetroFit.WebApiService;
import com.servicenow.resources.Game;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String TAG               = MainActivity.class.getSimpleName();
    private ArrayList<Game> nesGames = new ArrayList<Game>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Creating Main Activity");
        setContentView(R.layout.activity_main);

        /**
         * Tried to implement web request using Retrofit at first,
         * but was having issues with parsing json, so used HttpUrlConnection
         * You can uncomment the below method requestGameDetails() and execute to see the response.
         * I am keeping this code to just let you know that I tried. Given more time I could have resolved it.
         */
        //requestGameDetails();

        /**
         * Using HttpUrlConnection for making request
         */
        getGameDetails();

        Button button = findViewById(R.id.launchGameListButton);
        button.setOnClickListener(v -> {
            Log.d(TAG, "Launch list of games button clicked");
            startGameListActivity();
        });
    }

    private void startGameListActivity() {
        Intent intent = new Intent(MainActivity.this, GameListActivity.class);
        intent.putExtra("Games", nesGames);
        MainActivity.this.startActivity(intent);
    }

    /**
     * Getting game details using HttpUrlConnection
     */
    private void getGameDetails() {
        AsyncTask<String, String, JSONObject> task = new ApiRequestTask().execute(Constants.GAME_DETAILS_URL);
        boolean gamesFetched = true;
        try {
            Log.d(TAG, "getGameDetails: Task returned result");
            JSONArray temp = (JSONArray) task.get().get("games");
            for(int i = 0; i < temp.length(); i++) {
                nesGames.add(new Game(temp.getJSONObject(i).get("name").toString(),
                        temp.getJSONObject(i).get("cover").toString(),
                        temp.getJSONObject(i).get("shortDescription").toString(),
                        temp.getJSONObject(i).get("longDescription").toString()));
            }
            Log.d(TAG, "getGameDetails: Response received with " + nesGames.size() + " Games");
            gamesFetched = true;
        } catch (ExecutionException e) {
            gamesFetched = false;
            Log.e(TAG, "getGameDetails: ExecutionException " + e.toString());
        } catch (InterruptedException e) {
            gamesFetched = false;
            Log.e(TAG, "getGameDetails :InterruptedException " + e.toString());
        } catch (JSONException e) {
            gamesFetched = false;
            Log.e(TAG, "getGameDetails: JSONException " + e.toString());
        } catch (Exception e) {
            gamesFetched = false;
            Log.e(TAG, "getGameDetails: Exception " + e.toString());
        }
        if (gamesFetched) {
            Toast.makeText(getApplicationContext(), R.string.success_response_toast, Toast.LENGTH_SHORT).show();
        }   else if (!isNetworkAvailable()){
            Toast.makeText(getApplicationContext(), R.string.no_internet_toast, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_response_toast, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // Currently not being used. Explained above.
    private void requestGameDetails() {
        WebApiService service = ApiClient.getClient().create(WebApiService.class);
        Call<GamesObject> call = service.getAllGames();
        call.enqueue(new Callback<GamesObject>() {
            @Override
            public void onResponse(Call<GamesObject> call, Response<GamesObject> response) {
                Log.d(TAG, "onResponse: Success response " + response.toString());
            }

            @Override
            public void onFailure(Call<GamesObject> call, Throwable t) {
                Log.d(TAG, "onFailure: Failure " + t.toString());
            }
        });
    }
}