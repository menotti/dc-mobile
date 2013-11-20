public void openCarList(){
	Intent intent = new Intent(this, CarDetailActivity.class);
	List<String> list = fetchCarList();
	String[] carList = list.toArray(new String[list.size()]);
	intent.putExtra(CARLIST, carList);
	startActivity(intent);
}