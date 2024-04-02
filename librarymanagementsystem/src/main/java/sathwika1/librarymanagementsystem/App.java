package sathwika1.librarymanagementsystem;

import java.util.Scanner;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static SessionFactory sessionFactory;
    private static Session session;

    public static void main(String[] args) {
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
            session = sessionFactory.openSession();
        } catch (Exception e) {
            System.out.println("Failed to initialize Hibernate. Error: " + e.getMessage());
            return;
        }
        System.out.println("================================");
        System.out.println("LIBRARY MANAGEMENT SYSTEM");
        System.out.println("================================");

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Admin Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    System.out.println("Exiting... Goodbye!");
                    session.close(); // Close Hibernate session before exiting
                    System.exit(0); // Terminate the program
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

   
    //=========================================================================
        private static void adminLogin() {
            System.out.print("\nEnter admin username: ");
            String username = scanner.next();
            System.out.print("Enter admin password: ");
            String password = scanner.next();

            if (username.equals("sathwika") && password.equals("1234")) {
                adminMenu();
            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        }
    //============================================================================
        private static void adminMenu() {
            while (true) {
                System.out.println("\nAdmin Menu");
                System.out.println("1. Add Book");
                System.out.println("2. Add Members");
                System.out.println("3. Add Transactions");
                System.out.println("4. view books");
                System.out.println("5. update books");
                System.out.println("6. Delete Books");
                System.out.print(   " 7.Exit ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addBook1();
                        break;
                    case 2:
                        addMembers1();
                        break;
                    case 3:
                        addTransactions1();
                        break;
                    case 4:
                        viewBooks1();
                        break;
                    case 5:
                    	updateBook1();
                    	break;
                    case 6:
                        deleteBooks(scanner);
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter a number between 1 and 5.");
                }
            } while (choice != 5);

        } finally {
            // Close SessionFactory
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }


    //===========================================================================
            // Implementation of addBook method
        	private static void addBook1() {
                System.out.println("Adding a new book:");
                System.out.println("----------------------");

                System.out.println("Enter book title: ");
                String title = scanner.next();

                System.out.println("Enter book author: ");
                String author = scanner.next();

                System.out.println("Enter published year: ");
                int publishedYear = scanner.nextInt(); // Changed to int for simplicity

                // Create a new Book object
                books book = new books();
                book.setTitle(title);
                book.setAuthor(author);
                book.setPublishedYear(publishedYear);

                // Save the book to the database using Hibernate
                Transaction transaction = null;
                try {
                    transaction = session.beginTransaction();
                    session.save(book);
                    transaction.commit();
                    System.out.println("Book added successfully.");
                } catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                    }
                    System.out.println("Failed to add book. Error: " + e.getMessage());
                }
            }

        

       
     //=======================================================================================
        private static void viewBooks1() {
            System.out.println("Viewing Books:");
            System.out.println("----------------------");

            // Query the database to fetch all books
            List<Books> bookList = session.createQuery("FROM books", Books.class).getResultList();

            if (bookList.isEmpty()) {
                System.out.println("No books found.");
            } else {
                System.out.println("List of Books:");
                System.out.println("ID\tTitle\tAuthor\tPublished Year");
                for (books book : bookList) {
                    System.out.println(
                        book.getId() + "\t" +
                        book.getTitle() + "\t" +
                        book.getAuthor() + "\t" +
                        book.getPublishedYear()
                    );
                }
            }
        }
        
     //=======================================================================================   

        private static void addMembers1() {
            System.out.println("Adding a new member:");
            System.out.println("----------------------");
            
            System.out.println("Enter id: ");
            int id = scanner.nextInt();

            System.out.println("Enter member name: ");
            String name = scanner.next();

            System.out.println("Enter member email: ");
            String email = scanner.next();
            
            System.out.println("Enter member Address: ");
            String address = scanner.next();
            
            System.out.println("Enter member mobileno: ");
            int mobileno = scanner.nextInt();


            // Create a new Member object
            Members member = new Members();
            member.setAddress(address);
            member.setEmailid(email);
            member.setId(id);
            member.setMobileno(mobileno);
            member.setName(name);

            // Save the member to the database using Hibernate
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(member);
                transaction.commit();
                System.out.println("Member added successfully.");
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.out.println("Failed to add member. Error: " + e.getMessage());
            }
        }
    //=====================================================================================
        private static void addTransactions1() {
            System.out.println("Adding a new transaction:");
            System.out.println("----------------------");

            System.out.println("Enter transaction_id : ");
            int transaction_id = scanner.nextInt();
            
            System.out.println("Enter member ID: ");
            int memberId = scanner.nextInt();

            System.out.println("Enter checkout_date: ");
            int checkout_date = scanner.nextInt();
            
            System.out.println("Enter return_date: ");
            int return_date = scanner.nextInt();


            System.out.println("Enter book ID: ");
            int bookId = scanner.nextInt();

            // Create a new Transaction object
            Transactions transaction1 = new Transactions();
            transaction1.setCheckout_date(checkout_date);
            transaction1.setMember_id(memberId);
            transaction1.setReturn_date(return_date);
            transaction1.setTransaction_id(transaction_id);
            // Save the transaction to the database using Hibernate
            try {
                session.beginTransaction();
                session.save(transaction1);
                session.getTransaction().commit();
                System.out.println("Transaction added successfully.");
            } catch (Exception e) {
                session.getTransaction().rollback();
                System.out.println("Failed to add transaction. Error: " + e.getMessage());
            }
        }
     //===================================================================================  
        // Inside your App class
        private static void  updateBook1() {
            System.out.println("Updating Book Details:");
            System.out.println("----------------------");

            System.out.print("Enter the ID of the book to update: ");
            int bookIdToUpdate = scanner.nextInt();

            // Check if the book with the given ID exists in the database
            books bookToUpdate = session.get(books.class, bookIdToUpdate);
            if (bookToUpdate == null) {
                System.out.println("Book with ID " + bookIdToUpdate + " not found.");
                return; // Exit the method if the book is not found
            }

            // Display the current details of the book
            System.out.println("Current Details:");
            System.out.println("Title: " + bookToUpdate.getTitle());
            System.out.println("Author: " + bookToUpdate.getAuthor());
            System.out.println("Published Year: " + bookToUpdate.getPublishedYear());

            // Ask the user to enter the updated details
            System.out.println("Enter new title (press Enter to skip): ");
            String newTitle = scanner.nextLine().trim(); // Use nextLine() to handle spaces
            if (!newTitle.isEmpty()) {
                bookToUpdate.setTitle(newTitle);
            }

            System.out.println("Enter new author (press Enter to skip): ");
            String newAuthor = scanner.nextLine().trim(); // Use nextLine() to handle spaces
            if (!newAuthor.isEmpty()) {
                bookToUpdate.setAuthor(newAuthor);
            }

            System.out.println("Enter new published year (press Enter to skip): ");
            String newPublishedYearInput = scanner.nextLine().trim(); // Use nextLine() to handle spaces
            if (!newPublishedYearInput.isEmpty()) {
                try {
                    int newPublishedYear = Integer.parseInt(newPublishedYearInput);
                    bookToUpdate.setPublishedYear(newPublishedYear);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for published year. Please enter a valid year.");
                    return; // Exit the method if the input is invalid
                }
            }

            // Update the book in the database using Hibernate
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.update(bookToUpdate);
                transaction.commit();
                System.out.println("Book details updated successfully.");
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.out.println("Failed to update book details. Error: " + e.getMessage());
            }
        }



    //==================================================================================
    private static void deleteBooks(Scanner scanner) {
     System.out.println("Deleting a Book:");
     System.out.println("----------------------");

     System.out.print("Enter the ID of the book to delete: ");
     int bookIdToDelete = scanner.nextInt();

     // Check if the book with the given ID exists in the database
     books bookToDelete = session.get(books.class, bookIdToDelete);
     if (bookToDelete == null) {
         System.out.println("Book with ID " + bookIdToDelete + " not found.");
         return; // Exit the method if the book is not found
     }

     // Confirm with the user before deleting the book
     System.out.println("Are you sure you want to delete the following book?");
     System.out.println("ID: " + bookToDelete.getId());
     System.out.println("Title: " + bookToDelete.getTitle());
     System.out.println("Author: " + bookToDelete.getAuthor());
     System.out.println("Published Year: " + bookToDelete.getPublishedYear());
     System.out.print("Enter 'yes' to confirm deletion: ");
     String confirmation = scanner.next();

     if (confirmation.equalsIgnoreCase("yes")) {
         Transaction transaction = null;
         try {
             transaction = session.beginTransaction();
             session.delete(bookToDelete);
             transaction.commit();
             System.out.println("Book deleted successfully.");
         } catch (Exception e) {
             if (transaction != null) {
                 transaction.rollback();
             }
             System.out.println("Failed to delete book. Error: " + e.getMessage());
         }
     } else {
         System.out.println("Deletion canceled. Book not deleted.");
     }
    }

    private static void exit1() {
        System.out.println("Exiting...");
        session.close(); // Close Hibernate session before exiting
        sessionFactory.close(); // Close Hibernate session factory
        System.exit(0); // Terminate the program
    }
    

