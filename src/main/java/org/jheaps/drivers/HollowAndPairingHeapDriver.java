package org.jheaps.drivers;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.jheaps.AddressableHeap;
import org.jheaps.dag.HollowHeap;
import org.jheaps.tree.PairingHeap;

public class HollowAndPairingHeapDriver {

	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
		String filename = "/tmp/bug"; //args[0];

		try {
			// create array
			int maxIndex = DriverUtils.readMaxIndex(filename);
			AddressableHeap.Handle<Double, Integer>[] handles1 = null;
			AddressableHeap.Handle<Double, Integer>[] handles2 = null;
			if (maxIndex > 0) {
				handles1 = (AddressableHeap.Handle[]) Array.newInstance(AddressableHeap.Handle.class, maxIndex + 1);
				handles2 = (AddressableHeap.Handle[]) Array.newInstance(AddressableHeap.Handle.class, maxIndex + 1);
			}

			// create heaps
			AddressableHeap<Double, Integer> heap1 = new PairingHeap<>();
			AddressableHeap<Double, Integer> heap2 = new HollowHeap<>();

			// driver
			try (Stream<String> stream = Files.lines(Paths.get(filename))) {
				DriverUtils.playTwoHeaps(stream.iterator(), heap1, heap2, handles1, handles2);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
