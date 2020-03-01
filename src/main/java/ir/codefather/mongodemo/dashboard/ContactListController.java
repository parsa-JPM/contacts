package ir.codefather.mongodemo.dashboard;

import ir.codefather.mongodemo.Utils.URLUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class ContactListController {

    private final String CONTACT_LIST_VIEW = "contact-list";

    @GetMapping("/")
    public ModelAndView showList(HttpServletRequest request) {
        var restTemplate = new RestTemplate();
        var host = URLUtils.getHost(request);
        var modelAndView = new ModelAndView(CONTACT_LIST_VIEW);

        List<LinkedHashMap> contacts = restTemplate.getForObject(host + "/api/contacts", List.class);
        modelAndView.addObject("contacts",contacts);

        //TODO make paginate in view beutifully and make it dynamic;

        return modelAndView;
    }


}
