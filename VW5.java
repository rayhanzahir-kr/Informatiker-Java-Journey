boolean isLeapYear(int year) {
    if (year % 400 == 0) return true;
    if (year % 100 == 0) return false;
    return year % 4 == 0;
}

class Person {
    String name;
    int geburtsjahr;
    Person(String name, int geburtsjahr) {
        this.name = name;
        this.geburtsjahr = geburtsjahr;
    }
    public String toString() {
        return "Person(" + name + ", " + geburtsjahr + ")";
    }
}

//int i = 0;
Person p = new Person(null, -1);
Person p2 = new Person(null, -2);
p2.name = "Andi";
p2.geburtsjahr = 2001;

Person p3 = new Person("Tina", 2002);

class Date {
    int year; // verkürzbar: int year, month, day;
    int month;
    int day;
    static boolean isLeapYear(int year) {
        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        return year % 4 == 0;
    }
    Date(int year, int month, int day) {
        assert year >= 0;
        assert month >= 1 && month <= 12;
        assert day >= 1 && day <= maxNumberOfDays(month, year);
        this.year = year;
        this.month = month;
        this.day = day;
    }
    static int maxNumberOfDays(int month, int year) {
        // Die Anzahl der Tage ist (wegen des Sonderfalls Februar) vom Jahr abhängig
        int[] maxDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month == 2 && Date.isLeapYear(year))
            return 29;
        return maxDays[month - 1];
    }
    Date nextDay() {
        if (day < maxNumberOfDays(month, year)) {
            day = day + 1;
            return this;
        }
        day = 1;
        if (month < 12) {
            month += 1;
            return this;
        }
        month = 1;
        year += 1;
        return this;
    }
    Date plus(int days) {
        assert days >= 0;
        while (days != 0) {
            nextDay();
            days -= 1;
        }
        return this;
    }
    public String toString() {
        return "" + day + "." + month + "." + year;
    }
} 

Date heute = new Date(2025, 11, 12);


assert heute.toString().equals("12.11.2025");
assert heute.nextDay().toString().equals("13.11.2025"); // heute ist jetzt morgen ;-)
assert new Date(2025, 11, 30).nextDay().toString().equals("1.12.2025");
assert new Date(2025, 12, 31).nextDay().toString().equals("1.1.2026");


// Aufgabe: Schreiben Sie eine Methode `nextDay`, die ein Date-Objekt auf den nächsten Tag setzt.
// Große Frage: Wie vermeide ich die if-HÖLLE?
// Es ist zu hart, gleich die fertige Methode zu schreiben. Zerlegen Sie das Problem!
// Sie denken zu kompliziert. Einfacher: Welches kleine Teilproblem wäre schön, dafür
// eine Lösung zu haben?
// Ansatz: Date bekommt eine Methode maxNumberOfDays, die mir die maximale Anzahl an
// Tagen für den Monat zurückgibt, in dem das Datum liegt.
// if-HÖLLE vermeiden: Statt Entscheidungsdaten in if-Anweisungen, Daten in einem
// Array ablegen.
// Gelöst. Nächste Methode: `Date plus(int days)` -- scheint zu funktionieren
// Als nächstes: Date previousDay()
// Machen Sie gerade eine Entdeckung? Wir haben ein kleines "Problem".
// Wenn die Methode `maxNumbersOfDays` statisch wird, kann ich sie im Konstruktor
// in einem `assert` verwenden, um zu schauen, ob ein gültige Datum angegeben worden ist.