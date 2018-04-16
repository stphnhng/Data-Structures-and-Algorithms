public class UADict implements Dictionary {
	
	private class UANode{
		public int key;
		public String value;
		
		public UANode(int k, String v){
			this.key = k;
			this.value = v;
		}
	}
	
	private UANode[] dictionary;
	private int size;
	
	public UADict(){
		dictionary = new UANode[0];
		size = 0;
	}
	
	public void insert(int key, String value) {
		// TODO implement insert
		if(size == dictionary.length){
			UANode[] temp;
			if(size == 0){
				temp = new UANode[1];
			}else{
				temp = new UANode[size*2];
			}
			for(int i = 0; i < dictionary.length; i++){
				temp[i] = dictionary[i];
			}
			dictionary = temp;
		}
		dictionary[size] = new UANode(key,value);
		size++;
	}

	public String find(int key) {
		// TODO implement find
		for(int i = 0; i < size; i++){
			if(dictionary[i].key == key){
				return dictionary[i].value;
			}
		}
		return null;
	}

	public boolean delete(int key) {
		// TODO implement delete
		int index = -1;
		for(int i = 0; i < size; i++){
			if(dictionary[i] != null){
				if(dictionary[i].key == key){
					index = i;
					dictionary[i] = null;
				}
			}
		}
		if(index != -1){
			for(int j = index; j < size; j++){
				dictionary[j] = dictionary[j+1];
				size--;
				return true;
			}
		}
		return false;
	}

}
