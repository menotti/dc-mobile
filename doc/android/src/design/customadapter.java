public class CustomAdapter extends BaseExpandableListAdapter {
	private LayoutInflater inflater;
	private ArrayList<Parent> parent;
	
	public CustomAdapter(Context context, ArrayList<Parent> parent){
		this.parent = parent;
		inflater = LayoutInflater.from(context);
	}

	@Override
	//Obtem o nome de cada item
	public Object getChild(int groupPosition, int childPosition) {
		return parent.get(groupPosition).getArrayChildren().
		get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	//Nesse metodo voce seta os textos para ver os filhos na lista
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View view, ViewGroup viewGroup) {
	
		if(view == null){
			view = inflater.inflate(R.layout.list_item_child, viewGroup,
									false);
		}
		
		TextView textView = (TextView) 
			view.findViewById(R.id.list_item_text_child);
			
		textView.setText(parent.get(groupPosition).getArrayChildren().
		get(childPosition));
		
		return view;	
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		//retorna o tamanho do array de filhos
		return parent.get(groupPosition).getArrayChildren().size();
	}

	@Override
	//Obtem o titulo de cada pai
	public Object getGroup(int groupPosition) {
		return parent.get(groupPosition).getTitle();
	}

	@Override
	public int getGroupCount() {
		return parent.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	//Nesse metodo voce seta o texto para ver os pais na lista
	public View getGroupView(int groupPosition, boolean isExpanded,
			View view, ViewGroup viewGroup) {
		
		if(view == null) {
			//Carrega o layout do parent na view
			view = inflater.inflate(R.layout.list_item_parent, viewGroup,
									false);
		}
		
		//Obtem o textView
		TextView textView = (TextView) 
			view.findViewById(R.id.list_item_text_view);
			
		textView.setText(getGroup(groupPosition).toString());
		
		return view;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition,
									 int childPosition) {
		return true;
	}	
}