//===========================================================================
        // Implementation of addBook method
    	private static void addBook() {
            System.out.println("Adding a new book:");
            System.out.println("----------------------");

            System.out.println("Enter book title: ");
            String title = scanner.next();

            System.out.println("Enter book author: ");
            String author = scanner.next();

            System.out.println("Enter published year: ");
            int publishedYear = scanner.nextInt(); // Changed to int for simplicity

            // Create a new Book object
            books book = new books();
            book.setTitle(title);
            book.setAuthor(author);
            book.setPublishedYear(publishedYear);

            // Save the book to the database using Hibernate
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(book);
                transaction.commit();
                System.out.println("Book added successfully.");
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.out.println("Failed to add book. Error: " + e.getMessage());
            }
        }

    

   
 //=======================================================================================
    	  private static void viewBooks11() {
    	        System.out.println("Viewing Books:");
    	        System.out.println("----------------------");

    	        System.out.println("1. View book by ID");
    	        System.out.println("2. View all books");
    	        System.out.print("Enter your choice: ");
    	        int viewChoice = scanner.nextInt();

    	        switch (viewChoice) {
    	            case 1:
    	                viewBookById();
    	                break;
    	            case 2:
    	                viewAllBooks();
    	                break;
    	            default:
    	                System.out.println("Invalid choice! Please enter either 1 or 2.");
    	        }
    	    }

    	    private static void viewBookById() {
    	        System.out.print("Enter book ID to view details: ");
    	        int bookId = scanner.nextInt();

    	        // Query the database to fetch the book by ID
    	        books book = session.get(books.class, bookId);

    	        if (book != null) {
    	            System.out.println("Book Details:");
    	            System.out.println("ID: " + book.getId());
    	            System.out.println("Title: " + book.getTitle());
    	            System.out.println("Author: " + book.getAuthor());
    	            System.out.println("Published Year: " + book.getPublishedYear());
    	        } else {
    	            System.out.println("Book with ID " + bookId + " not found.");
    	        }
    	    }

    	    private static void viewAllBooks() {
    	        System.out.println("List of All Books:");
    	        System.out.println("ID\tTitle\tAuthor\tPublished Year");

    	        // Query the database to fetch all books
    	        List<books> bookList = session.createQuery("FROM books", books.class).getResultList();

    	        if (bookList.isEmpty()) {
    	            System.out.println("No books found.");
    	        } else {
    	            for (books book : bookList) {
    	                System.out.println(
    	                    book.getId() + "\t" +
    	                    book.getTitle() + "\t" +
    	                    book.getAuthor() + "\t" +
    	                    book.getPublishedYear()
    	                );
    	            }
    	        }
    	    }

    	   
    	
    
 //=======================================================================================   

    private static void addMembers() {
        System.out.println("Adding a new member:");
        System.out.println("----------------------");
        
        System.out.println("Enter id: ");
        int id = scanner.nextInt();

        System.out.println("Enter member name: ");
        String name = scanner.next();

        System.out.println("Enter member email: ");
        String email = scanner.next();
        
        System.out.println("Enter member Address: ");
        String address = scanner.next();
        
        System.out.println("Enter member mobileno: ");
        int mobileno = scanner.nextInt();


        // Create a new Member object
        Members member = new Members();
        member.setAddress(address);
        member.setEmailid(email);
        member.setId(id);
        member.setMobileno(mobileno);
        member.setName(name);

        // Save the member to the database using Hibernate
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(member);
            transaction.commit();
            System.out.println("Member added successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed to add member. Error: " + e.getMessage());
        }
    }
