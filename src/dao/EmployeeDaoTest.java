package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmployeeDaoTest {
    private EmployeeDaoImplement employeeDaoImplement = new EmployeeDaoImplement();
    private List<Employee> employeeList;

    public static void main(String[] args) throws SQLException {
        EmployeeDaoTest edt = new EmployeeDaoTest();
        edt.menuPrograma();

    }

    void menuOpciones() {
        System.out.print("Menu:\n" +
                "[1]- Create Employee\n" +
                "[2]- Remove Employee by ID\n" +
                "[3]- Read Employee by ID\n" +
                "[4]- Update Employee\n" +
                "[5]- Listar Employees\n" +
                "opcion: ");
    }

    void menuPrograma() throws SQLException {
        Scanner sc = new Scanner(System.in);

        menuOpciones();
        int opcion = sc.nextInt();
        sc.nextLine();

        while (opcion != 6) {

            switch (opcion) {
                case 1:
                    //CREATE EMPLOYEE
                    Employee newEmployee;
                    newEmployee = createEmployee();
                    employeeDaoImplement.create(newEmployee);
                    break;
                case 2:
                    // DELETE EMPLOYEE
                    int idEmployee = sc.nextInt();
                    employeeDaoImplement.delete(idEmployee);
                    break;
                case 3:
                    // READ BY ID
                    int idEmployeeRead = sc.nextInt();
                    employeeDaoImplement.read(idEmployeeRead);
                    break;
                case 4:
                    // UPDATE EMPLOYEE
                    Employee updaEmployee;
                    updaEmployee = updtEmployee();
                    employeeDaoImplement.update(updaEmployee);
                    break;
                case 5:
                    // LISTAR TABLE
                    employeeList = employeeDaoImplement.getEmployees();
                    break;
                default:
                    System.err.println("Introduce una opcion entre 1 -5");
            }
            opcion = sc.nextInt();
        }
    }

    Employee createEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.println("introduce lastName:");
        String lastName = sc.nextLine();

        System.out.println("introduce firstName");
        String firstName = sc.nextLine();

        System.out.println("introduce title");
        String title = sc.nextLine();

        System.out.println("introduce reportTo");
        int reportsTo = sc.nextInt();

        System.out.println("introduce birth Date");
        String birthDate = sc.nextLine();
        System.out.println("introduce hire Date");
        String hireDate = sc.nextLine();
        System.out.println("introduce  address");
        String address = sc.nextLine();
        System.out.println("introduce ");
        String city = sc.nextLine();
        System.out.println("introduce ");
        String state = sc.nextLine();
        System.out.println("introduce ");
        String country = sc.nextLine();
        System.out.println("introduce ");
        String postalCode = sc.nextLine();
        System.out.println("introduce ");
        String phone = sc.nextLine();
        System.out.println("introduce ");
        String fax = sc.nextLine();
        System.out.println("introduce ");
        String email = sc.nextLine();
        Employee employee = new Employee(lastName, firstName, title, reportsTo, birthDate, hireDate, address, city, state, country, postalCode, phone, fax, email);
        return employee;
    }

    Employee updtEmployee() {
        Scanner sc = new Scanner(System.in);
        String lastName = sc.nextLine();
        String firstName = sc.nextLine();
        String title = sc.nextLine();
        int reportsTo = sc.nextInt();
        String birthDate = sc.nextLine();
        String hireDate = sc.nextLine();
        String address = sc.nextLine();
        String city = sc.nextLine();
        String state = sc.nextLine();
        String country = sc.nextLine();
        String postalCode = sc.nextLine();
        String phone = sc.nextLine();
        String fax = sc.nextLine();
        String email = sc.nextLine();
        int idEmployee = sc.nextInt();
        Employee employee = new Employee(lastName, firstName, title, reportsTo, birthDate, hireDate, address, city, state, country, postalCode, phone, fax, email, idEmployee);
        return employee;
    }
}
