package carte;

import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;

public class CalculPosition {
  public static Coordinate convertEpsg4326to3857(Coordinate coordinate) {
    try {
      CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:4326");
      CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:3857");
      MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS, false);
      GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
      Point point = geometryFactory.createPoint(coordinate);
      Point targetPoint = (Point) JTS.transform(point, transform);

      return new Coordinate(targetPoint.getX(), targetPoint.getY());
    } catch (Exception e) {
      return new Coordinate(0, 0);
    }
  }

  public static Coordinate convertEpsg4326to3857(double lon, double lat) {
    double longitude = lon * 20037508.34 / 180;
    double latitude = Math.log(Math.tan((90 + lat) * Math.PI / 360)) / (Math.PI / 180);
    latitude = latitude * 20037508.34 / 180;
    return new Coordinate(longitude, latitude);
  }
}