//=====================================================================================
    private static void addTransactions() {
        System.out.println("Adding a new transaction:");
        System.out.println("----------------------");

        System.out.println("Enter transaction_id : ");
        int transaction_id = scanner.nextInt();
        
        System.out.println("Enter member ID: ");
        int memberId = scanner.nextInt();

        System.out.println("Enter checkout_date: ");
        int checkout_date = scanner.nextInt();
        
        System.out.println("Enter return_date: ");
        int return_date = scanner.nextInt();

        System.out.println("Enter book ID: ");
        int bookId = scanner.nextInt();

        // Create a new Transaction object
        Transactions transaction1 = new Transactions();
        transaction1.setCheckout_date(checkout_date);
        transaction1.setMember_id(memberId);
        transaction1.setReturn_date(return_date);
        transaction1.setTransaction_id(transaction_id);

        // Save the transaction to the database using Hibernate
        try {
            session.beginTransaction();
            session.save(transaction1);
            session.getTransaction().commit();
            System.out.println("Transaction added successfully.");
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Failed to add transaction. Error: " + e.getMessage());
        }
    }

   

    private static void viewTransactions() {
        System.out.println("Viewing Transactions:");
        System.out.println("----------------------");

        // Query the database to fetch all transactions
        List<Transactions> transactionList = session.createQuery("FROM Transactions", Transactions.class).getResultList();

        if (transactionList.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("List of Transactions:");
            System.out.println("Transaction ID\tMember ID\tCheckout Date\tReturn Date");
            for (Transactions transaction : transactionList) {
                System.out.println(
                        transaction.getTransaction_id() + "\t" +
                        transaction.getMember_id() + "\t" +
                        transaction.getCheckout_date() + "\t" +
                        transaction.getReturn_date()
                );
            }
        }
    }

    private static void deleteTransactions() {
        System.out.println("Deleting a Transaction:");
        System.out.println("----------------------");

        System.out.print("Enter the ID of the transaction to delete: ");
        int transactionIdToDelete = scanner.nextInt();

        // Check if the transaction with the given ID exists in the database
        Transactions transactionToDelete = session.get(Transactions.class, transactionIdToDelete);
        if (transactionToDelete == null) {
            System.out.println("Transaction with ID " + transactionIdToDelete + " not found.");
            return; // Exit the method if the transaction is not found
        }

        // Confirm with the user before deleting the transaction
        System.out.println("Are you sure you want to delete the following transaction?");
        System.out.println("Transaction ID: " + transactionToDelete.getTransaction_id());
        System.out.println("Member ID: " + transactionToDelete.getMember_id());
        System.out.println("Checkout Date: " + transactionToDelete.getCheckout_date());
        System.out.println("Return Date: " + transactionToDelete.getReturn_date());
        System.out.print("Enter 'yes' to confirm deletion: ");
        String confirmation = scanner.next();

        if (confirmation.equalsIgnoreCase("yes")) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.delete(transactionToDelete);
                transaction.commit();
                System.out.println("Transaction deleted successfully.");
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.out.println("Failed to delete transaction. Error: " + e.getMessage());
            }
        } else {
            System.out.println("Deletion canceled. Transaction not deleted.");
        }
    }

 //===================================================================================  
    private static void updateBook11() {
        System.out.println("Updating Book Details:");
        System.out.println("----------------------");

        System.out.print("Enter the ID of the book to update: ");
        int bookIdToUpdate = scanner.nextInt();

        // Check if the book with the given ID exists in the database
        books bookToUpdate = session.get(books.class, bookIdToUpdate);
        if (bookToUpdate == null) {
            System.out.println("Book with ID " + bookIdToUpdate + " not found.");
            return; // Exit the method if the book is not found
        }

        // Display the current details of the book
        System.out.println("Current Details:");
        System.out.println("Title: " + bookToUpdate.getTitle());
        System.out.println("Author: " + bookToUpdate.getAuthor());
        System.out.println("Published Year: " + bookToUpdate.getPublishedYear());

        // Ask the user to enter the updated details
        System.out.println("Enter new title (press Enter to skip): ");
        String newTitle = scanner.nextLine().trim(); // Use nextLine() to handle spaces
        if (!newTitle.isEmpty()) {
            bookToUpdate.setTitle(newTitle);
        }

        System.out.println("Enter new author (press Enter to skip): ");
        String newAuthor = scanner.nextLine().trim(); // Use nextLine() to handle spaces
        if (!newAuthor.isEmpty()) {
            bookToUpdate.setAuthor(newAuthor);
        }

        System.out.println("Enter new published year (press Enter to skip): ");
        String newPublishedYearInput = scanner.nextLine().trim(); // Use nextLine() to handle spaces
        if (!newPublishedYearInput.isEmpty()) {
            try {
                int newPublishedYear = Integer.parseInt(newPublishedYearInput);
                bookToUpdate.setPublishedYear(newPublishedYear);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for published year. Please enter a valid year.");
                return; // Exit the method if the input is invalid
            }
        }

        // Update the book in the database using Hibernate
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(bookToUpdate);
            transaction.commit();
            System.out.println("Book details updated successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed to update book details. Error: " + e.getMessage());
        }
    }



