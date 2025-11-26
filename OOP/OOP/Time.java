class Time{
    final int time;

    Time(int minute){
        time=(minute%1440+1440)%1440;
    }

    static Time of(int hour,int minute){
        return new Time(hour*60+minute);
    }

    Time add(int num){
        return new Time(time+num);
    }
    @Override
    
    public String toString(){
        return ""+time/60+(time%60<=10 ? ":0":":")+time%60;
    }
}

var a=Time.of(10,15);