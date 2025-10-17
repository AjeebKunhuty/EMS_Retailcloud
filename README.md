Created by Ajeeb Kunhuty Abdul Raheem.
Contact me at ajkeducat@gmail.com

# JSON Schema :
### Create new employee
`POST /api/employee`

body:
{
"name": "Name1",
"dob": "2025-11-04",
"salary": 264000.00,
"department": 1,
"address": "Kozhikode",
"role": "Product Engineer I",
"joiningDate": "2025-11-30",
"bonusPercentage": 10.00,
"manager" : 4
}

response : `"Name1 added successfully"`

### Update existing employee
`PUT /api/employee/{id}`

pathVariable : id (Integer)

body: Any combination of fields

{
"name": "Name1",
"dob": "2025-11-04",
"salary": 264000.00,
"department": 1,
"address": "Kozhikode",
"role": "Product Engineer I",
"joiningDate": "2025-11-30",
"bonusPercentage": 10.00,
"manager" : 4
}

response:
`EmployeeDTO(
        Integer id,
        String name,
        LocalDate dob,
        BigDecimal salary,
        String department,
        String address,
        String role,
        LocalDate joiningDate,
        BigDecimal bonusPercentage,
        String manager)`

### Transfer employee to another department
`POST /api/employee/{empId}/transfer`

pathVariable: empId (Integer)

parameter: deptId (Integer)

response: `"{empName} moved to {deptName}"`

### Set employee's department to null
(For top-level employees or layoff before deleting department)

`POST /api/employee/{empId}/exit`

pathVariable: empId (Integer)

response: `"{empName} department set to null"`

### Fetch employee details
`GET /api/employee`

parameter : lookup (boolean)

`lookup = true` => fetches employees' name and id

`lookup = false` (not required) => fetches employees' complete details

response: `{"totalPages" : y, "employees" : [], "currentPage" : x}`

### Fetch all employee and department details
`GET /api/employee/all`

response: `{"totalPages" : y, "employees" : [], "currentPage" : x},{"totalPages" : b, "departments" : [], "currentPage" : a`

### Fetch reporting chain
`GET /api/employee/chain`

response: `{"totalPages" : y, "employees" : [{"id" : id, "name" : name, "department" : deptName, "role" : role, "managerChain" : [managerName as String]}], "currentPage" : x`

### Fetch employee details ordered by longest serving
`GET /api/employee/longserving`

(based on joiningDate)

response: `{"totalPages" : y, "employees" : [], "currentPage" : x`

### Create new department
`POST /api/department`

body: {
"name" : "deptName",
"creationDate": "2008-07-01",
"head" : empId
}

response: `"{deptName} added"`

### Update existing department
`PUT /api/department/{id}`

pathVariable: id (Integer)

body: Any combination of fields

{
"name": "deptName",
"creationDate": "2008-10-01",
"head": "headID"
}

response: `DepartmentDTO( Integer id,
    String name,
    LocalDate creationDate,
    String head )`

### Delete existing department
`DELETE /api/department/{id}`

(works if no employees assigned to this department, enforced by java logic and sql on delete restrict)

pathVariable: id (Integer)

response: `Department {deptId} deleted successfully`

### Fetch department details
`GET /api/department`

optional parameter: expand (String)

`expand = "employee"` => Department details with employeeList

`without parameter or any other string` => Department details without employeeList

`/count` => Department details with employee count per department

`/salaryAnalytics` => Department details with minimum, maximum, average salaries

response: `{"totalPages" : y, "departments" : [ DepartmentDTO(     Integer id,
    String name,
    LocalDate creationDate,
    String head,
    List<EmployeeDTO> employeeList,
    Integer employeeCount,
    BigDecimal min,
    BigDecimal max,
    BigDecimal avg )], "currentPage" : x`

