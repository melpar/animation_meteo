package carte;

import java.awt.Color;

import org.geotools.data.collection.CollectionFeatureSource;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.map.MapContext;
import org.geotools.map.MapLayer;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;

import modification.VisiteurMoyenne;
import previsionVents.FacadePrevisionVents;
import previsionVents.ListePrevision;
import previsionVents.Prevision;

public class Dessiner {

  MapContext map;
  final SimpleFeatureType TYPE;
  private int indice;

  public Dessiner(MapContext map) {
    this.map = map;
    indice = 0;
    TYPE = setBuilder().buildFeatureType();
    // ajouterCalque();
  }

  /**
   * initialiser le builder.
   * 
   * @return
   */
  SimpleFeatureTypeBuilder setBuilder() {
    SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();

    builder.setName("LineFeature" + indice);
    indice++;
    // add a geometry property
    builder.setCRS(DefaultGeographicCRS.WGS84); // set crs first
    builder.add("line", LineString.class); // then add geometry
    return builder;
  }

  /**
   * ajouter un calque dans la map.
   * 
   * @return
   */
  MapContext ajouterCalque(Prevision prev, double t, int pasX, int pasY) {
    while (map.getLayerCount() >= 2) {
      map.removeLayer(1);
    }
    // build the type

    SimpleFeatureBuilder featureBuilderLinesR = new SimpleFeatureBuilder(TYPE);

    SimpleFeatureCollection collectionLinesR = new DefaultFeatureCollection("internal", TYPE);
    SimpleFeature featureLineR = featureBuilderLinesR.buildFeature(null);
    ((DefaultFeatureCollection) collectionLinesR).add(featureLineR);

    SimpleFeatureBuilder featureBuilderLinesY = new SimpleFeatureBuilder(TYPE);

    SimpleFeatureCollection collectionLinesY = new DefaultFeatureCollection("internal", TYPE);
    SimpleFeature featureLineY = featureBuilderLinesY.buildFeature(null);
    ((DefaultFeatureCollection) collectionLinesY).add(featureLineY);

    SimpleFeatureBuilder featureBuilderLinesG = new SimpleFeatureBuilder(TYPE);

    SimpleFeatureCollection collectionLinesG = new DefaultFeatureCollection("internal", TYPE);
    SimpleFeature featureLineG = featureBuilderLinesG.buildFeature(null);
    ((DefaultFeatureCollection) collectionLinesG).add(featureLineG);
    ListePrevision previsions = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions();
    for (int i = 0; i < prev.getListeDonneVent().length; i += pasX) {
      for (int j = 0; j < prev.getListeDonneVent()[i].length; j += pasY) {
        System.out.println("i : " + i);
        System.out.println("Pas x : " + pasX);
        System.out.println("j : " + j);
        System.out.println("Pas y : " + pasY);
        InformationsVents v = new InformationsVents();
        v.setPositionY(FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
            .getZonePrevision().getLatitudePosition(j));
        v.setPositionX(FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
            .getZonePrevision().getLongitudePosition(i));
        if (i < prev.getListeDonneVent().length - pasX
            && j < prev.getListeDonneVent()[i].length - pasY) {
          System.out.println("calcul de la moyenne");
          // calcul de la moyenne
          VisiteurMoyenne moyenne = new VisiteurMoyenne(null, null);
          for (int indexPasX = 0; indexPasX < pasX; indexPasX++) {
            for (int indexPasY = 0; indexPasY < pasY; indexPasY++) {
              prev.getListeDonneVent()[indexPasX + i][indexPasY + j].applique(moyenne);
            }
          }
          v.setDirection(moyenne.getMoyenneDirection());
          v.setVitesse(moyenne.getMoyenneVitesse());
        } else {
          v.setDirection(prev.getListeDonneVent()[i][j].getOrientationVent());
          v.setVitesse(prev.getListeDonneVent()[i][j].getVitesseVent());
        }
        if (v.getVitesse() < 15) {
          System.out.println("G");
          ((DefaultFeatureCollection) collectionLinesG).add(ajouterFigure(v, t));
        } else if (v.getVitesse() < 60) {
          System.out.println("Y");
          ((DefaultFeatureCollection) collectionLinesY).add(ajouterFigure(v, t));
        } else {
          System.out.println("R");
          ((DefaultFeatureCollection) collectionLinesR).add(ajouterFigure(v, t));
        }
      }
    }

    float lineWidt = 2.0f; // epaisseur des traits

    Style lineStyleR = SLD.createLineStyle(Color.red, lineWidt);
    SimpleFeatureSource collectionFeatureSourceR = new CollectionFeatureSource(collectionLinesR);
    MapLayer layerR = new MapLayer(collectionFeatureSourceR, lineStyleR);
    map.addLayer(layerR);// ajout du calque : dessin + caracteristique

    Style lineStyleG = SLD.createLineStyle(Color.GREEN, lineWidt);
    SimpleFeatureSource collectionFeatureSourceG = new CollectionFeatureSource(collectionLinesG);
    MapLayer layerG = new MapLayer(collectionFeatureSourceG, lineStyleG);
    map.addLayer(layerG);// ajout du calque : dessin + caracteristique

    Style lineStyleY = SLD.createLineStyle(Color.orange, lineWidt);
    SimpleFeatureSource collectionFeatureSourceY = new CollectionFeatureSource(collectionLinesY);
    MapLayer layerY = new MapLayer(collectionFeatureSourceY, lineStyleY);
    map.addLayer(layerY);// ajout du calque : dessin + caracteristique
    return map;
  }

