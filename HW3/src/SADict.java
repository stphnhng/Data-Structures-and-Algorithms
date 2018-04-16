public class SADict implements Dictionary {

	private class SANode{
		public int key;
		public String value;
		
		public SANode(int k, String v){
			this.key = k;
			this.value = v;
		}
	}
	
	private SANode[] dictionary;
	private int size;
	
	public SADict(){
		dictionary = new SANode[0];
		size = 0;
	}
		
	public void insert(int key, String value) {
		// TODO implement insert
		if(size == dictionary.length){
			SANode[] temp;
			if(size == 0){
				temp = new SANode[1];
			}else{
				temp = new SANode[size*2];
			}
			for(int i = 0; i < dictionary.length; i++){
				temp[i] = dictionary[i];
			}
			dictionary = temp;
		}
		int insertPosition = size;
		for(int i = size-1; i >= 0; i--){
			if(dictionary[i].key > key){
				insertPosition = i;
			}
		}
		for(int j = size; j > insertPosition; j--){
			dictionary[j] = dictionary[j-1];
		}
		dictionary[insertPosition] = new SANode(key,value);
		size++;
	}

	public String find(int key) {
		// TODO implement find (binary search)
		int low = 0;
		int high = size;
		while(low <= high){
			int mid = (low + high)/2;
			if(dictionary[mid].key > key){
				high = mid - 1;
			}else if(dictionary[mid].key < key){
				low = mid + 1;
			}else{
				return dictionary[mid].value;
			}
		}
		return null;
	}

	public boolean delete(int key) {
		// TODO implement delete
		int index = -1;
		for(int i = 0; i < size; i++){
			if(dictionary[i].key == key){
				index = i;
				dictionary[i] = null;
				break;
			}
		}
		if(index != -1){
			for(int j = index; j < size; j++){
				dictionary[j] = dictionary[j+1];
			}
			size--;
			return true;
		}
		return false;
	}

}
