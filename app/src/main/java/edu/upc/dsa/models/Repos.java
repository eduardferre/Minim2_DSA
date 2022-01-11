package edu.upc.dsa.models;

public class Repos {

    private String nameRepo;
    private String codeLanguage;

    /** Constructor **/
    public Repos(String nameRepo, String codeLanguage) {
        this.nameRepo = nameRepo;
        this.codeLanguage = codeLanguage;
    }

    public String getNameRepo() {
        return nameRepo;
    }

    public void setNameRepo(String nameRepo) {
        this.nameRepo = nameRepo;
    }

    public String getCodeLanguage() {
        return codeLanguage;
    }

    public void setCodeLanguage(String codeLanguage) {
        this.codeLanguage = codeLanguage;
    }

    public Repos(){};
}
