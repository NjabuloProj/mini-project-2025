package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import java.util.*;

public class AnimalCounter {

    private final int width;
    private final int height;
    private final boolean[][] visited;
    private final boolean[][] isEdge;

    public AnimalCounter(Image edgeMapImage) {
        this.width = (int) edgeMapImage.getWidth();
        this.height = (int) edgeMapImage.getHeight();
        this.visited = new boolean[height][width];
        this.isEdge = new boolean[height][width];

        PixelReader reader = edgeMapImage.getPixelReader();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = reader.getColor(x, y);
                if (isBlack(color)) {
                    isEdge[y][x] = true;
                }
            }
        }
    }

    public int countAnimals() {
        int count = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isEdge[y][x] && !visited[y][x]) {
                    bfs(x, y);
                    count++;
                }
            }
        }

        return count;
    }

    private void bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startY][startX] = true;

        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}}; // 8-connected

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1];

            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (isValid(newX, newY) && isEdge[newY][newX] && !visited[newY][newX]) {
                    queue.add(new int[]{newX, newY});
                    visited[newY][newX] = true;
                }
            }
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    private boolean isBlack(Color color) {
        return color.getRed() < 0.2 && color.getGreen() < 0.2 && color.getBlue() < 0.2;
    }
} 
