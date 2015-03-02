package pl.kap11.rozliczator.event.storage;

public class EventStorageFactory {

    private static enum Singleton{
        INSTANCE;

        private EventStorage instance;
    }

    public static EventStorage getEventStorage(){
        if(Singleton.INSTANCE.instance == null){
            Singleton.INSTANCE.instance = new TemporaryEventStorage();
        }
        return Singleton.INSTANCE.instance;
    }

}
