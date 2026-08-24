# Simple BMI Calculator

## 📌 Problem Description

You are building a **Basic BMI Calculator** in Java for an e-commerce health platform.

All input data is predefined as `String` values, as if the data was received from an online form.

The program demonstrates how to:

* Convert `String` values into appropriate data types.
* Convert `int` and `char` values into `String`.
* Calculate BMI using mathematical operations.
* Display the converted values and calculated BMI.

---

# 🎯 Objective

The program should perform the following conversions:

| Original Value       | Conversion     | Java Method              |
| -------------------- | -------------- | ------------------------ |
| `String` → `int`     | Weight         | `Integer.parseInt()`     |
| `String` → `double`  | Height         | `Double.parseDouble()`   |
| `String` → `boolean` | Summary option | `Boolean.parseBoolean()` |
| `int` → `String`     | User ID        | `String.valueOf()`       |
| `char` → `String`    | Gender         | `Character.toString()`   |

Then calculate BMI using:

```text
BMI = weight / (height × height)
```

---

# 🧠 Concepts Used

This program demonstrates:

* `Integer.parseInt()`
* `Double.parseDouble()`
* `Boolean.parseBoolean()`
* `String.valueOf()`
* `Character.toString()`
* Type conversion
* Primitive to String conversion
* Arithmetic operations
* `double` data type

---

# 🔄 String to Primitive Conversion

When data comes from forms, APIs, or user input, it is often received as a `String`.

For example:

```java
String weightStr = "70";
```

Although `"70"` looks like a number, Java treats it as a `String`.

To perform mathematical operations, we need to convert it into an `int`.

```java
int weight = Integer.parseInt(weightStr);
```

Now:

```text
"70" → 70
String → int
```

---

# 🔢 Integer Conversion

### Method

```java
Integer.parseInt()
```

### Example

```java
String number = "100";
int value = Integer.parseInt(number);
```

Now:

```text
number = "100"
value = 100
```

The value can now be used in mathematical calculations.

---

# 📏 Double Conversion

Height contains a decimal value:

```java
String heightStr = "1.75";
```

So we convert it into a `double`.

```java
double height = Double.parseDouble(heightStr);
```

Conversion:

```text
"1.75" → 1.75
String → double
```

---

# ✅ Boolean Conversion

The value:

```java
String showSummaryStr = "true";
```

is converted using:

```java
boolean showSummary = Boolean.parseBoolean(showSummaryStr);
```

Conversion:

```text
"true" → true
String → boolean
```

---

# 🔄 Primitive to String Conversion

Java also provides methods to convert primitive values into `String`.

## int → String

Use:

```java
String.valueOf()
```

Example:

```java
int userId = 101;

String userIdStr = String.valueOf(userId);
```

Conversion:

```text
101 → "101"
int → String
```

---

## char → String

Use:

```java
Character.toString()
```

Example:

```java
char gender = 'M';

String genderStr = Character.toString(gender);
```

Conversion:

```text
'M' → "M"
char → String
```

---

# 🧮 BMI Formula

BMI stands for **Body Mass Index**.

The formula used in this program is:

```text
BMI = weight / (height × height)
```

For:

```text
Weight = 70 kg
Height = 1.75 m
```

Calculation:

```text
BMI = 70 / (1.75 × 1.75)
```

```text
BMI = 70 / 3.0625
```

```text
BMI = 22.857142857142858
```

---

# 💻 Java Code

```java
class CodeChef {
    public static void main(String[] args) {

        String weightStr = "70";
        String heightStr = "1.75";
        String showSummaryStr = "true";

        int userId = 101;
        char gender = 'M';

        // Convert Strings to appropriate data types
        int weight = Integer.parseInt(weightStr);
        double height = Double.parseDouble(heightStr);
        boolean showSummary = Boolean.parseBoolean(showSummaryStr);

        // Convert int and char to String
        String userIdStr = String.valueOf(userId);
        String genderStr = Character.toString(gender);

        // Calculate BMI
        double bmi = weight / (height * height);

        // Print the results
        System.out.println("The integer value is: " + weight);
        System.out.println("The value of height is: " + height);
        System.out.println("The boolean value is: " + showSummary);
        System.out.println("The user ID as a string is: " + userIdStr);
        System.out.println("The gender as a string is: " + genderStr);
        System.out.println("Your BMI is: " + bmi);
    }
}
```

---

# 🖥️ Expected Output

```text
The integer value is: 70
The value of height is: 1.75
The boolean value is: true
The user ID as a string is: 101
The gender as a string is: M
Your BMI is: 22.857142857142858
```

