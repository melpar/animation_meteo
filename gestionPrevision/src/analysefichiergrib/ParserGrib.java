package analysefichiergrib;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.jgrib.GribFile;
import net.sourceforge.jgrib.GribRecord;
import net.sourceforge.jgrib.GribRecordGDS;
import net.sourceforge.jgrib.NoValidGribException;

/**
 * Parser d'un fichier grib pour extraire des vents.
 * 
 * @author StudioLab
 *
 */
public class ParserGrib {
  /**
   * Extrait les informations du fichier grib.
   * 
   * @param nameFile
   *          Chemin du fichier grib a parser.
   * @return liste des vents
   */
  public List<Vent> parserGrib(String nameFile) {
    List<Vent> ret = new ArrayList<>();
    try {
      GribFile grb = new GribFile(nameFile);

      GribRecordGDS r2 = grb.getGrids()[0];

      GribRecord ventU = grb.getRecord(1);
      GribRecord ventV = grb.getRecord(2);

      // GribRecord ventD = grb.getRecord(4);
      int nbx = r2.getGridNX();
      int nby = r2.getGridNY();

      for (int i = 0; i < nbx; i++) {
        for (int j = 0; j < nby; j++) {
          // affichage des la donnee (i,j)
          try {
            Vent v = new Vent(i, j, ventU.getValue(i, j), ventV.getValue(i, j));
            ret.add(v);
          } catch (NoValidGribException e) {
            e.printStackTrace();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ret;
  }

  /**
   * Recupere les parametres du fichier grib
   * 
   * @param nameFile
   *          Nom du fichier.
   * @return une information grille.
   */
  public InformationsGrille getInformationsGrille(String nameFile) {
    InformationsGrille informations = new InformationsGrille();

    try {
      System.out.println("test " + nameFile);
      GribFile grb = new GribFile(nameFile);

      GribRecordGDS r2 = grb.getGrids()[0];

      informations.setLattidude(r2.getGridLat1());
      informations.setLongitude(r2.getGridLon1());
      informations.setNombreX(r2.getGridNX());
      informations.setNombreY(r2.getGridNY());
      informations.setPasX(r2.getGridDX());
      informations.setPasY(r2.getGridDY());

      for (int index = 0; index < grb.getRecordCount() / 2; index++) {
        Date date = grb.getRecord(index * 2 + 1).getTime().getTime();

        GribRecord ventU = grb.getRecord(index * 2 + 1);
        GribRecord ventV = grb.getRecord((index + 1) * 2);

        // GribRecord ventD = grb.getRecord(4);
        int nbx = r2.getGridNX();
        int nby = r2.getGridNY();
        for (int i = 0; i < nbx; i++) {
          for (int j = 0; j < nby; j++) {
            // affichage des la donnee (i,j)
            try {
              Vent v = new Vent(i, j, ventU.getValue(i, j), ventV.getValue(i, j));
              Calendar calendar = Calendar.getInstance();
              calendar.setTime(date);
              informations.addVent(calendar, v);
            } catch (NoValidGribException e) {
              e.printStackTrace();
            }
          }
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return informations;
  }

  public static void main(String[] args) {
    ParserGrib parser = new ParserGrib();
    InformationsGrille infos = parser.getInformationsGrille("gascogne.grb");
    System.out.println(infos.getLattidude());

    System.out.println(infos.getLongitude());
    System.out.println(infos.getNombreX());
    System.out.println(infos.getNombreY());
    System.out.println(infos.getPasX());
    System.out.println(infos.getPasY());
    Map<Calendar, List<Vent>> vents = infos.getVents();
    Set<Calendar> cles = vents.keySet();
    Iterator<Calendar> it = cles.iterator();
    while (it.hasNext()) {
      Calendar cle = it.next();
      System.out.println(cle);
      // for (Vent v : vents.get(cle)) {
      // System.out.println(" " + v.getLatitude());
      // System.out.println(" " + v.getLongitude());
      // System.out.println(" " + v.getVecteurU());
      // System.out.println(" " + v.getVecteurV());
      // }
    }
  }
}
