package ru.naumen.collection.task2;

public enum  OrderType{
    NO_FOOD("без еды и напитков"),
    FOOD_AND_DRINKS("еда и напитки"),
    DRINK("только напитки");

    private final String desc;

    OrderType(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return desc;
    }
}
