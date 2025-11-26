import java.time.LocalDate;

import VW7.IO;

class Date {
    int day, month, year;

    Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    static boolean isLeapYear(int year){
        if(year % 400 == 0) return true;
        if(year % 100 == 0) return false;
        return year % 4 == 0;
    }

    static int maxDay(int month, int year){
        int[] maxDay = {31,28,31,30,31,30,31,31,30,31,30,31};
        if(month == 2 && isLeapYear(year))
            return 29;
        return maxDay[month-1];
    }

    static Date of(int year, int month, int day){
        return new Date(day, month, year); // Diperbaiki
    }

    Date nextDay(){
        if(day < maxDay(month, year)) 
            return new Date(day+1, month, year);
        if(month < 12)
            return new Date(1, month+1, year);
        return new Date(1, 1, year+1);
    }

    Date previousDate(){
        if(day > 1)
            return new Date(day-1, month, year);
        if(month > 1)
            return new Date(maxDay(month-1, year), month-1, year);
        return new Date(31, 12, year-1);
    }

    boolean isBefore(Date otherDate){
        if(this.year < otherDate.year)
            return true;
        if(this.year == otherDate.year && this.month < otherDate.month)
            return true;
        return this.year == otherDate.year && this.month == otherDate.month && this.day < otherDate.day;
    }

    boolean isEqual(Date otherDate){
        return this.year == otherDate.year && this.month == otherDate.month && this.day == otherDate.day;
    }
    
    Date plus(int days){
        Date result = this;
        if(days > 0){
            for(int i = 0; i < days; i++){
                result = result.nextDay();
            }
        } else if(days < 0){
            for(int i = 0; i < -days; i++){
                result = result.previousDate();
            }
        }
        return result;
    }

    int diff(Date otherDate){
        if(this.isEqual(otherDate)) return 0;
        if(!this.isBefore(otherDate)) return -otherDate.diff(this);
        
        Date current = this;
        int days = 0;
        while(!current.isEqual(otherDate)){
            days++;
            current = current.nextDay();
        }
        return days;
    }

    int dayOfWeek(){
        // Gunakan tanggal referensi yang diketahui hari-nya
        // Contoh: 1 Januari 2000 adalah Sabtu (hari ke-6)
        Date refDate = new Date(1, 1, 2000); // Sabtu
        int delta = refDate.diff(this);
        return (6 + delta % 7 + 7) % 7;
    }
      
    String dayOfWeek(String language) {
        String[] en = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
        String[] de = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
        String[] fr = {"lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche"};
        String[] ar = {"Al-Ithnayn", "Ath-Thulāthā'", "Al-Arbiʿā'", "Al-Khamīs", "Al-Jumʿah", "As-Sabt", "Al-Aḥad"};
        return switch (language) {
            case "en" -> en[dayOfWeek()-1];
            case "de" -> de[dayOfWeek()-1];
            case "fr" -> fr[dayOfWeek()-1];
            case "ar" -> ar[dayOfWeek()-1];
            default -> throw new IllegalArgumentException("unknown language");
        };
    }

    public String toString(){
        return day + "." + month + "." + year;
    }
}

// Test code
LocalDate ldToday = LocalDate.now();
Date heute = new Date(ldToday.getDayOfMonth(), ldToday.getMonthValue(), ldToday.getYear());
Date b = heute.previousDate();
Date a = new Date(24,11,2025);

try {
    assert false;
    System.out.println("WARNING: Test cases are ignored. Please enable assertions with `jshell -R-ea`");
} catch (AssertionError e) {
    System.out.print("Running tests ... ");
}
assert Date.of(2025,11,12).toString().equals("12.11.2025");
assert Date.of(2025,11,12).nextDay().toString().equals("13.11.2025");
assert Date.of(2025,11,12) != Date.of(2025,11,12).nextDay(); // nextDay() creates new Date object
assert Date.of(2025,11,30).nextDay().toString().equals("1.12.2025");
assert Date.of(2025,12,31).nextDay().toString().equals("1.1.2026");
assert Date.of(2025,1,1).plus(365).isEqual(Date.of(2026,1,1));
assert Date.of(2025,1,1).plus(365).plus(-365).isEqual(Date.of(2025,1,1));
assert Date.isLeapYear(2024) && Date.of(2024,1,1).plus(366).isEqual(Date.of(2025,1,1));
assert Date.of(2024,1,1).plus(366).plus(-366).isEqual(Date.of(2024,1,1));
assert Date.of(2000,1,1).dayOfWeek("de").equals("Samstag");
assert Date.of(1777,4,30).dayOfWeek("de").equals("Mittwoch");
assert Date.of(1855,2,23).dayOfWeek("de").equals("Freitag");

try {
    assert false;
} catch (AssertionError e) {
    IO.println("done!");
}