package com.servicenow.exercise_java.apiRequest;

import android.os.AsyncTask;
import android.util.Log;

import com.servicenow.exercise_java.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Creating Async task to execute http request on background when the app starts and MainActivity loads
 */
public class ApiRequestTask extends AsyncTask<String, String, JSONObject> {
    private String TAG = ApiRequestTask.class.getSimpleName();

    @Override
    protected JSONObject doInBackground(String... uri) {
        JSONObject responseJson = null;
        try{
            responseJson = getJSONObjectFromURL(uri[0]);
            Log.d(TAG, "Received response json.");
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: IOException " + e.toString());
        } catch (JSONException e) {
            Log.e(TAG, "doInBackground: JSONException " + e.toString());
        } catch (Exception e) {
            Log.e(TAG, "doInBackground: Generic exception " + e.toString());
        }
        return responseJson;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);
        Log.d(TAG, "onPostExecute: Result " + result);
    }

    /**
     * Using HttpURLConnection to get json
     * @param urlString
     * @return
     * @throws IOException
     * @throws JSONException
     */
    private JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(Constants.HTTP_READ_TIME_OUT);
        urlConnection.setConnectTimeout(Constants.HTTP_CONNECT_TIME_OUT);
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        String jsonString = sb.toString();
        return new JSONObject(jsonString);
    }
}
