package graph;

/**
 * @author wuxiaoming
 * @date 2017/11/29 22:30
 */

/**
 * 邻接表表示图
 */
public class Graph {

    private int[] vertices;//顶点数组
    private int[][] matrix;//图的节点的边
    private int verticesSize;
    private static final int MAX_WEIGHT = 0XFFF8;

    public Graph(int size) {
        this.verticesSize = size;
        this.vertices = new int[size];
        this.matrix = new int[size][size];
        //初始化顶点
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = i;
        }
    }

    /**
     * 计算v1到v2的权重(路径长度)
     *
     * @param v1
     * @param v2
     * @return
     */
    private int getWidget(int v1, int v2) {
        int weight = matrix[v1][v2];
        return weight == 0 ? 0 : (weight == MAX_WEIGHT ? -1 : weight);
    }

    /**
     * 获取所有的顶点
     *
     * @return
     */
    private int[] getVertices() {
        return vertices;
    }

    /**
     * 获取顶点V的出度
     *
     * @param v
     * @return
     */
    private int getOutDegree(int v) {
        int count = 0;
        for (int i = 0; i < matrix[v].length; i++) {
            if (matrix[v][i] != 0 && matrix[v][i] != MAX_WEIGHT) {
                count++;
            }
        }
        return count;
    }

    /**
     * 获取节点V的入度
     *
     * @param v
     * @return
     */
    private int getInDegree(int v) {
        int count = 0;
        for (int i = 0; i < verticesSize; i++) {
            if (matrix[i][v] != 0 && matrix[i][v] != MAX_WEIGHT) {
                count++;
            }
        }
        return count;
    }

    /**
     * 获取顶点v的第一个邻接点
     *
     * @param v
     * @return
     */
    private int getFirstNeighbor(int v) {
        for (int i = 0; i < verticesSize; i++) {
            if (matrix[v][i] > 0 && matrix[v][i] != MAX_WEIGHT) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找节点v的邻接点index的下一个邻接点
     * @param v 节点
     * @param index 节点
     * @return
     */
    private int getNextNeighbor(int v,int index){
        for (int i = index+1; i < verticesSize; i++) {
            if (matrix[v][i]>0&&matrix[v][i]!=MAX_WEIGHT) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        Graph graph = new Graph(5);
        int[] a0 = new int[]{0,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,6};
        int[] a1 = new int[]{9,0,3,MAX_WEIGHT,MAX_WEIGHT};
        int[] a2 = new int[]{2,MAX_WEIGHT,0,5,MAX_WEIGHT};
        int[] a3 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,0,1};
        int[] a4 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,0};
        graph.matrix[0] = a0;
        graph.matrix[1] = a1;
        graph.matrix[2] = a2;
        graph.matrix[3] = a3;
        graph.matrix[4] = a4;

        System.out.println(graph.getInDegree(2));
        System.out.println(graph.getOutDegree(2));
        System.out.println(graph.getFirstNeighbor(3));
        System.out.println(graph.getNextNeighbor(2,2));
    }
}
