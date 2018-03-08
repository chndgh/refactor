package com.company.project.model;

import javax.persistence.*;

@Table(name = "t_back_category")
public class TBackCategory {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cateid;

    private String hangye;

    private String zihangye;

    private String level1;

    private String level2;

    private String level3;

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
     * @return hangye
     */
    public String getHangye() {
        return hangye;
    }

    /**
     * @param hangye
     */
    public void setHangye(String hangye) {
        this.hangye = hangye;
    }

    /**
     * @return zihangye
     */
    public String getZihangye() {
        return zihangye;
    }

    /**
     * @param zihangye
     */
    public void setZihangye(String zihangye) {
        this.zihangye = zihangye;
    }

    /**
     * @return level1
     */
    public String getLevel1() {
        return level1;
    }

    /**
     * @param level1
     */
    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    /**
     * @return level2
     */
    public String getLevel2() {
        return level2;
    }

    /**
     * @param level2
     */
    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    /**
     * @return level3
     */
    public String getLevel3() {
        return level3;
    }

    /**
     * @param level3
     */
    public void setLevel3(String level3) {
        this.level3 = level3;
    }
}