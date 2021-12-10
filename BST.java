package com.tree;
import java.util.*;
class NodeBst
{
	String word;
	String meaning;
	NodeBst left,right;
	public NodeBst()
	{
		word=meaning=null;
		left=right=null;
	}
}
class BST1 {

	Scanner sc=new Scanner(System.in);
	NodeBst root;

	public BST1()
	{
		root=null;
	}

	public NodeBst create()
	{
		NodeBst ptr;
		do
		{
			System.out.println("Enter word : ");
			String word=sc.next();

			System.out.println("Enter its meaning : ");
			String meaning=sc.next();

			NodeBst temp=new NodeBst();
			temp.word=word;
			temp.meaning=meaning;
			if(root==null)
				root=temp;
			else
			{
				ptr=root;
				while(ptr!=null)
				{
					if(ptr.word.compareTo(temp.word)>0)
					{
						if(ptr.left==null)
						{
							ptr.left=temp;		
							break;
						}
						else
							ptr=ptr.left;
					}

					//					}

					else if(ptr.word.compareTo(temp.word)<0)
					{
						if(ptr.right==null)
						{
							ptr.right=temp;
							break;
						}
						//							ptr.right=temp;
						else
							ptr=ptr.right;
					}
				}
			}

			System.out.println("To continue adding words press 1 : ");
			// int ch=sc.nextInt();
		}while(sc.nextInt()==1);

		return root;


	}

	public void displayInorder(NodeBst root)
	{

		if(root==null)
		{
			//System.out.println("No words present");
			return;
		}
		else
		{
			displayInorder(root.left);
			System.out.println("Word : "+root.word+"\t\tMeaning : "+root.meaning);
			displayInorder(root.right);
		}
	}

	public NodeBst search(NodeBst root, String word)
	{
		NodeBst ptr=null;
		NodeBst temp=null;
		ptr=root;
		if(root==null)
			temp=null;
		while(ptr!=null)
		{
			if(ptr.word.equals(word))
			{
				temp=ptr;
				break;
			}
			else if(ptr.word.compareTo(word)>0)
				ptr=ptr.left;
			else if(ptr.word.compareTo(word)<0)
				ptr=ptr.right;

		}
		return temp;

	}

	public void update(NodeBst root,String word)
	{
		NodeBst temp;
		int ch=0;
		temp=search(root, word);
		if(temp==null)
			System.out.println("Word not found..");
		else
		{
			System.out.println("1-Update word\n2-Update meaning");
			System.out.println("Enter your choice : ");
			ch=sc.nextInt();

			switch(ch)
			{
			case 1:
				System.out.println("Enter updated word : ");
				String word1=sc.next();
				temp.word=word1;
				System.out.println("Data updated...");
				break;
			case 2:
				System.out.println("Enter updated meaning : ");
				String mean=sc.next();
				temp.meaning=mean;
				System.out.println("Data updated...");
				break;
			default:
				System.out.println("Invalid choice...");
			}

			//			System.out.println("Enter updated word : ");
			//			String word1=sc.next();
			//			temp.word=word1;
			//			System.out.println("To update");
		}
	}

	public NodeBst deleteNode(NodeBst root,String word)
	{
		NodeBst temp=search(root, word);
		if(temp==null)
			System.out.println("Not found...");
		else
		{
			System.out.println("Deleted...");
			if(root==null)
				return root;
			if(word.compareTo(root.word)<0)
				root.left=deleteNode(root.left,word);
			else if(word.compareTo(root.word)>0)
				root.right=deleteNode(root.right, word);
			else
			{
				if(root.left==null)
					return root.right;
				else if(root.right==null)
					return root.left;

				root.word=in_successor(root.right);
				root.right=deleteNode(root.right, root.word);
//				System.out.println("Deleted...");

			}
//			System.out.println("Deleted...");
		}
		return root;
	}

	String in_successor(NodeBst root)
	{
		String s=root.word;
		while(root.left!=null)
		{
			s=root.left.word;
			root=root.left;
		}
		return s;
	}
}

public class BST{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		BST1 bt=new BST1();
		NodeBst root=null,temp=null;
		int ch=0;
		do
		{
			System.out.println("1-Create\n2-Display\n3-Search\n4-Update data\n5-Delete");
			System.out.println("Enter your choice : ");
			ch=sc.nextInt();
			switch(ch)
			{
			case 1:
				root=bt.create();
				break;
			case 2:
//				if(root==null)
//				{
//					System.out.println("No tree");
//				}
//				else
//				{

				bt.displayInorder(root);
				if(root==null)
					System.out.println("No tree");
//				}

				break;
			case 3:
				System.out.println("Enter word to search : ");
				temp=bt.search(root, sc.next());
				if(temp==null)
					System.out.println("Word not found...");
				else
					System.out.println("Word found..");
				break;
			case 4:
				System.out.println("Enter word to update data : ");
				bt.update(root,sc.next());
				break;
			case 5:
				System.out.println("Enter word to delete : ");
				root=bt.deleteNode(root,sc.next());

//				System.out.println("Deleted...");
				break;
			default:
				System.out.println("Invalid choice...");
			}
			System.out.println("To continue press 1 : ");
		}while (sc.nextInt()==1);
		System.out.println("Thank you....");
	}
}
