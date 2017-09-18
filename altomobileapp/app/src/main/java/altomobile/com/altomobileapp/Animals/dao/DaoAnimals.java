package altomobile.com.altomobileapp.Animals.dao;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import altomobile.com.altomobileapp.Animals.dto.DtoAnimal;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DaoAnimals {

    private List<DtoAnimal> animalList = null;

    public List<DtoAnimal> findAnimals(final String url) {

        Thread task = new Thread(new Data(url));
        task.start();

        try {

            task.join();
        } catch (InterruptedException e) {

            Log.e("DaoAnimals", "Thread error", e.getCause());
        }

        return animalList;
    }

    private class Data implements Runnable {

        private String url;

        private Data(String url) {

            this.url = url;
        }

        @Override
        public void run() {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();

            Response response;
            try {

                response = client.newCall(request).execute();
                String result = response.body().string();
                Log.d("Resuts", result);
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<DtoAnimal>>(){}.getType();
                animalList =  gson.fromJson(result, type);
            } catch ( IOException ex ) {

                Log.e("DaoAnimals-Data", "Response error", ex.getCause());
            }
        }
    }
}