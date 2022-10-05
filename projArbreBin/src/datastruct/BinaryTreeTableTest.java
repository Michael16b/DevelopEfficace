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
		tree = new BinaryTreeTable<>();
		//fail("Not yet implemented");
	}

	@Test
	public void testSelect() {
		tree = new BinaryTreeTable<>();
		for (int i = 0; i < 10; i++) {
			tree.insert(i, "test"+i);
		}
		for (int i = 0; i < 10; i++) {
			if (tree.select(i) != "test"+i) {
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
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowTree() {
		fail("Not yet implemented");
	}

}
