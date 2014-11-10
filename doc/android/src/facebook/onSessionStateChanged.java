//onSessionStateChanged
public void oSSC(final Session session,SessionState state,Exception expt){
		if(session != null && session.isOpened()){
			Log.i("Script", "Usuário conectado");
			Request.newMeRequest(session, new Request.GraphUserCallback(){
				@Override
				public void onCompleted(GraphUser user, Response response){
					if(user != null){
					TextView temp = (TextView) findViewById(R.id.name);
					temp.setText(user.getFirstName()+" "+user.getLastName());

					temp = (TextView) findViewById(R.id.email);
					temp.setText(user.getProperty("email").toString());
						
					temp = (TextView) findViewById(R.id.id);
					temp.setText(user.getId());
						
		ProfilePictureView ppv = (ProfilePictureView) findViewById(R.id.fbImg);
		ppv.setProfileId(user.getId());

					}
				}
		    }).executeAsync();
		}
		else{
			Log.i("Script", "Usuário não conectado");
			
			//Limpa valores de texto
			TextView tv = (TextView) findViewById(R.id.name);
			tv.setText("Nome");

			tv = (TextView) findViewById(R.id.email);
			tv.setText("Email");
			
			tv = (TextView) findViewById(R.id.id);
			tv.setText("Id");
			
	ProfilePictureView ppv = (ProfilePictureView) findViewById(R.id.fbImg);
	ppv.setProfileId("");
		}
	}