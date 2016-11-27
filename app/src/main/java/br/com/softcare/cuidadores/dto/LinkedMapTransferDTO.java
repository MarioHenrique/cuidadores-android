package br.com.softcare.cuidadores.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by mario on 26/11/16.
 */

public class LinkedMapTransferDTO implements Serializable{


    public LinkedMapTransferDTO(HashMap linked) {
        this.linked = linked;
    }

    public HashMap getLinked() {
        return linked;
    }

    public void setLinked(HashMap linked) {
        this.linked = linked;
    }

    private HashMap linked;


}
