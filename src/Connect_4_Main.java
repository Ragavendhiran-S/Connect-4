import java.util.Arrays;
import java.util.Scanner;

public class Connect_4_Main {
    char[][] blocks;
    int[] blockPosition;
    public int left = 0,right = 0,count = 0;
    public Connect_4_Main() {
        this.blocks = new char[6][7];
        this.blockPosition = new int[7];
        Arrays.fill(this.blockPosition,5);
        for(char[] block: this.blocks) {
            Arrays.fill(block,' ');
        }
    }
    void initDependecies() {
        this.left = 0;
        this.right = 0;
        this.count = 0;
    }
    public void displayBlocks() {
        System.out.println("\n"+" _  ".repeat(7));
        for(int i=0; i<6; i++) {
            for(int j=0; j<7; j++) {
                System.out.print(" "+blocks[i][j]+" |");
            }
            System.out.println("\n"+" _  ".repeat(7));
        }
        for(int i=1; i<=7; i++) {
            System.out.print(" "+i+" |");
        }
    }
    boolean check4Connected(int num) {
        return num >= 4;
    }
    public boolean gameOver(int positionY,char symbol) {
        int positionX = blockPosition[positionY]--;
        int yTemp = positionY-1,count = 0;
        //horizontal check
        initDependecies();
        while(yTemp >= 0 && blocks[positionX][yTemp--] == symbol && count++ < 4)
            left++;
        yTemp = positionY+1;
        count = 0;
        while(yTemp < 7 && blocks[positionX][yTemp++] == symbol && count++ < 4)
            right++;
        if(check4Connected(right+left+1)) return true;

        //vertical check
        initDependecies();
        int xTemp = positionX - 1;
        count = 0;
        while(xTemp >= 0 && blocks[xTemp--][positionY] == symbol && count++ < 4)
            left++;
        xTemp = positionX+1;
        count = 0;
        while(xTemp < 6 && blocks[xTemp++][positionY] == symbol && count++ < 4)
            right++;
        if(check4Connected(right+left+1)) return true;

        //left to right diagonal
        initDependecies();
        xTemp = positionX - 1;
        yTemp = positionY - 1;
        count = 0;
        while(xTemp >= 0 && yTemp >= 0 && blocks[xTemp][yTemp] == symbol && count++<4)
            left++;
        xTemp = positionX-1;
        yTemp = positionY+1;
        count = 0;
        while(xTemp <positionX && yTemp <positionY && blocks[xTemp][yTemp] == symbol && count++<4)
            right++;
        if(check4Connected(right+left+1)) return true;

        //right to left diagonal
        initDependecies();
        xTemp = positionX + 1;
        yTemp = positionY + 1;
        count = 0;
        while(xTemp <positionX && yTemp < positionY && blocks[xTemp][yTemp] == symbol && count++<4)
            left++;
        xTemp = positionX+1;
        yTemp = positionY-1;
        count = 0;
        while(xTemp < positionX && yTemp <positionY && blocks[xTemp][yTemp] == symbol && count++<4)
            right++;
        return check4Connected(right + left + 1);
    }
    public static void main(String[] args) {
        System.err.println("           CONNECT - 4");
        Connect_4_Main game = new Connect_4_Main();
        Scanner sc = new Scanner(System.in);
        int positionX, positionY;
        boolean chance = true;
        game.displayBlocks();
        System.out.println();
        while(true) {
            System.out.print((chance?"X":"0")+" Enter :");
            positionY = sc.nextInt() - 1;
            positionX = game.blockPosition[positionY];
            if(positionX < 0) {
                System.out.println("Invalid Location !");
            }
            else {
                game.blocks[positionX][positionY] = chance? 'X' : 'O';
                if(game.gameOver(positionY,chance?'X':'O')) {
                    game.displayBlocks();
                    System.out.println("\n\nCongrats "+(chance ? "X" : "O")+" Wins !!");
                    break;
                }
                game.displayBlocks();
                System.out.println();
                chance = !chance;
            }
        }
    }
}