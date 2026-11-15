
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * ArrayListBasics demonstrates the fundamental operations
 * and commonly used methods of Java's ArrayList.
 *
 * Topics covered:
 * 1. Creating and adding elements
 * 2. Accessing and updating elements
 * 3. Searching elements
 * 4. Removing elements
 * 5. Iterating through an ArrayList
 * 6. Removing elements using removeIf()
 * 7. Sorting elements
 * 8. Collections utility methods
 * 9. Converting between lists and arrays
 * 10. Clearing an ArrayList
 *
 * @author Nasim Bidel
 * @version 1.0
 */
public class ArrayListBasics {

    /**
     * Main method used to demonstrate ArrayList operations.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        // 1. Creating an ArrayList and adding elements
        ArrayList<String> fruits = new ArrayList<>();

        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add(1, "Mango");

        System.out.println("1. After adding: " + fruits);
        System.out.println("Size: " + fruits.size());

        // 2. Accessing and updating elements
        System.out.println("\n2. get(0): " + fruits.get(0));

        fruits.set(2, "Blueberry");
        System.out.println("After set(2): " + fruits);

        // 3. Searching elements
        System.out.println("\n3. Contains Cherry: " + fruits.contains("Cherry"));
        System.out.println("Index of Mango: " + fruits.indexOf("Mango"));
        System.out.println("Index of Kiwi: " + fruits.indexOf("Kiwi"));

        // 4. Removing elements
        fruits.remove("Mango");
        fruits.remove(0);

        System.out.println("\n4. After removing: " + fruits);

        // Difference between removing by index and removing by value
        List<Integer> nums = new ArrayList<>(List.of(5, 1, 7, 3));

        System.out.println("Original list: " + nums);

        nums.remove(1); // Removes the element at index 1
        System.out.println("Remove index 1: " + nums);

        nums.remove(Integer.valueOf(7)); // Removes the value 7
        System.out.println("Remove value 7: " + nums);

        // 5. Iterating through an ArrayList

        List<String> colors = new ArrayList<>(
                List.of("Red", "Blue", "Green", "Yellow")
        );

        System.out.println("\n5. Colors: " + colors);

        // Traditional for loop
        for (int i = 0; i < colors.size(); i++) {
            System.out.println(colors.get(i));
        }

        // Enhanced for loop
        System.out.println("\nEnhanced for loop:");

        for (String c : colors) {
            System.out.println(c);
        }

        // Using Iterator
        System.out.println("\nUsing Iterator:");

        Iterator<String> it = colors.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // Using forEach() with a lambda expression
        System.out.println("\nForEach with Lambda:");

        colors.forEach(c -> System.out.println(c));

        // 6. Removing elements using removeIf()
        List<Integer> scores = new ArrayList<>(
                List.of(45, 82, 39, 91, 58, 77)
        );

        scores.removeIf(s -> s < 50);

        System.out.println("\n6. Scores >= 50: " + scores);

        // 7. Sorting an ArrayList
        List<String> names = new ArrayList<>(
                List.of("Sara", "ali", "Maya", "John")
        );

        // Natural alphabetical ordering
        Collections.sort(names);

        System.out.println("\n7. Natural sort: " + names + " (capitals first)");

        // Case-insensitive sorting
        names.sort(String.CASE_INSENSITIVE_ORDER);

        System.out.println("Ignore case: " + names);

        // Reverse sorting
        names.sort(Collections.reverseOrder());

        System.out.println("Reverse: " + names);

        // 8. Collections utility methods
        List<Integer> data = new ArrayList<>(
                List.of(4, 9, 2, 9, 6)
        );

        System.out.println("\n8. Max / Min: "
                + Collections.max(data) + " / " + Collections.min(data));

        System.out.println("Frequency of 9: "
                + Collections.frequency(data, 9));

        Collections.reverse(data);
        System.out.println("Reversed: " + data);

        Collections.swap(data, 0, data.size() - 1);
        System.out.println("Swap first/last: " + data);

        // 9. SubList and Array conversion
        List<Integer> numbers = new ArrayList<>(
                List.of(10, 20, 30, 40, 50)
        );

        System.out.println("\n9. subList(1, 4): "
                + numbers.subList(1, 4) + " (end index excluded)");

        // Convert ArrayList into an array
        Integer[] asArray = numbers.toArray(new Integer[0]);

        System.out.println("toArray: " + Arrays.toString(asArray));

        // Create an ArrayList from an array
        List<String> fromArray = new ArrayList<>(
                Arrays.asList("x", "y", "z")
        );

        fromArray.add("w");

        System.out.println("From array: " + fromArray);

        // 10. Clearing an ArrayList
        numbers.clear();

        System.out.println("\n10. After clear: "
                + numbers + ", isEmpty = " + numbers.isEmpty());
    }
}