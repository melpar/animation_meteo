package analysefichiergrib;

import org.junit.Test;

public class TestGrib {
  @Test
  public void testLecture() {
    ParserGrib parser = new ParserGrib();
    InformationsGrille infos = parser.getInformationsGrille("H:\\gascogne.grb");
    assert (infos.getLattidude() == 43.024);
    assert (infos.getLongitude() == -0.028);
    assert (infos.getNombreX() == 278);
    assert (infos.getNombreY() == 167);
    assert (infos.getPasX() == 0.036);
    assert (infos.getPasY() == -0.036);
    assert (infos.getVents().size() == infos.getNombreX() * infos.getNombreY());
  }
}
