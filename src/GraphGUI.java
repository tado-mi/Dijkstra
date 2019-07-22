import java.util.*;
import java.io.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphGUI extends JComponent {

  protected Graph graph;

  protected double minLat, minLon, maxLat, maxLon;
  protected double xscale, yscale;

  // animation
  protected String focusName;
  protected Color color;
  protected javax.swing.Timer timer;

  public GraphGUI(Graph graph, String filename) {

    this.graph = graph;

    this.minLat = graph.minLat;
    this.minLon = graph.minLon;
    this.maxLat = graph.maxLat;
    this.maxLon = graph.maxLon;

    this.timer = new javax.swing.Timer(1000, new TimeListener(filename));
    timer.start();

  }

  public void paintComponent(Graphics g) {

    // screen size
    Rectangle s = g.getClipBounds();
    double w = s.getWidth();
    double h = s.getHeight();

    this.xscale = w / (maxLon - minLon);
    this.yscale = h / (maxLat - minLat);

    drawGraph(g, w, h);

    Vertex focus = graph.get(focusName);
    g.setColor(color);
    drawVertex(focus, g, w, h);

  }

  public void drawGraph(Graphics g, double w, double h) {

    for (String name: graph.vertexList()) {

      Vertex u = graph.get(name);
      double ux = (u.lon - minLon) * xscale;
      double uy = (u.lat - minLat) * yscale;
      uy = - uy + h;

      if (u.name().charAt(0) != 'i') {

        g.drawString(u.name(), (int) ux, (int) uy - 3);

      }

      for (Edge e: u.adjList) {

        Vertex v = e.v;
        double vx = (v.lon - minLon) * xscale;
        double vy = (v.lat - minLat) * yscale;
        vy = - vy + h;

        g.drawLine((int) ux, (int) uy, (int) vx, (int) vy);

      }

    }

  }

  public void drawVertex(Vertex u, Graphics g, double w, double h) {

    if (u == null) return;

    int r = 10;

    double ux = (u.lon - minLon) * xscale;
    double uy = (u.lat - minLat) * yscale;
    uy = - uy + h;

    g.fillOval((int) ux - r/2, (int) uy - r/2, r, r);

  }

  public void drawPath(LinkedList<Vertex> path, Graphics g, double w, double h) {

    if (path == null) return;

    int r = 6;

    g.setColor(new Color(255, 0, 0));

    for (int i = 0; i < path.size() - 1; i = i + 1) {

      Vertex u = path.get(i);
      double ux = (u.lon - minLon) * xscale;
      double uy = (u.lat - minLat) * yscale;
      uy = - uy + h;

      Vertex v = path.get(i + 1);
      double vx = (v.lon - minLon) * xscale;
      double vy = (v.lat - minLat) * yscale;
      vy = - vy + h;

      g.drawLine((int) ux, (int) uy, (int) vx, (int) vy);
      g.fillOval((int) ux - r/2, (int) uy - r/2, r, r);

    }

  }

  // time listener
  protected class TimeListener implements ActionListener {

    String line;
    Scanner scanner;

    public TimeListener(String filename) {

      try {

        scanner = new Scanner(new File(filename));

      } catch (FileNotFoundException exp) {

        System.out.println(exp.getMessage());

      }

    }

    public void actionPerformed(ActionEvent e) {

      if (!scanner.hasNext()) return;

      String[] arr;

      do { // ignore "comments"

        line = scanner.nextLine();
        arr = line.split("\t");

      } while (arr[0].equals("m"));

      repaint();

    }

  }

}
