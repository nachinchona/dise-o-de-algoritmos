package Varios;

public class CountingLetras {
    public static char[] countSort(char[] inputArray) {
        int N = inputArray.length;
        int M = 26;

        int[] countArray = new int[M];

        for (int i = 0; i < N; i++) {
            char c = inputArray[i];
            int index = Character.toLowerCase(c) - 'a';
            countArray[index]++;
        }

        for (int i = 1; i < M; i++) {
            countArray[i] += countArray[i - 1];
        }

        char[] outputArray = new char[N];

        for (int i = N - 1; i >= 0; i--) {
            char c = inputArray[i];
            int index = Character.toLowerCase(c) - 'a';
            outputArray[countArray[index] - 1] = inputArray[i];
            countArray[index]--;
        }

        return outputArray;
    }

    public static void main(String[] args) {
        char[] inputArray = {'b', 'a', 'c', 'A', 'B', 'C', 'z', 'F', 'P', 'Z', 'B', 'f'};
        char[] outputArray = countSort(inputArray);

        for (int i = 0; i < inputArray.length; i++) {
            System.out.print(outputArray[i] + " ");
        }
    }
}

