

public class Storage extends Insert {

public Storage() {
currentSize = 0;
array = new Comparable[storagespace + 1];
}

public Storage(Comparable[] items) {
currentSize = items.length;
array = new Comparable[items.length + 1];
for (int i = 0; i < items.length; i++)
array[i + 1] = items[i];
storage_create();
}

public Insert.Position insert(Comparable j) {
if (currentSize + 1 == array.length)
doubleArray();

int hole = ++currentSize;
array[0] = j;
for (; j.compareTo(array[hole / 2]) < 0; hole /= 2)
array[hole] = array[hole / 2];
array[hole] = j;
return null;
}



public Comparable findMin() {
if (isEmpty())
try {
throw new Exception("Srorage space is empty");
} catch (Exception e) {

e.printStackTrace();
}
return array[1];
}

public Comparable deleteMin() {
Comparable minItem = findMin();
array[1] = array[currentSize--];
Str_bott(1);
return minItem;
}

private void storage_create() {
for (int i = currentSize / 2; i > 0; i--)
Str_bott(i);
}

public boolean isEmpty() {
return currentSize == 0;
}

public int size() {
return currentSize;
}

public void emty_storage() {
currentSize = 0;
}
private static final int storagespace = 100;
private int currentSize; 
public Comparable[] array; 

private void Str_bott(int k) {
int x;
Comparable l = array[k];
for (; k * 2 <= currentSize; k = x) {
x = k * 2;
if (x != currentSize
&& array[x + 1].compareTo(array[x]) < 0)
x++;
if (array[x].compareTo(l) < 0)
array[k] = array[x];
else
break;
}
array[k] = l;
}

private void doubleArray() {
Comparable[] arr;
arr = new Comparable[array.length * 2];
for (int i = 0; i < array.length; i++)
arr[i] = array[i];
array = arr;
}

public static void main(String[] args) {
int numItems = 5000;
Storage binheap = new Storage();
Integer[] items = new Integer[numItems - 1];
int i = 50;
int j;
for (i = 50, j = 0; i != 0; i = (i + 50) % numItems, j++) {
binheap.insert(new Integer(i));
items[j] = new Integer(i);
}
for (i = 1; i < numItems; i++)
if (((Integer) (binheap.deleteMin())).intValue() != i)

for (i = 1; i < numItems; i++)
if (((Integer) (binheap.deleteMin())) == i) {
    } else {
    System.out.println("The minimum node is " + i);
    }
}
}