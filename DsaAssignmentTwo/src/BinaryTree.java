

// java program for inorder and preorder traversal of a binary Tree
//Author : Praveen 

import java.util.Stack; 
//Class containing left and right child of current node
class Node {

    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}


class BinaryTree {

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Function for inorder traversal
    void inorder( Node root) {
        if (root == null) {
            return;
        }
       
        Stack<Node> stack = new Stack<Node>();
        Node node = root;
        
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        
        
        while (stack.size() > 0) {
          
        
            node = stack.pop();
            System.out.print(node.data + " ");
            if (node.right != null) {
                node = node.right;
                
                
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
        System.out.println("\n");
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //Function for preorder traversal
    public void preorder(Node root) {  
        
        if(root == null)  
            return;  
   
        Stack<Node> stack = new Stack<Node>();  
        stack.push(root);  
   
        while(!stack.empty()){  
           
            Node n = stack.pop();  
            System.out.printf("%d ",n.data);  
   
            
            if(n.right != null){  
                stack.push(n.right);  
            }  
            if(n.left != null){  
                stack.push(n.left);  
            }  
   
        }  
          System.out.println("\n");
    }  
  
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Creating a tree with sample data 1
    public static Node createBinaryTree()  
    {  
       
     Node rootNode =new Node(8);  
     Node node3=new Node(3);  
     Node node10=new Node(10);  
     Node node1=new Node(1);  
     Node node6=new Node(6);  
     Node node14=new Node(14);  
     Node node4=new Node(4); 
     Node node7=new Node(7); 
     Node node13=new Node(13); 
     
       
		rootNode.left = node3;
		rootNode.right = node10;
		node3.left = node1;
		node3.right = node6;
		node10.right = node14;
		node6.left = node4;
		node6.right = node7;
		node14.left = node13;
       
     return rootNode;  
    }  
    /////////////////////////////////////////////////////////
    //Creating a tree with sample data 2
    public static Node createBinaryTree2()  
    {  
       
     Node rootNode =new Node(10);  
     Node node5=new Node(5);  
     Node node20=new Node(20);  
     Node node4=new Node(4);  
     Node node6=new Node(6);  
     Node node15=new Node(15);  
     Node node25=new Node(25);  
       
     rootNode.left=node5;  
     rootNode.right=node20;  
       
     node5.left=node4;  
     node5.right=node6;  
       
     node20.left=node15;  
     node20.right=node25;  
       
     return rootNode;  
    }  
    /////////////////////////////////////////////////////////
    public static void main(String args[]) {
        
        /* creating a binary tree and populating 
         the nodes */
        BinaryTree tree = new BinaryTree();
        
        
       Node rootNode = createBinaryTree();
       Node rootNode2 = createBinaryTree2();
      
       System.out.println("Output with sample data 1 \n ===============================" ); 
        System.out.println("Output with inOrder traversal"); 
        tree.inorder(rootNode);
        System.out.println("Output with preOrder traversal"); 
        tree.preorder(rootNode);
        System.out.println("Output with sample data 2 \n ===============================" ); 
        System.out.println("Output with inOrder traversal"); 
        tree.inorder(rootNode2);
        System.out.println("Output with preOrder traversal"); 
        tree.preorder(rootNode2);
        
    }
    
   
}