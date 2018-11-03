public class searchNum {
    public static int tertiarySearch(int num, int[] nums, int h, int t){
        if (h > t)
            return -1;
        if (num == nums[h])
            return h;
        if (num == nums[t])
            return t;
        if (num == nums[h + (t - h + 1) / 3])
            return h + (t - h + 1) / 3;
        if (num == nums[h + ((t - h + 1) / 3) * 2])
            return h + ((t - h + 1) / 3) * 2;

        if (num < nums[h + (t - h + 1) / 3])
            return tertiarySearch(num, nums, h, h + (t - h + 1) / 3 - 1);
        if (num > nums[h + ((t - h + 1) / 3) * 2])
            return tertiarySearch(num, nums, h + ((t - h + 1) / 3) * 2 + 1, t);
        return tertiarySearch(num, nums, h + (t - h + 1) / 3 + 1, h + ((t - h + 1) / 3) * 2 - 1);
    }
    public static void main(String[] args){
        int[] array1 = new int[15], array2 = new int[16];
        for (int i = 1; i <= 15; i++)
            array1[i - 1] = i * i;
        for (int i = 1; i <= 16; i++)
            array2[i - 1] = i * 3;
        for(int i : array1)
            System.out.print(i + " ");
        System.out.println();
        for(int i : array2)
            System.out.print(i + " ");
        System.out.println();
        System.out.println(tertiarySearch(121, array1, 0, array1.length - 1));        //This number exists in array1.
        System.out.println(tertiarySearch(78, array1, 0, array1.length - 1));     //Doesn't exist.
        System.out.println(tertiarySearch(30, array2, 0, array2.length - 1));       //This number exists in array2.
        System.out.println(tertiarySearch(22, array2, 0, array2.length - 1));    //Doesn't exist.
    }
}
