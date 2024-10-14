package lab2.task1;

public class Car {
    public class Engine {
        private boolean working;

        public Engine() {
            this.working = false;
        }

        public void startEngine(){
            this.working = true;
        }

        public void stopEngine(){
            this.working = false;
        }

        public boolean isWorking() {
            return working;
        }
    }

    private String brand;
    private Engine engine;

    public Car(String brand) {
        this.brand = brand;
        this.engine = new Engine();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Engine getEngine() {
        return engine;
    }

    public boolean isEngineRunning(){
        return engine.isWorking();
    }
}
