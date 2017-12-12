package analyse_json;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ParserJSON {
  public static void main(String[] args) throws IOException {
    ParserJSON parser = new ParserJSON();
    parser.parserJSON();
  }

  public void parserJSON() throws IOException {
    JSONParser parser = new JSONParser();

    try {

      Object obj = parser.parse(new FileReader("json.txt"));

      JSONObject jsonObject = (JSONObject) obj;

      String name = (String) jsonObject.get("Name");
      String author = (String) jsonObject.get("Author");
      JSONArray companyList = (JSONArray) jsonObject.get("Company List");

      System.out.println("Name: " + name);
      System.out.println("Author: " + author);
      System.out.println("\nCompany List:");
      Iterator<String> iterator = companyList.iterator();
      while (iterator.hasNext()) {
        System.out.println(iterator.next());
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
