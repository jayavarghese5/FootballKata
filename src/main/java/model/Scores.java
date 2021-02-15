package model;

import lombok.*;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
public class Scores<T> {
    private T teamName;
    private int score;
}
