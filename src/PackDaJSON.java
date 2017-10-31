import Objects.User;
import org.json.simple.JSONObject;

public class PackDaJSON {
    public JSONObject putData(String login,String password){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("type", "auth");
    jsonObject.put("login", login);
    jsonObject.put("pass",password);
    return jsonObject;
    }


    public JSONObject packRegistrationData(String[]userData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type","registr");
        jsonObject.put("Name",userData[0]);
        jsonObject.put("Surname",userData[1]);
        jsonObject.put("Login",userData[2]);
        jsonObject.put("Password",userData[3]);
        return jsonObject;

    }

    public JSONObject packNewTask(User user, int command) {
        JSONObject packedTask=new JSONObject();
        packedTask.put("type","menu");
        packedTask.put("item",command);
        packedTask.put("login",user.getLogin());
        packedTask.put("task","newTask");



        return packedTask;
    }
}
