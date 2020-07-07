package ro.iteahome.medicom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.iteahome.medicom.model.dto.ConsultDTO;
import ro.iteahome.medicom.service.ConsultDTOService;

import java.util.ArrayList;

@Controller
@RequestMapping("/users")
public class ConsultController {

    @Autowired
    private ConsultDTOService consultDTOService;

    @PostMapping("/add-consult")
    public ModelAndView addConsult(ConsultDTO consultDTO) {
        ArrayList<ConsultDTO> consultDTOList = new ArrayList<>();
        consultDTOList.add(consultDTOService.addConsult(consultDTO));
        return new ModelAndView("consult/home-consult").addObject(consultDTOList);
    }

    @GetMapping("/consult/by-cnp")
    public ModelAndView findConsults(ConsultDTO consultDTO) {

        return new ModelAndView("consult/home-consult")
                .addObject(consultDTOService.findAllConsults(consultDTO.getPatient_cnp()));
    }

    @GetMapping("/find-patient/{cnp}")
    public  ModelAndView getPatient (@PathVariable String cnp) {
        return new ModelAndView("patient-view")
                .addObject(consultDTOService.findPatientByCnp(cnp));
    }
}
