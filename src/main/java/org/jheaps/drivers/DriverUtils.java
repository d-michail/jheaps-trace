package org.jheaps.drivers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

import org.jheaps.AddressableHeap;
import org.jheaps.AddressableHeap.Handle;
import org.jheaps.Heap;

public class DriverUtils {

	public static int readMaxIndex(String filename) throws IOException {
		try (Stream<String> stream = Files.lines(Paths.get(filename))) {
			Iterator<String> it = stream.iterator();
			while (it.hasNext()) {
				String line = it.next();
				String[] words = line.split("\\s+");
				switch (words[0]) {
				case "pqh":
					int maxIndex = Integer.valueOf(words[1]);
					return maxIndex;
				case "com":
					break;
				default:
					return 0;
				}
			}
		}
		return 0;
	}

	public static void play(Iterator<String> linesIt, AddressableHeap<Double, Integer> heap,
			AddressableHeap.Handle<Double, Integer>[] handles) {

		long start = System.currentTimeMillis();

		while (linesIt.hasNext()) {
			String line = linesIt.next();
			String[] words = line.split("\\s+");
			switch (words[0]) {
			case "pqh":
				break;
			case "com":
				break;
			case "ins": {
				double key = Double.valueOf(words[1]);
				int index = 0;
				if (words.length > 2) {
					index = Integer.valueOf(words[2]);
				}
				Handle<Double, Integer> newHandle = heap.insert(key);
				if (index > 0) {
					handles[index] = newHandle;
				}
				break;
			}
			case "fmn":
				heap.findMin();
				break;
			case "dmn":
				heap.deleteMin();
				break;
			case "dcr": {
				double key = Double.valueOf(words[1]);
				int index = Integer.valueOf(words[2]);
				handles[index].decreaseKey(key);
				break;
			}
			case "prv": {
				int index = Integer.valueOf(words[1]);
				handles[index].getKey();
				break;
			}
			case "inv": {
				int index = Integer.valueOf(words[1]);
				handles[index].getValue();
				break;
			}
			case "siz": {
				heap.size();
				break;
			}
			case "clr": {
				heap.clear();
				break;
			}
			default:
				throw new IllegalArgumentException("Failed to parse unknown driver directive:" + words[0]);
			}
			;
		}

		long end = System.currentTimeMillis();
		long millis = (end - start);
		System.out.println("Time: " + millis + " (ms)");
	}

	public static void playHeap(Iterator<String> linesIt, Heap<Double> heap) {

		long start = System.currentTimeMillis();

		while (linesIt.hasNext()) {
			String line = linesIt.next();
			String[] words = line.split("\\s+");
			switch (words[0]) {
			case "pqh":
				break;
			case "com":
				break;
			case "ins": {
				double key = Double.valueOf(words[1]);
				heap.insert(key);
				break;
			}
			case "fmn":
				heap.findMin();
				break;
			case "dmn":
				heap.deleteMin();
				break;
			case "siz": {
				heap.size();
				break;
			}
			case "clr": {
				heap.clear();
				break;
			}
			default:
				throw new IllegalArgumentException("Failed to parse unknown driver directive:" + words[0]);
			}
			;
		}

		long end = System.currentTimeMillis();
		long millis = (end - start);
		System.out.println("Time: " + millis + " (ms)");
	}
	
	public static void playNull(Iterator<String> linesIt, AddressableHeap.Handle<Double, Integer>[] handles) {

		long start = System.currentTimeMillis();

		while (linesIt.hasNext()) {
			String line = linesIt.next();
			String[] words = line.split("\\s+");
			switch (words[0]) {
			case "pqh":
				break;
			case "com":
				break;
			case "ins": {
				double key = Double.valueOf(words[1]);
				int index = 0;
				if (words.length > 2) {
					index = Integer.valueOf(words[2]);
				}
				if (index > 0) {
					handles[index] = null;
				}
				break;
			}
			case "fmn":
				break;
			case "dmn":
				break;
			case "dcr": {
				double key = Double.valueOf(words[1]);
				int index = Integer.valueOf(words[2]);
				handles[index] = null;
				break;
			}
			case "prv": {
				int index = Integer.valueOf(words[1]);
				handles[index] = null;
				break;
			}
			case "inv": {
				int index = Integer.valueOf(words[1]);
				handles[index] = null;
				break;
			}
			case "siz": {
				break;
			}
			case "clr": {
				break;
			}
			default:
				throw new IllegalArgumentException("Failed to parse unknown driver directive:" + words[0]);
			}
			;
		}

		long end = System.currentTimeMillis();
		long millis = (end - start);
		System.out.println("Time: " + millis + " (ms)");
	}
	
	public static void playTwoHeaps(Iterator<String> linesIt, AddressableHeap<Double, Integer> heap1, AddressableHeap<Double, Integer> heap2,
			AddressableHeap.Handle<Double, Integer>[] handles1, AddressableHeap.Handle<Double, Integer>[] handles2) {

		long start = System.currentTimeMillis();

		while (linesIt.hasNext()) {
			String line = linesIt.next();
			String[] words = line.split("\\s+");
			switch (words[0]) {
			case "pqh":
				break;
			case "com":
				break;
			case "ins": {
				double key = Double.valueOf(words[1]);
				int index = 0;
				if (words.length > 2) {
					index = Integer.valueOf(words[2]);
				}
				Handle<Double, Integer> newHandle1 = heap1.insert(key);
				if (index > 0) {
					handles1[index] = newHandle1;
				}
				Handle<Double, Integer> newHandle2 = heap2.insert(key);
				if (index > 0) {
					handles2[index] = newHandle2;
				}
				break;
			}
			case "fmn":
				Handle<Double, Integer> min1 = heap1.findMin();
				Handle<Double, Integer> min2 = heap2.findMin();
				if (!min1.getKey().equals(min2.getKey())) { 
					System.out.println("Heap 1 thinks minimum is " + min1.getKey());
					System.out.println("Heap 2 thinks minimum is " + min2.getKey());
					throw new RuntimeException("Bug found");
				}
				break;
			case "dmn":
				Handle<Double, Integer> elem1 = heap1.deleteMin();
				Handle<Double, Integer> elem2 = heap2.deleteMin();
				if (!elem1.getKey().equals(elem2.getKey())) {
					System.out.println("Heap 1 returned after dmn is " + elem1.getKey());
					System.out.println("Heap 2 returned after dmn is " + elem2.getKey());
					throw new RuntimeException("Bug found");
				}
				break;
			case "dcr": {
				double key = Double.valueOf(words[1]);
				int index = Integer.valueOf(words[2]);
				handles1[index].decreaseKey(key);
				handles2[index].decreaseKey(key);
				break;
			}
			case "prv": {
				int index = Integer.valueOf(words[1]);
				handles1[index].getKey();
				handles2[index].getKey();
				break;
			}
			case "inv": {
				int index = Integer.valueOf(words[1]);
				handles1[index].getValue();
				handles2[index].getValue();
				break;
			}
			case "siz": {
				heap1.size();
				heap2.size();
				break;
			}
			case "clr": {
				heap1.clear();
				heap2.clear();
				break;
			}
			default:
				throw new IllegalArgumentException("Failed to parse unknown driver directive:" + words[0]);
			}
			;
		}

		long end = System.currentTimeMillis();
		long millis = (end - start);
		System.out.println("Time: " + millis + " (ms)");
	}

}
