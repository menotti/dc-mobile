public class MainActivity extends Activity {
	private UiLifecycleHelper uiHelper;
	private Session.StatusCallback callback = new Session.StatusCallback() {
	@Override
	public void call(Session session, SessionState state, Exception exception){
		//onSessionStateChanged
		oSSC(session, state, exception);
		}
	};
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	uiHelper = new UiLifecycleHelper(this, callback);
	uiHelper.onCreate(savedInstanceState);
	LoginButton lb = (LoginButton) findViewById(R.id.fbLogin);
	lb.setPublishPermissions(Arrays.asList("email", "public_profile"));
}
@Override
protected void onResume() {
	super.onResume();	
	Session session = Session.getActiveSession();
	if(session != null && (session.isClosed() || session.isOpened())){
		oSSC(session, session.getState(), null);
	}
	uiHelper.onResume();
}
@Override
protected void onPause() {
	super.onPause();
	uiHelper.onPause();
}
@Override
protected void onDestroy() {
	super.onDestroy();
	uiHelper.onDestroy();
}
@Override
protected void onSaveInstanceState(Bundle bundle) {
	super.onSaveInstanceState(bundle);
	uiHelper.onSaveInstanceState(bundle);
}
@Override
protected void onActivityResult(int requestCode,int resultCode,Intent data){
	super.onActivityResult(requestCode, resultCode, data);
	uiHelper.onActivityResult(requestCode, resultCode, data);
}
}
