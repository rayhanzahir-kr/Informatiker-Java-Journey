package OOP;
 boolean isLeapyear (int year){
    if(year%400==0)
        return true;
    if(year%100==0)
        return false;
    return year%4==0;
    }
    

class Person{
    String name;
    int geburtsjahr;

    Person(String name,int geburtsjahr){
        this.name=name;
        this.geburtsjahr=geburtsjahr;
    }
    public String toString(){
        return "Person(" + name + "," + geburtsjahr + ")";
    }
}
 Person a=new Person("Andi",2004);

class Date{
    int day,month,year;

    Date(int day,int month,int year){
        this.day=day;
        this.month=month;
        this.year=year;
    }

    static boolean isLeapyear(int year){
        assert year>0;
        if(year % 400==0)
            return true;
        if (year % 100==0)
            return false;
        return year%4==0;
    }

    int maxDay(int year,int month){
        assert year>0;
        assert month > 0 && month < 13;

        int []maxDay={31,28,31,30,31,30,31,31,30,31,30,31};

        if(month ==2 && Date.isLeapyear(year))
            return 29;

        return maxDay[month-1];
    }

    Date nextDate(){

        if(day<maxDay(year,month)){
            day +=1;
            return this;
        }
        day =1;
        if(month <12){
            month +=1;
        return this;
        }
        day=1;
        month =1;
        year +=1;

    return this;

    }
    Date plus(int days){
    while(days!=0){
        nextDate();
        days -=1;
    }
        return this;
    }
    public String toString(){
        return "Date(" + day + "." + month + "." + year +")";
    }

    Date previousDay(){

        if(day>1){
            day =day -1;
        return this;
        }

        if(month>1){
            month =month -1;
            day=maxDay(year,month);
            return this;
        }
        year=year-1;
        month=12;
        day=maxDay(year, month);
        return this;

    }
    Date minus(int days){
        assert days>0;

        while(days!=0){
            previousDay();
            days -=1;
        }
        return this;
    }
}
Date heute =new Date (17,11,2025);


