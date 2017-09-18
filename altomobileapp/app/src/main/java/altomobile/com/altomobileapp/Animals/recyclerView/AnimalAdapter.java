package altomobile.com.altomobileapp.Animals.recyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import altomobile.com.altomobileapp.R;
import altomobile.com.altomobileapp.Animals.dto.DtoAnimal;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalViewHolder> {

    private List<DtoAnimal> animalList;
    private final OnItemClickListener listener;

    public AnimalAdapter(List<DtoAnimal> animalList, OnItemClickListener listener) {
        this.animalList = animalList;
        this.listener = listener;
    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.animal_row, parent, false);
        return new AnimalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AnimalViewHolder holder, int position) {

        final DtoAnimal dtoAnimal = animalList.get(position);
        holder.animalName.setText(dtoAnimal.getName());
        holder.bind(animalList.get(position), listener);

        if ( dtoAnimal.getPictureURL().isEmpty() || dtoAnimal.getPictureURL().equals("") ) {

            holder.animalImage.setImageResource(R.drawable.sad_android);
        } else {

            Thread imageTask = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {

                        InputStream in = (InputStream) new URL(dtoAnimal.getPictureURL()).getContent();
                        Bitmap bitmap = BitmapFactory.decodeStream(in);
                        holder.animalImage.setImageBitmap(bitmap);
                        in.close();
                    } catch (MalformedURLException ex) {

                        Log.d("Animal-Adapter", "Mal formed URL", ex.getCause());
                    } catch (IOException ex) {

                        Log.d("Animal-Adapter", "IO Exception", ex.getCause());
                    }
                }
            });

            try {

                imageTask.start();
                imageTask.join();
            } catch (InterruptedException ex) {

                Log.d("Animal-Adapter", "Thread join", ex.getCause());
            }
        }
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

}
