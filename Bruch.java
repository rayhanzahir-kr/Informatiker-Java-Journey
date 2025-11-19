class Bruch{
    long zähler,nenner;

    Bruch(long zähler,long nenner){
      
        long ggT = ggT(abs(zähler), nenner);
        this.zähler = zähler / ggT;
        this.nenner = nenner / ggT;

    }

    static long ggT(long a,long b){
        if(a==0) return b;
        if(b==0) return a;

        return ggT(b, a%b);
    }
    static long kgV(long a, long b) {
        return abs(a * b) / ggT(a, b);
    }

    static long abs(long zahl){
        return zahl>0 ? zahl:-zahl; 
    }

    static  Bruch of(long zähler,long nenner){
        return new Bruch(zähler, nenner);
    }
    static Bruch of(long ganzZahl){
        return new Bruch(ganzZahl, 1);
    }

    Bruch neg(){
        return new Bruch(-this.zähler, this.nenner);
    }
    Bruch plus(){
        return new Bruch(this.zähler,this.nenner);
    }
    Bruch inv(){
        if (this.zähler == 0) {
            throw new ArithmeticException("Division durch Null nicht erlaubt");
        }
        long neuerZähler = this.zähler > 0 ? this.nenner : -this.nenner;
        long neuerNenner = abs(this.zähler);
        return new Bruch(neuerZähler, neuerNenner);
    }

     Bruch add(Bruch b){
        long gemeinsamNenner= kgV(this.nenner, b.nenner);
        long x=(gemeinsamNenner/this.nenner)*this.zähler+b.zähler * (gemeinsamNenner/b.nenner);

        return new Bruch(x,gemeinsamNenner);
    }
    Bruch sub(Bruch b){
        return add(b.neg());
    }
    Bruch mul(Bruch b){
        long x=this.zähler*b.zähler;
        long y=this.nenner*b.nenner;

        return new Bruch(x,y);
    }
    Bruch div(Bruch b){

        return mul(b.inv());
    }


    public String toString(){
        if (zähler<0)
         return "-"+ Bruch.of(-zähler,nenner).toString();
        if (zähler == 0) return "0";
        if(zähler==nenner) return "1";
        if(zähler<nenner)
            return zähler + "/" + nenner;
        if(zähler%nenner==0)
            return ""+zähler/nenner;
        else{
            long ganzZahl= zähler/nenner;
        return ganzZahl +" "+ zähler%nenner +"/"+ nenner;
        }
    }

}


assert Bruch.of(1,2).toString().equals("1/2");
assert Bruch.of(5,4).toString().equals("1 1/4");
assert Bruch.of(10,8).zähler == 5 && Bruch.of(10,8).nenner == 4;
assert Bruch.of(-5,4).toString().equals("-1 1/4");
assert Bruch.of(-10,8).toString().equals("-1 1/4");
assert Bruch.of(3,3).toString().equals("1");
assert Bruch.of(6,3).toString().equals("2");
assert Bruch.of(7,3).toString().equals("2 1/3");
assert Bruch.of(-3,3).toString().equals("-1");
assert Bruch.of(-6,3).toString().equals("-2");
assert Bruch.of(-7,3).toString().equals("-2 1/3");
assert Bruch.of(1).toString().equals("1");
assert Bruch.of(7).toString().equals("7");
assert Bruch.of(-1).toString().equals("-1");
assert Bruch.of(-7).toString().equals("-7");
assert Bruch.of(0).toString().equals("0");

assert Bruch.of(1,3).add(Bruch.of(1,6)).toString().equals("1/2");
assert Bruch.of(1,2).mul(Bruch.of(2,3)).toString().equals("1/3");
assert Bruch.of(-1,3).mul(Bruch.of(1,2)).toString().equals("-1/6");
assert Bruch.of(-1,2).mul(Bruch.of(-2,3)).toString().equals("1/3");
assert Bruch.of(6,7).inv().toString().equals("1 1/6");
assert Bruch.of(-6,7).inv().toString().equals("-1 1/6");
assert Bruch.of(29).div(Bruch.of(2)).toString().equals("14 1/2");
assert Bruch.of(2,3).add(Bruch.of(1,3)).toString().equals("1");
assert Bruch.of(27,11).sub(Bruch.of(29,13)).toString().equals("32/143");




