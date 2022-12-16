package application.dto;

/**
 * @authors Jose Gerardo Gomez - Neyder Fabian Rodriguez - Andres Felipe Amezquita - David Orlando Rodriguez
 */
public class StationsDto extends Dto<Integer> {

    private String name;

    public StationsDto(Integer key) {
        super(key);
    }

    public StationsDto(Integer key, String name) {
        super(key);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
