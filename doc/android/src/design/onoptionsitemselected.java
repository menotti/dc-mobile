public boolean onOptionsItemSelected(MenuItem item){
	if(item.getItemId() == R.id.action_settings){
		startActivity(new Intent(this, SettingsActivity.class));
	}
	return true;
}