package com.example.xcsbooks.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.xcsbooks.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

public class GetBookCover {
	public static String BUSCA_URI = "http://diskexplosivo.com/xcsbooks/get_image.php";
	
	public static Bitmap getCoverFromBackEnd(String isbn){
		AsyncTask<URI, Integer, String> task;
		String resposta = null;
		
		List<NameValuePair> searchData = new ArrayList<NameValuePair>();
		searchData.add(new BasicNameValuePair("isbn", isbn));
		
		try {
			//Faz um request para LOGIN_URI com os dados digitados
			task = new RequestTask(searchData, BUSCA_URI, RequestTask.REQUEST_GET).execute();
			//Obtém a resposta do back-end
			resposta = task.get();
		} catch (Exception e){
			Log.e("COVER_REQUEST", "Error on GET REQUEST to URL");
		}
		
		if(resposta != null){
			int test = JSONParser.parseResposta(resposta);
			if(test < 0){
				Log.d("SEARCH_F", "Resposta: " + test);
				return null;
			}
			
			
			String r = JSONParser.parseImage(resposta);
			//Obtem a resposta e transforma em um Bitmap
			byte[] array = Base64.decode(r, Base64.DEFAULT);
			Bitmap cover = BitmapFactory.decodeByteArray(array, 0, array.length);
			Log.d("IMG_BYTES", resposta.getBytes().toString());
			File coverfile = getOutputMediaFile(isbn);
			
			if(coverfile == null){
				Log.d("COVER_F", "Erro ao criar arquivo imagem, verifique permissões");
				return null;
			}
			
			try{
				FileOutputStream fos = new FileOutputStream(coverfile);
				cover.compress(Bitmap.CompressFormat.JPEG, 100, fos);
				fos.close();
			} catch(FileNotFoundException e){
				Log.d("COVER_F", "Arquivo não encontrado: " + e.getMessage());
			} catch(IOException e) {
				Log.d("COVER_F", "Erro ao acessar arquivo: " + e.getMessage());
			}
			
			return cover;
		}
		
		return null;
	}
	
	public static Bitmap getCover(String isbn){
		//Abrir pasta para verificar arquivos
		boolean found = false;
		MyApplication myApp = MyApplication.getInstance();
		
		String imagePath = Environment.getExternalStorageDirectory() 
				+ "/Android/data/"
				+ myApp.getApplicationContext().getPackageName()
				+ "/Files/"
				+ isbn
				+ ".jpg";
		
		Bitmap cover = BitmapFactory.decodeFile(imagePath);
		
		if(cover != null){
			return cover;
		} else {
			cover = getCoverFromBackEnd(isbn);
			Log.d("COVER", "Got " + isbn + " cover from back-end");
			if(cover != null){
				return cover;
			}
			
			//Se não tem capa disponível, retorna a imagem padrão
			return BitmapFactory.decodeResource(myApp.getResources(), R.drawable.book_icon);
		}
		
		
	}
	
	/** Create a File for saving an image or video */
	private static  File getOutputMediaFile(String isbn){
		MyApplication myapp = MyApplication.getInstance();
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this. 
	    File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
	            + "/Android/data/"
	            + myapp.getApplicationContext().getPackageName()
	            + "/Files"); 

	    // This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            return null;
	        }
	    } 
	    File mediaFile;
	        String mImageName= isbn + ".jpg";
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
	        
	    return mediaFile;
	} 
}
