private void runThread(){
	runOnUiThread (new Thread(new Runnable() { 
	    public void run() {

			TextView lblprincipal = (TextView) findViewById(R.id.lblprincipal);
			lblprincipal.setText(almoco.getPrincipal());
					
			TextView lblguarnicao = (TextView) findViewById(R.id.lblguarnicao);
			lblguarnicao.setText(almoco.getGuarnicao());
					
			TextView lblarroz = (TextView) findViewById(R.id.lblarroz);
			lblarroz.setText(almoco.getArroz());
					
			TextView lblfeijao = (TextView) findViewById(R.id.lblfeijao);
			lblfeijao.setText(almoco.getFeijao());
					
			TextView lblsaladas = (TextView) findViewById(R.id.lblsaladas);
			lblsaladas.setText(almoco.getSaladas());
					
			TextView lblsobremesa = (TextView) findViewById(R.id.lblsobremesa);
			lblsobremesa.setText(almoco.getSobremesa());
					
			TextView lblbebida = (TextView) findViewById(R.id.lblbebida);
			lblbebida.setText(almoco.getBebida());
					
	            try {
	               Thread.sleep(300);
	            } 
	            catch (InterruptedException e) {
	               e.printStackTrace();
	            }
	    }
	}));
}