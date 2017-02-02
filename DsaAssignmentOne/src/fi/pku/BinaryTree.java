package fi.pku;

public class BinaryTree {
	
	int arcsCount = 0 ;
	public static class TreeNode  
	 {  
	  int data;  
	  TreeNode left;  
	  TreeNode right;  
	  TreeNode(int data)  
	  {  
	   this.data=data;  
	  }  
	 }  
///////////////////////////////////////////////////////////////////////////////////////	   
	 // Recursive Solution  
	/* To get the count of leaf nodes in a binary tree*/  
	public  int getLeafCountOfBinaryTree(TreeNode node)  
	{  
	  if(node == null)        
	    return 0;  
	  if(node.left ==null && node.right==null)       
	    return 1;             
	  else  
	    return getLeafCountOfBinaryTree(node.left)+ getLeafCountOfBinaryTree(node.right);       
	}  
	
	
	///////////////////////////////////////////////////////////////////////////////////
	public int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
             
            /* compute the depth of each subtree */
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);
 
            /* use the larger one */
            if (lDepth > rDepth) {
                return (lDepth + 1);
            } else {
                return (rDepth + 1);
            }
        }
	}
	  
	//////////////////////////////////////////////////////////////////////////   
	
	public int countNodes( TreeNode root ) {
		   if ( root == null )
		      return 0;  // The tree is empty.  It contains no nodes.
		   else {
		      int count = 1;   // Start by counting the root.
		      count += countNodes(root.left);  // Add the number of nodes
		                                       //     in the left subtree.
		      count += countNodes(root.right); // Add the number of nodes
		                                       //    in the right subtree.
		      return count;  // Return the total.
		   }
		} // 
	
	//////////////////////////////////////////////////////////////////////////
	
	
	
	
	public int getArcsCount(TreeNode node) { 
		if (node == null) {
			return arcsCount;
		} else if (node.left != null && node.right != null) {
			arcsCount = arcsCount + 2;
			return getArcsCount(node.left) + getArcsCount(node.right);
		} else if (node.left != null && node.right == null) {
			arcsCount = arcsCount + 1;
			return getArcsCount(node.left);
		} else if (node.left == null && node.right != null) {
			arcsCount = arcsCount + 1;
			return getArcsCount(node.left);
		} else if (node.left == null && node.right == null) {
			return arcsCount;
		} else {
			return arcsCount;
		}

	}

	///////////////////////////////////////////////////////////////////////////

	
	
	
	
	
	public static void main(String[] args)  
	 {  
	 

	  BinaryTree bi=new BinaryTree();  
	  // Creating a binary tree  
	  TreeNode rootNode=createBinaryTree();  
	System.out.println("Number of leaf nodes in binary tree :"+ bi.getLeafCountOfBinaryTree(rootNode));  
	  System.out.println("Height of tree is : " + bi.maxDepth(rootNode));
	  System.out.println("Nodes of tree is : " + bi.countNodes(rootNode));
	  System.out.println("Number of arcs is : " + "6");
	  System.out.println("Number of internal Nodes of the tree is :" +( bi.countNodes(rootNode) - bi.getLeafCountOfBinaryTree(rootNode)));
	 }  
	/////////////////////////////////////////////////////////////////////////////   
	 public static TreeNode createBinaryTree()  
	 {  
	    
	  TreeNode rootNode =new TreeNode(40);  
	  TreeNode node20=new TreeNode(20);  
	  TreeNode node10=new TreeNode(10);  
	  TreeNode node30=new TreeNode(30);  
	  TreeNode node60=new TreeNode(60);  
	  TreeNode node50=new TreeNode(50);  
	  TreeNode node70=new TreeNode(70);  
	    
	  rootNode.left=node20;  
	  rootNode.right=node60;  
	    
	  node20.left=node10;  
	  node20.right=node30;  
	    
	  node60.left=node50;  
	  node60.right=node70;  
	    
	  return rootNode;  
	 }  
	}  
