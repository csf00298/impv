package com.study.SuanFa.msSuanfa;

import java.util.Objects;

/**
 * @Description:
 * @Author: Created by CaoXiaoLong on 2018/1/26.
 */
public class THashMap<K,V> {
    private Node<K,V>[] table;

    static class Node<K,V>{
        private int hash;
        private K key;
        private V value;
        private Node<K,V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "hash=" + hash +
                    ", key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    public void put(K key,V value){
        //获取hash
        int hashCode = Objects.hashCode(key);
        //指定数组长度 初始化数组
        int length = 16;
        if(table == null){
            table = new Node[length];
        }
        //根据key算key的哈希值余数
//        int i = hashCode%length;
        int i = hashCode & (length -1);

        if(table[i] == null){
            table[i] = new Node<K,V>(hashCode, key,value,null);
        }else{
            while (true){
                Node<K,V> node = table[i];
                //判断table[i].key 是否等于传入的key
                if(hashCode == node.hash && (node.key==key || (key != null && node.key.equals(key)))){
                    node.value = value;
                    break;
                }else{
                    if(node.next.hash == hashCode && (node.key==key || (key != null && node.key.equals(key)))) {
                        node.next.value = value;
                        break;
                    }
                    node = node.next;
                }
            }

        }
    }
    public V get(K key){
        int hash = Objects.hashCode(key);
        if(table == null || table.length <= 0) return  null;
        int i = hash %table.length;
        Node<K,V> node = table [i];
        if(node == null)return null;
        if((node.hash == hash) && (node.key == key) || (key!=null && node.key.equals(key))) return node.value;
        else {
            while(true){
                if(node.next !=null){
                    if((node.next.hash == hash) && (node.next.key == key) || (key!=null && node.next.key.equals(key)))
                        return node.next.value;
                    node = node.next;
                }
            }
        }
    }

    public static void main(String[] args) {
        THashMap hMap = new THashMap<String,Integer>();
        hMap.put("aa",12);
        hMap.put("ab",13);
        hMap.put("ac",14);
        hMap.put("aa",15);
        hMap.put(null,16);

        System.out.println(hMap.table);
    }
}
