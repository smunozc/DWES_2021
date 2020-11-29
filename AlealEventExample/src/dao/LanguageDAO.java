package dao;

import java.util.List;

import model.Language;

public interface LanguageDAO {
	
	public List<Language> getLanguages();
	public boolean checkLanguage(String language);

}
