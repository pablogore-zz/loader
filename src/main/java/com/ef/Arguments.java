package com.ef;

import com.github.jankroken.commandline.annotations.LongSwitch;
import com.github.jankroken.commandline.annotations.Option;
import com.github.jankroken.commandline.annotations.ShortSwitch;
import com.github.jankroken.commandline.annotations.SingleArgument;
import com.github.jankroken.commandline.annotations.Toggle;

/**
 * This class handle the argument to be pass to process the log file.
 */
public final  class Arguments {
    /**
     * The startDate report.
     */
    private String startDate;
    /**
     * Duration type should be hourly o daily.
     */
    private String duration;
    /**
     * The threshold report.
     */
    private String threshold;
    /**
     * The access log file to be import.
     */
    private String accessLog;
    /**
     * The ip tp be filter.
     */
    private String ip;
    /**
     * Flag to clean the tables.
     */
    private boolean clean;
    /**
     * Flag to show the usage menu.
     */
    private boolean help;

    /**
     * Set the help value to show usage or no.
     * @param help  this help
     */
    @Option
    @LongSwitch("help")
    @ShortSwitch("h")
    @Toggle(true)
    public void setHelp(final boolean help) {
        this.help = help;
    }

    /**
     * set access log file value.
     * @param  accessLog this is the accessLog file path
     */
    @Option
    @LongSwitch("accesslog")
    @ShortSwitch("a")
    @SingleArgument
    public void setAccessLog(final String accessLog) {
        this.accessLog = accessLog;
    }

    /**
     *  set startDate report.
     * @param startDate
     *              this is the startDate
     */
    @Option
    @LongSwitch("startDate")
    @ShortSwitch("s")
    @SingleArgument
    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    /**
     * set duration type.
     * @param duration
     *              this is the duration type
     */
    @Option
    @LongSwitch("duration")
    @ShortSwitch("d")
    @SingleArgument
    public void setDuration(final String duration) {
        this.duration = duration;
    }

    /**
     * set threshold for report.
     * @param threshold
     *              this is the threshold for report
     */
    @Option
    @LongSwitch("threshold")
    @ShortSwitch("t")
    @SingleArgument
    public void setThreshold(final String threshold) {
        this.threshold = threshold;
    }

    /**
     * set the clean flag.
     * @param clean
     *          This is the clean flag
     */
    @Option
    @LongSwitch("clean")
    @ShortSwitch("c")
    @Toggle(true)
    public void setClean(final boolean clean) {
        this.clean = clean;
    }

    /**
     * set the ip for filter report.
     * @param ip
     *          thi is the ip for filter report
     */
    @Option
    @LongSwitch("ip")
    @ShortSwitch("i")
    @SingleArgument
    public void setIp(final String ip) {
        this.ip = ip;
    }

    /**
     * Get the acces log file.
     * @return
     *  the acces log file
     */
    public String getAccessLog() {
        return accessLog;
    }

    /**
     * Get Start Date.
     * @return startDate
     *
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Get duration type.
     * @return duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Get threshold for report.
     * @return threshold
     */
    public String getThreshold() {
        return threshold;
    }

    /**
     * Get ip for filter report.
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Get usage  string.
     * @return help
     */
    public boolean isHelp() {
        return help;
    }

    /**
     * Get clean db flag.
     * @return help
     */
    public boolean isClean() {
        return clean;
    }

    /**
     * Get string usage menu.
     */
    public void usage() {
        System.out.println("\n"
          + "\n\n"
          + "\t-a --accesslog \t\t accesslog file\n"
          + "\t-s --startDate \t\t start date under analysis\n"
          + "\t-d --duration \t\t duration daily | hourly\n"
          + "\t-t --threshold \t\t threshold value\n"
          + "\t-i --ip \t\t\t ip for filter\n"
          + "\t-c --clean \t\t\t clean tables for MySQL\n"
          + "\n\n");
    }
}
