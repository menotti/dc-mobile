public class JSONParser {
	
	public String getFormData(String jsonstr){
		String formData = null;
		
		try {
			JSONObject jObj = new JSONObject(jsonstr);
			JSONObject form = jObj.getJSONObject("form");
			formData = form.getString("testing");
			formData += "\n" + form.getString("user");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return formData; 
	}
}