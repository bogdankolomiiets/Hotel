package com.hotel.mariam.logic;

import com.hotel.mariam.ConnectionProvider;
import org.junit.Assert;
import org.junit.Test;

public class ConnectionProviderTest extends Assert {

    @Test
    public void getOneConnectionTest(){
        assertNotNull(ConnectionProvider.getConnection());
    }
}
