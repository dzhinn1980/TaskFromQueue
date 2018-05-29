package ru.dzhinn.taskfromqueue;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class QueueHandler extends TimerTask {

    final static Logger logger = Logger.getLogger(QueueHandler.class);

    final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    final static String TASKRUN_PROPERTIES = "config.properties";

    private Queue queue;

    private Date firstTime;
    private Long period;

    public void handle(Queue queue){
        this.queue = queue;
        init();
        runTimer();
    }

    private void runTimer(){
        if (firstTime.before(new Date())) return;
        Timer timer = new Timer();
        timer.schedule(this, firstTime, period);
    }

    private void init(){
        Properties properties = new Properties();
        try (InputStream is = QueueHandler.class.getClassLoader().getResourceAsStream(TASKRUN_PROPERTIES)) {
            properties.load(is);
            try {
                firstTime = df.parse(properties.getProperty("task.run.firstTime"));
                period = Long.parseLong(properties.getProperty("task.run.period"));
            } catch (Exception e) {
                logger.error("Schedule param firstTime or period wrong format.");
                e.printStackTrace();
            }
        } catch (Exception e) {
            logger.error("Can't read properties file.");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        if (queue == null) return;

        queue.connect();

        Task task = null;
        while ((task = queue.getNextTask()) != null){
            logger.info("Task with id = " + task.getTaskId() + " start at: " + new Date());
        }

        queue.disconnect();
    }
}
