package enterprise;

import java.util.*;

public class Enterprise implements IEnterprise {

    private List<Employee> employees;
    private Map<UUID, Employee> employeesById;
    private SortedMap<Date, List<Employee>> employeesByHiredate;
    private Map<Position, List<Employee>> employeesByPosition;
    private SortedMap<Double, List<Employee>> employeesBySalary;
    private SortedMap<String, List<Employee>> employeesByNamePosition;
    private SortedMap<Position, SortedMap<Double, List<Employee>>> employeesByPositionSalary;

    public Enterprise() {
        this.employees = new LinkedList<>();
        this.employeesById = new HashMap<>();
        this.employeesByHiredate = new TreeMap<>();
        this.employeesByPosition = new HashMap<>();
        this.employeesBySalary = new TreeMap<>();
        this.employeesByNamePosition = new TreeMap<>();
        this.employeesByPositionSalary = new TreeMap<>();
    }

    @Override
    public void add(Employee employee) {
        employees.add(employee);
        employeesById.put(employee.getId(), employee);

        employeesByHiredate.putIfAbsent(employee.getHireDate(), new ArrayList<>());
        employeesByHiredate.get(employee.getHireDate()).add(employee);

        employeesByPosition.putIfAbsent(employee.getPosition(), new ArrayList<>());
        employeesByPosition.get(employee.getPosition()).add(employee);

        employeesBySalary.putIfAbsent(employee.getSalary(), new ArrayList<>());
        employeesBySalary.get(employee.getSalary()).add(employee);

        String namePosition = employee.getFirstName() + employee.getLastName() + employee.getPosition().toString();
        employeesByNamePosition.putIfAbsent(namePosition, new ArrayList<>());
        employeesByNamePosition.get(namePosition).add(employee);

        employeesByPositionSalary.putIfAbsent(employee.getPosition(), new TreeMap<>());
        employeesByPositionSalary.get(employee.getPosition()).putIfAbsent(employee.getSalary(), new ArrayList<Employee>() {
        });
        employeesByPositionSalary.get(employee.getPosition()).get(employee.getSalary()).add(employee);
    }

    @Override
    public boolean contains(UUID id) {
        return this.employeesById.containsKey(id);
    }

    @Override
    public boolean contains(Employee employee) {
        return this.employees.contains(employee);
    }

    @Override
    public boolean change(UUID id, Employee employee) {
        if (!this.employeesById.containsKey(id)) {
            return false;
        }
        this.employeesById.put(id, employee);
        return true;
    }

    @Override
    public boolean fire(UUID id) {
        if (!this.employeesById.containsKey(id)) {
            return false;
        }
        Employee employee = this.employeesById.get(id);
        this.employeesById.remove(id);
        this.employees.remove(employee);
        this.employeesByHiredate.getOrDefault(employee.getHireDate(), new ArrayList<>()).remove(employee);
        this.employeesByPosition.getOrDefault(employee.getPosition(), new ArrayList<>()).remove(employee);
        this.employeesBySalary.getOrDefault(employee.getSalary(), new ArrayList<>()).remove(employee);
        this.employeesByPositionSalary.getOrDefault(employee.getPosition(), new TreeMap<>())
                .getOrDefault(employee.getSalary(), new ArrayList<>()).remove(employee);
        String namePosition = employee.getFirstName() + employee.getLastName() + employee.getPosition().toString();
        this.employeesByNamePosition.getOrDefault(namePosition, new ArrayList<>()).remove(employee);
        return true;
    }

    @Override
    public boolean raiseSalary(int months, int percent) {
        Date dateBeforeNMonts = new Date();
        dateBeforeNMonts.setMonth(dateBeforeNMonts.getMonth() - months);
        if (this.employeesByHiredate.subMap(new Date(Long.MIN_VALUE), dateBeforeNMonts).isEmpty()) {
            return false;
        }
        for (List<Employee> employeeList : this.employeesByHiredate.subMap(new Date(Long.MIN_VALUE), dateBeforeNMonts).values()) {
            for (Employee employee : employeeList) {
                this.employeesBySalary.getOrDefault(employee.getSalary(), new ArrayList<>()).remove(employee);
                employee.setSalary(employee.getSalary() * percent / 100 + employee.getSalary());
                employeesBySalary.putIfAbsent(employee.getSalary(), new ArrayList<>());
                employeesBySalary.get(employee.getSalary()).add(employee);
            }
        }
        return true;
    }

