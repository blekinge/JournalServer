package dk.statsbiblioteket.journalserver;

import com.leansoft.bigqueue.utils.FileUtil;
import dk.statsbiblioteket.doms.webservices.configuration.ConfigCollection;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: abr
 * Date: 5/3/13
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class SingletonJournalTest {

    SingletonJournal journal;

    @org.junit.Before
    public void setUp() throws Exception {
        ConfigCollection.getProperties().load(Thread.currentThread().getContextClassLoader().getResourceAsStream("test.properties"));
        journal = SingletonJournal.getInstance();
    }

    @org.junit.After
    public void tearDown() throws Exception {
        String journalDir = ConfigCollection.getProperties().getProperty("dk.statsbiblioteket.journalserver.dir");
        // FileUtil.deleteDirectory(new File(journalDir));
    }

    @org.junit.Test
    public void testPutAndGet() throws Exception {
        String payload1 = "string1/string2";
        String id1 = journal.put(payload1);
        assertEquals(payload1,journal.get(id1)) ;


        String payload2 = "string2/string1";
        String id2 = journal.put(payload2);
        assertEquals(payload2,journal.get(id2)) ;
    }



    @org.junit.Test
    public void testGet() throws Exception {

    }
}
