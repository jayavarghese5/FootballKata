import model.Result;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FootballTest {

    String filename="football.dat";
    String file_weather="weather.dat";
    Parser parse=new Parser(PaserConfig.defaultConfig);
    Parser weather_parse=new Parser(PaserConfig.weatherConfig);

    @Test
    public void testFootballScoreObjectCreated(){
        try {
            assertEquals("Object created success", 1,(int)((Result)parse.parseDataToObject(filename)).getDifference());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testFootballScoreResult(){
        Result footballScore=new Result();
        footballScore.setName("Aston_Villa");
        try {
            assertEquals("Working as expected",footballScore.getName(),((Result) parse.parseDataToObject(filename)).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testFootballWeatherScoreObjectCreated(){
        try {
            assertTrue("True",((Result)weather_parse.parseDataToObject(file_weather)).getDifference()>0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
