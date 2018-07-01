package com.yevtsy.fifteens;

import com.yevtsy.fifteens.model.Board;
import com.yevtsy.fifteens.model.NBoard;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class GameTest {
    @Test
    public void testNotEqualBoards() {
        Board b1 = new NBoard(3, new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 0});
        Board b2 = new NBoard(3, new byte[] {1, 2, 5, 4, 3, 6, 8, 7, 0});

        Assert.assertNotEquals(b1, b2);
    }

    @Test
    public void testEqualsBoards() {
        Board b1 = new NBoard(3, new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 0});
        Board b2 = new NBoard(3, new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 0});

        Assert.assertEquals(b1, b2);
    }

    @Test
    public void testMinPQ() {
        Queue<Integer> pq = new PriorityQueue<>((Integer o1, Integer o2) -> {
            if (o1 < o2) {
                return -1;
            } else if (o1 > o2) {
                return 1;
            }

            return 0;
        });

        pq.add(30);
        pq.add(18);
        pq.add(-1);
        pq.add(192);
        pq.add(80);
        pq.add(4);

        Assert.assertEquals(pq.poll(), Integer.valueOf(-1));
        Assert.assertEquals(pq.poll(), Integer.valueOf(4));
        Assert.assertEquals(pq.poll(), Integer.valueOf(18));
        Assert.assertEquals(pq.poll(), Integer.valueOf(30));
        Assert.assertEquals(pq.poll(), Integer.valueOf(80));
        Assert.assertEquals(pq.poll(), Integer.valueOf(192));
    }

    @Test
    public void testBinarySearch() {
        byte[] array = {3, 5, 10, 17, 0};
        final int ind = Arrays.binarySearch(array, (byte) 10);
        Assert.assertEquals(ind, 2);

        final int not_found = Arrays.binarySearch(array, (byte) 11);
        Assert.assertTrue(not_found < 0);
    }

    @Test
    public void testHeuristic() {
        byte[] board = {4, 6, 3, 8, 7, 12, 9, 14, 15, 13, 1, 5, 2, 10, 11, 0};

        int heuristic = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                if (board[i*4 + j]==0)
                    continue;
                int x = (board[i * 4 + j]-1)/4;
                int y = (board[i * 4 + j]-1)%4;
                heuristic += (Math.abs(x-i) + Math.abs(y-j));
            }

        Assert.assertEquals(heuristic, 36);
    }
}
