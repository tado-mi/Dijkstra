import javax.swing.*;
import java.util.*;

public class main {

  public static void main(String[] args) {

    try {

      String mapname = args[0];
      String S = args[1];
      String T = args[2];

      String filename = "../../data/" + mapname + ".txt";
      String logFilename = "../../" + S + "-" + T + ".txt";

      // initialise the graph
      Graph graph = new Graph();
      graph.createFromFile(filename);

      // find the shortest path
      Dijkstra dijkstra = new Dijkstra(graph, logFilename, true);
      LinkedList<Vertex> path = dijkstra.computeShortestPath(S, T);

      // compute the ration of the original picture
      double latRange = graph.maxLat - graph.minLat;
      double lonRange = graph.maxLon - graph.minLon;
      double ratio = latRange / lonRange;

      // compute the size of the window
      int h = 800;
      int w = (int) (h * ratio);

      // create the JFrame
      JFrame frame = new JFrame();
      frame.setTitle(mapname);
      frame.setSize(w, h);
      frame.setVisible(true);
      // terminates the run of the code when JFrame is closed
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // add the JComponent
      GraphGUI canvas = new GraphGUI(graph, logFilename);
      frame.add(canvas);

    } catch (ArrayIndexOutOfBoundsException exp) {

      System.out.println("please, ensure following arguments are provided: -mapname -Source -Target");

    }


  }


}
