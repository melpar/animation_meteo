package analyse_grib;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jgrib.GribFile;
import net.sourceforge.jgrib.GribRecord;
import net.sourceforge.jgrib.GribRecordGDS;
import net.sourceforge.jgrib.NoValidGribException;
import net.sourceforge.jgrib.NotSupportedException;

public class ParserGrib {

  public List<Vent> parserGrib() {
    List<Vent> ret = new ArrayList<>();
    try {
      GribFile grb = new GribFile("gascogne.grb");

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
            double vitesse = Math.sqrt((ventU.getValue(i, j) * ventU.getValue(i, j))
                + (ventV.getValue(i, j) * ventV.getValue(i, j)));

            double direction = Math.atan2(ventU.getValue(i, j), ventV.getValue(i, j));

            Vent v = new Vent(i, j, vitesse, direction);
            ret.add(v);
          } catch (NoValidGribException e) {
            e.printStackTrace();
          }
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (NotSupportedException e) {
      e.printStackTrace();
    } catch (NoValidGribException e) {
      e.printStackTrace();
    }
    return ret;
  }

  public static void main(String[] args) {
    ParserGrib parser = new ParserGrib();
    List<Vent> liste = parser.parserGrib();
    for (Vent v : liste) {
      System.out.println("vit " + v.getVitesse() + " dir " + v.getDirection());
    }
  }
}
