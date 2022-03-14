import java.util.TimerTask;

public class TimerHelper extends TimerTask {

    Store store;

    public void run(){
        store.purchasedProducts.clear();
    }

}
