import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		
		Employee emp1 = new Employee(1,"Engineer","One",10000000);
		Employee emp2 = new Employee(2,"Engineer","Two",10500000);
		Employee emp3 = new Employee(3,"Doctor","Three",12000000);
		Employee emp4 = new Employee(4,"Engineer","Four",7000000);
		Employee emp5 = new Employee(5,"Doctor","Five",9000000);
		
		List<Employee> employeeList = Arrays.asList(emp1,emp2,emp3,emp4,emp5);
		System.out.println(groupByJobTitle(employeeList));
		System.out.println(groupByJobTitleWithStreamApi(employeeList));
		System.out.println(calculateAverage(employeeList));
		System.out.println(calculateAverageWithStreamApi(employeeList));

	}
	
	public static Map<String, List<Employee>> groupByJobTitle(List<Employee> employeeList) {
		Map<String, List<Employee>> resultMap = new HashMap<>();
		for (int i = 0; i < employeeList.size(); i++) {
			Employee employee = employeeList.get(i);
			List<Employee> employeeSubList = resultMap.getOrDefault(employee.getTitle(), new ArrayList<Employee>());
			employeeSubList.add(employee);
			resultMap.put(employee.getTitle(), employeeSubList);
		}
		return resultMap;
	}
	
	public static Map<String, List<Employee>> groupByJobTitleWithStreamApi(List<Employee> employeeList) {
		//implement using stream api approach
		return employeeList.stream()
			    .filter(e -> e.getTitle() != null)
			    .collect(Collectors.groupingBy(Employee::getTitle));
		
	}
	
	public static double calculateAverage(List<Employee> employeeList) {
		int sum = 0;
		int count = 0;
		for (int i = 0; i < employeeList.size(); i++) {
			Employee employee = employeeList.get(i);
			sum += employee.getSalary();
			count++;
		}
		return (double) sum / count;
	}
	
	public static double calculateAverageWithStreamApi(List<Employee> employeeList) {
		//Implement the above code using streams and lambdas
		return employeeList.stream()
			    .map(Employee::getSalary)
			    .reduce(0, Integer::sum) / (double) employeeList.size();
	}

}
