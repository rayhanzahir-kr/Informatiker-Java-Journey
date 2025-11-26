class Bruch{
    long zähler, nenner;

    static long ggT(long a,long b){
        if(a==0) return b;
        if(b==0) return a;

        return ggT(b,a%b);
    }

    Bruch(long zähler,long nenner){
        long ggT=ggT(zähler,nenner);
        this.zähler=zähler/ggT;
        this.nenner=nenner/ggT;
    }

    static Bruch of(long a,long b){
        return new Bruch(a, b);
    }





  @Override
  public String toString(){

    if(zähler<0){
        return "-"+ new Bruch(zähler, nenner);
    }
    if(zähler==nenner){
        return "1";
    }
    if(zähler==0){
        return "0";
    }
    if(zähler<nenner){
        return zähler+"/"+nenner;
    }
    if(zähler%nenner==0){
        return ""+zähler/nenner;
    }else{
        long ganzZahl=zähler/nenner;
        return ""+ganzZahl+" "+zähler%nenner+"/"+nenner;
    }
  }


}

Bruch a=new Bruch(1,2)