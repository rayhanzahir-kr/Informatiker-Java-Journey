package VW7;

public class Test {
   static Turtle square(Turtle t, double width) {
        t.forward(width).left(90);
        t.forward(width);
        t.left(90);
        t.forward(width);
        t.left(90);
        t.forward(width);
        t.left(90);
        return t;
    }

    static void mandalaPolygonFlower(Turtle t){
        for(int i=0;i<50;i++ ){
            t.color("hsl("+(i*10)+",100%,50%");
            t.polygon(6, 20

            );
            t.left(10);
        }
    }

    
    void main() {
        Turtle t = new Turtle(0, 0, 100, 100);
        mandalaPolygonFlower(t);
        t.done();
    }
}