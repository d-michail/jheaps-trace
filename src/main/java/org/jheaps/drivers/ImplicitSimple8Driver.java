package org.jheaps.drivers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.jheaps.Heap;
import org.jheaps.array.DaryArrayHeap;

public class ImplicitSimple8Driver {

	public static void main(String args[]) {
		String filename = args[0];

		try {
			// create heap
			Heap<Double> heap = new DaryArrayHeap<>(8);

			// driver
			try (Stream<String> stream = Files.lines(Paths.get(filename))) {
				DriverUtils.playHeap(stream.iterator(), heap);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
