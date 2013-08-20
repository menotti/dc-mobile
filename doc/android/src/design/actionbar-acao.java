public boolean onOptionsItemSelected(MenuItem item){
	if(item.getItemId() == R.id.action_settings){
		startActivity(new Intent(this, SettingsActivity.class));
	} else if(item.getItemId() == R.id.action_search){
		LayoutParams lp = new LayoutParams
			(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		EditText search = new EditText(this);
		search.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
		search.setTextColor(Color.WHITE);
		search.setInputType(InputType.TYPE_CLASS_TEXT);
		getActionBar().setCustomView(search, lp);
		search.requestFocus();
		InputMethodManager imm = (InputMethodManager)
				getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(search, InputMethodManager.SHOW_IMPLICIT);
		search.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction
						(TextView v, int actionId, KeyEvent event) {
				Toast.makeText
				(MainActivity.this, v.getText(), Toast.LENGTH_SHORT).show();
				return true;
			}
		});
	}
	return true;
}