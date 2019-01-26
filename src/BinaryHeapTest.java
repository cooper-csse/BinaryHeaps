import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests binary heaps.
 * 
 * @author Matt Boutell. Created May 7, 2013.
 */
public class BinaryHeapTest {
	BinaryHeap<Integer> heap1 = new BinaryHeap<>(Integer.class);
	BinaryHeap<Integer> heap2 = new BinaryHeap<>(Integer.class);

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
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Integer.class);
		
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
		BinaryHeap<Integer> heap = new BinaryHeap<>(Integer.class);
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
}
