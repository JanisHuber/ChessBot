public class Move {
    public int startY;
    public int startX;
    public int endY;
    public int endX;
    public int depth;
    public int score;
    public int situation;

    public Move(int startY, int startX, int endY, int endX, int depth, int situation) {
        this.startY = startY;
        this.startX = startX;
        this.endY = endY;
        this.endX = endX;
        this.depth = depth;
        this.score = 0;
        this.situation = situation;
    }
}
