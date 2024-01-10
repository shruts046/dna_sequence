/*
 * Copyright 2021 Marc Liberatore.
 */

package sequencer;

import java.util.ArrayList;
import java.util.List;

public class Assembler {

	/**
	 * Creates a new Assembler containing a list of fragments.
	 * 
	 * The list is copied into this assembler so that the original list will not be
	 * modified by the actions of this assembler.
	 * 
	 * @param fragments
	 */

	List<Fragment> Frag = new ArrayList<Fragment>();

	public Assembler(List<Fragment> fragments) {
		for (int i = 0; i < fragments.size(); i++) {

			Frag.add(fragments.get(i));

		}
	}

	/**
	 * Returns the current list of fragments this assembler contains.
	 * 
	 * @return the current list of fragments
	 */
	public List<Fragment> getFragments() {
		return Frag;

	}

	/**
	 * Attempts to perform a single assembly, returning true iff an assembly was
	 * performed.
	 * 
	 * This method chooses the best assembly possible, that is, it merges the two
	 * fragments with the largest overlap, breaking ties between merged fragments by
	 * choosing the shorter merged fragment.
	 * 
	 * Merges must have an overlap of at least 1.
	 * 
	 * After merging two fragments into a new fragment, the new fragment is inserted
	 * into the list of fragments in this assembler, and the two original fragments
	 * are removed from the list.
	 * 
	 * @return true iff an assembly was performed
	 */
	public boolean assembleOnce() {
		int c = 0;

		int max = -1;

		Fragment merge1 = null;

		Fragment merge2 = null;

		Fragment m = null;

		for (Fragment frag1 : Frag) {

			for (Fragment frag2 : Frag) {

				if (frag1 == null || frag2 == null) {

					return false;

				}
				if (frag1 != frag2) {

					c = frag1.calculateOverlap(frag2);

					if (c > max) {

						max = c;

						merge1 = frag1;

						merge2 = frag2;

					}

				}

			}

		}

		if (max > 0) {

			Frag.remove(merge1);

			Frag.remove(merge2);

			m = merge1.mergedWith(merge2);

			Frag.add(m);

			return true;
		} else {

			return false;

		}
	}

	/**
	 * Repeatedly assembles fragments until no more assembly can occur.
	 */
	public void assembleAll() {
		while (assembleOnce()) {

		}
	}
}
