import model.Scores;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FootballTest {

    String filename="football.dat";
    Parser parse=new Parser(PaserConfig.defaultConfig);

    @Test
    public void testFootballScoreObjectCreated(){
        try {//need to change
           // assertEquals("Object created success",1,parse.parseDataToObject(filename).getScore());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testFootballScoreResult(){
        Scores footballScore=new Scores();
        footballScore.setTeamName("Aston_Villa");
        try {//need to change
           // assertEquals("Working as expected",footballScore.getTeamName(),parse.parseDataToObject(filename).getTeamName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
