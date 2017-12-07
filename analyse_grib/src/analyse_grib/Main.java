package analyse_grib;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;

import net.sourceforge.jgrib.GribFile;
import net.sourceforge.jgrib.GribRecord;
import net.sourceforge.jgrib.GribRecordGDS;
import net.sourceforge.jgrib.NoValidGribException;
import net.sourceforge.jgrib.NotSupportedException;

public class Main {
	
	 public static void main(String[] args)
	    {
	        try
	        {
	            GribFile grb = new GribFile("NorthEurope.wind.7days.grb");
	 
	            GribRecordGDS r2 = grb.getGrids()[0];
	            GribRecord ventU = grb.getRecord(1);
	            GribRecord ventV = grb.getRecord(2);
	 
	            int nbx = r2.getGridNX();
	            int nby = r2.getGridNY();
	 
	            for (int i = 0; i < nbx; i++)
	            {
	                for (int j = 0; j < nby; j++)
	                {
	                    // affichage des la donnee (i,j)
	                    try
	                    {
	                        System.out.println("vent : "+ventU.getValue(i, j) + " ("+i+","+j+")");
	                    }
	                    catch (NoValidGribException e)
	                    {
	                        e.printStackTrace();
	                    }
	                }
	                System.out.println();
	            }
	            // affichage de l'unite des donnees
	            System.out.println(ventV.getUnit());
	            // description de la donnee
	            System.out.println(ventU.getPDS());
	        }
	        catch (FileNotFoundException e)
	        {
	            e.printStackTrace();
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	        catch (NotSupportedException e)
	        {
	            e.printStackTrace();
	        }
	        catch (NoValidGribException e)
	        {
	            e.printStackTrace();
	        }
	    }
	}
