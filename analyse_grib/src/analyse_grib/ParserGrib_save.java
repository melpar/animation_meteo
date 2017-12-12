package analyse_grib;

import java.io.FileNotFoundException;
import java.io.IOException;

import net.sourceforge.jgrib.GribFile;
import net.sourceforge.jgrib.GribRecord;
import net.sourceforge.jgrib.GribRecordGDS;
import net.sourceforge.jgrib.NoValidGribException;
import net.sourceforge.jgrib.NotSupportedException;

public class ParserGrib_save {

  public static void main(String[] args) {
    ParserGrib_save parser = new ParserGrib_save();
    parser.parserGrib();
  }

  public void parserGrib() {
    try {
      // GribFile grb = new GribFile("NorthEurope.grb");
      GribFile grb = new GribFile("gascogne.grb");

      // GribFile grb = new GribFile("mailVent2.grb");
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
            // System.out.println(ventV.getValue(i, j) + " " + ventV.getUnit());
            System.out.println("Vitesse en (" + i + "," + j + ")"
                + Math.sqrt((ventU.getValue(i, j) * ventU.getValue(i, j))
                    + (ventV.getValue(i, j) * ventV.getValue(i, j))));
            System.out.println("Direction en (" + i + "," + j + ")"
                + Math.atan2(ventU.getValue(i, j), ventV.getValue(i, j)));
          } catch (NoValidGribException e) {
            e.printStackTrace();
          }
        }
        System.out.println();
      }
      // System.out.println(ventD.getUnit());
      // affichage de l'unite des donnees
      System.out.println(ventV.getUnit());
      // description de la donnee
      System.out.println(ventU.getPDS());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (NotSupportedException e) {
      e.printStackTrace();
    } catch (NoValidGribException e) {
      e.printStackTrace();
    }
  }
}
