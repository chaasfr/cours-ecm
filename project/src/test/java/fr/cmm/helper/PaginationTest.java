package fr.cmm.helper;

import org.junit.Test;
import org.junit.Assert;

import static java.util.Arrays.asList;

/**
 * Created by christian on 18/12/15.
 */
public class PaginationTest {

     Pagination pagination = new Pagination();

    @Test
    public void countIsMultipleOfPageSize(){
        pagination.setPageSize(25);
        pagination.setCount(pagination.getPageSize()*2);
        Assert.assertEquals(2,pagination.getPageCount());
    }

    @Test
    public void countIsNotMultipleOfPageSize(){
        pagination.setPageSize(25);
        pagination.setCount(pagination.getPageSize()*2+1);
        Assert.assertEquals(3,pagination.getPageCount());
    }

    @Test
    public void countIsNull(){
        pagination.setPageSize(10);
        pagination.setCount(0);
        Assert.assertEquals(1,pagination.getPageCount());
    }

    @Test
    public void pagination(){
        pagination.setCount(100);
        pagination.setPageSize(1);
        pagination.setPageIndex(1);
        Assert.assertEquals(asList(1,2,3,4,5,6,7,8,9,10),pagination.getPages());
        pagination.setPageIndex(8);
        Assert.assertEquals(asList(4,5,6,7,8,9,10,11,12,13),pagination.getPages());
        pagination.setPageIndex(99);
        Assert.assertEquals(asList(91,92,93,94,95,96,97,98,99,100),pagination.getPages());
    }
}
