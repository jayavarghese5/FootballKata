package model;

import lombok.*;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
public class Result<T> {
    private T name;
    private double difference;
}
