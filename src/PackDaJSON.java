import org.json.simple.JSONObject;

public class PackDaJSON {
    public JSONObject putData(String login,String password){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("login", login);
    jsonObject.put("pass",password);
    return jsonObject;
    }


}
