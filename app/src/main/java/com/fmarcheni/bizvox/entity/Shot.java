package com.fmarcheni.bizvox.entity;

import java.io.Serializable;


/**
 * Created by Flavio on 04/07/2015.
 */


public class Shot implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6742999733666144646L;
    private Long id;
    private String title;
    private Integer views_count;
    private String description;
    private Integer comments_count;
    private String created_at;
    private Image images;

    public Shot(String title, Integer views_count, String created_at) {
        this.title = title;
        this.views_count = views_count;
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Shot [id=" + id + ", title=" + title + ", views_count="
                + views_count + ", description=" + description
                + ", comments_count=" + comments_count + ", created_at="
                + created_at + ", images=" + images + "]";
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getViews_count() {
        return views_count;
    }
    public void setViews_count(Integer views_count) {
        this.views_count = views_count;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getComments_count() {
        return comments_count;
    }
    public void setComments_count(Integer comments_count) {
        this.comments_count = comments_count;
    }
    public String getCreated_at() {
        return created_at;
    }
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    public Image getImages() {
        return images;
    }
    public void setImages(Image images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shot shot = (Shot) o;

        return !(getId() != null ? !getId().equals(shot.getId()) : shot.getId() != null);

    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}