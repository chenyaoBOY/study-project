package com.study.jdk.enumlearn;

public enum Season {

    SPRING(1,"春天",Color.GREEN),
    SUMMER(2,"夏天",Color.RED),
    AUTUMN(3,"秋天",Color.BLUE),
    WINTER(4,"冬天",Color.YELLOW);


    private  int index;
    private  String name;
    private  Color color;

    Season(int index, String name, Color color) {
        this.index = index;
        this.name = name;
        this.color = color;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int compareSeason(Season one, Season two){
        return one.compareTo(two);
    }
}
