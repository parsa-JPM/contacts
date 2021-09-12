package ir.codefather.mongodemo.dto;

import ir.codefather.mongodemo.entities.Contact;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * It's use for Angular client because {@link org.springframework.data.domain.Page} is very complicate for DTO
 */
@Getter
@Setter
public class SimplePage {
    private List<Contact> contacts;
    private int number;
    private int size;
    private boolean first;
    private boolean last;
    private long totalElement;
    private int totalPages;
}
