package lab3;

public class BSTSet
{
	// the root of the tree
	private TNode root;

	// constructor
	public BSTSet()
	{
		root = null;
	}

	// constructor with parameter
	public BSTSet(int[] input)
	{
		// === BONUS is implemented===
		// (1) remove all duplicated values
		int size = 0;
		// create a new array to store not duplicated values
		int[] arr = new int[input.length];
		for (int i = 0; i < input.length; i++)
		{
			// check whether this value has been in array
			boolean duplicated = false;
			for (int j = 0; j < size; j++)
			{
				if (arr[j] == input[i])
				{
					duplicated = true;
					break;
				}
			}
			// add to new array only if it's not duplicated
			if (duplicated == false)
			{
				arr[size] = input[i];
				size++;
			}
		}
		// (2) sort array by bubble sort
		for (int i = 0; i <= (size - 1); i++)
		{
			for (int j = 0; j < (size - 1); j++)
			{
				int temp = j+1;
				int a = arr[j];
				int b = arr[temp];
				if(b<a) 
				{
					arr[j] = b;
					arr[temp] = a;
				}
			}
		}
		// (3) add to tree by this order: median one of all array, median one of
		// first half array,
		// median one of second half array, and so on...
		root = insertToTree(arr, 0, size - 1);
	}

	// a helper recursive method to insert values to make the tree with minimum
	// height
	private TNode insertToTree(int[] array, int start, int end)
	{
		if (start > end) return null;
		int mid =start + (end - start) / 2;
		TNode root = new TNode(array[mid], null, null);
		root.left = insertToTree(array, start, mid - 1);
		root.right = insertToTree(array, mid + 1, end);
		return root;
	}

	// whether in the tree
	public boolean isIn(int v)
	{
		TNode n = root;
		while (n != null)
		{
			if (n.element == v) return true;
			// toward left
			else if (v < n.element) n = n.left;
			// toward right
			else n = n.right;
		}
		return false;
	}

	// add one value
	public void add(int v)
	{
		// duplicated
		if (isIn(v)) return;
		// as the root
		if (root == null)
		{
			root = new TNode(v, null, null);
			return;
		}
		// find his father
		TNode n = root;
		while (n != null)
		{
			// toward left
			if (v < n.element && n.left != null) n = n.left;
			// as the left child
			else if (v < n.element && n.left == null)
			{
				n.left = new TNode(v, null, null);
				return;
			}
			// toward right
			else if (v > n.element && n.right != null) n = n.right;
			// as the right child
			else if (v > n.element && n.right == null)
			{
				n.right = new TNode(v, null, null);
				return;
			}
		}
	}

	// remove one value from the tree
	public boolean remove(int v)
	{
		if (isIn(v) == false) return false;
		// find the node to be deleted
		TNode current = root;
		TNode parent = root;
		boolean isLeftChild = true;
		while (current.element != v)
		{
			parent = current;
			if (v < current.element)
			{
				isLeftChild = true;
				current = current.left;
			}
			else
			{
				isLeftChild = false;
				current = current.right;
			}
		}
		// remove it
		// no child
		if (current.left == null && current.right == null)
		{
			if (current == root) root = null;
			else if (isLeftChild) parent.left = null;
			else parent.right = null;
		}
		// one left child
		else if (current.right == null && current.left != null)
		{
			if (current == root) root = current.left;
			else if (isLeftChild) parent.left = current.left;
			else parent.right = current.left;
		}
		// one right child
		else if (current.left == null && current.right != null)
		{
			if (current == root) root = current.right;
			else if (isLeftChild) parent.left = current.right;
			else parent.right = current.right;
		}
		// two children
		else if (current.left != null && current.right != null)
		{
			TNode successor = getSuccessor(current);
			if (current == root) root = successor;
			else if (isLeftChild) parent.left = successor;
			else parent.right = successor;
		}
		return true;
	}

	// get the successor for the node
	private TNode getSuccessor(TNode node)
	{
		TNode succ = node.right;
		TNode succParent = node;
		if(succ.left == null);
		{
			succParent.right = succ.right;
		}
		while (succ.left != null)
		{
			succParent = succ;
			succ = succ.left;
		}
		
			
		succParent.left = succ.right;
		return succ;
	}

