package facade;

import configuration.Configuration;
import edition.implementation.Edition;
import modification.ModifierImpl;

public class Facade {

  /*
   * Singleton ---------------------------
   */

  static private Facade facade;

  private Facade() {
    super();
    /*
     * Initialisation de l'objet Configuration
     */
    this.configuration = new Configuration();
  }

  static public Facade getInstance() {

    if (Facade.facade == null) {
      Facade.facade = new Facade();
    }

    return Facade.facade;
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
