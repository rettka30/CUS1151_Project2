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

        // Node Constructor:
        // keyword is set to keyword
        private Node(String keyword){
        	// TODO Instantialize a new Node with keyword k.
         this.keyword = keyword;
        }

        // Update Method:
        // In this method, if the record has not yet been set, it is here.
        // If there are any repeats, this ensures that there are none. At 
        // least for this set of data. If there there is already some data
        // there then a stack is created, where the new data entering now 
        // becomes the top node.
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
    
    // Insert method:
    // This method calls the recursive method where a new node is added by keyword 
    // or if that keyword already exists, then it just adds the new record data
    // to the stack for that keyword's node.
    public void insert(String keyword, FileData fd){
        Record recordToAdd = new Record(fd.id, fd.title, fd.author, null);
        //TODO Write a recursive insertion that adds recordToAdd 
        // to the list of records for the node associated with keyword.
        // If there is no node, this code should add the node
        root = insertion(root, keyword, recordToAdd);
    }
    
    // Insertion Method:
    // This is the insertion method that adds to the binary search tree by
    // recursion and adds to the linked list for each node. First, if the 
    // root does not exist, it will add that first insertion as the new 
    // root and start the stack for that node. This would be the base case 
    // for the recursion. If the root has already been intialized, then will 
    // compare the keyword with that of the root's. It will then send the 
    // keyword to the proper side of the tree, thanks to the compareTo method. 
    // This is where the recursion begins as the method calls itself, except
    // it passes direct in which the keyword must go, either to the left or
    // right side of the tree. It also passes the keyword and the record along 
    // with it. It will continue to do this until it either will create a new
    // node and create the stack becuase the keyword does not exist in the tree 
    // yet or, if the keyword is already in the tree, it will add to the stack.
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
    
    // Contains Method:
    // This method compares the keyword with all the keywords in the binary
    // search tree to see if this keyword is contained within the tree. It
    // will return a boolean variable(true or false) as to whether the keyword
    // exists in the tree or not. It does this recursively. As with recursion,
    // we start with a base case, where we check to see if the current node we
    // are looking at exists (not intialized yet). If it hasn't, we return false,
    // since the keyword can not exist and be compared to something that doesn't
    // exist. Otherwise, we start with the other base, where we check to see if 
    // the current node's keyword is equal to the keyword we are searching for.
    // If it does, it returns true. Then we go into the recursive if statements.
    // If the keyword is greater than the keyword of the current node, the method 
    // will call itself, but the current node changes to the left node of the current
    // node. and this will happen with the other side but vice versa. Lastly, if 
    // nothing is found, we return false.
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

   // Get_Records Method:
   // This method calls the recursive retrive method in order to get the record for
   // a particular node. This method also checks to make sure that this node exists
   // before calling the retrive method. The retrive method with return the top of
   // the stack of the record for that node. However, this node is linked to the 
   // node right below it and so on and so forth. So, you will get the entirety of
   // the stack printed.
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
    
    // Retrive method:
    // This is the recursive method that retrieves the Record from a particular node
    // based on the keyword, which the binary search tree is ordered by. We start with
    // the base case of whether or not the node exists. If it does not then it declare
    // the record variable as null and will return null, since you can't have the record
    // of something that does not exist. We then go into the recursive if statements, they
    // are just like the other recursive statments from the other functions. The difference
    // is that we also have the if statement in the else, since the keyword is neither greater
    // or less than the keyword of the current node. In this statement, we check to see if the 
    // sed keyword is equal to that of the node's. If so, then we return the record from that 
    // node. Otherwise, we just make the record variable equal to that of the current node.
    // Out of the if statements, we return whatever the return variable is.
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

    // Delete Method:
    // This method calls the recursive deletion method, where the node that equals the
    // given keyword is deleted and the tree shifts accordingly. You may also notice that
    // the deletion method returns a Node. This is because the root may be deleted, so
    // the root might have to be reset.
    public void delete(String keyword){
    	//TODO Write a recursive function which removes the Node with keyword 
    	// from the binary search tree.
    	// You may not use lazy deletion and if the keyword is not in the BST, 
    	// the function should do nothing.
      root = deletion(root, keyword);
    }
    
    
    // Deletion Method:
    // This method deletes the given node and shifts the tree according. This method is 
    // recursive like the other methods. It works in pretty much the same way of calling 
    // itself until the node whose keyword matches is found. The base case like the other 
    // methods is if we find a node that does not exist. We will just return it if that is 
    // the case. In the else, we have two if statements. Here we are returning in case of
    // simple deletion. If this is not the case, then we will have to the most approriate
    // node to take its place. This is where we call another method to find such a node,
    // in particular the smallest node on the right side of the tree. We get the keyword,
    // and call the method again with the replacement info. It then returns the current
    // node.
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
    
    // Heir Method:
    // This method is used to find the heir for the node deleted in the method above.
    // This methods finds the smallest node it can for the given node entered. It finds
    // the min value. It does this through a while loop, which does not stop until there
    // are no more left nodes. It returns the keyword, which is put into the deletion 
    // method above, so it can replace the deleted node.
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
}
