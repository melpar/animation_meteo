package carteFX.facade;

import configuration.Configuration;
import edition.implementation.Edition;
import modification.ModifierImpl;

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
   * Attribue ---------------------------
   */
  private ModifierImpl modifier;
  private Edition edition;
  private Configuration configuration;

  /*
   * Méthodes ---------------------------
   */

  /*
   * Getters et Setteurs ----------------
   */
  public Configuration getConfiguration() {
    return configuration;
  }

  public void setConfiguration(Configuration configuration) {
    this.configuration = configuration;
  }

}
