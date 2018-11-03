public class LinkedList {
    Node head;
    static class Node{
        int val;
        Node next;
        Node(int num){
            val = num;
            next = null;
        }
    }

    public static LinkedList buildList(int num){
        LinkedList res = new LinkedList();
        if(num >= 1){
            res.head = new Node((int)(Math.random() * 500));
            Node tra = res.head;
            for(int i = 0; i < num - 1; i++){
                tra.next = new Node((int)(Math.random() * 500));
                tra = tra.next;
            }
        }
        return res;
    }

    public static void traverseList(Node n){
        if(n != null){
            System.out.print(n.val + " ");
            traverseList(n.next);
        }
        else
            System.out.println();
    }

    public static void sortList(LinkedList l){
        Node pre_max = null;
        Node max = l.head;
        Node tra = l.head;
        while (tra.next != null){
            if (tra.next.val > max.val){
                pre_max = tra;
                max = tra.next;
            }
            tra = tra.next;
        }
        if(pre_max != null){
            pre_max.next = pre_max.next.next;
            max.next = l.head;
            l.head = max;
        }
        Node marker = l.head;
        while (marker.next != null){
            pre_max = marker;
            max = marker.next;
            tra = marker.next;
            while (tra.next != null){
                if (tra.next.val > max.val){
                    pre_max = tra;
                    max = tra.next;
                }
                tra = tra.next;
            }
            pre_max.next = pre_max.next.next;
            max.next = l.head;
            l.head = max;
        }
    }

    public static void main(String[] args){
        LinkedList list = buildList(20);
        traverseList(list.head);
        sortList(list);
        traverseList(list.head);
    }
}
