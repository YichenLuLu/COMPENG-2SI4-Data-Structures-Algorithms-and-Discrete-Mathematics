package lab3;
//modified from ArrayStack in the ppt
public class MyStack<E>
{
	private E[] items;
	private int topIndex = -1;

	public MyStack()
	{
		this(10000);
	}

	public MyStack(int n)
	{
		items = (E[]) new Object[n];
	}

	public boolean isEmpty()
	{
		return (topIndex < 0);
	}

	public void push(E e)
	{
		if (topIndex == items.length - 1)
		{
			E[] newArray = (E[]) new Object[2 * items.length];
			for (int i = 0; i < items.length; i++)
				newArray[i] = items[i];
			items = newArray;
		} // end if
		items[++topIndex] = e;
	}

	public E top() throws Exception
	{
		if (isEmpty()) throw new Exception("Stack underflow");
		else return (items[topIndex]);
	}

	public E pop() throws Exception
	{
		if (isEmpty())
		{
			throw new Exception("Stack underflow");
		}
		else
		{
			E tempE = items[topIndex];
			items[topIndex--] = null;
			return (tempE);
		}
	}
}
