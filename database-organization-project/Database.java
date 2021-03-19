import java.util.ArrayList;
import java.util.List;
import java.util.Random; 
import java.sql.*;
//import javax.sql.*;

public class Database {
    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String user = "postgres";
    private static String password = "password";

    public static void main(String[] args) {
        Database database = new Database();
        
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Class Not Found: " + cnfe.getMessage());
        }
        try {
            database.insertCustomers(conn);
            database.insertEmployees(conn);
            database.insertInventory(conn);
            database.insertModel(conn);
            System.out.println("Successfully added data to database");
        }  catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void insertCustomers(Connection conn) {
        String SQL = "INSERT INTO customer(FirstName,LastName,CustomerID,StreetAddress,ApartmentNumber,City,State,ZipCode,PhoneNumber,Email) " + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (
            PreparedStatement statement = conn.prepareStatement(SQL);) {
            int count = 0;
            String[] firstNames = new String[] {"John", "Daniel", "James", "Robert", "Michael", "Sophia", "Kamila", "Mary", "Jennifer", "Susan"};
            String[] lastNames = new String[] {"Smith", "Jablonski", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Wilson", "Stone"};
            List<Customer> list = new ArrayList<Customer>();
            for(int i = 0; i < 10; i++) {
                Random rand = new Random();
                int id = rand.nextInt(9999);
                Customer customer = new Customer();
                customer.FirstName = firstNames[i];
                customer.LastName = lastNames[i];
                customer.CustomerID = id;
                customer.StreetAddress = "123 Easy St";
                customer.ApartmentNumber = id % 2 == 0 ? String.valueOf(id/2) : null;
                customer.City = "Chicago";
                customer.State = "Illinois";
                customer.ZipCode = 60618;
                customer.PhoneNumber = 1234567890;
                customer.Email = firstNames[i] + "@gmail.com";
                list.add(customer);
            }

            for (Customer customer : list) {
                statement.setString(1, customer.FirstName);
                statement.setString(2, customer.LastName);
                statement.setInt(3, customer.CustomerID);
                statement.setString(4, customer.StreetAddress);
                statement.setString(5, customer.ApartmentNumber);
                statement.setString(6, customer.City);
                statement.setString(7, customer.State);
                statement.setInt(8, customer.ZipCode);
                statement.setInt(9, customer.PhoneNumber);
                statement.setString(10, customer.Email);

                statement.addBatch();
                count++;
                // execute every 100 rows or less
                if (count % 100 == 0 || count == list.size()) {
                    statement.executeBatch();
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insertEmployees(Connection conn) {
        String SQL = "INSERT INTO employee(FirstName,LastName,EmployeeID,PaymentType,JobType,SSN,Salary) " + "VALUES(?,?,?,?,?,?,?)";
        try (
            PreparedStatement statement = conn.prepareStatement(SQL);) {
            int count = 0;
            
            String [] firstName = new String[] {"Samantha", "Bryan","Andrew", "Bill", "Zack", "Mark", "Will", "Tom", "Ted","Mark"};
            String [] lastName = new String[] {"Park", "Cuomo", "Clinton", "Gates", "Kim" , "Wang", "Talyor", "Wilson","Robert", "Evans"};

            List<Employee> list = new ArrayList<Employee>();

            for(int i = 0; i < 10; i++) {
                Random rand = new Random();
                Employee employee = new Employee();

                employee.FirstName = firstName[i];
                employee.LastName = lastName[i];
                employee.EmployeeID = rand.nextInt(100000);
                list.add(employee);
            }

            for (Employee employee : list) {
                statement.setString(1, employee.FirstName);
                statement.setString(2, employee.LastName);
                statement.setInt(3, employee.EmployeeID);
                statement.setString(4, employee.PaymentType);
                statement.setString(5, employee.JobType);
                statement.setInt(6, employee.SSN);
                statement.setInt(7, employee.Salary);
        

                statement.addBatch();
                count++;
                // execute every 100 rows or less
                if (count % 100 == 0 || count == list.size()) {
                    statement.executeBatch();
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insertModel(Connection conn) {
        String SQL = "INSERT INTO model(ModelNumber,SalePrice,ModelName) " + "VALUES(?,?,?)";
        try (
            PreparedStatement statement = conn.prepareStatement(SQL);) {
            int count = 0;
            String [] names= new String[] {"tdpsg", "qbrhu", "awlks", "nlban","tuqfa","zyvlz","qzfgb","wrxaf","fshos","terto"};
            List<Model> list = new ArrayList<Model>();

            for(int i = 0; i < 10; i++) {
                Random rand = new Random();
                Model model = new Model();
                model.ModelNumber = rand.nextInt(100000);
                model.SalePrice= rand.nextInt(5000);
                model.ModelName= names[i];
                list.add(model);
            }

            for (Model model : list) {
                statement.setString(3, model.ModelName);
                statement.setInt(1, model.ModelNumber);
                statement.setInt(2, model.SalePrice);
    
                statement.addBatch();
                count++;
                // execute every 100 rows or less
                if (count % 100 == 0 || count == list.size()) {
                    statement.executeBatch();
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insertInventory(Connection conn) {
        String SQL = "INSERT INTO inventory(ID,Cost,LeadTime,CategoryType,Quantity,Department) " + "VALUES(?,?,?,?,?,?)";
        try (
            PreparedStatement statement = conn.prepareStatement(SQL);) {
            int count = 0;
            String[] CategoryType = new String[] {"Home Goods", "Electrical"};
            String[] departments = new String[] {"Raw Materials", "Work-In-Progress", "Finished Goods", "Packing Materials", "Safety Stock", "Decoupling", "Cycle", "MRO Goods", "Services", "Transportation"};
            List<Inventory> list = new ArrayList<Inventory>();
            for(int i = 0; i < 10; i++) {
                Random rand = new Random();
                int id = rand.nextInt(100000);
                Inventory inventory = new Inventory();
                inventory.ID = id;
                inventory.Cost = i + 500;
                inventory.LeadTime = rand.nextInt(30);
                inventory.CategoryType = i % 2 == 0 ? CategoryType[0] : CategoryType[1];
                inventory.Quantity = rand.nextInt(10) + i + 1;
                inventory.Department = departments[i];
                list.add(inventory);
            }

            for (Inventory inventory : list) {
                statement.setInt(1, inventory.ID);
                statement.setInt(2, inventory.Cost);
                statement.setInt(3, inventory.LeadTime);
                statement.setString(4, inventory.CategoryType);
                statement.setInt(5, inventory.Quantity);
                statement.setString(6, inventory.Department);

                statement.addBatch();
                count++;
                // execute every 100 rows or less
                if (count % 100 == 0 || count == list.size()) {
                    statement.executeBatch();
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}