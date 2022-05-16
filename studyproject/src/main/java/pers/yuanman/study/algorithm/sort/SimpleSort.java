package pers.yuanman.study.algorithm.sort;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * java中一些简单排序
 */
public class SimpleSort {
    /**
     * 插入排序
     *
     * @param list
     */
    public static void insertSort(ArrayList<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            int tmp = list.get(i);
            int j = i - 1;
            for (; j >= 0; j--) {
                Integer jtmp = list.get(j);
                if (jtmp > tmp) {
                    list.set(j + 1, jtmp);
                } else {
                    //list.get(j+1) = tmp;  只要j回退的时候，遇到了 比tmp小的元素就结束这次的比较
                    break;
                }
            }
            //j回退到了 小于0 的地方
            list.set(j + 1, tmp);
        }
    }

    /**
     * 希尔排序（插入排序优化）
     *
     * @param list
     */
    public static void shellSort(ArrayList<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        int gp = list.size() / 2;
        while (gp >= 1) {
            shellSort(list, gp);
            gp = gp / 2;
        }
    }

    private static void shellSort(ArrayList<Integer> list, int gp) {
        for (int i = gp; i < list.size(); i++) {
            int tmp = list.get(i);
            int j = i - gp;
            for (; j >= 0; j -= gp) {
                Integer jtmp = list.get(j);
                //System.out.println("jtmp:"+jtmp + " tmp:"+tmp);
                if (jtmp > tmp) {
                    list.set(j + gp, jtmp);
                } else {
                    break;
                }
            }
            list.set(j + gp, tmp);
            //list.forEach(s->System.out.print(s+" "));
            //System.out.println();
        }
    }

    /**
     * 选择排序
     *
     * @param list
     */
    public static void selectSort(ArrayList<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        //依次选择最小值放置最前
        for (int i = 0; i < list.size(); i++) {
            int index = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(index) > list.get(j)) {
                    index = j;
                }
            }
            Integer temp = list.get(i);
            list.set(i, list.get(index));
            list.set(index, temp);
        }
    }

    /**
     * 冒泡排序
     *
     * @param list
     */
    public static void bubbleSort(ArrayList<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        //比较两个相邻的元素，如果第一个数比第二个数大，我们就交换他们的位置
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    Integer temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }

        }
    }

    /**
     * 堆排序
     * @param arr
     */
    public static void heapSort(int arr[]) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            int temp = 0;
            //  count++;
            //  System.out.println("i="+i);
            adjustHeap(arr, i, arr.length);
            // System.out.println("第"+ count+"次"+ Arrays.toString(arr));
        }
        for (int j = arr.length - 1; j > 0; j--)
        //第二个for循琢是每次将根的值取出和最末尾的值互换再重新调整为大顶堆
        // 交换
        {
            //System.out.println("j=" + j);
           int temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //System.out.println(Arrays.toString(arr));
            adjustHeap(arr, 0, j);
        }


    }

    private static void adjustHeap(int arr[], int i, int length) {
        //功能：将以i对应的非叶子节点为根的子的树调整成大顶堆（一次只调整局部），调整的时候总是从最底层开始调整
        //举例： int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 {4, 9, 8, 5, 6}
        //	 * 如果我们再次调用  adjustHeap 传入的是 i = 0 => 得到 {4, 9, 8, 5, 6} => {9,6,8,5, 4}
        //arr:待调整的数组
        //i:表示非叶子结点在数组中的索引
        //length:表示对多少个元素进行调整，length是在逐渐减少的，因为每次和根节点交换就找到了一个最大值，这个值不再参与调整

        int temp = arr[i];
        //k=i*2+1 表示i的左子节点
        //2k+1:下次循环调整就是k对应的左子节点（在此节点不为叶子节点的情况下，K依然小于要调整的节点的个数，
        // 在第一次调整为大顶堆，这个2k+1不会起作用，后面从堆顶调整才会起作用）
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1])//说明左子节点的值小于右子节点的值
            {
                k++;//让k指向右子节点
            }
            //当k+1>=length时越界

            if (arr[k] > temp)//此时arr[k]是左子节点和右子节点中最大的值
            {
                //子节点大于父节点，则还要进行处理
               // System.out.println("进入交换");
                arr[i] = arr[k];//把较大的值赋给当前节点
                arr[k] = temp;
                i = k;//i指向k继续循环比较,因为非叶子节点是根据传进来的i，所以是循环调节i下面的子树
                //System.out.println("交换之后" + Arrays.toString(arr));
            } else {
                break;
                //因为节点从下往上找的，如果下面还有节点，在上个这样的方法已经经过了比较
                //因为如果不交换，下面的已经调整过了，如果发生了交换，就不能保证下面仍然是大顶堆
            }
        }
        //当for循环结束后，已经将以i为父节点的树的最大值放在了i原先的位置（局部）
        //arr[i]=temp;//将temp值放到调整后的位置
        //System.out.println("一次调整结束" + Arrays.toString(arr));
    }

    /**
     * 快速排序
     * 默认以第一个元素划分
     * @param array
     */
    public static void quickSort(int[] array){
        quickSort(array, 0, array.length -1);
    }
    /**
     * 快速排序
     * @param array
     * @param low
     * @param high
     */
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // 获取划分子数组的位置
            int position = partition(array, low, high);
            // 左子数组递归调用
            quickSort(array, low, position -1);
            // 右子数组递归调用
            quickSort(array, position + 1, high);
        }
    }
    private static int partition(int[] array, int low, int high) {
        // 取最后一个元素作为中心元素
        int pivot = array[high];
        // 定义指向比中心元素大的指针，首先指向第一个元素
        int pointer = low;
        // 遍历数组中的所有元素，将比中心元素大的放在右边，比中心元素小的放在左边
        for (int i = low; i < high; i++) {
            if (array[i] <= pivot) {
                // 将比中心元素小的元素和指针指向的元素交换位置
                // 如果第一个元素比中心元素小，这里就是自己和自己交换位置，指针和索引都向下一位移动
                // 如果元素比中心元素大，索引向下移动，指针指向这个较大的元素，直到找到比中心元素小的元素，并交换位置，指针向下移动
                int temp = array[i];
                array[i] = array[pointer];
                array[pointer] = temp;
                pointer++;
            }
           // System.out.println(Arrays.toString(array));
        }
        // 将中心元素和指针指向的元素交换位置
        int temp = array[pointer ];
        array[pointer] = array[high];
        array[high] = temp;
        return pointer;
    }

    /**
     * 归并排序
     * @param array
     */
    public static void mergeSort(int[] array){
        mergeSort(array,0,array.length-1,new int[array.length]);
    }

    /**
     * 归并排序
     * @param array
     * @param left
     * @param right
     * @param tempArray
     */
    public static void mergeSort(int[] array,int left,int right,int[] tempArray){
        if (left<right){
            int mid = (left+right)/2;//中间索引
            //向左递归分解
            mergeSort(array,left,mid,tempArray);
            //向右递归分解
            mergeSort(array,mid+1,right,tempArray);
            //合并
            sort(array,left,mid,right,tempArray);
        }
    }

    /**
     *
     * @param array 待排序的数组
     * @param left  左边有序子数组的初始索引
     * @param mid   中间索引
     * @param right  右边的索引
     * @param tempArray 用于排序时存储未完全排序的数组
     */
    private static void sort(int[] array,int left,int mid,int right,int[] tempArray){
        int i = left;//初始化i，左边有序子数组的初始索引
        int j = mid+1;//初始化j，右边有序子数组的初始索引
        int index = 0;//用于标识tempArray数组当前所在的下标
        //(一)
        //先把左右两边(有序)的子数组按照规则填充到tempArray数组
        //直到左右两边的有序子数组，有一边处理完毕为止
        while (i<=mid&&j<=right){//当左右子数组都没完成数据转移时
            if (array[i]<=array[j]){//左边的元素小于右边的元素，将该元素加入到临时数组中,
                // 一直循环直到找到一个元素比右边的大，将转移权交给右边的子数组
                tempArray[index] = array[i];
                i++;//向右移动一位
                index++;
            }else {//右边的元素小于左边的元素，将该元素加入到临时数组中,
                // 一直循环直到找到一个元素比左边的大，将转移权交给左边的子数组
                tempArray[index] = array[j];
                j++;//向右移动一位
                index++;
            }
        }
        //(二)
        //把有剩余数据的一边的数据依次全部填充到temp
        while (i<=mid){//说明左边的子数组有剩余元素
            tempArray[index] = array[i];
            i++;
            index++;
        }
        while (j<=right){//说明右边的子数组有剩余元素
            tempArray[index] = array[j];
            j++;
            index++;
        }

        //(三)
        //将tempArray数组的元素拷贝到array
        // 注意，并不是每次都是拷贝所有元素到array（待排序）数组中
        // 第一次合并tempLeft = 0 , right = 1
        // 第二次合并tempLeft= 2 right = 3
        // 第三次合并 tempLeft=0 right = 3
        // 最后一次tempLeft= 0 right = 7
        index=0;
        int tempLeft = left;
        while (tempLeft<=right){
            array[tempLeft] = tempArray[index];
            index++;
            tempLeft++;
        }
    }

}
