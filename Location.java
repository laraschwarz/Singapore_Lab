public class Location{
    String name;
    String abbr;
    int x;
    int y;

    public Location(String name, String abbr, int x, int y){
        this.name = name;
        this.abbr = abbr;
        this.x = x;
        this.y = y;
    }
            
    public String getName(){
        return name;
    }

    public String getAbbr(){
        return abbr;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public int hashCode() {
        char char1 = abbr.charAt(0);
        char char2 = abbr.charAt(1);
        char char3 = abbr.charAt(2);

        int value1 = (int) char1 - 97;
        int value2 = (int) char2 - 97;
        int value3 = (int) char3 - 97;

        return (value1*26*26) + (value2*26) + value3;
    }

    @Override
    public boolean equals(Object input) {
        return abbr.equals(((Location)(input)).getName());
    }

    public String toString(){
        return name + " (" + abbr + ") ";
    }
}