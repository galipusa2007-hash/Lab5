package ru.itmo.galiya.base;

public class MeasurementParam {
    private long id;
    private String name;
    private final String description; //тут как бы final, но если что поменяю, если нужно будет description

    public MeasurementParam(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
}
