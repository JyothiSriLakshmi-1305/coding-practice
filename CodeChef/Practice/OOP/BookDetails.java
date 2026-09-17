class Book {
    String title;
    String author;
    int pages;

    void displayInfo() {
        System.out.println("Book Title: "+title);
        System.out.println("Author: "+author);
        System.out.println("Number of Pages: "+pages);
    }
}

class BookDetails {
    public static void main(String[] args) {
        Book myBook=new Book();

        myBook.title="Java Programming";
        myBook.author="John Doe";
        myBook.pages=300;

        myBook.displayInfo();
    }
}

/*
Book Title: Java Programming
Author: John Doe
Number of Pages: 300
  */
