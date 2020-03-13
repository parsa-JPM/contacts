package ir.codefather.mongodemo.dashboard;

import ir.codefather.mongodemo.Utils.RestPageImpl;
import ir.codefather.mongodemo.Utils.URLUtils;
import ir.codefather.mongodemo.entities.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ContactListController {

    private final String CONTACT_LIST_VIEW = "contact-list";

    @GetMapping("/")
    public ModelAndView showList(HttpServletRequest request) {
        return prePareDataToShowList(request, 1);
    }

    @GetMapping("/page/{page}")
    public ModelAndView showList(HttpServletRequest request, @PathVariable Integer page) {
        return prePareDataToShowList(request, page);
    }

    private ModelAndView prePareDataToShowList(HttpServletRequest request, Integer page) {
        var restTemplate = new RestTemplate();
        var host = URLUtils.getHost(request);
        var modelAndView = new ModelAndView(CONTACT_LIST_VIEW);

        String endpoint = "/api/contacts/".concat(page.toString());

        RestPageImpl<Contact> contacts = restTemplate.getForObject(host.concat(endpoint), RestPageImpl.class);

        modelAndView.addObject("contacts", contacts);

        return modelAndView;
    }


}
