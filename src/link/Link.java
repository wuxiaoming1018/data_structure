package link;

/**
 * @author wuxiaoming
 * @date 2017-11-20 14:21
 */
public class Link {
    public String name;
    public int leg;
    public Link next;

    public Link(String name, int leg) {
        this.name = name;
        this.leg = leg;
    }

    public void displayLink() {
        System.out.println(name + " has " + leg + " legs");
    }
}
