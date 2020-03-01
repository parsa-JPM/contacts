package ir.codefather.mongodemo.dto;

import javax.validation.constraints.NotEmpty;

public class ContactDTO {

    private String id;
    @NotEmpty(message = "name must not to be empty")
    private String name;
    @NotEmpty(message = "number must not to be empty")
    private String number;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
