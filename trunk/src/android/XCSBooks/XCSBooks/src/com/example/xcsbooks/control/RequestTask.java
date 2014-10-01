package com.example.xcsbooks.control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

public class RequestTask extends AsyncTask<URI, Integer, String>{
	private List<NameValuePair> args;
	private String urlArg;
	private int method;
	private SharedPreferences prefs;
	String ckName, ckValue;
	public final static int REQUEST_POST = 1;
	public final static int REQUEST_GET = 2;
	public final static String DOMAIN = "diskexplosivo.com";
	
	public RequestTask(List<NameValuePair> mArgs, String url, int mMethod) {
		args = mArgs;
		urlArg = url;
		method = mMethod;
		
	}
	
    @Override
    protected String doInBackground(URI... uri) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try{
	        String[] keyValueSets = CookieManager.getInstance().getCookie(DOMAIN).split(";");
	        for(String cookie : keyValueSets){
	        	String[] keyValue = cookie.split("=");
	        	String key = keyValue[0];
	        	String value = "";
	        	if(keyValue.length > 1)
	        		value = keyValue[1];
	        	BasicClientCookie bcc = new BasicClientCookie(key, value);
	        	bcc.setDomain(DOMAIN);
	        	httpclient.getCookieStore().addCookie(bcc);
	        }
        } catch (NullPointerException e){
        	e.printStackTrace();
        }
        
        HttpResponse response;
        String responseString = null;
        
        try {
        	if(method == REQUEST_GET){
        		if(!urlArg.endsWith("?"));
        			urlArg += "?";
        			
        		String paramStr = URLEncodedUtils.format(args, "utf-8");
        		urlArg += paramStr;
        		
        		Log.d("URL_GET", "URL: " + urlArg);
        		HttpGet get = new HttpGet(urlArg);
        		response = httpclient.execute(get);
        	} else {
        		HttpPost post = new HttpPost(urlArg);
        		System.out.println(urlArg);
            	post.setEntity(new UrlEncodedFormEntity(args));
                response = httpclient.execute(post);
        	}
        	
            
            StatusLine statusLine = response.getStatusLine();
            
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                responseString = out.toString();
                out.close();
                
                responseString = out.toString();

                CookieStore cookieStore = httpclient.getCookieStore();
                List<Cookie> cookies = cookieStore.getCookies();
                //Save cookie to sharedpref
                if(cookies != null){
                	for(Cookie cookie : cookies){
                		Log.d("COOKIES", cookie.getName() + ": " + cookie.getValue());
                		String cookieString = cookie.getName() + "=" + cookie.getValue() + "; domain=" + cookie.getDomain();
                		Log.d("DOMAIN", cookie.getDomain());
                		CookieManager.getInstance().setCookie(cookie.getDomain(), cookieString);
                	}
                }
               
            } else { 
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }  
    
    @Override
    protected void onPostExecute(String result) {
    	super.onPostExecute(result);
    	 CookieSyncManager.getInstance().sync();
    }
}