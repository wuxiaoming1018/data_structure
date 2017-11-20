package link;

/**
 * @author wuxiaoming
 * @date 2017-11-20 14:23
 */
public class LinkListApp {
    public static void main(String[] args) {
        LinkList linkList = new LinkList();
        linkList.insertFirst("wuxiaoming", 2);
        linkList.insertFirst("chicken", 2);
        linkList.insertFirst("pig", 4);
        linkList.insertFirst("sheep", 4);
        linkList.displayList();
//        while (!linkList.isEmpty()){
//            Link aLink = linkList.deleteFirst();
//            System.out.print("Delete: ");
//            aLink.displayLink();
//        }
        Link link = linkList.find("pig");
        if (link != null) {
            System.out.println("Found link with key: " + link.name);
        }else{
            System.out.println("Can't find link");
        }
        Link delete = linkList.delete("pig");
        if (delete != null) {
            System.out.println("Delete link with key: "+delete.name);
        }else{
            System.out.println("Can't delete link");
        }

        linkList.displayList();
    }
}
