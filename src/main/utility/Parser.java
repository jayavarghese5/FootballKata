import lombok.RequiredArgsConstructor;
import model.Result;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
class PaserConfig{
    //Position of the headers
    public final int teamname;
    public final int f;
    public final int a;
    public static PaserConfig defaultConfig=new PaserConfig(2,7,9);
    public static PaserConfig weatherConfig=new PaserConfig(1,2,3);
}
@RequiredArgsConstructor
public class Parser {
    final PaserConfig paserConfig;

    public <T> T parseDataToObject( String filename) throws Exception {
        Stream<String> contents = getStream(filename);
        List<Result> list = cleanStream(contents).map(parserWithNonFunctionals).sorted(Comparator.comparingDouble(s->s.getDifference())).collect(Collectors.toList());
        return (T)list.get(0);
    }

    private Stream<String> cleanStream(Stream<String> contents) {
        return contents.skip(1).filter(line -> !line.trim().startsWith("----"));
    }

    private Stream<String> getStream(String filename) throws IOException, URISyntaxException {
        return Files.lines(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));
    }

    <From,To>Function<From,To> addErrorMessage(Function<From,To> raw,String pattern){
        return from->{try{
            return raw.apply(from);
        } catch (Exception e) {
            throw new RuntimeException(MessageFormat.format(pattern,from));
        }
        };
    }

    Function<String, Result> parserWithNonFunctionals= addErrorMessage(this::parse,"Error parsing line[{0}]");

    Result parse(String line){
                line=line.replace("*","");
                String value[] = line.split("\\s+");
                Result score = score = new Result();
                score.setName(value[paserConfig.teamname]);
                score.setDifference(Math.abs((Double.valueOf(value[paserConfig.f])) - (Double.valueOf(value[paserConfig.a]))));
                System.out.println(score.toString());
                assert(score.getName()!=null);
                return score;

    }


}
