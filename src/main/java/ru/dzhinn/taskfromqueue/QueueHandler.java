package ru.dzhinn.taskfromqueue;

import org.apache.log4j.Logger;
import ru.dzhinn.taskfromqueue.service.TaskService;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class QueueHandler {

    final static Logger logger = Logger.getLogger(QueueHandler.class);

    final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    final static String TASKRUN_PROPERTIES = "config.properties";

    private ScheduledExecutorService executorService;

    private Queue queue;

    private TaskService taskService;

    private Date firstTime;
    private Long period;

    private boolean alwaysRun;


    public void handle(Queue queue, TaskService taskService){
        this.queue = queue;
        this.taskService = taskService;
        init();
    }

    public void setAlwaysRun(boolean alwaysRun) {
        this.alwaysRun = alwaysRun;
    }


    private void init(){
        Properties properties = new Properties();
        try (InputStream is = QueueHandler.class.getClassLoader().getResourceAsStream(TASKRUN_PROPERTIES)) {
            properties.load(is);
            try {
                firstTime = df.parse(properties.getProperty("task.run.firstTime"));
                period = Long.parseLong(properties.getProperty("task.run.period"));

                int poolSize = Integer.parseInt(properties.getProperty("pool.size"));
                executorService = Executors.newScheduledThreadPool(poolSize);

                Runnable handleTasks = new Runnable() {
                    @Override
                    public void run() {
                        if (queue == null) return;

                        queue.connect();

                        Task task = null;
                        while ((task = queue.getNextTask()) != null){

                            final Task finalTask = task;
                            executorService.submit(new Runnable() {
                                @Override
                                public void run() {
                                    logger.info("Task with id = " + finalTask.getTaskId() + " start at: " + new Date());

                                    taskService.runTask(finalTask);
                                }
                            });
                        }
                        queue.disconnect();
                    }
                };

                Long currentTime = new Date().getTime();
                long startScheduler = firstTime.getTime() - currentTime;
                executorService.scheduleWithFixedDelay(handleTasks, startScheduler, period, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                logger.error("Schedule param firstTime or period wrong format.");
                e.printStackTrace();
            }
        } catch (Exception e) {
            logger.error("Can't read properties file.");
            e.printStackTrace();
        }
    }

}
