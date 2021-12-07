package time;

public class TimeView
{
    public int displayMinutes(TimeController controller)
    {
        return controller.timeModel.getMinutes();
    }
    public int displaySeconds(TimeController controller)
    {
        return  controller.timeModel.getSeconds();
    }


}
