package com.company.project.model;

import javax.persistence.*;

@Table(name = "t_proxy_verify")
public class TProxyVerify {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ip;

    private Integer port;

    private String type;

    private Integer pass;

    @Column(name = "verify_time")
    private String verifyTime;

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
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @param port
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return pass
     */
    public Integer getPass() {
        return pass;
    }

    /**
     * @param pass
     */
    public void setPass(Integer pass) {
        this.pass = pass;
    }

    /**
     * @return verify_time
     */
    public String getVerifyTime() {
        return verifyTime;
    }

    /**
     * @param verifyTime
     */
    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime;
    }
}