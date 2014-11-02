package com.example.tabhostrelativelayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class TabInfo {
		public String tag;
		public Class klass;
		public Bundle args;
		public Fragment fragment;
		
		TabInfo(String tag, Class klass, Bundle args){
			this.tag = tag;
			this.klass = klass;
			this.args = args;
		}
}
