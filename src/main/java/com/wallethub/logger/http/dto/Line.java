package com.wallethub.logger.http.dto;

import jdk.nashorn.internal.runtime.options.Option;

import java.util.Date;
import java.util.Optional;

public class Line {
    private Optional<String> id;
    private Date date;
    private String ip;
    private String request;
    private String status;
    private String userAgent;

    public Line(Optional<String> id, Date date, String ip, String request, String status, String userAgent) {
        this.id = id;
        this.date = date;
        this.ip = ip;
        this.request = request;
        this.status = status;
        this.userAgent = userAgent;
    }

    public Optional<String> getId() {
        return id;
    }

    public void setId(Optional<String> id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
