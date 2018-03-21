package configuration;

public class Configuration {
  /*
   * Attribue ---------------------------
   */
  private String dossierSauvegarde;
  private String unite;
  private String representation;
  private Integer pas;
  private boolean conserver;

  /*
   * Consctructeur ----------------------
   */
  public Configuration() {
    super();
    this.dossierSauvegarde = "Gribs";
    this.unite = "km/h";
    this.representation = "Fleches";
    this.pas = 10;
    this.conserver = true;
  }

  /*
   * Getters et Setteurs ----------------
   */
  public String getDossierSauvegarde() {
    return dossierSauvegarde;
  }

  public void setDossierSauvegarde(String dossierSauvegarde) {
    this.dossierSauvegarde = dossierSauvegarde;
  }

  public String getUnite() {
    return unite;
  }

  public void setUnite(String unite) {
    this.unite = unite;
  }

  public String getRepresentation() {
    return representation;
  }

  public void setRepresentation(String representation) {
    this.representation = representation;
  }

  public Integer getPat() {
    return pas;
  }

  public void setPat(Integer pas) {
    this.pas = pas;
  }

  public boolean isConserver() {
    return conserver;
  }

  public void setConserver(boolean conserver) {
    this.conserver = conserver;
  }

}
