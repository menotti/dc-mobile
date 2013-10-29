package br.ufscar.dc.mobile.camera1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

public class CameraAccess {
	Context c;
	Camera cam;
	
	public CameraAccess(Context c){
		this.c = c;
		cam = null;
	}
	
	private boolean checkCameraHardware(){
		if(c.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
			return true;
		return false;
	}

	public Camera getCameraInstance(){
		Camera cam = null;
		if(checkCameraHardware()){
			try{
				cam = Camera.open();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		
		return cam;
	}
	
	public void releaseCamera(){
		cam.release();
	}
}
