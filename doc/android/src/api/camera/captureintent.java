private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
private static final int MEDIA_TYPE_IMAGE = 1;
private static final int MEDIA_TYPE_VIDEO = 2;
private Uri fileUri;

@Override
public void onCreate(Bundle savedInstanceState) {
...

	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	
	fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
	intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
	
	startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
}