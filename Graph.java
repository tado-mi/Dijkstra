import java.util.*;
import java.io.*;

public class Graph {

  protected HashMap<String, Vertex> G;

  // parameters for GUI
  double minLat =  Double.MAX_VALUE;
  double minLon =  Double.MAX_VALUE;
  double maxLat = -Double.MAX_VALUE;
  double maxLon = -Double.MAX_VALUE;

  public Graph() {

    G = new HashMap<>();

  }

  public void createFromFile(String filename) {

    Scanner scan = null;
    try {

      scan = new Scanner(new File(filename));

    } catch (FileNotFoundException e) {

      System.out.println(filename + " not found.");
      System.out.println("terminating.");
      return;

    }

    while (scan.hasNext()) {

      String   line  = scan.nextLine();
      String[] param = line.split("\t");

      if (param[0].equals("i")) {

        String name = param[1];
        double lat = Double.parseDouble(param[2]);
        double lon = Double.parseDouble(param[3]);

        Vertex v = new Vertex(name, lat, lon);
        add(v);

        if (minLat > lat) minLat = lat;
        if (minLon > lon) minLon = lon;
        if (maxLat < lat) maxLat = lat;
        if (maxLon < lon) maxLon = lon;

      } else if (param[0].equals("r")) {

        String u = param[2];
        String v = param[3];
        connect(u, v);


      }

    }

  }

  public void add(Vertex v) {

    G.put(v.name, v);

  }

  public void connect(String vName, String uName) {

    Vertex v = G.get(vName);
    Vertex u = G.get(uName);

    v.addConnection(u);
    u.addConnection(v);

  }

  public Vertex get(String name) {

    return G.get(name);

  }

  public Set<String> vertexList() {

    return G.keySet();

  }


}
