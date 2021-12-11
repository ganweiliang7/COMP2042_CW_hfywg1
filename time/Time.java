package time;

public class Time {
    public int centiseconds = 0;
    public int seconds = 0;
    public int minutes = 0;



    public void reset()
    {
        minutes = 0;
        seconds = 0;
        centiseconds = 0;


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