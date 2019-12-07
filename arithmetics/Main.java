package com.arthcode.arithmetics;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> a = stringToArray("333345334545454533454533334533454545453345454545");
        ArrayList<Integer> b = stringToArray("543333453345454543333453345454545334545333345334545454533454545453545334545333345334545454533454545453");
        printNumber(multiplication(a, b));
        System.out.println(primeList(100));
        System.out.println(primeList(1000000));
    }

    private static ArrayList<Integer> addition(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> sum = new ArrayList<>();
        if(a.size() > b.size()) {
            if(!(Collections.frequency(a, 0) == a.size())) {
                int df = a.size() - b.size();
                for (int i = 0; i < df; i++) {
                    b.add(0);
                }
            } else {
                return b;
            }
        } else if(a.size() < b.size()) {
            if(!(Collections.frequency(b, 0) == b.size())) {
                int df = b.size() - a.size();
                for (int i = 0; i < df; i++) {
                    a.add(0);
                }
            } else {
                return a;
            }
        }
        int mx = Math.max(a.size(), b.size());
        int mem = 0;
        for(int i = 0; i < mx; i++) {
            int an = a.get(i);
            int bn = b.get(i);
            int t = an + bn + mem;
            int c = t >= 10 ? t - 10 : t;
            mem = t >= 10 ? 1 : 0;
            if(!(i == mx - 1)) {
                sum.add(c);
            } else {
                if(t >= 10) {
                    sum.add(c);
                    sum.add(mem);
                } else {
                    sum.add(t);
                }
            }
        }
        return sum;
    }

    private static ArrayList<Integer> stringToArray(String s) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            arrayList.add(Integer.parseInt(String.valueOf(s.charAt(i))));
        }
        return arrayList;
    }

    private static void printNumber(ArrayList<Integer> i) {
        StringBuilder s = new StringBuilder();
        for (Integer integer : i) {
            s.append(integer);
        }
        System.out.println(s.reverse());
    }

    private static ArrayList<Integer> multiplication(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> mul = new ArrayList<>();
        if(a.size() > b.size()) {
            int df = a.size() - b.size();
            for(int i = 0; i < df; i++) {
                b.add(0);
            }
        } else if(a.size() < b.size()) {
            int df = b.size() - a.size();
            for(int i = 0; i < df; i++) {
                a.add(0);
            }
        }
        int mx = Math.max(a.size(), b.size());
        for(int i = 0; i < mx; i++) {
            ArrayList<Integer> subMul = new ArrayList<>();
            int mem = 0;
            for (int k = 0; k < mx; k++) {
                int an = a.get(i);
                int bn = b.get(k);
                int t = an * bn + mem;
                int c = t >= 10 ? t % 10 : t;
                mem = t >= 10 ? t / 10 : 0;
                if(!(k == mx - 1)) {
                    subMul.add(c);
                } else {
                    if(t >= 10) {
                        subMul.add(c);
                        subMul.add(mem);
                    } else {
                        subMul.add(t);
                    }
                }
            }
            for(int z = 0; z < i; z++) {
                subMul.add(0, 0);
            }
            mul = addition(mul, subMul);
        }
        return mul;
    }

    private static ArrayList<Integer> primeList(int n) {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if(eratosthenesSieve(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    private static boolean eratosthenesSieve(int n) {
        boolean isPrime = true;
        for (int i = 2; i < Math.ceil(Math.sqrt(n)); i++) {
            if(n % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
}
