public void writeJSON() {
	JSONObject object = new JSONObject();
	try {
		object.put("name", "Matheus");
		object.put("age", new Integer(200));
		object.put("university", "UFSCar");
	} catch (JSONException e) {
		e.printStackTrace();
	}
} 