---

# 🔍 Step-by-Step Explanation

## Step 1: Store Input as Strings

```java
String weightStr = "70";
String heightStr = "1.75";
String showSummaryStr = "true";
```

The values are initially stored as strings.

This simulates data received from:

* Online forms
* Web applications
* APIs
* Text fields

---

## Step 2: Convert Weight

```java
int weight = Integer.parseInt(weightStr);
```

Converts:

```text
"70" → 70
```

Now `weight` is an `int`.

---

## Step 3: Convert Height

```java
double height = Double.parseDouble(heightStr);
```

Converts:

```text
"1.75" → 1.75
```

Now `height` is a `double`.

---

## Step 4: Convert Boolean

```java
boolean showSummary = Boolean.parseBoolean(showSummaryStr);
```

Converts:

```text
"true" → true
```

Now `showSummary` is a `boolean`.

---

## Step 5: Convert int to String

```java
String userIdStr = String.valueOf(userId);
```

Converts:

```text
101 → "101"
```

---

## Step 6: Convert char to String

```java
String genderStr = Character.toString(gender);
```

Converts:

```text
'M' → "M"
```

---

## Step 7: Calculate BMI

```java
double bmi = weight / (height * height);
```

Calculation:

```text
70 / (1.75 × 1.75)
```

Result:

```text
22.857142857142858
```

---

# 📚 Conversion Methods Quick Reference

| Conversion         | Method                   | Example           |
| ------------------ | ------------------------ | ----------------- |
| `String → int`     | `Integer.parseInt()`     | `"70"` → `70`     |
| `String → double`  | `Double.parseDouble()`   | `"1.75"` → `1.75` |
| `String → boolean` | `Boolean.parseBoolean()` | `"true"` → `true` |
| `int → String`     | `String.valueOf()`       | `101` → `"101"`   |
| `char → String`    | `Character.toString()`   | `'M'` → `"M"`     |

---

# ⚠️ Important Points

## 1. `parseInt()` returns an int

```java
int value = Integer.parseInt("50");
```

---

## 2. `parseDouble()` returns a double

```java
double value = Double.parseDouble("5.5");
```

---

## 3. `parseBoolean()` returns a boolean

```java
boolean value = Boolean.parseBoolean("true");
```

For normal usage:

```text
"true" → true
```

Other strings generally result in:

```text
false
```

---

## 4. `String.valueOf()` can convert primitive values

```java
String str = String.valueOf(101);
```

Result:

```text
"101"
```

---

## 5. `Character.toString()` converts a char

```java
String str = Character.toString('M');
```

Result:

```text
"M"
```

---

# 🌍 Real-World Applications

These conversion techniques are commonly used when working with:

* 🌐 Web forms
* 📱 Mobile applications
* 🔗 REST APIs
* 🗄️ Databases
* 🛒 E-commerce applications
* 👤 User profiles
* 📊 Data processing
* 🧮 Calculators

For example, a web form might send:

```text
weight = "70"
height = "1.75"
```

The backend can convert these strings into numeric values before performing calculations.

---

# 🎯 Interview Questions

### Q1. How do you convert a String to an int in Java?

```java
int value = Integer.parseInt("100");
```

---

### Q2. How do you convert a String to a double?

```java
double value = Double.parseDouble("10.5");
```

---

### Q3. How do you convert a String to a boolean?

```java
boolean value = Boolean.parseBoolean("true");
```

---

### Q4. How do you convert an int to a String?

```java
String value = String.valueOf(100);
```

---

### Q5. How do you convert a char to a String?

```java
String value = Character.toString('A');
```

---

# 🧠 Quick Revision

Remember these five methods:

```text
String → int
Integer.parseInt()

String → double
Double.parseDouble()

String → boolean
Boolean.parseBoolean()

int → String
String.valueOf()

char → String
Character.toString()
```

---

# ⭐ Key Takeaway

> **Parsing methods convert String data into primitive data types, while methods like `String.valueOf()` and `Character.toString()` convert primitive values into Strings.**

The main pattern to remember is:

```text
String → Primitive
        ↓
     parse()

Primitive → String
        ↓
 conversion methods
```

### Final Revision Example

```java
String numberStr = "70";

int number = Integer.parseInt(numberStr);

String result = String.valueOf(number);
```

Conversion flow:

```text
"70"
 ↓
Integer.parseInt()
 ↓
70
 ↓
String.valueOf()
 ↓
"70"
```
