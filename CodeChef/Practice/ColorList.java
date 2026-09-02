import java.util.ArrayList;

class ColorList {
    public static void main(String[] args) {
        ArrayList<String> colors = new ArrayList<>();

        colors.add("Red");
        colors.add("Blue");
        colors.add("Green");
        colors.add("Yellow");
        colors.add("Purple");

        boolean hasBlue = colors.contains("Blue");
        boolean hasPink = colors.contains("Pink");

        System.out.println("Contains Blue: " + hasBlue);
        System.out.println("Contains Pink: " + hasPink);
    }
}
/* Output:
Contains Blue: true
Contains Pink: false
  */
