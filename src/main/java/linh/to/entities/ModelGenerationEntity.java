package linh.to.entities;

public class ModelGenerationEntity {
    private String name;
    private String contentURL;
    private String generation;
    private String carType;
    private String imageURL;

    private String desciption; //power & dimensions

    public ModelGenerationEntity() {

    }

    public String getName() {
        return name;
    }

    public ModelGenerationEntity setName(String name) {
        this.name = name;
        return this;
    }


    public String getContentURL() {
        return contentURL;
    }

    public ModelGenerationEntity setContentURL(String contentURL) {
        this.contentURL = contentURL;
        return this;
    }


    public String getImageURL() {
        return imageURL;
    }

    public ModelGenerationEntity setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public String getDesciption() {
        return desciption;
    }

    public ModelGenerationEntity setDesciption(String desciption) {
        this.desciption = desciption;
        return this;
    }

    public ModelGenerationEntity setCarType(String carType) {
        this.carType = carType;
        return this;
    }

    public String getGeneration() {
        return generation;
    }

    public ModelGenerationEntity setGeneration(String generation) {
        this.generation = generation;
        return this;
    }

    public String getCarType() {
        return carType;
    }
}
