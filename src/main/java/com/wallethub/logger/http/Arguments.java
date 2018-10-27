package com.wallethub.logger.http;

import com.github.jankroken.commandline.annotations.*;

/**
 * This class handle the argument to be pass to process the log file.
 */

public class Arguments{
    private String startDate;
    private String duration;
    private String threshold;
    private String accessLog;
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

    public boolean isHelp() {
        return help;
    }

    public void usage() {
        System.out.println("\n" +
                "Usage: ./draw_call_graph.sh -c classpath [-p <package1>:<package2>] [-o <output.dot>] <method1> [<method2>]\n" +
                "\t-a --accesslog \t\t accesslog file\n" +
                "\t-s --startDate \t\t start date under analysis\n" +
                "\t-d --duration \t\t duration to be included, optional, dayly | hourly\n" +
                "\t-t --threshold \t\t threshold\n" +
                "\n\n");
    }
}
