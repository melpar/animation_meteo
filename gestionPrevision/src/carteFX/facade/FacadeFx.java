package carteFX.facade;

import configuration.Configuration;
import edition.implementation.Edition;
import modification.ModifierImpl;
import previsionVents.ZonePrevision;

public class FacadeFx {

  /*
   * Singleton ---------------------------
   */

  static private FacadeFx facade;

  private FacadeFx() {
    super();
    /*
     * Initialisation de l'objet Configuration
     */
    this.modifier = new ModifierImpl();
    this.configuration = new Configuration();
  }

  static public FacadeFx getInstance() {

    if (FacadeFx.facade == null) {
      FacadeFx.facade = new FacadeFx();
    }

    return FacadeFx.facade;
  }

  /*
   * ------------------------------------
   */

  /*
   * Attribut ---------------------------
   */
  private ModifierImpl modifier;
  private Edition edition;
  private Configuration configuration;
  private ZonePrevision zone;

  /*
   * Methodes ---------------------------
   */

  /*
   * Getters et Setters ----------------
   */

  public Configuration getConfiguration() {
    return configuration;
  }

  public static FacadeFx getFacade() {
    return facade;
  }

  public void setConfiguration(Configuration configuration) {
    this.configuration = configuration;
  }

  public ZonePrevision getZone() {
    return zone;
  }

  public void setZone(ZonePrevision zone) {
    this.zone = zone;
  }

}
