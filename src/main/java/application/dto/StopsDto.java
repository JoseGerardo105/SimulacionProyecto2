package application.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @authors Jose Gerardo Gomez - Neyder Fabian Rodriguez - Andres Felipe Amezquita - David Orlando Rodriguez
 */
public class StopsDto extends Dto<Integer> {

    private int stationKey;
    private String stationName;
    private List<Integer> lines;

    public StopsDto(Integer key, String stationName) {
        super(key);
        this.stationName = stationName;
        lines = new ArrayList<>();
    }

    public StopsDto(Integer key, int stationKey) {
        super(key);
        this.stationKey = stationKey;
        lines = new ArrayList<>();
    }   
    
    public void addLine(int line) {
        lines.add(line);
    }

    public int getLine() {
        return lines.get(0);
    }
    public int getStationKey() {
        return stationKey;
    }

}
