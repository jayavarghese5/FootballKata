import lombok.RequiredArgsConstructor;
import lombok.val;
import model.Scores;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
class PaserConfig{
    public final int teamname;
    public final int f;
    public final int a;
    public static PaserConfig defaultConfig=new PaserConfig(2,7,9);
}
@RequiredArgsConstructor
public class Parser {
    final PaserConfig paserConfig;
    public <T extends Comparable<T>> T parseDataToObject(Function<String,T> parser, String filename) throws Exception {
        Stream<String> contents = getStream(filename);
        List<T> list = cleanStream(contents).map(parser).sorted().collect(Collectors.toList());
        return list.get(0);
    }

    private Stream<String> cleanStream(Stream<String> contents) {
        return contents.skip(1).filter(line -> !line.trim().startsWith("----"));
    }

    private Stream<String> getStream(String filename) throws IOException, URISyntaxException {
        return Files.lines(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));
    }

    Scores parse(String line){
            try {
                String value[] = line.split("\\s+");
                Scores score = score = new Scores();
                score.setTeamName(value[paserConfig.teamname]);
                score.setScore(Math.abs((Integer.valueOf(value[paserConfig.f])) - (Integer.valueOf(value[paserConfig.a]))));
                assert(score.getTeamName()!=null);
                return score;
            } catch (Exception e) {
                throw new RuntimeException("Parsing Error on line [" + line + "]");
            }
    }
}
