package analyse_grib;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jgrib.GribFile;
import net.sourceforge.jgrib.GribRecord;
import net.sourceforge.jgrib.GribRecordGDS;
import net.sourceforge.jgrib.NoValidGribException;

public class ParserGrib {

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

  public InformationsGrille getInformationsGrille(String nameFile) {
    InformationsGrille informations = new InformationsGrille();

    try {
      GribFile grb = new GribFile(nameFile);

      GribRecordGDS r2 = grb.getGrids()[0];

      informations.setLattidude(r2.getGridLat2());
      informations.setLongitude(r2.getGridLon2());
      informations.setNombreX(r2.getGridNX());
      informations.setNombreY(r2.getGridNY());
      informations.setPasX(r2.getGridDX());
      informations.setPasY(r2.getGridDY());

      GribRecord ventU = grb.getRecord(1);
      informations.setDatePrevision(ventU.getTime().getTime());
      GribRecord ventV = grb.getRecord(2);

      // GribRecord ventD = grb.getRecord(4);
      int nbx = r2.getGridNX();
      System.out.println(nbx);
      int nby = r2.getGridNY();
      System.out.println(nby);
      for (int i = 0; i < nbx; i++) {
        for (int j = 0; j < nby; j++) {
          // affichage des la donnee (i,j)
          try {
            Vent v = new Vent(i, j, ventU.getValue(i, j), ventV.getValue(i, j));
            informations.addVent(v);
          } catch (NoValidGribException e) {
            e.printStackTrace();
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
    InformationsGrille infos = parser.getInformationsGrille("H:\\gascogne.grb");
  }
}
