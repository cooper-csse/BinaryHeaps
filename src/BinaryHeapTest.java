import java.util.Arrays;
import java.util.PriorityQueue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests binary heaps.
 * 
 * @author Matt Boutell. Created May 7, 2013.
 */
public class BinaryHeapTest {
	private BinaryHeap<Integer> heap1 = new BinaryHeap<>(Integer.class);
	private BinaryHeap<Integer> heap2 = new BinaryHeap<>(Integer.class);

	@Before
	public void setupHeaps() {
		for (int i = 0; i < 25; i++) this.heap1.insert(i); // Add items to heap1 in order
		for (int i = 0; i < 25; i++) this.heap2.insert(24 - i); // Add items to heap2 in reverse order
	}

	/**
	 * Simple test method for insert, delete, toString, and sort. Enforces the
	 * method signatures.
	 */
	@Test
	public void testSimple() {
		BinaryHeap<Integer> heap = new BinaryHeap<>(Integer.class);
		
		// deleteMin returns null if it has no elements.
		assertNull(heap.deleteMin());
		heap.insert(21);
		assertEquals("[null, 21]", heap.toString());
		int min = heap.deleteMin();
		assertEquals(21, min);
		assertEquals("[null]", heap.toString());
		String[] csLegends = new String[] { "Edsger Dijkstra", "Grace Hopper", "Donald Knuth", "Ada Lovelace",
				"Claude Shannon", "Alan Turing" };
		BinaryHeap.sort(csLegends, String.class);
		assertEquals("[Ada Lovelace, Alan Turing, Claude Shannon, Donald Knuth, Edsger Dijkstra, Grace Hopper]",
				Arrays.toString(csLegends));
	}


	/**
	 * Test to see if .insert() is adding new items into the correct locations
	 */
	@Test
	public void testInsert() {
		Integer[] array = {0, 3, 1, 8, 4, 2, 12, 15, 9, 7, 5, 11, 19, 20, 13, 24, 18, 21, 10, 22, 16, 17, 6, 23, 14};

		// Look at items in heap2's (reversed inserts) array to check if they are in order
		for (int i = 0; i < 25; i++) assertEquals("at index " + i, array[i], this.heap2.array[i + 1]);
	}

	/**
	 * Test to see if the size variable is updating correctly after each insert
	 */
	@Test
	public void testSizeAfterInsert() {
		assertEquals(25, this.heap1.size);
		assertEquals(25, this.heap2.size);
	}

	/**
	 * Test to see how many times the array must resize to accommodate for new items
	 */
	@Test
	public void testResizeCount() {
		assertEquals(2, this.heap1.resizeCount);
		assertEquals(2, this.heap2.resizeCount);
	}

	/**
	 * Test to see how many times items swap around in the array
	 */
	@Test
	public void testSwapCount() {
		assertEquals(0, this.heap1.swapCount);
		assertEquals(74, this.heap2.swapCount);
	}

	/**
	 * Test to see if there are any discontinuities in the array
	 */
	@Test
	public void testEmptySlots() {
		for (int i = 0; i < 25; i++) {
			assertNotNull(this.heap1.array[i + 1]);
			assertNotNull(this.heap2.array[i + 1]);
		}
	}

	/**
	 * Make sure all parents are less than their children
	 */
	@Test
	public void testCheckInOrder() {
		for (int i = 1; i < 13; i++) {
			assertTrue("at index " + i + " in heap1", this.heap1.array[i] < Math.min(this.heap1.array[i * 2], this.heap1.array[i * 2 + 1]));
			assertTrue("at index " + i + " in heap2", this.heap2.array[i] < Math.min(this.heap2.array[i * 2], this.heap2.array[i * 2 + 1]));
		}
	}

	/**
	 * Test to see if the .deleteMin() method correctly removes the first item and sorts the array.
	 */
	@Test
	public void testDelete() {
		for (int i = 0; i < 25; i++) {
			assertEquals(i, (int) heap1.deleteMin());
		}
		BinaryHeap<Integer> heap = new BinaryHeap<>(Integer.class);
		PriorityQueue<Integer> queue = new PriorityQueue<>(heap.capacity);

		assertNull("deleteMin on empty heap", heap.deleteMin());

		for (int i = 0; i < 25; i++) {
			int num = (int) Math.floor(Math.random() * 100);
			heap.insert(num);
			queue.add(num);
		}

		for (int i = 0; i < 24; i++) {
			assertNotNull("at iteration " + i, heap.array[1]);
			assertEquals("at index " + i, queue.remove(), heap.deleteMin());
		}
	}

	/**
	 * Check if repeatedly inserting and deleting updates the swapCount to the correct value
	 */
	@Test
	public void testRepeatedInsertAndDelete() {
		for (int i = 0; i < 25; i++) {
			assertEquals(0, (int) heap2.deleteMin());
			heap2.insert(0);
			assertEquals(81 + i * 7, heap2.swapCount);
		}
	}

	/**
	 * Make sure all parents are less than their children after deleting several times
	 */
	@Test
	public void testCheckInOrderAfterDelete() {
		for (int i = 0; i < 10; i++) {
			assertEquals(i, (int) this.heap1.deleteMin());
			assertEquals(i, (int) this.heap2.deleteMin());
		}
		for (int i = 1; i <= heap1.size / 2; i++) {
			assertTrue("after delete at index " + i + " in heap1", this.heap1.array[i] < Math.min(this.heap1.array[i * 2], this.heap1.array[i * 2 + 1]));
			assertTrue("after delete at index " + i + " in heap2", this.heap2.array[i] < Math.min(this.heap2.array[i * 2], this.heap2.array[i * 2 + 1]));
		}
	}

	/**
	 * Check if the sort method accurately sorts any Comparable[]
	 */
	@Test
	public void testHeapSort() {
		Integer[] numbers = {5, 2, 7, 6, 1, 8, 3, 9, 4, 0};
		String[] letters = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p"};
		BinaryHeap.sort(numbers);
		assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", Arrays.toString(numbers));
		BinaryHeap.sort(letters);
		assertEquals("[e, i, o, p, q, r, t, u, w, y]", Arrays.toString(letters));

		// Run a shuffle and test order 100 times
		Integer[] numbersBig = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		for (int i = 0; i < 100; i++) {
			shuffleArray(numbersBig);
			BinaryHeap.sort(numbersBig);
			assertTrue(isSorted(numbersBig));
		}
	}

	private static boolean isSorted(Comparable[] array) {
		Comparable last = array[0];
		for (Comparable item : array) { if (item.compareTo(last) < 0) return false; last = item; }
		return true;
	}

	private static void shuffleArray(Integer[] array) {
		java.util.Random random = new java.util.Random();
		for (int i = array.length - 1; i > 0; i--) {
			int index = random.nextInt(i + 1);
			if (index != i) {
				array[index] ^= array[i];
				array[i] ^= array[index];
				array[index] ^= array[i];
			}
		}
	}
}
