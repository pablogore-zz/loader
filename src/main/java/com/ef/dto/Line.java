package com.ef.dto;

import java.util.Date;

/**
 * This is a Java Bean that represent each row int the file access log.
 */
public class Line {
    /**
     * This is the date column.
     */
    private Date date;
    /**
     * This is the ip column.
     */
    private String ip;
    /**
     * This is the request column.
     */
    private String request;
    /**
     * This is the status column.
     */
    private String status;
    /**
     * This is the user_agent column.
     */
    private String userAgent;

    /**
     * This is the constructor for Line DTO.
     * @param date
     *      this is the date param.
     * @param ip
     *      this is the ip param.
     * @param request
     *      this request the date param.
     * @param status
     *      this is the status param.
     * @param userAgent
     *      this is the userAgent param.
     */
    public Line(final Date date, final String ip, final String request,
                final String status, final String userAgent) {
        this.date = date;
        this.ip = ip;
        this.request = request;
        this.status = status;
        this.userAgent = userAgent;
    }

    /**
     * date of transaction .
     * @return
     *      a Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the date of the transaction.
     * @param date
     *          this is the date
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * ip of the transaction.
     * @return
     *  ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * set the ip of the transaction.
     * @param ip
     *          this is the ip
     */
    public void setIp(final String ip) {
        this.ip = ip;
    }

    /**
     * request  of the transaction.
     * @return
     *  the request
     */
    public String getRequest() {
        return request;
    }

    /**
     * set the request of the transaction.
     * @param request
     *          this is the request
     */
    public void setRequest(final String request) {
        this.request = request;
    }

    /**
     * transaction status.
     * @return
     *  the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * set the status of the transaction.
     * @param status
     *          this is the status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Get the user agent of the transaction.
     * @return
     *  the userAgent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * set the user agent.
     * @param userAgent
     *          this is the userAgent
     */
    public void setUserAgent(final String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * string representacion of the Line object.
     * @return
     *  the string
     */
    @Override
    public String toString() {
        return "Line{"
                +   "date=" + date
                +   ", ip='" + ip + '\''
                +    ", request='" + request + '\''
                +    ", status='" + status + '\''
                +    ", userAgent='" + userAgent + '\''
                +   '}';
    }
}
