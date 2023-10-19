package linh.to.models;

import java.util.List;

public class CarModel {
    private String generalName;
    private String imgURL;
    private String contentURL;

    private List<CarGenerationModel> carGeneration;
    public CarModel(String generalName, String imgURL, String contentURL) {
        this.generalName = generalName;
        this.imgURL = imgURL;
        this.contentURL = contentURL;
    }

    public String getGeneralName() {
        return generalName;
    }

    public void setGeneralName(String generalName) {
        this.generalName = generalName;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getContentURL() {
        return contentURL;
    }

    public void setContentURL(String contentURL) {
        this.contentURL = contentURL;
    }

    public List<CarGenerationModel> getCarGeneration() {
        return carGeneration;
    }

    public void setCarGeneration(List<CarGenerationModel> carGeneration) {
        this.carGeneration = carGeneration;
    }
}
