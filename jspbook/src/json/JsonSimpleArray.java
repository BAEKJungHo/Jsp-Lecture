package json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonSimpleArray {
	public static void main(String[] args) {
		// JSON DATA는 가장 아래 단계에서 부터 만들어 나간다.
		System.out.println("-----------------------------------------------------");
		System.out.println("JSON String 생성");
		JSONObject jObj = new JSONObject();
		
		JSONArray jArray = new JSONArray();
		jArray.add("국어"); // String[] subject = { "국어", "math", "english" };
		jArray.add("math");
		jArray.add("english");
		jObj.put("subject", jArray);
		// 위 코드는 다음과 같습니다. 
		// {"subject" : ["국어","math","english"]}
		
		System.out.println(jObj.toString());
		
		System.out.println("-----------------------------------------------------");
		System.out.println("JSON String 파싱");
		try {
			JSONArray rSub = (JSONArray)jObj.get("subject");
			System.out.println("Size= " + rSub.size());
			for(int i=0; i<rSub.size(); i++) {
				System.out.println("subject[" + i + "] : " + (String)rSub.get(i));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("-----------------------------------------------------");
	}
}
