import java.awt.event.KeyEvent;
import java.util.Arrays;

import static java.awt.Color.*;

public class Game extends Ease.Ease {
    int direction;
    int score;
    int[] bodyX;
    int[] bodyY;
    int beanX;
    int beanY;
    int size;
    int upDateIndex;
    boolean lose;
    boolean canTurn;
    int handicap;
    int steps;
    public Game(int size, int handicap) throws InterruptedException {
        super(size * 10, size * 10,100);
        this.steps = 0;
        this.handicap = handicap;
        this.direction = 0;
        this.score = 1;
        this.size = size;
        this.upDateIndex = 1;
        bodyX = new int[size * size];
        bodyY = new int[size * size];
        bodyX[0] = 0;
        bodyY[0] = (int) Math.floor((double) size / 2);
        beanX = handicap + (int)(Math.random() * (size - (2 * handicap)));
        beanY = handicap + (int)(Math.random() * (size - (2 * handicap)));
        this.lose = false;
        this.canTurn = true;
    }

    public void paint() {
        rect(0,0,width,height,darkGray);
        steps++;
        text(0,size*10/3,"Score: " + score,lightGray,20);
        canTurn = true;
        upDateIndex+=1;
        if(upDateIndex>score-1) {
            upDateIndex = 1;
        }
        bodyX[upDateIndex] = bodyX[0];
        bodyY[upDateIndex] = bodyY[0];
        rect(beanX * 10,beanY * 10,10,10,gray);
        if(beanX == bodyX[0] && beanY == bodyY[0]) {
            System.out.println("Munch.");
            refreshRate *= 1;
            score++;
            bodyX[score-1]=bodyX[0];
            bodyY[score-1]=bodyY[0];
            boolean ready = false;
            while(!ready) {
                ready = true;
                beanX = handicap + (int)(Math.random() * (size - (2 * handicap)));
                beanY = handicap + (int)(Math.random() * (size - (2 * handicap)));
                for(int i = 0; i < score; i ++) {
                    if(beanX==bodyX[i] && beanY==bodyY[i]) {
                        ready = false;
                    }
                }
            }
        }
        if (direction % 2 == 0 && canTurn && !lose) {
            bodyX[0] += (1 - direction);
        } else if(canTurn && !lose) {
            bodyY[0] += (direction - 2);
        }
        for(int i = 0; i < score; i++) {
            rect(bodyX[i]*10,bodyY[i]*10,10,10,white);
        }
        for(int i = 2; i < score; i ++) {
            if(bodyX[0]==bodyX[i] && bodyY[0]==bodyY[i]) {
                lose = true;
                System.err.println("LOST");
            }
        }
        if(bodyX[0] > size-1 || bodyX[0] < 0 || bodyY[0] > size-1 || bodyY[0] < 0) {
            lose = true;
        }
        if(lose) {
            refreshRate = 100;
            text(size*4,size*4,"You Lost...",lightGray,size/2);
            score--;
            if(score<0) {
                direction = 0;
                bodyX = new int[size * size];
                bodyY = new int[size * size];
                bodyX[0] = 0;
                bodyY[0] = (int) Math.floor((double) size / 2);
                beanX = (int)(Math.random()*size);
                beanY = (int)(Math.random()*size);
                lose = false;
                score = 1;
            }
        }
    }
    public void keyPressed(KeyEvent e) {
        if(canTurn) {
            if (e.getKeyChar() == 'd' && direction != 2) {
                this.direction = 0;
            } else if (e.getKeyChar() == 'w' && direction != 3) {
                this.direction = 1;
            } else if (e.getKeyChar() == 'a' && direction != 0) {
                this.direction = 2;
            } else if (e.getKeyChar() == 's' && direction != 1) {
                this.direction = 3;
            }
            canTurn = false;
        }
    }
    public float setFloat(double diff,boolean extra) {
        if(extra) {
            if(diff%2==0) {
                System.err.println((float)(diff/(double)width*10));
                return (float) (10*diff/(double)width);
            } else {
                return (float)(10*(width-diff)/(double)width);
            }
        }
        return (float)diff/100;
    }
}
