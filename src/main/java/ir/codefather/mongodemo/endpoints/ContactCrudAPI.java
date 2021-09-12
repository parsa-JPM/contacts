package ir.codefather.mongodemo.endpoints;


import ir.codefather.mongodemo.dto.ContactDTO;
import ir.codefather.mongodemo.dto.SimplePage;
import ir.codefather.mongodemo.entities.Contact;
import ir.codefather.mongodemo.repos.ContactRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin
public class ContactCrudAPI {

    public static final String UPLOAD_DIR = "/var/www/upload/avatars/";

    private final ContactRepo contactRepo;

    public ContactCrudAPI(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }


    @PostMapping("/api/add/contact")
    public Contact add(ContactDTO contactDTO) {

        Contact contact = new Contact(contactDTO.getName(), contactDTO.getNumber());

        if (contactDTO.getProfile() != null && !contactDTO.getProfile().isEmpty())
            uploadAvatar(contactDTO.getProfile(), contact);

        return contactRepo.save(contact);
    }

    @PostMapping("/api/update/contact/{id}")
    public Contact update(ContactDTO contactDTO, @PathVariable String id) {

        Contact contact = contactRepo.findById(id).orElseThrow();
        contact.name = contactDTO.getName();
        contact.number = contactDTO.getNumber();

        if (contactDTO.getProfile() != null && !contactDTO.getProfile().isEmpty()) {
            uploadAvatar(contactDTO.getProfile(), contact);
        }

        contactRepo.save(contact);

        return contact;
    }


    /**
     * upload profile image of contact
     *
     * @param file
     * @param contact
     * @return void
     */
    private void uploadAvatar(MultipartFile file, Contact contact) {
        String fileLocation = UPLOAD_DIR + file.getOriginalFilename();
        Path avatarDir = Paths.get(UPLOAD_DIR);
        Path avatarPath = Paths.get(fileLocation);

        try {
            if (Files.notExists(avatarDir))
                Files.createDirectory(avatarDir);

            Files.write(avatarPath, file.getBytes());
            contact.setProfile(fileLocation);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @PostMapping("/api/delete/contact/{id}")
    public void delete(@PathVariable String id) {
        contactRepo.deleteById(id);
    }


    @GetMapping("/api/ng/contacts")
    public SimplePage getContactsNG(@RequestParam(value = "search", required = false) String search) {
        Page<Contact> contactPage = getContacts(0, search);
        SimplePage contactSimplePage = new SimplePage();
        contactSimplePage.setContacts(contactPage.getContent());
        contactSimplePage.setFirst(contactPage.isFirst());
        contactSimplePage.setLast(contactPage.isLast());
        contactSimplePage.setSize(contactPage.getSize());
        contactSimplePage.setTotalElement(contactPage.getTotalElements());
        contactSimplePage.setNumber(contactPage.getNumber());
        contactSimplePage.setTotalPages(contactPage.getTotalPages());

        return contactSimplePage;
    }

    @GetMapping("/api/contacts")
    public Page<Contact> getContacts(@RequestParam(value = "search", required = false) String search) {
        return getContacts(0, search);
    }

    @GetMapping("/api/contacts/{page}")
    public Page<Contact> getContactsWithPage(@PathVariable int page,
                                             @RequestParam(value = "search", required = false) String search) {
        /**
         * this is why we send natural numbers but pages start from zero
         */
        if (page != 0)
            page--;

        return getContacts(page, search);
    }

    private Page<Contact> getContacts(int page, String search) {
        var pageSize = 5;

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("name"));

        if (search == null || search.isBlank()) {
            Page<Contact> contacts = contactRepo.findAllPaginate(pageable);
            return contacts;
        }

        Page<Contact> contacts = contactRepo.search(search, pageable);

        return contacts;
    }
}
