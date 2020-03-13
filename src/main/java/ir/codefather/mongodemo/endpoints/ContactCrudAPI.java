package ir.codefather.mongodemo.endpoints;


import ir.codefather.mongodemo.dto.ContactDTO;
import ir.codefather.mongodemo.entities.Contact;
import ir.codefather.mongodemo.repos.ContactRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContactCrudAPI {

    private final ContactRepo contactRepo;

    public ContactCrudAPI(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }


    @PostMapping("/api/add/contact")
    public Contact add(@RequestBody ContactDTO contactDTO) {
        Contact contact = contactRepo.save(new Contact(contactDTO.getName(), contactDTO.getNumber()));

        return contact;
    }

    @PostMapping("/api/update/contact/{id}")
    public Contact update(@RequestBody ContactDTO contactDTO, @PathVariable String id) {
        Contact contact = contactRepo.findById(id).orElseThrow();
        contact.name = contactDTO.getName();
        contact.number = contactDTO.getNumber();
        contactRepo.save(contact);

        return contact;
    }

    @PostMapping("/api/delete/contact/{id}")
    public void delete(@PathVariable String id) {
        contactRepo.deleteById(id);
    }


    @GetMapping("/api/contacts")
    public Page<Contact> getContacts() {

        return getContacts(0);
    }

    @GetMapping("/api/contacts/{page}")
    public Page<Contact> getContactsWithPage(@PathVariable int page) {
        /**
         * this is why we send natural numbers but pages start from zero
         */
        if (page != 0)
            page--;

        return getContacts(page);
    }

    private Page<Contact> getContacts(int page) {
        Pageable pageable = PageRequest.of(page, 4, Sort.by("name"));
        Page<Contact> contacts = contactRepo.findAllPaginate(pageable);

        return contacts;
    }
}
