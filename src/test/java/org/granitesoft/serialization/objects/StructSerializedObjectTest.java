package org.granitesoft.serialization.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

import collections.immutable.ImmCollections;

public class StructSerializedObjectTest {

	@Test
	public void testEquals() {
        Serialized so = SerializedFactory.object(ImmCollections.asMap("x", SerializedFactory.of(6)));
		assertEquals(so, so);
		assertNotEquals(so, null);
		assertNotEquals(so, "");
		assertNotEquals(so, SerializedFactory.object(ImmCollections.asMap("x", SerializedFactory.of(5))));
		assertNotEquals(so, SerializedFactory.object());
		assertNotEquals(SerializedFactory.object(), so);
		assertEquals(so, SerializedFactory.object(ImmCollections.asMap("x", SerializedFactory.of(6))));
	}
	@Test
	public void testHashCode() {
        Serialized so = SerializedFactory.object(ImmCollections.asMap("x", SerializedFactory.of(6)));
		HashSet<Serialized> h = new HashSet<>();
		h.add(so);
		assertTrue(h.contains(so));
	}
	@Test
	public void testAddAll() {
        Serialized so = SerializedFactory.object(ImmCollections.asMap("x", SerializedFactory.of(6))
                .put("y", SerializedFactory.of("6"))
                .put("z", SerializedFactory.of(true))
                .put("A", SerializedFactory.of("1")));
		HashSet<Serialized> h = new HashSet<>();
		h.add(so);
		Serialized so2 = SerializedFactory.object(ImmCollections.asMap("x", SerializedFactory.of(6))
                .put("y", SerializedFactory.of("6"))
                .put("z", SerializedFactory.of(true))
                .put("A", SerializedFactory.of("1")));
		assertTrue(h.contains(so2));
	}
	@Test
	public void testIsNull() {
		assertFalse(SerializedFactory.object().isNull());
	}
	@Test
	public void testToString() {
		assertEquals(SerializedFactory.object(ImmCollections.asMap("x", SerializedFactory.of(6))).toString(), "{x=6}");
		assertEquals(SerializedFactory.object(ImmCollections.asMap("x", SerializedFactory.of(6), "y", SerializedFactory.of(7))).toString(), "{x=6, y=7}");
		assertEquals(SerializedFactory.object().toString(), "{}");
	}
	@Test
	public void testExceptions() {
		Serialized so = SerializedFactory.object();
		assertThrows(UnsupportedOperationException.class, () -> so.asAtom());
 	}
}
