package com.servicenow.exercise_java;

import android.os.AsyncTask;
import android.util.Log;

import com.servicenow.exercise_java.apiRequest.ApiRequestTask;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

public class ApiUnitTest {
    private String TAG = ApiUnitTest.class.getSimpleName();

    @Test
    public void testErrorWhenIncorrectUri() {
        String url = "Abcd";
        ApiRequestTask task = new ApiRequestTask();

        try {
            task.getJSONObjectFromURL(url);
            Assert.fail(MalformedURLException.class.getName());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
        }
    }

    @Test
    public void testExpectedResponse() {
        String testUrl = "https://jsonblob.com/api/jsonBlob/f5bf443c-160d-11ea-ab7b-c5ee597d34d8";
        String testNameInJsonToVerify = "games";

        AsyncTask<String, String, JSONObject> task = new ApiRequestTask().execute(testUrl);
        try {
            Assert.assertTrue(task.get().has(testNameInJsonToVerify));
        } catch (ExecutionException e) {
            Log.e(TAG, "getGameDetails: ExecutionException " + e.toString());
        } catch (InterruptedException e) {
            Log.e(TAG, "getGameDetails :InterruptedException " + e.toString());
        } catch (Exception e) {
            Log.e(TAG, "getGameDetails: Exception " + e.toString());
        }
    }

    /**
     * More tests to test the api can be added.
     * 1. test to verify if Json is not malformed
     * 2. test to verify if internet is connected or no
     * 3. test to verify if server is down
     * 4. test to verify time out times. Server should respond before time out values stored in constants
     */
}
