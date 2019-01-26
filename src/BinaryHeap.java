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

	/**
	 * Add a new item to the heap. Automatically gets put in the correct location.
	 * @param item the new item being added to the heap
	 */
	public void insert(T item) {
		if (this.size >= this.capacity) {
			this.capacity *= 2;
			T[] array = (T[]) Array.newInstance(this.classType, this.capacity + 1);
			System.arraycopy(this.array, 1, array, 1, this.size);
			this.resizeCount++;
			this.array = array;
		}
		int index = this.size + 1;
		this.array[index] = item;
		this.size++;
		while (index > 1 && this.array[index / 2].compareTo(item) > 0) {
			this.array[index] = this.array[index / 2];
			this.array[index / 2] = item;
			this.swapCount++;
			index /= 2;
		}
	}

	public T deleteMin() {
		if (this.size == 0) return null;
		T item = this.array[1];
		int index = 1;
		T last = this.array[this.size];
		this.array[index] = last;
		this.array[this.size] = null;
		while (index <= (this.size-1) / 2) {
			T left = this.array[index * 2];
			T right = this.array[index * 2 + 1];
			if (right == null) {
				if (last.compareTo(left) > 0) {
					this.array[index] = left;
					this.array[index * 2] = last;
					this.swapCount ++;
				}
				break;
			}
			if (last.compareTo(left) < 0 && last.compareTo(right) < 0) break;
			else if (left.compareTo(right) < 0) {
				this.array[index] = left;
				this.array[index * 2] = last;
				this.swapCount ++;
				index *= 2;
			} else {
				this.array[index] = right;
				this.array[index * 2 + 1] = last;
				this.swapCount ++;
				index = index * 2 + 1;
			}
		}
		this.size--;
		return item;
	}

	public String toString() {
		String output = "null" + (this.size != 0 ? ", " : "");
		for (int i = 0; i < this.size; i++) output += this.array[i + 1] + (i + 1 != this.size ? ", " : "");
		return "[" + output + "]";
	}

	public static void sort(Comparable[] array, Class<?> classType) {

	}
}
