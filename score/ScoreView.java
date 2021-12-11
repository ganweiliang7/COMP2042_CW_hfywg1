package score;

public class ScoreView {

    public int getScore(ScoreController score)
    {
        return  score.scoreModel.returnScore();
    }
}
