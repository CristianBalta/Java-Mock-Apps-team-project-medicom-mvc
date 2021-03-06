package ro.iteahome.medicom.utils;

import ro.iteahome.medicom.model.dto.ConsultDTO;

import java.util.ArrayList;
import java.util.List;

public class ConsultList {
    private ArrayList<ConsultDTO> consultDTOList;

    public ConsultList () {
        consultDTOList = new ArrayList<>();
    }

    public ArrayList<ConsultDTO> getConsultDTOList() {
        return consultDTOList;
    }

    public void setConsultDTOList(ArrayList<ConsultDTO> consultDTOList) {
        this.consultDTOList = consultDTOList;
    }
}
