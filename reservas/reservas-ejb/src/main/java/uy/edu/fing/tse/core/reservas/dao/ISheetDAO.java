package uy.edu.fing.tse.core.reservas.dao;

public interface ISheetDAO {

	void createSession(String sheetId, String hash);
	String getSession(String sheetId);

}
