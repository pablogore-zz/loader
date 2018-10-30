package com.ef;

import com.github.jankroken.commandline.annotations.*;

/**
 * This class handle the argument to be pass to process the log file.
 */

public class Arguments{
    private String startDate;
    private String duration;
    private String threshold;
    private String accessLog;
    private String ip;
    private boolean clean;
    private boolean help;

    @Option
    @LongSwitch("help")
    @ShortSwitch("h")
    @Toggle(true)
    public void setHelp(boolean help) {
        this.help = help;
    }

    @Option
    @LongSwitch("accesslog")
    @ShortSwitch("a")
    @SingleArgument
    public void setAccessLog(String accessLog) {
        this.accessLog = accessLog;
    }

    @Option
    @LongSwitch("startDate")
    @ShortSwitch("s")
    @SingleArgument
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Option
    @LongSwitch("duration")
    @ShortSwitch("d")
    @SingleArgument
    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Option
    @LongSwitch("threshold")
    @ShortSwitch("t")
    @SingleArgument
    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    @Option
    @LongSwitch("clean")
    @ShortSwitch("c")
    @Toggle(true)
    public void setClean(boolean clean) {
        this.clean = clean;
    }

    @Option
    @LongSwitch("ip")
    @ShortSwitch("i")
    @SingleArgument
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAccessLog() {
        return accessLog;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getDuration() {
        return duration;
    }

    public String getThreshold() {
        return threshold;
    }

    public String getIp() {
        return ip;
    }

    public boolean isHelp() {
        return help;
    }

    public boolean isClean() {
        return clean;
    }

    public void usage() {
        System.out.println("\n" +
                "We have four operation that we could perform\n"+
                "Load accesslog into mysql  -> java -jar  <jarName> --accesslog <logfile/path/>\n"+
                "clean tables               -> java -jar <jarName>  --clean \n"+
                "filter by ip               -> java -jar <jarName>  --ip <ip value>\n"+
                "startDate|durration|threshold   -> java -jar <jarName> --startDate=2017-01-01.00:00:00 --duration=daily  --threshold=500\n"+
                "startDate|durration|threshold   -> java -jar <jarName> --startDate=2017-01-01.00:00:00 --duration=hourly --threshold=500\n"+
                "\n\n"+
                "\t-a --accesslog \t\t accesslog file\n" +
                "\t-s --startDate \t\t start date under analysis\n" +
                "\t-d --duration \t\t duration to be included, optional, dayly | hourly\n" +
                "\t-t --threshold \t\t threshold value\n" +
                "\t-i --ip \t\t\t ip for filter\n" +
                "\t-c --clean \t\t\t clean tables for MySQL\n" +
                "\n\n");
    }
}