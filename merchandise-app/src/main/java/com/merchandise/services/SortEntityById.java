package com.merchandise.services;

import java.util.Comparator;

import com.merchandise.entities.Merchandise;

public class SortEntityById implements Comparator<Merchandise>{

	@Override
	public int compare(Merchandise m1, Merchandise m2) {
		return m1.getPartnerId() - m2.getPartnerId();
	}



}
