package com.company.project.model;

import javax.persistence.*;

@Table(name = "t_product")
public class TProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zhanggui;

    @Column(name = "categoryId")
    private String categoryid;

    private String brand;

    @Column(name = "goodsTitle")
    private String goodstitle;

    private String stock;

    private String price;

    @Column(name = "promotionPrice")
    private String promotionprice;

    @Column(name = "salesVolume")
    private String salesvolume;

    @Column(name = "confirmGoodsCount")
    private String confirmgoodscount;

    @Column(name = "comentCount")
    private String comentcount;

    @Column(name = "deliveryFromProvince")
    private String deliveryfromprovince;

    @Column(name = "deliveryFromCity")
    private String deliveryfromcity;

    private String bookmarks;

    @Column(name = "shopType")
    private Integer shoptype;

    @Column(name = "shopLength")
    private String shoplength;

    @Column(name = "shopName")
    private String shopname;

    @Column(name = "goodsId")
    private String goodsid;

    @Column(name = "spuId")
    private String spuid;

    @Column(name = "sellerId")
    private String sellerid;

    @Column(name = "shopId")
    private String shopid;

    @Column(name = "encryptSellerId")
    private String encryptsellerid;

    @Column(name = "apiBeans")
    private String apibeans;

    @Column(name = "initApi")
    private String initapi;

    @Column(name = "shopRate")
    private String shoprate;

    private String shuxing;

    private String zizhi;

    @Column(name = "sellerCredit")
    private String sellercredit;

    @Column(name = "totalRate")
    private String totalrate;

    @Column(name = "dataRateUrl")
    private String datarateurl;

    @Column(name = "rateCounterApi")
    private String ratecounterapi;

    private String yinxiang;

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
     * @return categoryId
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
     * @return brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return goodsTitle
     */
    public String getGoodstitle() {
        return goodstitle;
    }

    /**
     * @param goodstitle
     */
    public void setGoodstitle(String goodstitle) {
        this.goodstitle = goodstitle;
    }

    /**
     * @return stock
     */
    public String getStock() {
        return stock;
    }

    /**
     * @param stock
     */
    public void setStock(String stock) {
        this.stock = stock;
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
     * @return promotionPrice
     */
    public String getPromotionprice() {
        return promotionprice;
    }

    /**
     * @param promotionprice
     */
    public void setPromotionprice(String promotionprice) {
        this.promotionprice = promotionprice;
    }

    /**
     * @return salesVolume
     */
    public String getSalesvolume() {
        return salesvolume;
    }

    /**
     * @param salesvolume
     */
    public void setSalesvolume(String salesvolume) {
        this.salesvolume = salesvolume;
    }

    /**
     * @return confirmGoodsCount
     */
    public String getConfirmgoodscount() {
        return confirmgoodscount;
    }

    /**
     * @param confirmgoodscount
     */
    public void setConfirmgoodscount(String confirmgoodscount) {
        this.confirmgoodscount = confirmgoodscount;
    }

    /**
     * @return comentCount
     */
    public String getComentcount() {
        return comentcount;
    }

    /**
     * @param comentcount
     */
    public void setComentcount(String comentcount) {
        this.comentcount = comentcount;
    }

    /**
     * @return deliveryFromProvince
     */
    public String getDeliveryfromprovince() {
        return deliveryfromprovince;
    }

    /**
     * @param deliveryfromprovince
     */
    public void setDeliveryfromprovince(String deliveryfromprovince) {
        this.deliveryfromprovince = deliveryfromprovince;
    }

    /**
     * @return deliveryFromCity
     */
    public String getDeliveryfromcity() {
        return deliveryfromcity;
    }

    /**
     * @param deliveryfromcity
     */
    public void setDeliveryfromcity(String deliveryfromcity) {
        this.deliveryfromcity = deliveryfromcity;
    }

    /**
     * @return bookmarks
     */
    public String getBookmarks() {
        return bookmarks;
    }

    /**
     * @param bookmarks
     */
    public void setBookmarks(String bookmarks) {
        this.bookmarks = bookmarks;
    }

    /**
     * @return shopType
     */
    public Integer getShoptype() {
        return shoptype;
    }

    /**
     * @param shoptype
     */
    public void setShoptype(Integer shoptype) {
        this.shoptype = shoptype;
    }

    /**
     * @return shopLength
     */
    public String getShoplength() {
        return shoplength;
    }

    /**
     * @param shoplength
     */
    public void setShoplength(String shoplength) {
        this.shoplength = shoplength;
    }

    /**
     * @return shopName
     */
    public String getShopname() {
        return shopname;
    }

    /**
     * @param shopname
     */
    public void setShopname(String shopname) {
        this.shopname = shopname;
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
     * @return spuId
     */
    public String getSpuid() {
        return spuid;
    }

    /**
     * @param spuid
     */
    public void setSpuid(String spuid) {
        this.spuid = spuid;
    }

    /**
     * @return sellerId
     */
    public String getSellerid() {
        return sellerid;
    }

    /**
     * @param sellerid
     */
    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    /**
     * @return shopId
     */
    public String getShopid() {
        return shopid;
    }

    /**
     * @param shopid
     */
    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    /**
     * @return encryptSellerId
     */
    public String getEncryptsellerid() {
        return encryptsellerid;
    }

    /**
     * @param encryptsellerid
     */
    public void setEncryptsellerid(String encryptsellerid) {
        this.encryptsellerid = encryptsellerid;
    }

    /**
     * @return apiBeans
     */
    public String getApibeans() {
        return apibeans;
    }

    /**
     * @param apibeans
     */
    public void setApibeans(String apibeans) {
        this.apibeans = apibeans;
    }

    /**
     * @return initApi
     */
    public String getInitapi() {
        return initapi;
    }

    /**
     * @param initapi
     */
    public void setInitapi(String initapi) {
        this.initapi = initapi;
    }

    /**
     * @return shopRate
     */
    public String getShoprate() {
        return shoprate;
    }

    /**
     * @param shoprate
     */
    public void setShoprate(String shoprate) {
        this.shoprate = shoprate;
    }

    /**
     * @return shuxing
     */
    public String getShuxing() {
        return shuxing;
    }

    /**
     * @param shuxing
     */
    public void setShuxing(String shuxing) {
        this.shuxing = shuxing;
    }

    /**
     * @return zizhi
     */
    public String getZizhi() {
        return zizhi;
    }

    /**
     * @param zizhi
     */
    public void setZizhi(String zizhi) {
        this.zizhi = zizhi;
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
     * @return dataRateUrl
     */
    public String getDatarateurl() {
        return datarateurl;
    }

    /**
     * @param datarateurl
     */
    public void setDatarateurl(String datarateurl) {
        this.datarateurl = datarateurl;
    }

    /**
     * @return rateCounterApi
     */
    public String getRatecounterapi() {
        return ratecounterapi;
    }

    /**
     * @param ratecounterapi
     */
    public void setRatecounterapi(String ratecounterapi) {
        this.ratecounterapi = ratecounterapi;
    }

    /**
     * @return yinxiang
     */
    public String getYinxiang() {
        return yinxiang;
    }

    /**
     * @param yinxiang
     */
    public void setYinxiang(String yinxiang) {
        this.yinxiang = yinxiang;
    }
}