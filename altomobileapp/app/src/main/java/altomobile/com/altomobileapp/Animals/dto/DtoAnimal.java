package altomobile.com.altomobileapp.Animals.dto;

/**
 * Created by hiram on 14/09/17.
 */

public class DtoAnimal {

    private int id;
    private String name;
    private String life;
    private String pictureURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}