  /**
   * creation de la figure.
   * 
   * @param infos
   *          informations relatives au vent.
   * @return représentation d'une fleche
   */
  LineString creerFigure(InformationsVents infos, double taille) {
    // infos.setDirection(infos.getDirection() + (Math.PI / 2));
    infos.setDirection(infos.getDirection() % (2 * Math.PI));

    GeometryFactory gFac = JTSFactoryFinder.getGeometryFactory(JTSFactoryFinder.EMPTY_HINTS);
    Coordinate[] coordinates = new Coordinate[5];

    double y = infos.getPositionX();
    double x = infos.getPositionY();
    System.out.println("x : " + x + " y : " + y + " taille : " + taille);
    double z = Math.sin(infos.getDirection()) * taille;
    double w = Math.cos(infos.getDirection()) * taille;
    double coeff1x = 0.0;
    double coeff1y = 0.0;
    double coeff2x = 0.0;
    double coeff2y = 0.0;
    if (infos.getDirection() > 0 && infos.getDirection() < Math.PI) {
      coeff1x = Math.sin((infos.getDirection() - 3 * Math.PI / 4)) * taille * 0.5;
      coeff1y = Math.cos(infos.getDirection() - 3 * Math.PI / 4) * taille * 0.5;
      coeff2x = Math.sin(infos.getDirection() + 3 * Math.PI / 4) * taille * 0.5;
      coeff2y = Math.cos(infos.getDirection() + 3 * Math.PI / 4) * taille * 0.5;
    } else {
      coeff1x = Math.sin((infos.getDirection() - Math.PI / 4)) * taille * 0.5;
      coeff1y = Math.cos(infos.getDirection() - Math.PI / 4) * taille * 0.5;
      coeff2x = Math.sin(infos.getDirection() + Math.PI / 4) * taille * 0.5;
      coeff2y = Math.cos(infos.getDirection() + Math.PI / 4) * taille * 0.5;
    }
    coordinates[0] = CalculPosition.convertEpsg4326to3857(new Coordinate(x, y));
    coordinates[1] = CalculPosition.convertEpsg4326to3857(new Coordinate(x + w, y + z));
    coordinates[2] = CalculPosition
        .convertEpsg4326to3857(new Coordinate(x + w + coeff1x, y + z - coeff1y));

    coordinates[3] = CalculPosition.convertEpsg4326to3857(new Coordinate(x + w, y + z));
    coordinates[4] = CalculPosition
        .convertEpsg4326to3857(new Coordinate(x + w - coeff2x, y + z + coeff2y));

    LineString line = gFac.createLineString(coordinates);
    return line;
  }

  /**
   * mise en forme de la figure.
   * 
   * @param infos
   *          informations relatives à la fleches
   * @return représentation de la fleche
   */
  SimpleFeature ajouterFigure(InformationsVents infos, double taille) {
    SimpleFeatureBuilder featureBuilderLines = new SimpleFeatureBuilder(TYPE);
    featureBuilderLines.add(creerFigure(infos, taille));
    SimpleFeature featureLine = featureBuilderLines.buildFeature(null);
    return featureLine;
  }

}
