import Objects.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {
    public User parseNameSurname(String nameSurname, User user){

        JSONParser jsonParser = new JSONParser();
        try {
           JSONObject n = (JSONObject) jsonParser.parse(nameSurname);

           user.setName((String) n.get("Name"));
           user.setSurName((String) n.get("Surname"));


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return user;
    }
}
