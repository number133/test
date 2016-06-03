/**
 * Created by Abylay.Sabirgaliyev
 * Created: 01.06.2016 9:50
 * Copyright © LLP JazzSoft
 */
public class TrafficLightSimulator implements Runnable {
    private Thread thread;//holds the thread that runs the simulation
    private TrafficLightColor color;//holds the traffic light color
    boolean stop=false;//set to true to stop the simulation
    boolean changed=false;//true when the light has changed
    public TrafficLightSimulator(TrafficLightColor inital){
        color=inital;
        thread=new Thread(this);
        thread.start();
    }
    public TrafficLightSimulator(){
        color=TrafficLightColor.RED;
        thread=new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        //start up the light
        while(!stop){
            try{
                switch(color){
                    case GREEN:
                        Thread.sleep(10000);//green sleeps for 10 seconds
                        break;
                    case RED:
                        Thread.sleep(2000);//yellow for 2 seconds
                        break;
                    case YELLOW:
                        Thread.sleep(12000);//red for 12 seconds
                        break;

                }
            }catch(Exception e){

            }
            changeColor();
        }
    }
    synchronized void changeColor(){
        switch(color){
            case RED:
                color=TrafficLightColor.GREEN;
                break;
            case YELLOW:
                color=TrafficLightColor.RED;
                break;
            case GREEN:
                color=TrafficLightColor.YELLOW;
        }
        changed=true;
        System.out.println("Notfiy Called We changed the light");
        notify();
    }
    synchronized void waitForChange(){
        try{
            while(!changed){
                System.out.println("waiting for Light to change");
                wait();
            }
            changed=false;

        }catch(Exception e){

        }
    }
    synchronized TrafficLightColor getColor(){
        return color;
    }
    synchronized void cancel(){
        stop=true;
    }

}
