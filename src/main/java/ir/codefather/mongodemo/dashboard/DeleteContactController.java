package ir.codefather.mongodemo.dashboard;

import ir.codefather.mongodemo.Utils.URLUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DeleteContactController {

    @GetMapping("/delete/contact/{id}")
    public String deleteContact(@PathVariable String id, HttpServletRequest request) {
        var restTemplate = new RestTemplate();

        restTemplate.postForLocation(URLUtils.getHost(request) + "/api/delete/contact/" + id, null);

        return "redirect:/";
    }

}
