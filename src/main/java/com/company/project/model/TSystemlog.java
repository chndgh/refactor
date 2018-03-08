package com.company.project.model;

import javax.persistence.*;

@Table(name = "t_systemlog")
public class TSystemlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "taskerName")
    private String taskername;

    @Column(name = "taskerSeries")
    private String taskerseries;

    @Column(name = "taskerOwner")
    private String taskerowner;

    @Column(name = "runHost")
    private String runhost;

    private String url;

    private Integer result;

    @Column(name = "errMessage")
    private String errmessage;

    private String runtime;

    private String proxy;

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
     * @return taskerName
     */
    public String getTaskername() {
        return taskername;
    }

    /**
     * @param taskername
     */
    public void setTaskername(String taskername) {
        this.taskername = taskername;
    }

    /**
     * @return taskerSeries
     */
    public String getTaskerseries() {
        return taskerseries;
    }

    /**
     * @param taskerseries
     */
    public void setTaskerseries(String taskerseries) {
        this.taskerseries = taskerseries;
    }

    /**
     * @return taskerOwner
     */
    public String getTaskerowner() {
        return taskerowner;
    }

    /**
     * @param taskerowner
     */
    public void setTaskerowner(String taskerowner) {
        this.taskerowner = taskerowner;
    }

    /**
     * @return runHost
     */
    public String getRunhost() {
        return runhost;
    }

    /**
     * @param runhost
     */
    public void setRunhost(String runhost) {
        this.runhost = runhost;
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
     * @return result
     */
    public Integer getResult() {
        return result;
    }

    /**
     * @param result
     */
    public void setResult(Integer result) {
        this.result = result;
    }

    /**
     * @return errMessage
     */
    public String getErrmessage() {
        return errmessage;
    }

    /**
     * @param errmessage
     */
    public void setErrmessage(String errmessage) {
        this.errmessage = errmessage;
    }

    /**
     * @return runtime
     */
    public String getRuntime() {
        return runtime;
    }

    /**
     * @param runtime
     */
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    /**
     * @return proxy
     */
    public String getProxy() {
        return proxy;
    }

    /**
     * @param proxy
     */
    public void setProxy(String proxy) {
        this.proxy = proxy;
    }
}