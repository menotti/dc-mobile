@Override
public void onCreate(Bundle savedInstanceState) {
...

    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

    fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO); 
    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); 

    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
  
    startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
}