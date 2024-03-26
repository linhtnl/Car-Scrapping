package linh.to.entities;

import java.util.List;

public class ModelEntity {
    private String generalName;
    private String imgURL;
    private String contentURL;

    private List<ModelGenerationEntity> carGeneration;
    public ModelEntity(String generalName, String imgURL, String contentURL) {
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

    public List<ModelGenerationEntity> getCarGeneration() {
        return carGeneration;
    }

    public void setCarGeneration(List<ModelGenerationEntity> carGeneration) {
        this.carGeneration = carGeneration;
    }
}
