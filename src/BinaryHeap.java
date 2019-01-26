import java.lang.reflect.Array;

public class BinaryHeap<T extends Comparable<? super T>> {
	public static int initialCapacity = 8;

	public T[] array;
	public Class<?> classType;
	public int size;
	public int capacity;
	public int resizeCount;
	public int swapCount;

	public BinaryHeap(Class<?> classType) {
		this.capacity = BinaryHeap.initialCapacity;
		this.classType = classType;
		this.array = (T[]) Array.newInstance(this.classType, this.capacity + 1);
	}

	public void insert(T item) {

	}

	public T deleteMin() {
		return null;
	}

	public String toString() {
		return "";
	}

	public static void sort(Comparable[] array, Class<?> classType) {

	}
}
