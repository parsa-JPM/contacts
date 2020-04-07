package ir.codefather.mongodemo.dashboard;

import ir.codefather.mongodemo.entities.Contact;
import ir.codefather.mongodemo.repos.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class ServeAvatarController {

    @Autowired
    private ContactRepo contactRepo;

    @GetMapping("/user/avatar/{userId}")
    public ResponseEntity<byte[]> serve(@PathVariable String userId) {
        Optional<Contact> contact = contactRepo.findById(userId);

        if (contact.isEmpty())
            throw new NotFoundException();

        String fileLocation = contact.get().getProfile();
        Path path = Paths.get(fileLocation);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        /**
         * if you need to set some specific headers in your response,
         * this approach is more straightforward than setting headers by means of HttpServletResponse object that is
         * accepted by the method as a parameter.
         * It makes the method signature clear and focused.
         */
        return new ResponseEntity<>(getFile(path), headers, HttpStatus.OK);
    }


    /**
     * get bytes of avatar file
     *
     * @param path
     * @return byte[]
     */
    private byte[] getFile(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            if (e instanceof NoSuchFileException)
                throw new NotFoundException();
            e.printStackTrace();
        }

        return new byte[]{};
    }
}

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not found")
class NotFoundException extends RuntimeException {
}