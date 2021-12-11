package time;

public class TimeController {

    public Time timeModel;
    public TimeView timeView;

    public TimeController(Time model, TimeView view)
    {
        timeModel = model;
        timeView = view;

    }
    /**
     *start to calculate the time of the game
     * @throws InterruptedException
     */
    public void starttime() throws InterruptedException
    {

        timeModel.centiseconds ++ ;
        if(timeModel.centiseconds == 60)
        {
            timeModel.seconds ++;
            timeModel.centiseconds = 0;
        }
        if(timeModel.seconds==60)
        {
            timeModel.minutes++;
            timeModel.seconds = 0;
        }

    }
}
