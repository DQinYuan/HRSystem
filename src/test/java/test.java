import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by duqinyuan on 2017/3/29.
 *
 * @author duqinyuan
 * @version 1.0
 */
public class test {
    public static void main(String[] args) {
        System.out.println(new Date().toString());
    }

    public static String   test(){
        try{
            return "try";
        } finally {
            System.out.println("finaly");
        }
    }
}
