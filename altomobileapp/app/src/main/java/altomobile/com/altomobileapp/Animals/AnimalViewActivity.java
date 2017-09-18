package altomobile.com.altomobileapp.Animals;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import altomobile.com.altomobileapp.Animals.recyclerView.AnimalAdapter;
import altomobile.com.altomobileapp.Animals.dao.DaoAnimals;
import altomobile.com.altomobileapp.Animals.dto.DtoAnimal;
import altomobile.com.altomobileapp.Animals.recyclerView.OnItemClickListener;
import altomobile.com.altomobileapp.R;

public class AnimalViewActivity extends AppCompatActivity {

    private List<DtoAnimal> animalList;
    private RecyclerView recyclerView;
    private AnimalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        animalList = new DaoAnimals().findAnimals("https://flavioruben.herokuapp.com/data.json");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        adapter = new AnimalAdapter(animalList, new OnItemClickListener() {
            @Override public void onItemClick(DtoAnimal dtoAnimal) {
                Toast.makeText(getApplicationContext(), "Animal life:" + dtoAnimal.getLife(), Toast.LENGTH_LONG).show();
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}