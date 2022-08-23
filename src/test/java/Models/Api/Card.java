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
public class Card {

    String name;
    String idLists;
    String idBoard;

    public static Card createCard(Map<String,String> data){

        Card card = new Card();
        card.setName(data.get("name"));
        return card;

    }

}
