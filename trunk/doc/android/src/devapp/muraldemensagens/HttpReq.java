private class HttpAsyncTask extends AsyncTask<String, Void, String> {
 @Override
 protected String doInBackground(String... urls) {
   return GET(urls[0]);
 }

 @Override
 protected void onPostExecute(String result) {
     Toast.makeText(getBaseContext(), "Uhu peguei as mensagens!", 
					 Toast.LENGTH_LONG).show();
     try {
	JSONObject jObject = new JSONObject(result);
	JSONArray jArray = jObject.getJSONArray("mensagemList");
	ArrayList<Item> items = new ArrayList<Item>();
				
       for (int i = 0; i < jArray.length(); i++) {
	JSONObject jObj = jArray.getJSONObject(i);
	JSONObject msgTeste = jArray.getJSONObject(i);
	System.out.println(i + " id : " + jObj.getInt("id"));
	System.out.println(i + " msg : " + jObj.getString("msg"));
	System.out.println(i + " nome : " + jObj.getString("nome"));
	items.add(new Item(msgTeste.getString("nome"),msgTeste.getString("msg")));
       }
		
	 MyAdapter adapter = new MyAdapter(MensagensActivity.this, items);
	 ListView listView = (ListView) findViewById(R.id.listview);
	 listView.setAdapter(adapter);

	} catch (JSONException e) {
	 e.printStackTrace();
	 Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
	 Log.i("JSONException", e.toString());
	}
       }
    }