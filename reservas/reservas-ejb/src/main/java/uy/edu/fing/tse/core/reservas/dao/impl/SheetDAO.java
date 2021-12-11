package uy.edu.fing.tse.core.reservas.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import uy.edu.fing.tse.core.reservas.dao.ISheetDAO;

@Stateless
public class SheetDAO implements ISheetDAO{

	private static Map<String, String> sheetRepository = new HashMap<>();

	@Override
	public void createSession(String sheetId, String hash) {
		sheetRepository.put(sheetId, hash);
	}

	@Override
	public String getSession(String sheetId) {
		return sheetRepository.get(sheetId);
	}

}