//==================================================================================
private static void deleteBooks1(Scanner scanner) {
 System.out.println("Deleting a Book:");
 System.out.println("----------------------");

 System.out.print("Enter the ID of the book to delete: ");
 int bookIdToDelete = scanner.nextInt();

 // Check if the book with the given ID exists in the database
 books bookToDelete = session.get(books.class, bookIdToDelete);
 if (bookToDelete == null) {
     System.out.println("Book with ID " + bookIdToDelete + " not found.");
     return; // Exit the method if the book is not found
 }

 // Confirm with the user before deleting the book
 System.out.println("Are you sure you want to delete the following book?");
 System.out.println("ID: " + bookToDelete.getId());
 System.out.println("Title: " + bookToDelete.getTitle());
 System.out.println("Author: " + bookToDelete.getAuthor());
 System.out.println("Published Year: " + bookToDelete.getPublishedYear());
 System.out.print("Enter 'yes' to confirm deletion: ");
 String confirmation = scanner.next();

 if (confirmation.equalsIgnoreCase("yes")) {
     Transaction transaction = null;
     try {
         transaction = session.beginTransaction();
         session.delete(bookToDelete);
         transaction.commit();
         System.out.println("Book deleted successfully.");
     } catch (Exception e) {
         if (transaction != null) {
             transaction.rollback();
         }
         System.out.println("Failed to delete book. Error: " + e.getMessage());
     }
 } else {
     System.out.println("Deletion canceled. Book not deleted.");
 }
}
//====================================================
private static void exit() {
    System.out.println("Exiting...");
    session.close(); // Close Hibernate session before exiting
    sessionFactory.close(); // Close Hibernate session factory
    System.exit(0); // Terminate the program
}
}