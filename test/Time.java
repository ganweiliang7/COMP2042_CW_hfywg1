package test;

import java.util.ArrayList;

public class Time {
    public int centiseconds = 0;
    public int seconds = 0;
    public int minutes = 0;
    ArrayList<String> times = new ArrayList<>();

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

    public void reset()
    {
        minutes = 0;
        seconds = 0;


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