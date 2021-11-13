package test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Time {
    public int centiseconds = 0;
    public int seconds = 0;
    public int minutes = 0;

    public void starttime() throws InterruptedException
    {

            centiseconds ++ ;
            if(centiseconds == 60)
            {
                seconds ++;
                centiseconds = 0;
            }
            if(seconds==60)
            {
                minutes++;
                seconds = 0;
            }

    }


    public int getMinutes()
    {
        return minutes;
    }
    public int getSeconds()
    {
        return seconds;
    }

}