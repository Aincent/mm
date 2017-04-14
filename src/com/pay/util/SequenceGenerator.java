package com.pay.util;

public class SequenceGenerator {

	private static int seqId = 0;

	public static synchronized int nextSequence() {
		if (seqId == 999) {
			seqId = 0;
		}

		return seqId++;
	}

}
