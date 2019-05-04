import java.util.*;

public class Vertex implements Comparable<Vertex> {

  protected String name;
  protected double lat, lon; // lattitude and longitude

  // Dijkstra specific instance variables
  protected double distance; // distance from the source;
  protected boolean explored;
  protected Vertex previous;

  LinkedList<Edge> adjList;

  public Vertex(String name, double lat, double lon) {

    this.name = name;
    this.lat = lat;
    this.lon = lon;

    this.adjList = new LinkedList<>();

    this.distance = Double.MAX_VALUE;
    this.explored = false;
    this.previous = null;

  }

  public String toString() {

    String ans = name;
    // ans = ans + "\t";
    // ans = ans + "( " + lat + ", " + lon + " )\t";
    // ans = ans + distance;

    return ans;

  }

  public boolean isEqual(Vertex v) {

    if (name.equals(v.name)) {

      return (lat == v.lat) && (lon == v.lon);

    }

    return false;

  }

  // setters
  public void setDistance(double d) {

    this.distance = d;

  }

  public void setPrevious(Vertex v) {

    this.previous = v;

  }

  public void unExplore() {

    this.explored = false;

  }

  public void doExplore() {

    this.explored = true;

  }

  public void removePath() {

    this.previous = null;

  }

  // getters
  public String name() {

    return this.name;

  }

  public double distance() {

    return this.distance;

  }

  public boolean isExplored() {

    return this.explored;

  }

  public LinkedList<Edge> adjList() {

    return this.adjList;

  }

  public LinkedList<Vertex> getPath() {

    LinkedList<Vertex> ans = new LinkedList<>();
    Vertex curr = this;

    while (curr != null) {

      ans.add(curr);
      curr = curr.previous;

    }

    return ans;

  }

  public void addEdge(Edge e) {

    adjList.add(e);

  }

  public void addConnection(Vertex v) {

    Edge e = new Edge(this, v);
    addEdge(e);

  }

  // compare by the distance from the source
  public int compareTo(Vertex v) {

    if (distance < v.distance) return -1;
    if (distance > v.distance) return  1;

    return 0;

  }


}
