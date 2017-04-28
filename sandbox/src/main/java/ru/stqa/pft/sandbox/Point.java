package ru.stqa.pft.sandbox;
class Point {
  public static void main(String[] args) {
//    Point p1 = new Point(0, 0);
//    Point p2 = new Point(30, 50);
//    System.out.println("p1 = " + p1.x + ", " + p1.у);
//    System.out.println("p2 = " + p2.x + ", " + p2.у);
//    System.out.println("p1.distance(p2) = " + p1.distance(p2));


    double p1 = 0;
    double p2 = 5;

//    System.out.println(" Растояние между точками " + distance(p1, p2));
  }

  public double у;
  public double x;

  public Point( double x, double y){
     this.x = x;
     this.у = y;
    }

//  public double distance(double x, double y) {
//    double dx = this.x - x;
//    double dy = this.у - y;
//    return Math.sqrt(dx * dx + dy * dy);
//  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x) + (p1.у - p2.у)*(p1.у - p2.у));
  }
}

