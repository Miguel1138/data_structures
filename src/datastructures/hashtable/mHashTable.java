package datastructures.hashtable;

import java.util.*;

public class mHashTable {

    private int size = 7;
    private Node[] dataMap;

    public mHashTable() {
        dataMap = new Node[size];
    }

    private int hash(String key) {
        int hash = 0;
        char[] keyChars = key.toCharArray();
        for (int i = 0; i < keyChars.length; i++) {
            int asciiValue = keyChars[i];
            hash = (hash + asciiValue * 23) % dataMap.length;
        }

        return hash;
    }

    public void set(String key, int value) {
        int index = hash(key);
        Node newNode = new Node(key, value);
        // Verifica se o indicie hash não está vazio
        if (dataMap[index] == null) {
            dataMap[index] = newNode;
        } // Caso Esteja irá interar pela lista vinculada até inserir no final dela o novo objeto.
        else {
            Node temp = dataMap[index];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public int get(String key) {
        int index = hash(key);
        Node temp = dataMap[index];
        while (temp != null) {
            if (temp.key == key) return temp.value;
            temp = temp.next;
        }
        return 0;
    }

    public ArrayList keys() {
        ArrayList<String> allKeys = new ArrayList<>();
        for (int i = 0; i < dataMap.length; i++) {
            Node temp = dataMap[i];
            while (temp != null) {
                allKeys.add(temp.key);
                temp = temp.next;
            }
        }
        return allKeys;
    }

    public static boolean itemInCommon(int[] arr1, int[] arr2) {
        HashMap<Integer, Boolean> myHashMap = new HashMap<>();
        for (int i : arr1) {
            myHashMap.put(i, true);
        }

        for (int j : arr2) {
            if (myHashMap.get(j) != null) return true;
        }

        return false;
    }

    public List<Integer> findDuplicates(int[] nums) {
        HashMap<Integer, Integer> numCounts = new HashMap<>();
        for (int num : nums)
            numCounts.put(num, numCounts.getOrDefault(num, 0) + 1);

        List<Integer> duplicates = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : numCounts.entrySet()) {
            if (entry.getValue() > 1)
                duplicates.add(entry.getKey());
        }

        return duplicates;
    }

    // BIG O = (O)^2
    public static Character firstNonRepeatingChar(String string) {
        // Mapeia a string e cataloga a quantidade de caracteres e se há repetições.
        HashMap<Character, Integer> charCounts = new HashMap<>();
        for (Character character : string.toCharArray()) {
            charCounts.put(character, charCounts.getOrDefault(character, 0) + 1);
        }

        // Procura a primeira letra que não se repete.
        for (Map.Entry<Character, Integer> entry : charCounts.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }

        return null;
    }

    public List<List<String>> groupAnagrams(String[] strings) {
        if (strings == null || strings.length == 0) return new ArrayList<>();

        Map<String, List<String>> anagramGroup = new HashMap<>();
        for (String string : strings) {
            char[] chars = string.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            // Check if there are any group created for that anagram.
            if (!anagramGroup.containsKey(sorted))
                anagramGroup.put(sorted, new ArrayList<>());
            else {
                // Add the string to an existing group
                List<String> group = anagramGroup.get(sorted);
                group.add(string);
                anagramGroup.put(sorted, group);
            }
        }

        return new ArrayList<>(anagramGroup.values());
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int complement = target - num;

            if(numMap.containsKey(complement)) {
                return new int[] { numMap.get(complement), i };
            } else {
                numMap.put(num, i);
            }
        }

        return new int[] {};
    }

    public int[] subarraySum(int[] nums, int target) {
        Map<Integer, Integer>  sumIndex = new HashMap<>();
        sumIndex.put(0, -1);
        int currentSum = 0;
        for(int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            if(sumIndex.containsKey(currentSum - target)) {
                return new int[] {sumIndex.get(currentSum - target) + 1, i};
            } else {
                sumIndex.put(currentSum, i);
            }
        }

        return new int[] {};
    }

    protected class Node {
        private String key;
        private int value;
        private Node next;

        public Node(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
