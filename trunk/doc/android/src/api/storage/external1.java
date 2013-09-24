boolean mExternalStorageAvailable = false;
boolean mExternalStorageWriteable = false;
String state = Environment.getExternalStorageState();

if (Environment.MEDIA_MOUNTED.equals(state)) {
    mExternalStorageAvailable = mExternalStorageWriteable = true;
} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
    mExternalStorageAvailable = true;
    mExternalStorageWriteable = false;
} else {
    mExternalStorageAvailable = mExternalStorageWriteable = false;
}