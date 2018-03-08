package com.company.project.model;

import javax.persistence.*;

@Table(name = "t_shop")
public class TShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String zhanggui;

    private String xingyu;

    private String shoptype;

    private String shopcreatetime;

    private String dsr;

    private String goodcount;

    private String pingrate;

    private String guanzhu;

    private Long shopid;

    private String location;

    private String fuwuchengnuo;

    private String payway;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return zhanggui
     */
    public String getZhanggui() {
        return zhanggui;
    }

    /**
     * @param zhanggui
     */
    public void setZhanggui(String zhanggui) {
        this.zhanggui = zhanggui;
    }

    /**
     * @return xingyu
     */
    public String getXingyu() {
        return xingyu;
    }

    /**
     * @param xingyu
     */
    public void setXingyu(String xingyu) {
        this.xingyu = xingyu;
    }

    /**
     * @return shoptype
     */
    public String getShoptype() {
        return shoptype;
    }

    /**
     * @param shoptype
     */
    public void setShoptype(String shoptype) {
        this.shoptype = shoptype;
    }

    /**
     * @return shopcreatetime
     */
    public String getShopcreatetime() {
        return shopcreatetime;
    }

    /**
     * @param shopcreatetime
     */
    public void setShopcreatetime(String shopcreatetime) {
        this.shopcreatetime = shopcreatetime;
    }

    /**
     * @return dsr
     */
    public String getDsr() {
        return dsr;
    }

    /**
     * @param dsr
     */
    public void setDsr(String dsr) {
        this.dsr = dsr;
    }

    /**
     * @return goodcount
     */
    public String getGoodcount() {
        return goodcount;
    }

    /**
     * @param goodcount
     */
    public void setGoodcount(String goodcount) {
        this.goodcount = goodcount;
    }

    /**
     * @return pingrate
     */
    public String getPingrate() {
        return pingrate;
    }

    /**
     * @param pingrate
     */
    public void setPingrate(String pingrate) {
        this.pingrate = pingrate;
    }

    /**
     * @return guanzhu
     */
    public String getGuanzhu() {
        return guanzhu;
    }

    /**
     * @param guanzhu
     */
    public void setGuanzhu(String guanzhu) {
        this.guanzhu = guanzhu;
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

    /**
     * @return fuwuchengnuo
     */
    public String getFuwuchengnuo() {
        return fuwuchengnuo;
    }

    /**
     * @param fuwuchengnuo
     */
    public void setFuwuchengnuo(String fuwuchengnuo) {
        this.fuwuchengnuo = fuwuchengnuo;
    }

    /**
     * @return payway
     */
    public String getPayway() {
        return payway;
    }

    /**
     * @param payway
     */
    public void setPayway(String payway) {
        this.payway = payway;
    }
}