package lab3;

//modified from ArrayQueue in the ppt
public class MyQueue<E>
{
	public static final int CAPACITY = 1000;
	private E[] Q;
	private int front = 0;
	private int end = 0;

	public MyQueue()
	{
		this(CAPACITY);
	}

	public MyQueue(int cap)
	{
		Q = (E[]) new Object[cap];
	}

	public boolean isEmpty()
	{
		return (front == end);
	}

	public int getSize()
	{
		if (front <= end) return (end - front);
		else return (Q.length + end - front);
	}

	public boolean isFull()
	{
		return (getSize() == Q.length - 1);
	}

	public void enqueue(E e) throws Exception
	{
		if (isFull()) throw new Exception("Queue overflow");
		else
		{
			Q[end] = e;
			end = (end + 1) % Q.length;
		}
	}

	public E dequeue() throws Exception
	{
		E e;
		if (isEmpty()) throw new Exception("Error: queue underflow!");
		else
		{
			e = Q[front];
			Q[front] = null; // Explicit Nulling
			if (++front == Q.length) front = 0;
			return (e);
		}
	}
}
