package org.jheaps.drivers;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.jheaps.AddressableHeap;

public class NullHeapDriver {

	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
		String filename = args[0];

		try {
			// create array
			int maxIndex = DriverUtils.readMaxIndex(filename);
			AddressableHeap.Handle<Double, Integer>[] handles = null;
			if (maxIndex > 0) {
				handles = (AddressableHeap.Handle[]) Array.newInstance(AddressableHeap.Handle.class, maxIndex + 1);
			}

			// driver
			try (Stream<String> stream = Files.lines(Paths.get(filename))) {
				DriverUtils.playNull(stream.iterator(), handles);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
