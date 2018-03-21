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
import previsionVents.ZonePrevision;

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
    if (map.getLayerCount() == 2) {
      map.removeLayer(1);
    }
    // build the type

    SimpleFeatureBuilder featureBuilderLines = new SimpleFeatureBuilder(TYPE);

    SimpleFeatureCollection collectionLines = new DefaultFeatureCollection("internal", TYPE);
    SimpleFeature featureLine = featureBuilderLines.buildFeature(null);
    ((DefaultFeatureCollection) collectionLines).add(featureLine);

    ListePrevision previsions = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions();
    for (int i = 0; i < prev.getListeDonneVent().length; i += pasX) {
      for (int j = 0; j < prev.getListeDonneVent()[i].length; j += pasY) {
        ZonePrevision zone = new ZonePrevision(previsions.getZonePrevision().getLatitudePosition(i),
            previsions.getZonePrevision().getLongitudePosition(j),
            previsions.getZonePrevision().getPasX(), previsions.getZonePrevision().getPasY(), 1, 1);
        VisiteurMoyenne visiteur = new VisiteurMoyenne(zone);
        previsions.applique(visiteur);
        InformationsVents v = new InformationsVents();
        v.setPositionY(previsions.getZonePrevision().getLatitudePosition(i));
        v.setPositionX(previsions.getZonePrevision().getLongitudePosition(j));
        v.setDirection(visiteur.getMoyenneDirection());
        ((DefaultFeatureCollection) collectionLines).add(ajouterFigure(v, t));
      }
    }

    float lineWidt = 2.0f; // epaisseur des traits

    Style lineStyle = SLD.createLineStyle(Color.red, lineWidt); // definition de
                                                                // la couleur et
                                                                // de
    // l'epaisseur

    SimpleFeatureSource collectionFeatureSource = new CollectionFeatureSource(collectionLines);
    MapLayer layer = new MapLayer(collectionFeatureSource, lineStyle);
    map.addLayer(layer);// ajout du calque : dessin + caracteristique
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
    infos.setDirection(infos.getDirection() % (2 * Math.PI));
    GeometryFactory gFac = JTSFactoryFinder.getGeometryFactory(JTSFactoryFinder.EMPTY_HINTS);
    Coordinate[] coordinates = new Coordinate[5];

    double y = infos.getPositionX();
    double x = infos.getPositionY();
    System.out.println("x :" + x + " y :" + y);
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
    System.out.println(coordinates[0].x + " " + coordinates[0].y);
    coordinates[1] = CalculPosition.convertEpsg4326to3857(new Coordinate(x + w, y + z));
    coordinates[2] = CalculPosition
        .convertEpsg4326to3857(new Coordinate(x + w + coeff1x, y + z - coeff1y));

    coordinates[3] = CalculPosition.convertEpsg4326to3857(new Coordinate(x + w, y + z));
    coordinates[4] = CalculPosition
        .convertEpsg4326to3857(new Coordinate(x + w - coeff2x, y + z + coeff2y));

    LineString line = gFac.createLineString(coordinates);
    System.out.println(line);
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
