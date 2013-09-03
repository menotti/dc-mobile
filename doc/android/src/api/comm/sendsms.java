private void sendSMS(String message, String phone){
	try{
		SmsManager smsMan = SmsManager.getDefault();
		smsMan.sendTextMessage(phone, null, message, null, null);
	} catch(IllegalArgumentException e) {
		e.printStackTrace();
		Toast.makeText(this, "Error sending message", Toast.LENGTH_SHORT)
			.show();
	}
}