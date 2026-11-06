# Data Structures and Algorithms

Java implementations of core searching, sorting, and Big-O analysis topics from my Data Structures and Algorithms course at Algonquin College.

Every class is self-contained and has a `main` method with a small demo, so each file can be compiled and run on its own.

## Requirements

- Java 8 or later
- No external libraries

## How to Run

Compile and run any file from the project folder:

```bash
javac SelectionSort.java
java SelectionSort
```

Or compile everything at once:

```bash
javac *.java
```

## Contents

### Big-O and Timing

| File | Topic | What it shows |
|---|---|---|
| `TimingDemo.java` | Measuring execution time | `System.nanoTime()`, `System.currentTimeMillis()`, and `Instant`/`Duration` |
| `SchedulingDemo.java` | Scheduling tasks | `Timer`/`TimerTask` vs. `ScheduledExecutorService` (runs for about 11 seconds) |
| `LoopAnalysis.java` | Analyzing loops | Counts real iterations for `i++`, `i += 2`, `i *= 2`, nested loops, and sequential loops |

### Arrays and Searching

| File | Topic | Time complexity |
|---|---|---|
| `ArrayOperations.java` | Insert and delete in unordered and ordered arrays | Unordered insert O(1), ordered insert O(n), delete O(n) |
| `SearchAlgorithms.java` | Linear search, binary search, and the Guess-A-Number game | Linear O(n), binary O(log n) |
| `TwoSum.java` | Find two numbers that add up to a target, using an `ArrayList` | O(n²) time, O(1) space |

### Sorting

| File | Best | Average | Worst | Extra space | How it works |
|---|---|---|---|---|---|
| `BubbleSort.java` | O(n) | O(n²) | O(n²) | O(1) | Swaps neighbors that are out of order |
| `SelectionSort.java` | O(n²) | O(n²) | O(n²) | O(1) | Moves the smallest remaining value into place |
| `InsertionSort.java` | O(n) | O(n²) | O(n²) | O(1) | Inserts each value into the sorted part on the left |
| `MergeSort.java` | O(n log n) | O(n log n) | O(n log n) | O(n) | Splits in half, sorts each half, then merges them |
| `QuickSort.java` | O(n log n) | O(n log n) | O(n²) | O(log n) | Splits values around a pivot, then sorts each side |
| `HeapSort.java` | O(n log n) | O(n log n) | O(n log n) | O(1) | Builds a max-heap, then moves the largest value to the end |

## Big-O Quick Reference

| Notation | Name | Example |
|---|---|---|
| O(1) | Constant | Insert at the end of an unordered array |
| O(log n) | Logarithmic | Binary search |
| O(n) | Linear | Linear search |
| O(n log n) | Linearithmic | Merge sort, heap sort |
| O(n²) | Quadratic | Selection sort, bubble sort |

**Rules for loops**

- A single loop with `i++` or `i += 2` is O(n).
- A single loop with `i *= 2` is O(log n).
- Nested loops multiply: O(n) × O(n) = O(n²).
- Sequential loops add, then keep only the largest term: O(n²) + O(n) = O(n²).
- Drop constants and lower-order terms: 5n³ + 3n² + 100n is O(n³).

## Lessons Learned

- **Choosing a data structure changes the Big-O.** `ArrayList.get(i)` is O(1), but `LinkedList.get(i)` is O(n), so the same nested-loop Two Sum code would become O(n³) on a `LinkedList`.
- **Integer overflow can cause wrong answers.** `(low + high) / 2` can overflow on very large arrays, so binary search uses `low + (high - low) / 2` instead. Two Sum adds values as `long` for the same reason.
- **Timers need to be stopped.** A `java.util.Timer` keeps the program running until `timer.cancel()` is called.
- **Counting iterations makes Big-O concrete.** `LoopAnalysis` and `SelectionSort` print the actual loop counts, which shows that selection sort's inner loop always runs n(n-1)/2 times.

## Acknowledgments

- Topics and examples are based on the *Big O Notation & Algorithmic Efficiency* study notes by Dr. James Mwangi, Algonquin College.
- `HeapSort.java` was written with help from an AI assistant (Claude) and is included to complete the set of sorting algorithms.