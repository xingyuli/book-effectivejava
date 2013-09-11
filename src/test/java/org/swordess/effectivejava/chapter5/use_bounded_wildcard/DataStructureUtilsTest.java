package org.swordess.effectivejava.chapter5.use_bounded_wildcard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.swordess.effectivejava.chapter5.use_bounded_wildcard.DataStructureUtils.Prefer;

import junit.framework.TestCase;

public class DataStructureUtilsTest extends TestCase {

	public void testSetBothNotEmpty() {
		Set<String> s1 = new HashSet<String>();
		s1.add("today");
		s1.add("now");
		s1.add("ok");
		
		Set<String> s2 = new HashSet<String>();
		s2.add("yesterday");
		s2.add("just now");
		s2.add("ok");
		s2.add("now");

		Set<String> intersection = DataStructureUtils.intersection(s1, s2);
		assertEquals(2, intersection.size());
		assertTrue(intersection.contains("now"));
		assertTrue(intersection.contains("ok"));
		
		Set<String> union = DataStructureUtils.union(s1, s2);
		assertEquals(5, union.size());
		assertTrue(union.contains("today"));
		assertTrue(union.contains("now"));
		assertTrue(union.contains("ok"));
		assertTrue(union.contains("yesterday"));
		assertTrue(union.contains("just now"));
	}
	
	public void testSetWithOneEmpty() {
		Set<String> s1 = null;
		
		Set<String> s2 = new HashSet<String>();
		s2.add("yesterday");
		s2.add("just now");
		s2.add("ok");
		s2.add("now");

		Set<String> intersection = DataStructureUtils.intersection(s1, s2);
		assertTrue(intersection.isEmpty());
		
		Set<String> union = DataStructureUtils.union(s1, s2);
		assertEquals(4, union.size());
		assertTrue(union.contains("yesterday"));
		assertTrue(union.contains("just now"));
		assertTrue(union.contains("ok"));
		assertTrue(union.contains("now"));
	}
	
	public void testListBothNotEmpty() {
		List<String> l1 = new ArrayList<String>();
		l1.add("today");
		l1.add("now");
		l1.add("ok");
		l1.add("today");
		
		List<String> l2 = new ArrayList<String>();
		l2.add("yesterday");
		l2.add("just now");
		l2.add("just now");
		l2.add("ok");
		l2.add("now");
		
		List<String> intersection = DataStructureUtils.intersection(l1, l2);
		assertEquals(2, intersection.size());
		assertTrue(intersection.contains("now"));
		assertTrue(intersection.contains("ok"));
		
		List<String> union = DataStructureUtils.union(l1, l2);
		assertEquals(5, union.size());
		assertTrue(union.contains("today"));
		assertTrue(union.contains("now"));
		assertTrue(union.contains("ok"));
		assertTrue(union.contains("yesterday"));
		assertTrue(union.contains("just now"));
	}
	
	public void testListWithOneEmpty() {
		List<String> l1 = null;
		
		List<String> l2 = new ArrayList<String>();
		l2.add("yesterday");
		l2.add("just now");
		l2.add("just now");
		l2.add("ok");
		l2.add("now");
		
		List<String> intersection = DataStructureUtils.intersection(l1, l2);
		assertTrue(intersection.isEmpty());
		
		List<String> union = DataStructureUtils.union(l1, l2);
		assertEquals(4, union.size());
		assertTrue(union.contains("yesterday"));
		assertTrue(union.contains("just now"));
		assertTrue(union.contains("ok"));
		assertTrue(union.contains("now"));
	}
	
