package com.fmarcheni.bizvox.entity;

import java.io.Serializable;


/**
 * Created by Flavio on 04/07/2015.
 */

public class Image implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6137718080157939498L;
    private String normal;
    private String teaser;
    private String hidpi;



    @Override
    public String toString() {
        return "Image [normal=" + normal + ", teaser=" + teaser + ", hidpi="
                + hidpi + "]";
    }
    public Image() {
    }
    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getHidpi() {
        return hidpi;
    }

    public void setHidpi(String hidpi) {
        this.hidpi = hidpi;
    }

}


