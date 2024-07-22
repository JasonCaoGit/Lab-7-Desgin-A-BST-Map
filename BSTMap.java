package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Random;

public class BSTMap<K extends Comparable<K>, V> implements bstmap.Map61B<K, V> {
    private K key;
    private V value;
    private BSTMap left;
    private BSTMap right;
    private Sentinel sentinel;


    private class Sentinel {
        private BSTMap<K, V> next;
        public Sentinel(BSTMap<K, V> next) {
            this.next = next;
        }
    }


    public V remove(K key) {
        //if the current node equals to the key
        //case 1 no child
        //connection to its parent is severed
        //case 2 one child, severe its connection to its parent, its parent connects to its child
        // case 3 two children find its predecessor or successor
        //predecessor: go to its left and continue goes right to the last node
        //delete the predecessor and change the case 3 node to the predecessor




        //We have a note that is to be removed

        /*
        * For different
        * */
        if (this.key.equals(key)) {
            if (this.left == null && this.right == null) {
                V ret = (V) this.value;
                this.sentinel.next = null;
                return ret;
            } else if (this.right != null || this.left != null) {
                if (this.right != null) {
                    V ret = (V) this.value;
                    sentinel.next = this.right;//connecting this(its parent) to its children
                    return ret;
                } else {
                    V ret = (V) this.value;
                    sentinel.next = this.left;
                    return ret;
                }

            } else {
                BSTMap<K, V> toRemove = this.left;
                        V ret = (V) this.value;
                        while (toRemove.right != null) {
                            toRemove = toRemove.right;
                        }
                        this.key = toRemove.key;
                        this.value = toRemove.value;
                        remove(toRemove.key);
                        return ret;

            }
        }






        if (this.key.compareTo(key) < 0) {
            //if the right of this has nodes
            if (this.right != null) {
                //if the right of the node is what we wanna find
                if (this.right.key.equals(key)) {
                    //if the key of interest has no children, which is case 1.
                    if (this.right.right == null && this.right.left == null) {//case 1 has one child
                        //cut the connection between current node and its right
                        V ret = (V) this.right.value;
                        this.right = null;
                        return ret;
                    } else if (this.right.right != null || this.right.left != null) {// if the node has one child, which is case 2
                        if (this.right.right != null) {
                            V ret = (V) this.right.value;
                            this.right = this.right.right;//connecting this(its parent) to its children
                            return ret;
                        } else {
                            V ret = (V) this.right.value;
                            this.right = this.right.left;
                            return ret;
                        }


                    } else {//case 3 has two children
                        BSTMap<K, V> toRemove = this.right.left;
                        V ret = (V) this.right.value;
                        while (toRemove.right != null) {
                            toRemove = toRemove.right;
                        }
                        this.right.key = toRemove.key;
                        this.right.value = toRemove.value;
                        remove(toRemove.key);
                        return ret;




                    }

                } else {
                    return (V) this.right.remove(key);
                }

            } else {
                return null;
            }

        }



        if (this.key.compareTo(key) > 0) {
            //if the right of this has nodes
            if (this.left != null) {
                //if the right of the node is what we wanna find
                if (this.left.key.equals(key)) {
                    //if the key of interest has no children, which is case 1.
                    if (this.left.right == null && this.left.left == null) {//case 1 has one child
                        //cut the connection between current node and its right
                        V ret = (V) this.left.value;
                        this.left = null;
                        return ret;
                    } else if (this.left.right != null || this.left.left != null) {// if the node has one child, which is case 2
                        if (this.left.right != null) {
                            V ret = (V) this.left.value;
                            this.left = this.left.right;
                            return ret;
                            //connecting this(its parent) to its children
                        } else {
                            V ret = (V) this.left.value;
                            this.left = this.left.left;
                            return ret;
                        }


                    } else {//case 3 has two children
                        BSTMap<K, V> toRemove = this.left.left;
                        V ret = (V) this.left.value;
                        while (toRemove.right != null) {
                            toRemove = toRemove.right;
                        }
                        this.left.key = toRemove.key;
                        this.left.value = toRemove.value;
                        remove(toRemove.key);
                        return ret;



                    }

                } else {
                    return (V) this.left.remove(key);
                }

            } else {
                return null;
            }

        }

        return null;










    }

    public boolean containsKey(K key) {
        V value=this.get(key);
        return value != null;
    }

    public void put(K key, V value) {
        if(this.key.equals(key)) {
            this.value = value;
            return;
        }
        if (this.key.compareTo(key) < 0) {
            if (this.right == null) {
                this.right = new BSTMap(key, value);
            } else {
                this.right.put(key, value);
            }

        } else {
            if (this.left == null) {
                this.left = new BSTMap(key, value);
            } else {
                this.left.put(key, value);
            }
        }
    }



    public V get(K key) {
        if (this.key.equals(key)) {
            return this.value;
        }

        if (this.key.compareTo(key) < 0) {
            if (this.right != null) {
                return (V) this.right.get(key);
            } else {
                return null;
            }

        } else {
            if (this.left != null) {
                return (V) this.left.get(key);
            } else {
                return null;
            }
        }
    }

    public static void main(String[] args) {


        Random rand = new Random();
        System.out.println("hello world");
        BSTMap<Integer, Integer> map = new BSTMap<>(3, 4);
        map.put(1, 2);
        map.put(4, 5);
        map.put(12, 7);
        map.put(7, 8);




        System.out.println(map.containsKey(3));
        System.out.println(map.get(3));

        System.out.println(map.remove(12));
//        for (int i = 0; i < 100; i++) {
//            int key =rand.nextInt(100);
//            map.put(key,key+rand.nextInt(3));
//            System.out.println("added key: "+ key +" value: "+ map.get(key));
//        }



    }
    public BSTMap(K key, V value, BSTMap left, BSTMap right) {

        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
        this.sentinel = new Sentinel(this);

    }
    public BSTMap(K key, V value) {
            this(key, value, null, null);
    }



}


