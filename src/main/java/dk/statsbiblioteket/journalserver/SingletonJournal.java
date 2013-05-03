package dk.statsbiblioteket.journalserver;

import com.leansoft.bigqueue.BigArrayImpl;
import com.leansoft.bigqueue.IBigArray;
import dk.statsbiblioteket.doms.webservices.configuration.ConfigCollection;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: abr
 * Date: 5/3/13
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class SingletonJournal {

    private static SingletonJournal instance;
    public synchronized static SingletonJournal getInstance() throws IOException {
        if (instance != null){
            return instance;
        }
        String journalDir = ConfigCollection.getProperties().getProperty("dk.statsbiblioteket.journalserver.dir");
        String journalName = ConfigCollection.getProperties().getProperty("dk.statsbiblioteket.journalserver.name");
        int journalSize = Integer.parseInt(ConfigCollection.getProperties().getProperty("dk.statsbiblioteket.journalserver.pageSize"));
        IBigArray bigArray = new BigArrayImpl(journalDir,journalName,journalSize);
        instance = new SingletonJournal(bigArray);
        return getInstance();
    }



    private IBigArray bigArray;

    public SingletonJournal(IBigArray bigArray) {
        this.bigArray = bigArray;
    }


    public String put(String payload) throws IOException {
        long index = bigArray.append(payload.getBytes(utf8()));
        return ""+index;
    }

    public String get(String id) throws IOException {

        long index = Long.parseLong(id);
        byte[] payload = bigArray.get(index);
        String result = new String(payload, utf8());
        return result;
    }

    private static Charset utf8(){
        return Charset.forName("UTF-8");
    }
}
