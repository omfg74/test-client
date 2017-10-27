import org.json.simple.JSONObject;

public class PackDaJSON {
    public JSONObject putData(String login,String password){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("login", login);
    jsonObject.put("pass",password);
    return jsonObject;
    }


    public JSONObject packRegistrationData(String[]userData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Name",userData[0]);
        jsonObject.put("Surname",userData[1]);
        jsonObject.put("Login",userData[2]);
        jsonObject.put("Password",userData[3]);
        return jsonObject;

    }
}
