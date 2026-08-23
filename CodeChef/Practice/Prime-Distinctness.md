# Prime Distinctness

## 📌 Problem Statement

Given a positive integer `N`, determine whether `N` is prime or not.

* If `N` is `1` or prime, return `1`.
* Otherwise, return the number of **distinct prime factors** of `N`.

---

## 🧪 Examples

### Example 1

**Input:**

```text
121
```

Prime factorization:

```text
121 = 11 × 11
```

Distinct prime factors:

```text
11
```

**Output:**

```text
1
```

### Example 2

**Input:**

```text
26
```

Prime factorization:

```text
26 = 2 × 13
```

Distinct prime factors:

```text
2, 13
```

**Output:**

```text
2
```

---

## 🧠 Key Concept

The important word in this problem is **distinct**.

A prime factor should be counted only once, even if it occurs multiple times.

For example:

```text
60 = 2 × 2 × 3 × 5
```

Distinct prime factors are:

```text
2, 3, 5
```

Therefore:

```text
Answer = 3
```

---

## 🔍 Approach

1. Start checking factors from `2`.
2. If `i` divides `N`, then `i` is a prime factor.
3. Add `i` to a `HashSet`.
4. Keep dividing `N` by `i` using a `while` loop.
5. This removes repeated occurrences of the same factor.
6. Continue checking while `i * i <= N`.
7. If a value greater than `2` remains, it is also a prime factor.
8. Add the remaining value to the `HashSet`.
9. Return the size of the `HashSet`.

---

## 💡 Why HashSet?

A `HashSet` stores only **unique values**.

For example:

```text
60 → 2, 2, 3, 5
```

The `HashSet` stores:

```text
{2, 3, 5}
```

Therefore:

```java
primes.size()
```

gives the number of distinct prime factors.

---

## 🔄 Dry Run

Let's take:

```text
N = 60
```

Initially:

```text
n = 60
Set = {}
```

### Step 1: Check factor 2

```text
60 % 2 == 0
```

Divide repeatedly:

```text
60 → 30 → 15
```

Add `2`:

```text
Set = {2}
```

### Step 2: Check factor 3

```text
15 % 3 == 0
```

Divide:

```text
15 → 5
```

Add `3`:

```text
Set = {2, 3}
```

### Step 3: Remaining number

Now:

```text
n = 5
```

`5` is a prime factor.

Add `5`:

```text
Set = {2, 3, 5}
```

Final answer:

```text
3
```

---

## 💻 Java Solution

```java
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static int distinctPrimeFactors(int n) {

        Set<Integer> primes = new HashSet<>();

        int x = n;

        for (int i = 2; i * i <= x; i++) {

            while (n % i == 0) {
                n /= i;
                primes.add(i);
            }
        }

        if (n > 2) {
            primes.add(n);
        }

        return primes.size();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();

        for (int i = 0; i < t; i++) {

            int num = scanner.nextInt();

            if (num <= 2) {
                System.out.println(1);
                continue;
            }

            System.out.println(distinctPrimeFactors(num));
        }

        scanner.close();
    }
}
```

---

## 🧪 Sample Input

```text
2
121
26
```

## ✅ Sample Output

```text
1
2
```

---

## 📖 Code Explanation

### 1. Create a HashSet

```java
Set<Integer> primes = new HashSet<>();
```

Stores only unique prime factors.

---

### 2. Check factors

```java
for (int i = 2; i * i <= x; i++)
```

We check possible factors up to approximately `√N`.

This is much faster than checking all numbers up to `N`.

---

### 3. Remove repeated factors

```java
while (n % i == 0) {
    n /= i;
    primes.add(i);
}
```

For example:

```text
72 = 2 × 2 × 2 × 3 × 3
```

The loop removes all repeated `2`s and `3`s.

The `HashSet` ensures each factor is counted only once.

---

### 4. Handle the remaining prime factor

```java
if (n > 2) {
    primes.add(n);
}
```

Example:

```text
26 → 2 × 13
```

After removing `2`, the remaining value is `13`.

So `13` must also be added.

---

### 5. Return the answer

```java
return primes.size();
```

The size of the set gives the number of distinct prime factors.

---

## ⏱️ Complexity Analysis

### Time Complexity

For one test case:

```text
O(√N)
```

For `T` test cases:

```text
O(T × √N)
```

### Space Complexity

```text
O(K)
```

where `K` is the number of distinct prime factors.

---

## 🎯 Placement Concepts Covered

This problem helps practice:

* Prime numbers
* Prime factorization
* Factors
* `HashSet`
* Duplicate removal
* `for` loop
* `while` loop
* Square-root optimization
* Time complexity
* Space complexity

---

## 🔑 Important Patterns

### Pattern 1: Distinct Elements

Whenever a problem asks for **unique** or **distinct** elements, think about:

```java
HashSet
```

### Pattern 2: Prime Factorization

A common prime-factorization pattern is:

```java
for (int i = 2; i * i <= n; i++) {

    while (n % i == 0) {
        n /= i;
    }
}
```

---

## 🧠 Quick Revision

### N = 72

```text
72 = 2 × 2 × 2 × 3 × 3
```

Distinct prime factors:

```text
2, 3
```

Answer:

```text
2
```

### N = 13

`13` is prime.

Answer:

```text
1
```

### N = 1

According to the problem:

```text
Answer = 1
```

---

## ⭐ Takeaway

> Factorize the number, store each prime factor in a `HashSet`, and return the number of unique factors.

### Remember

```text
Distinct → HashSet
Prime Factorization → √N
Repeated Factor → while loop
Answer → HashSet.size()
```
