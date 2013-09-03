try {
	HttpPost post = new HttpPost(uri[0]);
	List<NameValuePair> nvp = new ArrayList<NameValuePair>();
	nvp.add(new BasicNameValuePair("testing", "Post"));
	nvp.add(new BasicNameValuePair("user", "You"));
	
	post.setEntity(new UrlEncodedFormEntity(nvp));
	response = httpclient.execute(post);
	...
...
}
