import model.Scores;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FootballTest {

    String filename="football.dat";
    String file_weather="weather.dat";
    Parser parse=new Parser(PaserConfig.defaultConfig);
    Parser weather_parse=new Parser(PaserConfig.defaultConfig);

    @Test
    public void testFootballScoreObjectCreated(){
        try {
            assertEquals("Object created success", 1,((Scores)parse.parseDataToObject(filename)).getScore());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testFootballScoreResult(){
        Scores footballScore=new Scores();
        footballScore.setTeamName("Aston_Villa");
        try {
            assertEquals("Working as expected",footballScore.getTeamName(),((Scores) parse.parseDataToObject(filename)).getTeamName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
