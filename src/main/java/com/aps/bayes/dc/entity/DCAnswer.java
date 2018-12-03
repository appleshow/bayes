package com.aps.bayes.dc.entity;

import java.util.ArrayList;
import java.util.List;

public class DCAnswer {
    private List<Integer> blues;
    private Integer red;
    private List<KeyProbability> keyProbabilityList;

    public DCAnswer() {
        this.blues = new ArrayList<>();
        this.keyProbabilityList = new ArrayList<>();
    }


    public List<Integer> getBlues() {
        return blues;
    }

    public void setBlues(List<Integer> blues) {
        this.blues = blues;
    }

    public Integer getRed() {
        return red;
    }

    public void setRed(Integer red) {
        this.red = red;
    }

    public List<KeyProbability> getKeyProbabilityList() {
        return keyProbabilityList;
    }

    public void setKeyProbabilityList(List<KeyProbability> keyProbabilityList) {
        this.keyProbabilityList = keyProbabilityList;
    }
}