	public void testMapBothNotEmpty() {
		Map<String, String> m1 = new HashMap<String, String>();
		m1.put("aaa", "111");
		m1.put("bbb", "bValue in map1");
		m1.put("ccc", "333");
		m1.put("ddd", "dValue in map1");
		
		Map<String, String> m2 = new HashMap<String, String>();
		m2.put("bbb", "bValue in map2");
		m2.put("ddd", "dValue in map2");
		m2.put("one", "1");
		m2.put("two", "2");
		
		Map<String, String> intersectionPreferFormer = DataStructureUtils.intersection(m1, m2, Prefer.FORMER);
		assertEquals(2, intersectionPreferFormer.size());
		assertTrue(intersectionPreferFormer.containsKey("bbb") && intersectionPreferFormer.get("bbb").equals("bValue in map1"));
		assertTrue(intersectionPreferFormer.containsKey("ddd") && intersectionPreferFormer.get("ddd").equals("dValue in map1"));
		
		Map<String, String> intersectionPreferLater = DataStructureUtils.intersection(m1, m2, Prefer.LATER);
		assertEquals(2, intersectionPreferLater.size());
		assertTrue(intersectionPreferLater.containsKey("bbb") && intersectionPreferLater.get("bbb").equals("bValue in map2"));
		assertTrue(intersectionPreferLater.containsKey("ddd") && intersectionPreferLater.get("ddd").equals("dValue in map2"));
		
		Map<String, String> unionPreferFormer = DataStructureUtils.union(m1, m2, Prefer.FORMER);
		assertEquals(6, unionPreferFormer.size());
		assertTrue(unionPreferFormer.containsKey("aaa") && unionPreferFormer.get("aaa").equals("111"));
		assertTrue(unionPreferFormer.containsKey("bbb") && unionPreferFormer.get("bbb").equals("bValue in map1"));
		assertTrue(unionPreferFormer.containsKey("ccc") && unionPreferFormer.get("ccc").equals("333"));
		assertTrue(unionPreferFormer.containsKey("ddd") && unionPreferFormer.get("ddd").equals("dValue in map1"));
		assertTrue(unionPreferFormer.containsKey("one") && unionPreferFormer.get("one").equals("1"));
		assertTrue(unionPreferFormer.containsKey("two") && unionPreferFormer.get("two").equals("2"));
		
		Map<String, String> unionPreferLater = DataStructureUtils.union(m1, m2, Prefer.LATER);
		assertEquals(6, unionPreferLater.size());
		assertTrue(unionPreferLater.containsKey("aaa") && unionPreferLater.get("aaa").equals("111"));
		assertTrue(unionPreferLater.containsKey("bbb") && unionPreferLater.get("bbb").equals("bValue in map2"));
		assertTrue(unionPreferLater.containsKey("ccc") && unionPreferLater.get("ccc").equals("333"));
		assertTrue(unionPreferLater.containsKey("ddd") && unionPreferLater.get("ddd").equals("dValue in map2"));
		assertTrue(unionPreferLater.containsKey("one") && unionPreferLater.get("one").equals("1"));
		assertTrue(unionPreferLater.containsKey("two") && unionPreferLater.get("two").equals("2"));
	}
	
	public void testMapWithOneEmpty() {
		Map<String, String> m1 = null;
		
		Map<String, String> m2 = new HashMap<String, String>();
		m2.put("bbb", "bValue in map2");
		m2.put("ddd", "dValue in map2");
		m2.put("one", "1");
		m2.put("two", "2");
		
		Map<String, String> intersectionPreferFormer = DataStructureUtils.intersection(m1, m2, Prefer.FORMER);
		assertTrue(intersectionPreferFormer.isEmpty());
		
		Map<String, String> intersectionPreferLater = DataStructureUtils.intersection(m1, m2, Prefer.LATER);
		assertTrue(intersectionPreferLater.isEmpty());
		
		Map<String, String> unionPreferFormer = DataStructureUtils.union(m1, m2, Prefer.FORMER);
		assertEquals(4, unionPreferFormer.size());
		assertTrue(unionPreferFormer.containsKey("bbb") && unionPreferFormer.get("bbb").equals("bValue in map2"));
		assertTrue(unionPreferFormer.containsKey("ddd") && unionPreferFormer.get("ddd").equals("dValue in map2"));
		assertTrue(unionPreferFormer.containsKey("one") && unionPreferFormer.get("one").equals("1"));
		assertTrue(unionPreferFormer.containsKey("two") && unionPreferFormer.get("two").equals("2"));
		
		Map<String, String> unionPreferLater = DataStructureUtils.union(m1, m2, Prefer.LATER);
		assertEquals(4, unionPreferLater.size());
		assertTrue(unionPreferLater.containsKey("bbb") && unionPreferLater.get("bbb").equals("bValue in map2"));
		assertTrue(unionPreferLater.containsKey("ddd") && unionPreferLater.get("ddd").equals("dValue in map2"));
		assertTrue(unionPreferLater.containsKey("one") && unionPreferLater.get("one").equals("1"));
		assertTrue(unionPreferLater.containsKey("two") && unionPreferLater.get("two").equals("2"));
	}
	
}
