# Flight Ticket Formatter using `join()` Method

## 📌 Problem Description

In this example, we demonstrate how an airline ticketing system can use the Java `String.join()` method to format and display flight details in a structured way.

The `join()` method combines multiple strings using a specified delimiter.

---

## 🎯 Objective

The program should:

1. Store the departure city.
2. Store the destination city.
3. Store the flight timings.
4. Use `String.join()` to combine the details.
5. Display the formatted flight information.

---

## 🧠 What is `String.join()`?

`String.join()` is a method in Java used to combine multiple strings into a single string.

It places a specified **delimiter** between each string.

### Syntax

```java
String.join(delimiter, elements);
```

For example:

```java
String result = String.join(" | ", "Java", "Python", "SQL");
```

Output:

```text
Java | Python | SQL
```

Here:

* `" | "` → delimiter
* `"Java"` → first string
* `"Python"` → second string
* `"SQL"` → third string

---

# ✈️ Flight Ticket Example

Suppose an airline wants to display:

```text
From: New York
To: London
Timings: 00:13 am
```

Instead of printing each value separately, we can combine them using `String.join()`.

---

## 💻 Java Code

```java
class CodeChef {
    public static void main(String[] args) {

        // Step 1: Declare string variables for flight details.
        String departureCity = "New York";
        String destinationCity = "London";
        String timings = "00:13 am";

        // Step 2: Use the join() method to format
        // the flight details into a single structured output.
        String flightDetails = String.join(
                " | ",
                "From: " + departureCity,
                "To: " + destinationCity,
                "Timings: " + timings
        );

        // Step 3: Display the structured flight details.
        System.out.println("Flight Details: " + flightDetails);
    }
}
```

---

# 🖥️ Output

```text
Flight Details: From: New York | To: London | Timings: 00:13 am
```

---

# 🔍 Step-by-Step Explanation

## Step 1: Declare the Variables

```java
String departureCity = "New York";
String destinationCity = "London";
String timings = "00:13 am";
```

Three string variables are created:

| Variable          | Value      |
| ----------------- | ---------- |
| `departureCity`   | `New York` |
| `destinationCity` | `London`   |
| `timings`         | `00:13 am` |

---

## Step 2: Use `String.join()`

```java
String flightDetails = String.join(
        " | ",
        "From: " + departureCity,
        "To: " + destinationCity,
        "Timings: " + timings
);
```

The delimiter is:

```text
" | "
```

The strings are:

```text
From: New York
To: London
Timings: 00:13 am
```

`String.join()` places the delimiter between them.

So the result becomes:

```text
From: New York | To: London | Timings: 00:13 am
```

---

## Step 3: Display the Result

```java
System.out.println("Flight Details: " + flightDetails);
```

This prints:

```text
Flight Details: From: New York | To: London | Timings: 00:13 am
```

---

# 🧪 More Examples

## Example 1: Joining Programming Languages

```java
String languages = String.join(" | ", "Java", "Python", "C++");
System.out.println(languages);
```

Output:

```text
Java | Python | C++
```

---

## Example 2: Joining Fruits

```java
String fruits = String.join(", ", "Apple", "Banana", "Mango");
System.out.println(fruits);
```

Output:

```text
Apple, Banana, Mango
```

---

## Example 3: Joining Names

```java
String names = String.join("-", "John", "Alice", "Bob");
System.out.println(names);
```

Output:

```text
John-Alice-Bob
```

---

# 🔄 How `join()` Works

Consider:

```java
String result = String.join(" | ", "Java", "Python", "SQL");
```

Think of it as:

```text
Java
  ↓
Java | Python
  ↓
Java | Python | SQL
```

Final result:

```text
Java | Python | SQL
```

The delimiter is inserted **between** the strings.

It is not added at the beginning or end.

---

# 🧠 Important Concept: Delimiter

A **delimiter** is a character or sequence of characters used to separate values.

Examples:

### Comma

```java
String.join(",", "A", "B", "C");
```

Output:

```text
A,B,C
```

### Comma with Space

```java
String.join(", ", "A", "B", "C");
```

Output:

```text
A, B, C
```

### Pipe

```java
String.join(" | ", "A", "B", "C");
```

Output:

```text
A | B | C
```

### Hyphen

```java
String.join("-", "2026", "08", "24");
```

Output:

```text
2026-08-24
```

---

# ⚠️ Important Points

### 1. `join()` does not modify the original strings

Strings in Java are immutable.

`String.join()` creates a new combined string.

---

### 2. The delimiter goes between elements

For:

```java
String.join("-", "A", "B", "C");
```

The output is:

```text
A-B-C
```

Not:

```text
-A-B-C-
```

---

### 3. It is useful for formatting

Instead of:

```java
System.out.println("Java " + "Python " + "SQL");
```

We can use:

```java
String.join(" | ", "Java", "Python", "SQL");
```

This is especially useful when the separator needs to be consistent.

---

# 🌍 Real-World Applications

`String.join()` can be useful in many real-world situations.

### ✈️ Flight Information

```text
From: New York | To: London | Timings: 00:13 am
```

### 📋 Reports

```text
Name: John | Age: 22 | Department: IT
```

### 📝 Lists

```text
Java, Python, SQL, HTML
```

### 📊 CSV Data

```text
101,John,IT,85
```

### 🔗 URLs or Paths

Values can be combined using `/` or another suitable separator.

---

# 🎯 Interview Point

### Question:

**What is the purpose of `String.join()` in Java?**

### Answer:

`String.join()` is used to concatenate multiple strings using a specified delimiter between them.

Example:

```java
String result = String.join("-", "A", "B", "C");
```

Output:

```text
A-B-C
```

---

# 🔑 Quick Revision

### Syntax

```java
String.join(delimiter, elements);
```

### Example

```java
String result = String.join(" | ", "Java", "Python", "SQL");
```

### Output

```text
Java | Python | SQL
```

### Remember

```text
join() → combines strings
delimiter → separates strings
result → one combined String
```

---

# ⭐ Key Takeaway

> **`String.join()` combines multiple strings into a single string using a specified delimiter.**

### Easy way to remember:

**JOIN = Combine + Separate**

For example:

```java
String.join(" | ", "A", "B", "C");
```

means:

```text
A + " | " + B + " | " + C
```

Result:

```text
A | B | C
```
