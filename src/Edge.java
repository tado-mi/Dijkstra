public class Edge {

  Vertex u, v;
  protected double weight;

  public Edge(Vertex u, Vertex v) {

    this.u = u;
    this.v = v;

    setWeight();

  }

  public String toString() {

    return "( " + u.name + ", " + v.name +")";

  }

  // setters
  protected void setWeight() {

    double lat = Math.pow(u.lat - v.lat, 2);
    double lon = Math.pow(u.lon - v.lon, 2);

    weight = Math.sqrt(lat + lon);

  }

  // getters
  public double weight() {

    return weight;

  }

}
