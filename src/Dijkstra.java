import java.util.*;
import java.io.*;

public class Dijkstra {

  protected Graph graph;
  protected String S, T, logFilename;

  protected String progressLog;
  protected boolean recordLog;

  public Dijkstra(Graph graph, String filename, boolean recordLog) {

    this.graph = graph;
    this.logFilename = filename;

    this.recordLog = recordLog;

  }

  protected void log(String str) {

    progressLog += str;
    progressLog += '\n';

    recordLog(str + '\n');

  }

  public LinkedList<Vertex> computeShortestPath(String S, String T) {

    this.S = S;
    this.T = T;

    this.progressLog = "";

    long start = System.nanoTime();
    log("\\\t" + "computing path from " + S + " to " + T);
    LinkedList<Vertex> ans = computeShortestPath();

    long end = System.nanoTime();
    long elapsed = end - start;
    elapsed = (long) (elapsed / Math.pow(10, 6));
    log("\\\t" + "time: " + elapsed + " miliseconds.");

    return ans;

  }

  public void init() {

    long start = System.nanoTime();

    for (String name: graph.vertexList()) {

      Vertex v = graph.get(name);
      v.setDistance(Double.MAX_VALUE);
      v.unExplore();
      v.removePath();

    }

    long end = System.nanoTime();
    long elapsed = end - start;
    elapsed = (long) (elapsed / Math.pow(10, 6));
    log("\\\t" + "initialisation complete in " + elapsed + " miliseconds.");

  }

  public LinkedList<Vertex> computeShortestPath() {

    init();

    Vertex s = graph.get(S);
    Vertex t = graph.get(T);

    if (s == null) {

      log("\\\t" + "source is invalid -->> terminating.");
      return null;

    }

    if (t == null) {

      log("\\\t" + "destination is invalid -->> terminating.");
      return null;

    }

    LinkedList<Vertex> shortestPath = new LinkedList<>();

    s.setDistance(0.0);

    PriorityQueue<Vertex> Q = new PriorityQueue<>();
    Q.offer(s);

    while(!Q.isEmpty()) {

      Vertex focus = Q.poll();
      focus.doExplore();

      log("v\t" + focus + "\t" + focus.distance() + "\t" + focus.getPath());

      if (focus.isEqual(t)) {

        shortestPath = t.getPath();
        log("\\\t" + "-----------------------");
        log("\\\t" + "Dijkstra's algorithm is terminated with success.");
        log("\\\t" + shortestPath.toString());

        return shortestPath;

      }

      for (Edge e: focus.adjList()) {

        Vertex w = e.v;
        if (w.isExplored()) continue;

        log("e\t" + w + "\t" +"inf" + "\t" + w.getPath().toString());

        double newDistance = focus.distance() + e.weight();
        if (newDistance < w.distance()) {

          w.setDistance(newDistance);
          w.setPrevious(focus);
          log("u\t" + w + "\t" + w.distance() + "\t" + w.getPath().toString());

          Q.offer(w);
          log("q\t" + Q.toString());

        }

      }

    }

    log("\\\t" + "there is no " + s + " - " + t + " path in the graph.");
    return null;

  }

  public void printLog() {

    System.out.println(progressLog);

  }

  public void recordLog(String line) {

    if (!recordLog) return;

    try {

      BufferedWriter wr = new BufferedWriter(new FileWriter(logFilename, true));
      wr.write(line);
      wr.flush();
      wr.close();

    } catch (IOException exp) {

      System.out.println(exp.getMessage());

    }

  }


}
