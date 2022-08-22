package Models.Api;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Board {

    private String id;
    private String name ;
    private String desc ;
    private boolean closed;

    public static Board createBoard(Map<String,String> data){

        Board board = new Board();
        board.setClosed(Boolean.parseBoolean(data.get("closed")));
        board.setDesc(data.get("desc"));
        board.setName(data.get("name"));

        return board;

    }

}
