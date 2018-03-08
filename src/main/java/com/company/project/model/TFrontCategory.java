package com.company.project.model;

import javax.persistence.*;

@Table(name = "t_front_category")
public class TFrontCategory {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cateid;

    private String catename;

    private Integer level;

    private String parentcateid;

    private Integer updatetime;

    /**
     * @return Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return cateid
     */
    public String getCateid() {
        return cateid;
    }

    /**
     * @param cateid
     */
    public void setCateid(String cateid) {
        this.cateid = cateid;
    }

    /**
     * @return catename
     */
    public String getCatename() {
        return catename;
    }

    /**
     * @param catename
     */
    public void setCatename(String catename) {
        this.catename = catename;
    }

    /**
     * @return level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return parentcateid
     */
    public String getParentcateid() {
        return parentcateid;
    }

    /**
     * @param parentcateid
     */
    public void setParentcateid(String parentcateid) {
        this.parentcateid = parentcateid;
    }

    /**
     * @return updatetime
     */
    public Integer getUpdatetime() {
        return updatetime;
    }

    /**
     * @param updatetime
     */
    public void setUpdatetime(Integer updatetime) {
        this.updatetime = updatetime;
    }
}