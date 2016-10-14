package com.persistence;

public class PersistenceContextFactory {

	public static PersistenceContextInterface getPersistenceContext(String type) {
		return TestingPersistenceContext.getInstance();
	}
}