	// get union tree
	public BSTSet union(BSTSet s)
	{
		// create one array to store all values from current tree and s tree
		// duplicated is allowed
		int[] array = new int[size() + s.size()];
		int size = toArray(array, 0, root);
		toArray(array, size, s.root);
		// create one new tree
		return new BSTSet(array);
	}

	// a helper method convert the tree to an array
	// the number of elements in the array is returned
	private int toArray(int array[], int size, TNode n)
	{
		if (n == null) return size;
		// fill current node
		array[size] = n.element;
		size++;
		// left side
		size = toArray(array, size, n.left);
		// right side
		size = toArray(array, size, n.right);
		return size;
	}

	// get a intersection tree
	public BSTSet intersection(BSTSet s)
	{
		// don't know the exact length of array, an array with enough space is
		// created
		int[] result = new int[size()];
		int n = 0;
		// check each value
		int[] array = new int[size()];
		toArray(array, 0, root);
		for (int v : array)
		{
			if (s.isIn(v))
			{
				result[n] = v;
				n++;
			}
		}
		// shrink the array to real size
		int[] result2 = new int[n];
		for (int i = 0; i < n; i++)
			result2[i] = result[i];
		// create one new tree
		return new BSTSet(result2);
	}

	public BSTSet difference(BSTSet s)
	{
		// don't know the exact length of array, an array with enough space is
		// created
		int[] result = new int[size()];
		int n = 0;
		// check each value
		int[] array = new int[size()];
		toArray(array, 0, root);
		for (int v : array)
		{
			if (s.isIn(v) == false)
			{
				result[n] = v;
				n++;
			}
		}
		// shrink the array to real size
		int[] result2 = new int[n];
		for (int i = 0; i < n; i++)
			result2[i] = result[i];
		// create one new tree
		return new BSTSet(result2);
	}

	// get the number of nodes in the tree
	public int size()
	{
		return size(root);
	}

	// the helper method for size
	private int size(TNode n)
	{
		if (n == null) return 0;
		else return 1 + size(n.left) + size(n.right);
	}

	// get the height of the tree
	public int height()
	{
		if (root == null) return -1;
		int h=getLayers(root);
		return h-1;
	}

	// the helper method to get the height
	private int getLayers(TNode n)
	{
		if (n == null) return 0;
		int h1 = getLayers(n.left);
		int h2 = getLayers(n.right);
		if (h1 >= h2) return h1 + 1;
		else return h2 + 1;
	}

	// print the tree
	public void printBSTSet()
	{
		if (root == null) System.out.println("The set is empty");
		else
		{
			System.out.print("The set elements are: ");
			printBSTSet(root);
			System.out.print("\n");
		}
	}

	// a helper method for print tree
	private void printBSTSet(TNode t)
	{
		if (t != null)
		{
			printBSTSet(t.left);
			System.out.print(" " + t.element + ", ");
			printBSTSet(t.right);
		}
	}

	// non-recursive method to print the tree

	

	public void printNonRec()
	{
		MyStack<TNode> stack = new MyStack<>();
		// start from root
		TNode node = root;
		while (node != null || !stack.isEmpty())
		{
			// start from the left-most
			if (node != null)
			{
				// put his father to stack
				stack.push(node);
				// visit the left-most
				node = node.left;
			}
			// now from the left-most to parent
			else
			{
				TNode tem;
				try
				{
					tem = stack.pop();
				}
				catch (Exception e)
				{
					break;
				}
				System.out.print(" " + tem.element + ", ");
				node = tem.right;
			}
		}
		System.out.print("\n");
	}
        
	// print in level order
	public void printLevelOrder()
	{
		if (root == null) return;
		MyQueue<TNode> queue = new MyQueue<TNode>();
		try
		{
			//start from root
			queue.enqueue(root);
			while (!queue.isEmpty())
			{
				TNode node = queue.dequeue();
				// show current
				System.out.print(" " + node.element + ", ");
				// add his children to queue again
				if (node.left != null) queue.enqueue(node.left);
				if (node.right != null) queue.enqueue(node.right);
			}
		}
		catch (Exception e)
		{
		}
	}
	
	//get the root
	TNode getRoot()
	{
		return root;
	}

}
