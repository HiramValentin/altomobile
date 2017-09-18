package altomobile.com.altomobileapp.Animals.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import altomobile.com.altomobileapp.Animals.dto.DtoAnimal;
import altomobile.com.altomobileapp.R;

public class AnimalViewHolder extends RecyclerView.ViewHolder {

    public TextView animalName;
    public ImageView animalImage;

    public AnimalViewHolder(View view) {

        super(view);
        animalName = (TextView) view.findViewById(R.id.animalName);
        animalImage = (ImageView) view.findViewById(R.id.animalImage);
    }

    public void bind(final DtoAnimal dtoAnimal, final OnItemClickListener listener) {

        itemView.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {

                listener.onItemClick(dtoAnimal);
            }
        });
    }
}