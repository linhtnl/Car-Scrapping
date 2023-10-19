package linh.to.models;

public class CarGenerationModel {
    private String name;
    private int fromYear;
    private String contentURL;
    private int toYear;
    private String carType;
    private String imageURL;

    private String desciption; //power & dimensions

    public CarGenerationModel() {

    }

    public String getName() {
        return name;
    }

    public CarGenerationModel setName(String name) {
        this.name = name;
        return this;
    }

    public int getFromYear() {
        return fromYear;
    }

    public CarGenerationModel setFromYear(int fromYear) {
        this.fromYear = fromYear;
        return this;
    }

    public String getContentURL() {
        return contentURL;
    }

    public CarGenerationModel setContentURL(String contentURL) {
        this.contentURL = contentURL;
        return this;
    }

    public int getToYear() {
        return toYear;
    }

    public CarGenerationModel setToYear(int toYear) {
        this.toYear = toYear;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public CarGenerationModel setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public String getDesciption() {
        return desciption;
    }

    public CarGenerationModel setDesciption(String desciption) {
        this.desciption = desciption;
        return this;
    }

    public CarGenerationModel setCarType(String carType) {
        this.carType = carType;
        return this;
    }


    public String getCarType() {
        return carType;
    }
}
