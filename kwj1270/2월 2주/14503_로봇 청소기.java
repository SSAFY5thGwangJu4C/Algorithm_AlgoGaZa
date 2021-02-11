# 14503_로봇 청소기       
> 문제를 잘.... 읽어야겠다.      


```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// N x M
// 동서 남북 이동 가능
// 현재위치 청소 -> 청소 했으면 다시 안감
// 왼쪽방향에 청소 공간 존재하면 거기로 이동
// 청소 공간없다면 계속 돈다.
// 4방향 모두 청소, 벽이면 보는 방향 기준으로 뒤로가기
// 뒤쪽도 못가면 후진

class Robot {
    int x;
    int y;
    int dir;

    public Robot(int x, int y, int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

class Main {
    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder STRING_BUILDER = new StringBuilder();
    private static StringTokenizer stringTokenizer;

    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    private static int[] dirs = {3,0,1,2};
    private static int[] backDirs = {2,3,0,1};

    private static int dir;
    private static int n;
    private static int m;

    private static int start_x;
    private static int start_y;

    private static boolean isVisited[][];
    private static int map[][];
    private static final Queue<Robot> Q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(BUFFERED_READER.readLine(), " ");
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[n][m];
        isVisited = new boolean[n][m];
        stringTokenizer = new StringTokenizer(BUFFERED_READER.readLine(), " ");
        start_x = Integer.parseInt(stringTokenizer.nextToken());
        start_y = Integer.parseInt(stringTokenizer.nextToken());
        dir = Integer.parseInt(stringTokenizer.nextToken());

        for(int i=0; i < n; i++){
            stringTokenizer = new StringTokenizer(BUFFERED_READER.readLine(), " ");
            for(int j=0; j < m; j++){
                if(stringTokenizer.nextToken().equals("0"))continue;
                map[i][j] = 1;
            }
        }

        Q.offer(new Robot(start_x, start_y, dir));
        isVisited[start_x][start_y] = true;
        int answer = 1;
        while (!Q.isEmpty()){
            int now_x = Q.peek().x;
            int now_y = Q.peek().y;
            int now_dir = Q.peek().dir;
            Q.poll();
            int origin_dir = now_dir;
            boolean flag = true;
            for(int i=0; i < 4; i++){
                now_dir = dirs[now_dir];
                int nx = now_x + dx[now_dir];
                int ny = now_y + dy[now_dir];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(map[nx][ny] == 0 && isVisited[nx][ny] == false) {
                    answer++;
                    isVisited[nx][ny] = true;
                    Q.offer(new Robot(nx, ny, now_dir));
                    flag = false;
                    break;
                }
            }
            if(flag){
                int next_dir = backDirs[origin_dir];
                int nx = now_x + dx[next_dir];
                int ny = now_y + dy[next_dir];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(map[nx][ny] == 0) {
                    Q.offer(new Robot(nx, ny, origin_dir));
                }
            }

        }
        System.out.println(answer);
    }


}
```
