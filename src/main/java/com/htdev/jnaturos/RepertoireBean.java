/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htdev.jnaturos;

/**
 * Bean qui permet de gerer le combobox des type de répertoires.
 * @author herve
 */
class RepertoireBean {

    public String getLabelREPERTOIRE() {
        return labelREPERTOIRE;
    }

    public void setLabelREPERTOIRE(String labelREPERTOIRE) {
        this.labelREPERTOIRE = labelREPERTOIRE;
    }

    public int getNumREPERTOIRE() {
        return numREPERTOIRE;
    }

    public void setNumREPERTOIRE(int numREPERTOIRE) {
        this.numREPERTOIRE = numREPERTOIRE;
    }

    public RepertoireBean(String labelREPERTOIRE, int numREPERTOIRE) {
        this.labelREPERTOIRE = labelREPERTOIRE;
        this.numREPERTOIRE = numREPERTOIRE;
    }

    
    @Override
    public String toString() {
        return labelREPERTOIRE;
    }
    
    
    
    String labelREPERTOIRE;
    int numREPERTOIRE;
}
