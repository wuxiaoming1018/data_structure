package graph;

import java.util.*;

/**
 * @author wuxiaoming
 * @date 2017-11-30 9:29
 */
public class PrimAlgorithm {
    private char[] vertices;
    private int[][] matrix;
    private int verticesSize;
    private static final int MAX_NUMBER = 0XFFF8;
    private char error = 'x';
    private List<Character> mCharList;
    private Map<Character,Integer> map = new HashMap<>();

    public PrimAlgorithm() {
        mCharList = new ArrayList<>();
        mCharList.add('A');//从‘A’开始操作
        vertices = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        this.verticesSize = vertices.length;
        matrix = new int[vertices.length][vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            map.put(vertices[i],i);
        }
    }

    /**
     * 获取顶点数组
     *
     * @return
     */
    private char[] getVertices() {
        return vertices;
    }

    /**
     * 获取两个节点之间的权值
     *
     * @param v1
     * @param v2
     * @return
     */
    private int getWidget(char v1, char v2) {
        int widget = matrix[map.get(v1)][map.get(v2)];
        return widget == 0 ? 0 : (widget == MAX_NUMBER ? -1 : widget);
    }

    /**
     * 获取某节点的第一个邻接点
     *
     * @param v1
     * @return
     */
    private char getFirstNeighbor(char v1) {
        for (int i = 0; i < verticesSize; i++) {
            if (matrix[map.get(v1)][i] != 0 && matrix[map.get(v1)][i] != MAX_NUMBER) {
                mCharList.add(vertices[i]);
                return vertices[i];
            }
        }
        return error;
    }

    private int getMinValue(List<Character> mList) {
        List<Integer> dataList = new ArrayList<>();
        for (Character character : mList) {
            char firstNeighbor = getFirstNeighbor(character);
            dataList.add(getWidget(character, firstNeighbor));
        }
        return Collections.min(dataList);
    }

    public static void main(String[] args) {
        PrimAlgorithm prim = new PrimAlgorithm();
        int[] a0 = new int[]{0, 50, 60, 0, 0, 0, 0};
        int[] a1 = new int[]{50, 0, 0, 65, 40, 0, 0};
        int[] a2 = new int[]{60, 0, 0, 52, 0, 0, 45};
        int[] a3 = new int[]{0, 65, 52, 0, 50, 30, 42};
        int[] a4 = new int[]{0, 40, 0, 50, 0, 70, 0};
        int[] a5 = new int[]{0, 0, 0, 30, 70, 0, 0};
        int[] a6 = new int[]{0, 0, 45, 42, 0, 0, 0};
        prim.matrix[0] = a0;
        prim.matrix[1] = a1;
        prim.matrix[2] = a2;
        prim.matrix[3] = a3;
        prim.matrix[4] = a4;
        prim.matrix[5] = a5;
        prim.matrix[6] = a6;
        /*prim.getFirstNeighbor('A');
        prim.getMinValue(prim.mCharList);
        for (Character character : prim.mCharList) {
            System.out.print(character+" ");
        }*/
        String as = "15847895425986245";
        System.out.println(as.substring(10,13));
        System.out.println(getLength(as));
    }

    public static int getLength(String s) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为3，否则为1
        if (s != null) {
            for (int i = 0; i < s.length(); i++) {
                String temp = s.substring(i, i + 1);
                if (temp.matches(chinese)) {
                    valueLength += 3;
                } else {
                    valueLength += 1;
                }
            }
        }
        return valueLength;
    }
}
