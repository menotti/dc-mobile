Almoco almoco = new Almoco();
	
	private Almoco degenerateJSON(String data){
		try{
			JSONObject jsonObj = new JSONObject(data);
			
			almoco.setPrincipal(jsonObj.getString("principal"));
			almoco.setGuarnicao(jsonObj.getString("guarnicao"));
			almoco.setArroz(jsonObj.getString("arroz"));
			almoco.setFeijao(jsonObj.getString("feijao"));
			almoco.setSaladas(jsonObj.getString("saladas"));
			almoco.setSobremesa(jsonObj.getString("sobremesa"));
			almoco.setBebida(jsonObj.getString("bebida"));
	
			runThread();
		}
		catch(JSONException e){ e.printStackTrace(); }
		return(almoco);
	}