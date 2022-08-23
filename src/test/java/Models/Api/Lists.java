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
public class Lists {

    String id;
    String name;
    String idBoard;

    public static Lists createLists(Map<String,String> data){

        Lists lists = new Lists();
        lists.setId(data.get("id"));
        lists.setName(data.get("name"));

        return lists;

    }

}