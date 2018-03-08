package com.company.project.model;

import javax.persistence.*;

@Table(name = "t_search_shop")
public class TSearchShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long shopid;

    private Long shopuserid;

    private String shopurl;

    private Long zhangguiid;

    private String zhangguiname;

    private String zhangguiurl;

    private String location;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return shopid
     */
    public Long getShopid() {
        return shopid;
    }

    /**
     * @param shopid
     */
    public void setShopid(Long shopid) {
        this.shopid = shopid;
    }

    /**
     * @return shopuserid
     */
    public Long getShopuserid() {
        return shopuserid;
    }

    /**
     * @param shopuserid
     */
    public void setShopuserid(Long shopuserid) {
        this.shopuserid = shopuserid;
    }

    /**
     * @return shopurl
     */
    public String getShopurl() {
        return shopurl;
    }

    /**
     * @param shopurl
     */
    public void setShopurl(String shopurl) {
        this.shopurl = shopurl;
    }

    /**
     * @return zhangguiid
     */
    public Long getZhangguiid() {
        return zhangguiid;
    }

    /**
     * @param zhangguiid
     */
    public void setZhangguiid(Long zhangguiid) {
        this.zhangguiid = zhangguiid;
    }

    /**
     * @return zhangguiname
     */
    public String getZhangguiname() {
        return zhangguiname;
    }

    /**
     * @param zhangguiname
     */
    public void setZhangguiname(String zhangguiname) {
        this.zhangguiname = zhangguiname;
    }

    /**
     * @return zhangguiurl
     */
    public String getZhangguiurl() {
        return zhangguiurl;
    }

    /**
     * @param zhangguiurl
     */
    public void setZhangguiurl(String zhangguiurl) {
        this.zhangguiurl = zhangguiurl;
    }

    /**
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }
}