package com.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Signal;
import sun.misc.SignalHandler;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args){
        Job job = new Job();

        Thread mainThread = Thread.currentThread();
        SignalHandler handler = (Signal sig) -> {
            log.info("received signal from preStop hook");
            job.status = JobStatus.TERMINATING;
            try {
                mainThread.join(2000);
            } catch (Exception e) {
                log.error("handle preStop hook fail", e);
            }
            log.info("finished handling signal from preStop hook");
        };
        //handling specific SIGNALs from outside
        Signal.handle(new Signal("CHLD"), handler);
        Signal.handle(new Signal("TRAP"), handler);

        job.work();
    }


    static class Job {
        String status = JobStatus.DEFAULT;

        public Job() {

        }

        public void work() {
            status = JobStatus.RUNNING;
            while (status.equals(JobStatus.RUNNING)) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.info("tik tok");
            }
        }
    }

    static class JobStatus {
        final static String DEFAULT = "DEFAULT";
        final static String RUNNING = "RUNNING";
        final static String TERMINATING = "TERMINATING";
    }

}
