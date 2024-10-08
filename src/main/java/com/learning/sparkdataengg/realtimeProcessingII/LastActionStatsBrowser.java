package com.learning.sparkdataengg.realtimeProcessingII;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Iterator;
import java.util.Set;

public class LastActionStatsBrowser {
    public static void main(String[] args) {

        try {
            Jedis jedis =new Jedis("localhost");
            String lbKey = "last-action-stats";

            while (true) {

                //Query the leaderboard and print the results
                Set<Tuple> scores=
                        jedis.zrevrangeWithScores(
                                lbKey,0,-1);

                Iterator<Tuple> iScores = scores.iterator();
                int position=1;

                while (iScores.hasNext()) {
                    Tuple score= iScores.next();
                    System.out.println(
                            "Country Stats - " + position + " : "
                                    +  score.getElement() + " = " + score.getScore());
                    position++;
                }
                System.out.println("-------------------------------------------------------");

                Thread.currentThread().sleep(5000);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
}
