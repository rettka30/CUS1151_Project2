/**
 * @author rettk
 *
 */
class BST{
    
    Node root;

    private class Node{
    	
    	String keyword;
        Record record;
        int size;
        Node l;
        Node r;

        private Node(String keyword){
        	// TODO Instantialize a new Node with keyword k.
         this.keyword = keyword;
        }

        private void update(Record r){
        	//TODO Adds the Record r to the linked list of records. 
        	// You do not have to check if the record is already in the list.
        	//HINT: Add the Record r to the front of your linked list.
         if(record == null) {
            record = r;
            size++;
         } else if(r.id == record.id) {
            record = r;
         } else {
            r.next = record;
            record = r;
            size++;
         }
        }
    }

    public BST(){
        this.root = null;
    }
      
    public void insert(String keyword, FileData fd){
        Record recordToAdd = new Record(fd.id, fd.title, fd.author, null);
        //TODO Write a recursive insertion that adds recordToAdd 
        // to the list of records for the node associated with keyword.
        // If there is no node, this code should add the node
        root = insertion(root, keyword, recordToAdd);
    }
    
    public Node insertion(Node current, String keyword, Record record) {
      if(current == null) {
         current = new Node(keyword);
         current.update(record);
         return current;
      }
      if(current.keyword.compareTo(keyword) < 0) {
         current.l = insertion(current.l, keyword, record);
      } else if(current.keyword.compareTo(keyword) > 0) {
         current.r = insertion(current.r, keyword, record);
      } else { 
   		if(keyword.equals(current.keyword)) {
            current.update(record);
   			return current; 
   		}
         return current;
   	}  
      return current;
    }

    public boolean contains(Node current, String keyword) {
    	//TODO Write a recursive function which returns true 
    	// if a particular keyword exists in the BST
      if(current == null) {
         return false;
      }
    	if(current.keyword.equals(keyword)) {
         return true;
      } else if(current.keyword.compareTo(keyword) < 0) {
         return contains(current.l, keyword);
      } else if(current.keyword.compareTo(keyword) > 0) {
         return contains(current.r, keyword);
      } else {
         return false;
      }
    }

	public Record get_records(String keyword){
        //TODO Returns the first record for a particular keyword. 
    	// This record will link to other records
    	// If the keyword is not in the BST, it should return null.
      if(contains(root, keyword) == true) {
         return retrieve(root, keyword);
      } else {
        return null;
      }
    }
    
    public Record retrieve(Node current, String keyword) {
      Record record;
      if (current == null) {
         record = null;
         return record;
      } 
   	if(current.keyword.compareTo(keyword) < 0) {
   		record = retrieve(current.l, keyword); 
   	} else if(current.keyword.compareTo(keyword) > 0) {
   		record = retrieve(current.r, keyword); 
      } else { 
   		if(keyword.equals(current.keyword)) {
   			return current.record; 
   		}
         record = current.record;
   	} 
   	return record; 
    }

    public void delete(String keyword){
    	//TODO Write a recursive function which removes the Node with keyword 
    	// from the binary search tree.
    	// You may not use lazy deletion and if the keyword is not in the BST, 
    	// the function should do nothing.
      root = deletion(root, keyword);
    }
    
    public Node deletion(Node current, String keyword) {
   		if (current == null) {
            return current;
         } 
   		if (current.keyword.compareTo(keyword) < 0) {
   			current.l = deletion(current.l, keyword); 
   		} else if (current.keyword.compareTo(keyword) > 0) {
   			current.r = deletion(current.r, keyword); 
         } else { 
   			if (current.l == null) {
   				return current.r; 
   			} else if (current.r == null) {
   				return current.l; 
            }
   			current.keyword = heir(current.r); 
   			current.r = deletion(current.r, current.keyword); 
   		} 
   		return current; 
    } 
    
    public String heir(Node current) {
      String keyword = current.keyword; 
		while (current.l != null) { 
			keyword = current.l.keyword; 
			current = current.l; 
		} 
		return keyword; 
    }
    
    public void print(){
        print(root);
    }

    private void print(Node t){
        if (t != null){
            print(t.l);
            System.out.println(t.keyword);
            Record r = t.record;
            while(r != null){
            	System.out.println("\t" + r.title);
                r = r.next;
            }
            print(t.r);
        } 
    }
    
    public void traversePreOrder() {
      traversePreOrder(root);
    }
    
    public void traversePreOrder(Node node) {
       if (node != null) {
           System.out.print(" " + node.keyword);
           traversePreOrder(node.l);
           traversePreOrder(node.r);
       }
    }
}
