package previsionVents;

public class UtilMoyenne {
  public DonneeVent calculerMoyenne(DonneeVent... donneeVents) {

    double sommeDir = 0.0;
    double sommeVit = 0.0;

    for (DonneeVent donnee : donneeVents) {
      sommeDir += donnee.getOrientationVent();
      sommeVit += donnee.getVitesseVent();
    }
    return new DonneeVent(sommeVit / donneeVents.length, sommeDir / donneeVents.length);
  }
}
