package ro.iteahome.medicom.utils;

import ro.iteahome.medicom.model.dto.ConsultDTO;

import java.util.ArrayList;
import java.util.List;

public class ConsultList {
    private List<ConsultDTO> consultDTOList;

    public ConsultList () {
        consultDTOList = new ArrayList<>();
    }

    public List<ConsultDTO> getConsultDTOList() {
        return consultDTOList;
    }

    public void setConsultDTOList(List<ConsultDTO> consultDTOList) {
        this.consultDTOList = consultDTOList;
    }
}
