package fasade;

import configuration.Configuration;
import edition.implementation.Edition;
import modification.ModifierImpl;

public class Fasade {

  /*
   * Singleton ---------------------------
   */

  static private Fasade fasade;

  private Fasade() {
    super();
    /*
     * Initialisation de l'objet Configuration
     */
    this.configuration = new Configuration();
  }

  static public Fasade getInstance() {

    if (Fasade.fasade == null) {
      Fasade.fasade = new Fasade();
    }

    return Fasade.fasade;
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
