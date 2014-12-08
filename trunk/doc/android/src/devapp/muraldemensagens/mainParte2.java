NotificationManager nm = (NotificationManager) 
				getSystemService(NOTIFICATION_SERVICE);
	PendingIntent p = PendingIntent.getActivity(this, 0, 
				new Intent(this, MensagensActivity.class), 0);
		
	NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
	builder.setTicker("Opa nova mensagem!!");
	builder.setContentTitle("nova mensagem recebida!");
	builder.setSmallIcon(R.drawable.ic_launcher);
	builder.setContentIntent(p);
		
	NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
	String [] descs = new String[]{"clique aqui para ver"};
	for(int i = 0; i < descs.length; i++){
		style.addLine(descs[i]);
	}
	builder.setStyle(style);
		
	Notification n = builder.build();
	n.vibrate = new long[]{150, 300, 150, 600};
	n.flags = Notification.FLAG_AUTO_CANCEL;
	nm.notify(R.drawable.ic_launcher, n);
		
	try{
		Uri som = 
	    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		Ringtone toque = RingtoneManager.getRingtone(this, som);
		toque.play();
	}
	catch(Exception e){}
}
