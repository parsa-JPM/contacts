package ir.codefather.mongodemo.dto;

import ir.codefather.mongodemo.validations.FileSize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

public class ContactDTO {

    private String id;
    @NotEmpty(message = "name must not to be empty")
    private String name;
    @NotEmpty(message = "number must not to be empty")
    private String number;

    @FileSize(4000000)
    private MultipartFile profile;


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

    public MultipartFile getProfile() {
        return profile;
    }

    public void setProfile(MultipartFile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", file=" + profile +
                '}';
    }
}
