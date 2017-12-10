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
	
	int testDecalage;
	
	
	/**
	 * Initialiser le calque
	 * @param map
	 * @param modif
	 */
	public Dessiner(MapContext map,int modif) {
		this.map=map;
		testDecalage=modif-70;
		TYPE = setBuilder().buildFeatureType();
		ajouterCalque();
	}
	
	/**
	 * initialiser le builder
	 * @return
	 */
	SimpleFeatureTypeBuilder setBuilder(){
		SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();

		builder.setName( "LineFeature"+testDecalage );

		//add a geometry property
		builder.setCRS( DefaultGeographicCRS.WGS84 ); // set crs first
		builder.add( "line", LineString.class ); // then add geometry
		return builder;
	}


	/**
	 * ajouter un calque dans la map.
	 * @return
	 */
	MapContext ajouterCalque (){
		

		//build the type
		 

		SimpleFeatureBuilder featureBuilderLines = new SimpleFeatureBuilder(TYPE);

		SimpleFeatureCollection collectionLines = new DefaultFeatureCollection("internal",TYPE);
		SimpleFeature featureLine = featureBuilderLines.buildFeature(null);
		((DefaultFeatureCollection) collectionLines).add(featureLine);   
		
		((DefaultFeatureCollection) collectionLines).add(ajouterFigure(testDecalage,5));  
		

		float lineWidt = 2.0f; // epaisseur des trai

		Style lineStyle = SLD.createLineStyle(Color.red, lineWidt); // definition de la couleur et de l'epaisseur

		SimpleFeatureSource collectionFeatureSource = new CollectionFeatureSource(collectionLines);

		map.addLayer(collectionFeatureSource, lineStyle);//ajout du calque : dessin + caracteristique
		
		return map;
	}
	
	
	/**
	 * creation de la figure
	 * @param x
	 * @param y
	 * @return
	 */
	LineString creerFigure(int x,int y){
		GeometryFactory gFac = JTSFactoryFinder.getGeometryFactory(JTSFactoryFinder.EMPTY_HINTS);
		Coordinate[] coordinates = new Coordinate[5];

		double latStart = 100000.0;
		double lonStart = 0.0;

		double latEnd = 75000.0;
		double lonEnd = -25000.0;
		
		double latEnd2 = 75000.0;
		double lonEnd2 = 25000.0;

		coordinates[0] = new Coordinate(x+0, y+0);
		coordinates[1] = new Coordinate(x+lonStart, y+latStart);
		coordinates[2] = new Coordinate(x+lonEnd, y+latEnd);
		coordinates[3] = new Coordinate(x+lonStart, y+latStart);
		coordinates[4] = new Coordinate(x+lonEnd2, y+latEnd2);
		

		LineString line = gFac.createLineString(coordinates );
		 System.out.println(line);
		return line;
	}
	/**
	 * mise en forme de la figure
	 * @param x
	 * @param y
	 * @return
	 */
	SimpleFeature ajouterFigure(int x,int y){
		SimpleFeatureBuilder featureBuilderLines = new SimpleFeatureBuilder(TYPE);
		featureBuilderLines.add(creerFigure(x,20));
		SimpleFeature featureLine = featureBuilderLines.buildFeature(null);
		return featureLine;
	}
	
}
