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

	@Test
	public void testInsert() {
		BinaryHeap<Integer> heap = new BinaryHeap<>(Integer.class);
		Integer[] array = {0, 3, 1, 8, 4, 2, 12, 15, 9, 7, 5, 11, 19, 20, 13, 24, 18, 21, 10, 22, 16, 17, 6, 23, 14};

		// Look at items in heap2's (reversed inserts) array to check if they are in order
		for (int i = 0; i < 25; i++) assertEquals("at index " + i, array[i], this.heap2.array[i + 1]);
	}

	@Test
	public void testResizeCount() {
		assertEquals(2, this.heap1.resizeCount);
		assertEquals(2, this.heap2.resizeCount);
	}

	@Test
	public void testSwapCount() {
		assertEquals(0, this.heap1.swapCount);
		assertEquals(74, this.heap2.swapCount);
	}