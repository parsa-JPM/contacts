package ir.codefather.mongodemo.dashboard;

import ir.codefather.mongodemo.Utils.URLUtils;
import ir.codefather.mongodemo.dto.ContactDTO;
import ir.codefather.mongodemo.entities.Contact;
import ir.codefather.mongodemo.repos.ContactRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * It also used for update
 */
@Controller
public class AddContactController {

    private static final Logger logger = LoggerFactory.getLogger(AddContactController.class);

    private static String view = "add-contact";

    @Autowired
    private ContactRepo contactRepo;


    @GetMapping("/add/contact")
    public ModelAndView showAddPage(Model model) {
        var modelAndView = new ModelAndView(view);
        /**
         * to use in fresh form
         */
        if (!model.containsAttribute("contactModel"))
            modelAndView.addObject("contactModel", new ContactDTO());

        return modelAndView;
    }

    @GetMapping("/update/contact/{id}")
    public ModelAndView showContact(@PathVariable String id, Model model) {
        var modelAndView = new ModelAndView(view);

        /**
         * to keep change of user on the form when it reject from save action
         */
        if (!model.containsAttribute("contactModel")) {
            Contact contact = contactRepo.findById(id).orElseThrow();
            modelAndView.addObject("contactModel", contact);
        }


        return modelAndView;
    }


    /**
     * it will redirect and use flash attr to pass variable to views
     *
     * @param contactDTO
     * @param validationResult
     * @param redAttr
     * @return String
     */
    @PostMapping("/save/contact")
    public String saveContact(@Valid ContactDTO contactDTO,
                              BindingResult validationResult,
                              RedirectAttributes redAttr,
                              HttpServletRequest request) {

        if (validationResult.hasErrors()) {
            redAttr.addFlashAttribute("contactModel", contactDTO);
            redAttr.addFlashAttribute(BindingResult.class.getName() + ".contactModel", validationResult);

            if (contactDTO.getId() == null || contactDTO.getId().isBlank())
                return "redirect:/add/contact";
            else
                return "redirect:/update/contact/" + contactDTO.getId();
        }

        saveContact(contactDTO, request);

        return "redirect:/";
    }


    /**
     * call contact apis to save or update
     *
     * @param contactDTO
     * @param request
     */
    private void saveContact(ContactDTO contactDTO, HttpServletRequest request) {
        var restTemplate = new RestTemplate();

        System.out.println(contactDTO);

        if (contactDTO.getId() == null || contactDTO.getId().isBlank())
            restTemplate.postForLocation(URLUtils.getHost(request) + "/api/add/contact", contactDTO);
        else
            restTemplate.postForLocation(URLUtils.getHost(request) + "/api/update/contact/" + contactDTO.getId()
                    , contactDTO);

        logger.info("saved contact : " + contactDTO);
    }
}
