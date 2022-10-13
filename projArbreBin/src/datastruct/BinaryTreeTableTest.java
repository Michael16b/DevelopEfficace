package datastruct;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Objects;

public class BinaryTreeTableTest {
	static BinaryTreeTable<Integer, String> tree = new BinaryTreeTable<>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tree = new BinaryTreeTable<>();
		
	}

	@Test
	public void testBinaryTreeTable() {
		// Sonarlint -> Instance methods should not write to "static" fields (java:S2696)
		tree = new BinaryTreeTable<>();
		assertThrows(IllegalArgumentException.class, () -> tree.select(null));
	}

	@Test
	public void testSelect() {
		tree = new BinaryTreeTable<>();
		for (int i = 0; i < 10; i++) {
			tree.insert(i, "test"+i);
		}
		for (int i = 0; i < 10; i++) {
			if (!tree.select(i).equals("test" + i)) {
				fail("testSelect isn't implemented correctly");
			}
		}
	}

	@Test
	public void testInsert() {
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
	}

	@Test
	public void testDelete() {
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
	}
	@Test
	public void testToString() {
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
	}

	@Test
	public void testClone() {
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
	}

	@Test
	public void testShowTree() {
		fail("Not yet implemented");
	}

}
