package com.company.project.model;

import javax.persistence.*;

@Table(name = "t_search")
public class TSearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "portraitURL")
    private String portraiturl;

    private String title;

    private String price;

    private String owner;

    @Column(name = "deliverFromProvince")
    private String deliverfromprovince;

    @Column(name = "deliverFromCity")
    private String deliverfromcity;

    @Column(name = "customerCount")
    private Integer customercount;

    @Column(name = "commentCount")
    private Integer commentcount;

    @Column(name = "ownerType")
    private Integer ownertype;

    private String duliang;

    @Column(name = "goodsLink")
    private String goodslink;

    @Column(name = "goodsId")
    private String goodsid;

    @Column(name = "categoryID")
    private String categoryid;

    @Column(name = "sellerCredit")
    private String sellercredit;

    @Column(name = "totalRate")
    private String totalrate;

    @Column(name = "baseSearch")
    private Integer basesearch;

    @Column(name = "BackCategoryId")
    private String backcategoryid;

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
     * @return portraitURL
     */
    public String getPortraiturl() {
        return portraiturl;
    }

    /**
     * @param portraiturl
     */
    public void setPortraiturl(String portraiturl) {
        this.portraiturl = portraiturl;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return deliverFromProvince
     */
    public String getDeliverfromprovince() {
        return deliverfromprovince;
    }

    /**
     * @param deliverfromprovince
     */
    public void setDeliverfromprovince(String deliverfromprovince) {
        this.deliverfromprovince = deliverfromprovince;
    }

    /**
     * @return deliverFromCity
     */
    public String getDeliverfromcity() {
        return deliverfromcity;
    }

    /**
     * @param deliverfromcity
     */
    public void setDeliverfromcity(String deliverfromcity) {
        this.deliverfromcity = deliverfromcity;
    }

    /**
     * @return customerCount
     */
    public Integer getCustomercount() {
        return customercount;
    }

    /**
     * @param customercount
     */
    public void setCustomercount(Integer customercount) {
        this.customercount = customercount;
    }

    /**
     * @return commentCount
     */
    public Integer getCommentcount() {
        return commentcount;
    }

    /**
     * @param commentcount
     */
    public void setCommentcount(Integer commentcount) {
        this.commentcount = commentcount;
    }

    /**
     * @return ownerType
     */
    public Integer getOwnertype() {
        return ownertype;
    }

    /**
     * @param ownertype
     */
    public void setOwnertype(Integer ownertype) {
        this.ownertype = ownertype;
    }

    /**
     * @return duliang
     */
    public String getDuliang() {
        return duliang;
    }

    /**
     * @param duliang
     */
    public void setDuliang(String duliang) {
        this.duliang = duliang;
    }

    /**
     * @return goodsLink
     */
    public String getGoodslink() {
        return goodslink;
    }

    /**
     * @param goodslink
     */
    public void setGoodslink(String goodslink) {
        this.goodslink = goodslink;
    }

    /**
     * @return goodsId
     */
    public String getGoodsid() {
        return goodsid;
    }

    /**
     * @param goodsid
     */
    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    /**
     * @return categoryID
     */
    public String getCategoryid() {
        return categoryid;
    }

    /**
     * @param categoryid
     */
    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    /**
     * @return sellerCredit
     */
    public String getSellercredit() {
        return sellercredit;
    }

    /**
     * @param sellercredit
     */
    public void setSellercredit(String sellercredit) {
        this.sellercredit = sellercredit;
    }

    /**
     * @return totalRate
     */
    public String getTotalrate() {
        return totalrate;
    }

    /**
     * @param totalrate
     */
    public void setTotalrate(String totalrate) {
        this.totalrate = totalrate;
    }

    /**
     * @return baseSearch
     */
    public Integer getBasesearch() {
        return basesearch;
    }

    /**
     * @param basesearch
     */
    public void setBasesearch(Integer basesearch) {
        this.basesearch = basesearch;
    }

    /**
     * @return BackCategoryId
     */
    public String getBackcategoryid() {
        return backcategoryid;
    }

    /**
     * @param backcategoryid
     */
    public void setBackcategoryid(String backcategoryid) {
        this.backcategoryid = backcategoryid;
    }
}