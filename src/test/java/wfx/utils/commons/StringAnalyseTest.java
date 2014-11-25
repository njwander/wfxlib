package wfx.utils.commons;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * Created by fanxin.wfx 2014.11.25
 */
public class StringAnalyseTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void getIdbGroup() {
        Assert.assertEquals(StringAnalyse.getIdbGroup(100034l, 256), 34);
        Assert.assertEquals(StringAnalyse.getIdbGroup(1000000000000034l, 256), 34);
        Assert.assertEquals(StringAnalyse.getIdbGroup(100002334l, 256), 30);
        Assert.assertEquals(StringAnalyse.getIdbGroup(10000512l, 256), 0);
        Assert.assertEquals(StringAnalyse.getIdbGroup(512l, 256), 0);
    }
} 
