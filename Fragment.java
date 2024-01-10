/*
 * Copyright 2021 Marc Liberatore.
 */

package sequencer;

public class Fragment {
	private String nuc;

	/**
	 * Creates a new Fragment based upon a String representing a sequence of
	 * nucleotides, containing only the uppercase characters G, C, A and T.
	 * 
	 * @param nucleotides
	 * @throws IllegalArgumentException if invalid characters are in the sequence of
	 *                                  nucleotides
	 */
	public Fragment(String nucleotides) throws IllegalArgumentException {
		for (int i = 0; i < nucleotides.length(); i++) {
			if (nucleotides.charAt(i) == 'G' || nucleotides.charAt(i) == 'C' || nucleotides.charAt(i) == 'A'
					|| nucleotides.charAt(i) == 'T') {

			} else {
				throw new IllegalArgumentException("invalid nucleotides");
			}
		}
		nuc = nucleotides;
	}

	/**
	 * Returns the length of this fragment.
	 * 
	 * @return the length of this fragment
	 */
	public int length() {
		return nuc.length();
	}

	/**
	 * Returns a String representation of this fragment, exactly as was passed to
	 * the constructor.
	 * 
	 * @return a String representation of this fragment
	 */
	@Override
	public String toString() {
		return nuc;
	}

	/**
	 * Return true if and only if this fragment contains the same sequence of
	 * nucleotides as another sequence.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (!(o instanceof Fragment)) {
			return false;
		}

		Fragment f = (Fragment) o;
		if (this.nuc.equals(f.nuc)) {
			return true;
		}
		// Don't unconditionally return false; check that
		// the relevant instances variables in this and f
		// are semantically equal
		return false;
	}

	/**
	 * Returns the number of nucleotides of overlap between the end of this fragment
	 * and the start of another fragment, f.
	 * 
	 * The largest overlap is found, for example, CAA and AAG have an overlap of 2,
	 * not 1.
	 * 
	 * @param f the other fragment
	 * @return the number of nucleotides of overlap
	 */
	public int calculateOverlap(Fragment f) {
		int count = 0;
		int maxCount = 0;
		for (int i = 0; i < f.nuc.length(); i++) {
			if (this.nuc.endsWith(f.nuc.substring(0, f.nuc.length() - i))) {
				count = f.nuc.length() - i;
				if (count > maxCount) {
					maxCount = count;
				}
			}
		}

		return maxCount;
	}

	/**
	 * Returns a new fragment based upon merging this fragment with another fragment
	 * f.
	 * 
	 * This fragment will be on the left, the other fragment will be on the right;
	 * the fragments will be overlapped as much as possible during the merge.
	 * 
	 * @param f the other fragment
	 * @return a new fragment based upon merging this fragment with another fragment
	 */
	public Fragment mergedWith(Fragment f) {
		int overlap;
		overlap = calculateOverlap(f);
		String string1, string2;
		string1 = "";
		string2 = "";
		if (this.nuc.substring(this.nuc.length() - overlap)
				.equals(f.nuc.substring(0, overlap))) {
			string1 = this.nuc + f.nuc.substring(overlap);

		}
		if (f.nuc.substring(f.nuc.length() - overlap).equals(this.nuc.substring(0, overlap))) {
			string2 = f.nuc + this.nuc.substring(overlap);

		}
		Fragment fragment = null;

		if (string1.length() != 0 && string2.length() != 0) {
			if (string1.length() < string2.length()) {
				fragment = new Fragment(string2);
			} else {
				fragment = new Fragment(string1);
			}
		} else {
			if (string1.length() == 0)
				fragment = new Fragment(string2);
			else
				fragment = new Fragment(string1);

		}
		return fragment;
	}
}