    @Override
    public int getCount() {
        return this.employees.size();
    }

    @Override
    public Employee getByUUID(UUID id) {
        if (!this.employeesById.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return this.employeesById.get(id);
    }

    @Override
    public Position positionByUUID(UUID id) {
        if (!this.employeesById.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return this.employeesById.get(id).getPosition();
    }

    @Override
    public Iterable<Employee> getByPosition(Position position) {
        if (!this.employeesByPosition.containsKey(position)) {
            throw new IllegalArgumentException();
        }
        return this.employeesByPosition.get(position);
    }

    @Override
    public Iterable<Employee> getBySalary(double minSalary) {
        List<Employee> outputList = (LinkedList<Employee>) this.searchBySalary(minSalary, Double.MAX_VALUE);
        if (outputList.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return outputList;
    }

    @Override
    public Iterable<Employee> getBySalaryAndPosition(double salary, Position position) {
        if (!this.employeesByPositionSalary.containsKey(position) ||
                !this.employeesByPositionSalary.get(position).containsKey(salary)) {
            throw new IllegalArgumentException();
        }

        return this.employeesByPositionSalary.get(position).get(salary);
    }

    @Override
    public Iterable<Employee> searchBySalary(double minSalary, double maxSalary) {
        if (this.employeesBySalary.subMap(minSalary, maxSalary + 0.0001).isEmpty()) {
            return new LinkedList<>();
        }
        List<Employee> outputList = new LinkedList<>();
        for (List<Employee> employeeList : this.employeesBySalary.subMap(minSalary, maxSalary + 0.0001).values()) {
            outputList.addAll(employeeList);
        }
        return outputList;
    }

    @Override
    public Iterable<Employee> searchByPosition(Iterable<Position> positions) {
        List<Employee> outputList = new LinkedList<>();
        for (Position p : positions) {
            if (this.employeesByPosition.containsKey(p)) {
                outputList.addAll(this.employeesByPosition.get(p));
            }
        }
        if (outputList.isEmpty()) {
            return new ArrayList<>();
        }
        return outputList;
    }

    @Override
    public Iterable<Employee> allWithPositionAndMinSalary(Position position, double minSalary) {
        if (!this.employeesByPositionSalary.containsKey(position) ||
                this.employeesByPositionSalary.get(position).subMap(minSalary, minSalary + Double.MAX_VALUE).isEmpty()) {
            return new ArrayList<>();
        }
        SortedSet<Employee> outputList = new TreeSet<>();
        for (List<Employee> employeeList : this.employeesByPositionSalary.get(position).subMap(minSalary, minSalary + Double.MAX_VALUE).values()) {
            outputList.addAll(employeeList);
        }
        return outputList;
    }

    @Override
    public Iterable<Employee> searchByFirstName(String firstName) {
        if (this.employeesByNamePosition.subMap(firstName, firstName + Character.MAX_VALUE).isEmpty()) {
            return new ArrayList<>();
        }
        List<Employee> outputList = new LinkedList<>();
        for (List<Employee> employeeList : this.employeesByNamePosition.subMap(firstName, firstName + Character.MAX_VALUE).values()) {
            outputList.addAll(employeeList);
        }
        return outputList;
    }

    @Override
    public Iterable<Employee> searchByNameAndPosition(String firstName, String lastName, Position position) {
        String namePosition = firstName + lastName + position.toString();
        if (!this.employeesByNamePosition.containsKey(namePosition)) {
            return new ArrayList<>();
        }

        return this.employeesByNamePosition.get(namePosition);
    }

    @Override
    public Iterator<Employee> iterator() {
        return this.employees.iterator();
    }
}
