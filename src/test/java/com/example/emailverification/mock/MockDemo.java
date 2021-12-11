package com.example.emailverification.mock;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MockDemo {

    @Mock
    List<Integer> mockList;

    @Test
    public void testMockList() {
        Mockito.when(mockList.size()).thenReturn(100);
        assertEquals(mockList.size(), 100);
    }

    @Test
    public void testSpyList() {
        List<Integer> spyList = Mockito.spy(new ArrayList<>());

        spyList.add(100);
        spyList.add(200);

        Mockito.verify(spyList).add(100);

        assertEquals(spyList.size(), 2);
    }
}
