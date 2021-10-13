
public class LinkedStack<T> implements StackADT<T> {

	private List<T> list = new List<T>();
	
	@Override
	public void push(T element) {
		list.addFirst(element);

	}

	@Override
	public T pop() {
		if(this.isEmpty()) {
			System.out.println("There is nothing to pop...List is empty.");
			return null;
		}else {
			list.delete(0);
			return null;
		}
	}

	@Override
	public T peek() {
		if(this.isEmpty()) {
			System.out.println("There is nothing to peek...List is empty.");
			return null;
		}else {
			return list.getFirst();
		}
	}

	@Override
	public boolean isEmpty() {
		if(list.length() == 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int size() {
		return list.length();
	}
	
}
