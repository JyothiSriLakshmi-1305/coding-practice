class OffByOneError {
    public static void main(String[] args) {
    // Initialize the string
    String string = "12345";
    // Potential Logical Error: Ensure the correct loop range is used
    for (int i = 0; i < string.length(); i++) {
        System.out.println(string.charAt(i));
    }
    System.out.println("The elements of the list are: " + string);  

    }
}
//Output

/*
1
2
3
4
5
The elements of the list are: 12345
  */
