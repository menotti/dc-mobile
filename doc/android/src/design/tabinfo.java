public class TabInfo {
		private String tag;
		private Class klass;
		private Bundle args;
		private Fragment fragment;
		
		TabInfo(String tag, Class klass, Bundle args){
			this.tag = tag;
			this.klass = klass;
			this.args = args;
		}
}