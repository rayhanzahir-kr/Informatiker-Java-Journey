package VW7;

import java.util.Locale;

class Turtle {
   ViewBox viewBox;
   double x;
   double y;
   double angle = 0.0;
   double width = 1.0;
   boolean penDown = true;
   String svg = "";
   int counter = 0;
   String strokeColor="black";

   Turtle(double var1, double var3, double var5, double var7) {
      this.viewBox = new ViewBox(var1, var3, var5, var7);
      this.x = this.viewBox.x + this.viewBox.width / 2.0;
      this.y = this.viewBox.y + this.viewBox.height / 2.0;
      String var10001 = this.svg;
      this.svg = var10001 + String.format(Locale.US, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"%.2f %.2f %.2f %.2f\">\n", this.viewBox.x, this.viewBox.y, this.viewBox.width, this.viewBox.height);
   }

   Turtle forward(double var1) {
      double var3 = Math.cos(Math.toRadians(this.angle)) * var1;
      double var5 = Math.sin(Math.toRadians(this.angle)) * var1;
      double var7 = this.x + var3;
      double var9 = this.y + var5;
      if (this.penDown) {
         double var11 = this.viewBox.height - (this.y - this.viewBox.y) + this.viewBox.y;
         double var13 = this.viewBox.height - (var9 - this.viewBox.y) + this.viewBox.y;
         String var10001 = this.svg;
         this.svg = var10001 + String.format(Locale.US, " <line id=\"%d\" x1=\"%.2f\" y1=\"%.2f\" x2=\"%.2f\" y2=\"%.2f\"\n" +
       "        stroke=\"%s\" stroke-width=\"%.2f\" />\n"  , this.counter++, this.x, var11, var7, var13,this.strokeColor, this.width);
      }

      this.x = var7;
      this.y = var9;
      return this;
   }

   Turtle backward(double var1) {
      this.forward(-var1);
      return this;
   }

   Turtle right(double var1) {
      this.angle -= var1;
      this.angle = (this.angle % 360.0 + 360.0) % 360.0;
      return this;
   }

   Turtle left(double var1) {
      this.angle += var1;
      this.angle = (this.angle % 360.0 + 360.0) % 360.0;
      return this;
   }

   Turtle width(double var1) {
      this.width = var1;
      return this;
   }

   Turtle penUp() {
      this.penDown = false;
      return this;
   }

   Turtle penDown() {
      this.penDown = true;
      return this;
   }

   Turtle color(String color){
      this.strokeColor=color;
      return this;
   }
     Turtle polygon(int n, double size) {
      if (n < 3) return this; // kein sinnvolles Polygon
      double angleStep = 360.0 / n;
      for (int i = 0; i < n; i += 1) {
         this.forward(size);
         this.left(angleStep);
      }
      return this;
   }

   void done() {
      IO.println(this.svg + "</svg>");
   }
}
