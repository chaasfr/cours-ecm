package fr.cmm.helper;

import org.junit.Test;
import org.junit.Assert;

import javax.inject.Inject;

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
        Assert.assertEquals(0,pagination.getPageCount());
    }
}
