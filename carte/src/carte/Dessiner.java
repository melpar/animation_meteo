package carte;

import java.awt.Color;
import java.util.List;

import org.geotools.data.collection.CollectionFeatureSource;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.map.MapContext;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;

public class Dessiner {

  MapContext map;
  final SimpleFeatureType TYPE;
  private static final int coefficient = 10000;
  private static final int taille = 25;
  int testDecalage;

  /**
   * Initialiser le calque
   * 
   * @param map
   * @param modif
   */
  public Dessiner(MapContext map, int modif) {
    this.map = map;
    testDecalage = modif - 70;
    TYPE = setBuilder().buildFeatureType();
    // ajouterCalque();
  }

  /**
   * initialiser le builder
   * 
   * @return
   */
  SimpleFeatureTypeBuilder setBuilder() {
    SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();

    builder.setName("LineFeature" + testDecalage);

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
  MapContext ajouterCalque(List<InformationsVents> vents) {

    // build the type

    SimpleFeatureBuilder featureBuilderLines = new SimpleFeatureBuilder(TYPE);

    SimpleFeatureCollection collectionLines = new DefaultFeatureCollection("internal", TYPE);
    SimpleFeature featureLine = featureBuilderLines.buildFeature(null);
    ((DefaultFeatureCollection) collectionLines).add(featureLine);

    for (InformationsVents infos : vents) {
      ((DefaultFeatureCollection) collectionLines).add(ajouterFigure(infos));
    }
    float lineWidt = 2.0f; // epaisseur des trai

    Style lineStyle = SLD.createLineStyle(Color.red, lineWidt); // definition de la couleur et de
                                                                // l'epaisseur

    SimpleFeatureSource collectionFeatureSource = new CollectionFeatureSource(collectionLines);

    map.addLayer(collectionFeatureSource, lineStyle);// ajout du calque : dessin + caracteristique

    return map;
  }

  /**
   * creation de la figure
   * 
   * @param x
   * @param y
   * @return
   */
  LineString creerFigure(InformationsVents infos) {
    GeometryFactory gFac = JTSFactoryFinder.getGeometryFactory(JTSFactoryFinder.EMPTY_HINTS);
    Coordinate[] coordinates = new Coordinate[5];

    double x = infos.getPositionX();
    double y = infos.getPositionY();
    double latStart = 100000.0;
    double lonStart = 0.0;

    double latEnd = 75000.0;
    double lonEnd = -25000.0;

    double latEnd2 = 75000.0;
    double lonEnd2 = 25000.0;

    double z = Math.sin(infos.getDirection()) * taille * coefficient;
    double w = Math.cos(infos.getDirection()) * taille * coefficient;
    double coeff1x = Math.sin((infos.getDirection() - 3 * Math.PI / 4)) * taille * coefficient * 1
        / 4;
    double coeff1y = Math.cos(infos.getDirection() - 3 * Math.PI / 4) * taille * coefficient * 1
        / 4;
    double coeff2x = Math.sin(infos.getDirection() + 3 * Math.PI / 4) * taille * coefficient * 1
        / 4;
    double coeff2y = Math.cos(infos.getDirection() + 3 * Math.PI / 4) * taille * coefficient * 1
        / 4;
    coordinates[0] = new Coordinate(x, y);
    coordinates[1] = new Coordinate(x + w, y + z);
    if (infos.getDirection() > Math.PI || infos.getDirection() == 0) {
      coordinates[2] = new Coordinate(x + w + coeff1x, y + z - coeff1y);

    } else {
      coordinates[2] = new Coordinate(x + w - coeff1x, y + z - coeff1y);

    }

    coordinates[3] = new Coordinate(x + w, y + z);
    coordinates[4] = new Coordinate(x + w - coeff2x, y + z + coeff2y);

    LineString line = gFac.createLineString(coordinates);
    System.out.println(line);
    return line;
  }

  /**
   * mise en forme de la figure
   * 
   * @param x
   * @param y
   * @return
   */
  SimpleFeature ajouterFigure(InformationsVents infos) {
    SimpleFeatureBuilder featureBuilderLines = new SimpleFeatureBuilder(TYPE);
    featureBuilderLines.add(creerFigure(infos));
    SimpleFeature featureLine = featureBuilderLines.buildFeature(null);
    return featureLine;
  }

}
