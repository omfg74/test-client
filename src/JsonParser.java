import Objects.Answer;
import Objects.Task;
import Objects.TaskList;
import Objects.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

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

    public void parseTasklist(String tasklist) {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray)jsonParser.parse(tasklist);
            JSONObject jsonObject = (JSONObject)jsonParser.parse(tasklist);//хер пойми что с этим делать

            TaskList taskList = new TaskList();
            for (int i = 0; i <jsonArray.size()  ; i++) {

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public Answer parseRegAnswer(String s) {
        Answer answer = new Answer();
        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject jo = (JSONObject)jsonParser.parse(s);
            Boolean ans = (Boolean)jo.get("Answer");
            String text = (String)jo.get("Text ");
            answer.setAnswer((ans));
            answer.setText(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public Answer parseAuthAnswer(String s) {
        Answer answer = new Answer();
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jo = (JSONObject)jsonParser.parse(s);
            Boolean ans = (Boolean) jo.get("Answer");
            String text = (String)jo.get("Text ");
            answer.setAnswer(ans);
            answer.setText(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public JSONObject parseTheTaskResult(String taskResult) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jo=null;
        try {
 jo = (JSONObject)jsonParser.parse(taskResult);
            String a =(String)jo.get("taskType");
//            System.out.println(a);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jo;
    }

    public void parseList(String taskResult) {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jo = (JSONObject)jsonParser.parse(taskResult);
            ArrayList<Task>tasks = new ArrayList<>();
            JSONArray ar  = (JSONArray) jo.get("content");

            for (int i = 0; i < ar.size() ; i++) {
                if(i%2==0){
                    System.out.print(ar.get(i)+" ");
                }else
                    System.out.println(ar.get(i));

                }
                System.out.println();


        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
