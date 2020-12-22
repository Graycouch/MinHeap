import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Heap 
{
    public static void main(String[] args) throws FileNotFoundException 
    {
        File inputFile = new File("inputFile.txt");
        Scanner in = new Scanner(inputFile);
        
        int arraySize = Integer.parseInt(in.nextLine());
        MinHeap Heap = new MinHeap(arraySize);
        int counter = 1;
        
        while(in.hasNextLine() && counter < arraySize)
        {
            counter++;
            String[] instructions = in.nextLine().split(" ");
            
            switch(instructions[0])
            {
                case "IN":
                    Heap.insert(Integer.parseInt(instructions[1]));
                    break;
               
                case "DK":
                    Heap.decreaseKey(Integer.parseInt(instructions[1]) , Integer.parseInt(instructions[2]));
                    break;
                
                case "EM":
                    Heap.extractMin();
                    break;
                
                default:
                    break;
            }
        }
        System.out.println(Heap.extractMin());
        in.close();
    }
}

class MinHeap
{
    private int array[];
    private int arraySize;
    private int heapSize;
    
    public MinHeap(int arraySize)
    {
        this.arraySize = arraySize;
        this.array = new int[this.arraySize];
        heapSize = 0;
    }
    
    public void insert(int element)
    {
        if(this.heapSize < 1)
        {
            this.array[this.heapSize] = element;
            this.heapSize++;
        }
        else
        {
            this.array[this.heapSize] = element;
            this.heapSize++;
            floatUp(this.heapSize - 1);
        }
    }
    
    private int parent(int index) 
    { 
        return index / 2; 
    }
    
     private int leftChild(int index) 
    { 
        return (2 * index); 
    } 
     
    private int rightChild(int index) 
    { 
        return (2 * index) + 1; 
    }
    
    private boolean isLeaf(int index) 
    { 
        if (index >= (heapSize / 2) && index <= heapSize) 
        { 
            return true; 
        } 
        return false; 
    } 
    
    private void swap(int firstIndex, int secondIndex) 
    { 
        int tmp; 
        tmp = array[firstIndex]; 
        array[firstIndex] = array[secondIndex]; 
        array[secondIndex] = tmp; 
    }
    
    public void decreaseKey(int index , int newElement)
    {
        this.array[index] = newElement;
        floatUp(index);
    }
    
    public int extractMin()
    {
        int min = this.array[0];
        this.array[0] = this.array[this.heapSize - 1];
        this.heapSize -= 1;
        sinkDown(0);
        return min;
    }
    
    private void floatUp(int index) 
    {   
        while (parent(index) >= 0) 
        {
            int parent = parent(index);
            if (this.array[index] < this.array[parent] && this.array[index] > 0) 
            {
                int temp = this.array[parent];
                this.array[parent] = this.array[index];
                this.array[index] = temp;
                index = parent;
            }
            else
                break; 
        }
    }
    
    private void sinkDown(int index)
    {
        if (!isLeaf(index)) { 
            if (array[index] > array[leftChild(index)] || array[index] > array[rightChild(index)]) 
            {
                if (array[leftChild(index)] < array[rightChild(index)]) 
                { 
                    swap(index , leftChild(index)); 
                    sinkDown(leftChild(index)); 
                }
                
                else 
                { 
                    swap(index , rightChild(index)); 
                    sinkDown(rightChild(index)); 
                } 
            } 
        } 
    }
}