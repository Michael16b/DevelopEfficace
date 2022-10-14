package datastruct;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class BinaryTreeTableTest {
	static BinaryTreeTable<Integer, String> tree = new BinaryTreeTable<>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tree = new BinaryTreeTable<>();
		
	}

	@Test
	public void testBinaryTreeTable() {
		// Sonarlint -> Instance methods should not write to "static" fields (java:S2696)
		System.out.println("testBinaryTreeTable");
		tree = new BinaryTreeTable<>();
		assertThrows(IllegalArgumentException.class, () -> tree.select(null));
		System.out.println("testBinaryTreeTable : OK");
	}

	@Test
	public void testSelect() {
		System.out.println("testSelect");
		tree = new BinaryTreeTable<>();
		for (int i = 0; i < 10; i++) {
			tree.insert(i, "test"+i);
		}
		for (int i = 0; i < 10; i++) {
			if (!tree.select(i).equals("test" + i)) {
				fail("testSelect isn't implemented correctly");
			}
		}
		System.out.println("testSelect : OK");
	}

	@Test
	public void testInsert() {
		System.out.println("testInsert");
		tree = new BinaryTreeTable<>();
		for (int i = 0; i < 10; i++) {
			if (!tree.insert(i, "test"+i)) {
				fail("testInsert isn't implemented correctly");
			}
		}
		if (tree.insert(1, "a")) {
			fail("testInsert isn't implemented correctly");
		}
		assertThrows(IllegalArgumentException.class, () -> tree.insert(null, "a"));
		System.out.println("testInsert : OK");
	}

	@Test
	public void testDelete() {
		System.out.println("testDelete");
		tree = new BinaryTreeTable<>();
		for (int i = 0; i < 10; i++) {
			tree.insert(i, "test" + i);
		}
		for (int i = 0; i < 10; i++) {
			if (!tree.delete(i)) {
				fail("testDelete isn't implemented correctly");
			}
		}
		if (tree.delete(1)) {
			fail("testDelete isn't implemented correctly");
		}
		System.out.println("testDelete : OK");
	}
	@Test
	public void testToString() {
		System.out.println("testToString");
		tree = new BinaryTreeTable<>();
		for (int i = 0; i < 10; i++) {
			tree.insert(i, "test" + i);
		}

		StringBuilder expected = new StringBuilder("");

		for (int i = 0; i < 10; i++) {
			expected.append("\nclé=").append(i).append("\tdata=test").append(i);
		}

		if (!tree.toString().equals(expected.toString())) {
			fail("testToString isn't implemented correctly");
		}
		System.out.println("testToString : OK");
	}

	@Test
	public void testClone() {
		System.out.println("testClone");
		tree = new BinaryTreeTable<>();
		for (int i = 0; i < 10; i++) {
			tree.insert(i, "test" + i);
		}
		BinaryTreeTable<Integer, String> tree2 = tree.clone();
		System.out.println(tree2);

		for (int i = 0; i < 10; i++) {
			if (!tree2.select(i).equals(tree.select(i))) {
				fail("testClone isn't implemented correctly");
			}
		}
		System.out.println("testClone : OK");
	}

	@Test
	public void testShowTree() {
		System.out.println("testShowTree");
		for (int i = 0; i < 10; i++) {
			tree.insert(i, "test" + i);
		}
		tree.showTree();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("testShowTree : OK");
	}

}
