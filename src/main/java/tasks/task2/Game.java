package tasks.task2;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Game {

    private int id;

    private List<List<Cube>> games;
}
