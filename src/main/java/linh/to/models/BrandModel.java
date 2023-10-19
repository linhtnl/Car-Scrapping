package linh.to.models;

import java.util.List;

public class BrandModel {
    private String name;
    private String imageURL;
    private String contentURL;
     private List<CarModel> cars;
    public BrandModel() {
    }

    public BrandModel(String name, String imageURL, String contentURL) {
        this.name = name;
        this.imageURL = imageURL;
        this.contentURL = contentURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getContentURL() {
        return contentURL;
    }

    public void setContentURL(String contentURL) {
        this.contentURL = contentURL;
    }

    public void setCars(List<CarModel> cars) {
        this.cars = cars;
    }

    public List<CarModel> getCars() {
        return cars;
    }
}
