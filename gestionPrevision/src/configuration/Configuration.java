package configuration;

public class Configuration {
  /*
   * Attribue ---------------------------
   */
  private String dossierSauvegardeGrib;
  private String dossierSauvegardeJson;
  private String unite;
  private String representation;
  private Integer pas;
  private boolean conserver;

  /*
   * Consctructeur ----------------------
   */
  public Configuration() {
    super();
    this.dossierSauvegardeGrib = "Gribs";
    this.dossierSauvegardeJson = "Gribs";
    this.unite = "km/h";
    this.representation = "Fleches";
    this.pas = 10;
    this.conserver = true;
  }

  /*
   * Getters et Setteurs ----------------
   */

  public String getUnite() {
    return unite;
  }

  public String getDossierSauvegardeGrib() {
    return dossierSauvegardeGrib;
  }

  public void setDossierSauvegardeGrib(String dossierSauvegardeGrib) {
    this.dossierSauvegardeGrib = dossierSauvegardeGrib;
  }

  public String getDossierSauvegardeJson() {
    return dossierSauvegardeJson;
  }

  public void setDossierSauvegardeJson(String dossierSauvegardeJson) {
    this.dossierSauvegardeJson = dossierSauvegardeJson;
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
