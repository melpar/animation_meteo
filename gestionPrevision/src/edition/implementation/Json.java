package edition.implementation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edition.usines.FabricationListPrevision;
import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;

public class Json {

  /**
   * 
   * @param previsions
   * @param path
   */
  @SuppressWarnings("unchecked")
  public void jsonWrite(ListePrevision previsions, String path) {

    JSONObject obj = new JSONObject();

    // coordoné point haut gauche
    ZonePrevision zone = previsions.getZonePrevision();

    obj.put("latitude X", new Double(zone.getLatitudeHautGauche()));
    obj.put("longitude Y", new Double(zone.getLongitudeHautGauche()));

    // pas de la matrice
    obj.put("pas X", new Double(zone.getPasX()));
    obj.put("pas Y", new Double(zone.getPasY()));

    // taille de la matrice
    obj.put("nombre X", new Integer(zone.getNombreX()));
    obj.put("nombre Y", new Integer(zone.getNombreY()));

    // nombre de previsiont
    Integer nbPrevision = previsions.getListePrevision().size();

    // les previsions

    JSONObject listPrevision = new JSONObject();

    for (int i = 0; i < nbPrevision; i++) {
      JSONObject unePrevision = new JSONObject();

      // date de la prevision
      Calendar date = previsions.getListePrevision().get(i).getDatePrevision();
      date.set(2, Calendar.MONTH);
      DateFormat indfm = new SimpleDateFormat("dd-MM-yyyy HH'h'mm");
      unePrevision.put("Date", indfm.format(date.getTime()));

      // matrice
      DonneeVent[][] donneeVent = previsions.getListePrevision().get(i).getListeDonneVent();
      JSONArray listeMatriceY = new JSONArray();
      for (int j = 0; j < donneeVent.length; j += 10) {
        JSONArray listeMatriceX = new JSONArray();
        for (int k = 0; k < donneeVent[j].length; k += 10) {
          JSONObject listeDonneeVent = new JSONObject();
          listeDonneeVent.put("vitesse", new Double(donneeVent[j][k].getVitesseVent()));
          listeDonneeVent.put("direction", new Double(donneeVent[j][k].getOrientationVent()));
          listeMatriceX.add(listeDonneeVent);
        }
        listeMatriceY.add(listeMatriceX);
      }
      unePrevision.put("Matrice", listeMatriceY);
      listPrevision.put("Prevision " + i, unePrevision);
    }

    obj.put("Previsions", listPrevision);

    // ecrire le fichier
    try {
      FileWriter file = new FileWriter(path);
      file.write(obj.toJSONString());
      file.flush();
      file.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.print(obj);

  }

  public ListePrevision JsonRead(String path) {

    JSONParser parser = new JSONParser();

    try {
      ArrayList<Prevision> listPrevision = new ArrayList<Prevision>();

      Object obj = parser.parse(new FileReader(path));

      JSONObject jsonObject = (JSONObject) obj;

      Double latitude = (Double) jsonObject.get("latitude X");
      Double longitude = (Double) jsonObject.get("longitude Y");
      Double pasX = (Double) jsonObject.get("pas X");
      Double pasY = (Double) jsonObject.get("pas Y");
      Integer nombreX = ((Long) jsonObject.get("nombre X")).intValue();
      Integer nombreY = ((Long) jsonObject.get("nombre Y")).intValue();

      ListePrevision zonePrevision = FabricationListPrevision.creerListPrevisionVide(latitude,
          longitude, pasX, pasY, nombreX, nombreY);

      JSONObject previsions = (JSONObject) jsonObject.get("Previsions");

      for (int i = 0; i < previsions.size(); i++) {
        Prevision unePrevision = new Prevision();

        JSONObject prevision = (JSONObject) previsions.get("Prevision " + i);

        String date = (String) prevision.get("Date");
        String[] gestionCalendrier = date.split(" ");
        String[] gestionDate = gestionCalendrier[0].split("-");
        String[] gestionHeure = gestionCalendrier[1].split("h");

        int day = Integer.parseInt(gestionDate[0]);
        int month = Integer.parseInt(gestionDate[1]);
        int year = Integer.parseInt(gestionDate[2]);

        int hourOfDay = Integer.parseInt(gestionHeure[0]);
        int minute = Integer.parseInt(gestionHeure[1]);

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hourOfDay, minute);

        unePrevision.setDate(cal);

        JSONArray matrice = (JSONArray) prevision.get("Matrice");
        DonneeVent[][] donneeVent = new DonneeVent[nombreX][nombreY];

        Iterator<JSONArray> iteratorY = matrice.iterator();
        for (int j = 0; iteratorY.hasNext(); j++) {
          Iterator<JSONObject> iteratorX = iteratorY.next().iterator();
          for (int k = 0; iteratorX.hasNext(); k++) {
            JSONObject donnee = iteratorX.next();
            double vitesse = (Double) donnee.get("vitesse");
            double direction = (Double) donnee.get("direction");
            donneeVent[j][k] = new DonneeVent();
            donneeVent[j][k].setVitesseVent(vitesse);
            donneeVent[j][k].setOrientationVent(direction);
          }
        }
        unePrevision.setMatrice(donneeVent);
        listPrevision.add(unePrevision);
      }
      zonePrevision.setListePrevision(listPrevision);
      return zonePrevision;

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return null;
  }

}
