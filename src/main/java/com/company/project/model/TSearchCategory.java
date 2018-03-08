package com.company.project.model;

import javax.persistence.*;

@Table(name = "t_search_category")
public class TSearchCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String url;

    private Integer page;

    @Column(name = "categoryID")
    private String categoryid;

    @Column(name = "totalCount")
    private Integer totalcount;

    /**
     * @return id
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
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page
     */
    public void setPage(Integer page) {
        this.page = page;
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
     * @return totalCount
     */
    public Integer getTotalcount() {
        return totalcount;
    }

    /**
     * @param totalcount
     */
    public void setTotalcount(Integer totalcount) {
        this.totalcount = totalcount;
    }
}