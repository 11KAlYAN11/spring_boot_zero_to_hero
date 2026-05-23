import { useEffect, useState } from "react";

function App() {

    const [employees, setEmployees] = useState([]);

    const [name, setName] = useState("");

    const [salary, setSalary] = useState("");

    // FETCH EMPLOYEES

    const fetchEmployees = async () => {

        const response =
            await fetch("http://localhost:8080/employees");

        const data = await response.json();

        setEmployees(data);
    };

    // CREATE EMPLOYEE

    const addEmployee = async () => {

        await fetch("http://localhost:8080/employees", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                name,
                salary
            })
        });

        setName("");
        setSalary("");

        fetchEmployees();
    };

    // DELETE EMPLOYEE

    const deleteEmployee = async (id) => {

        await fetch(
            `http://localhost:8080/employees/${id}`,
            {
                method: "DELETE"
            }
        );

        fetchEmployees();
    };

    // INITIAL LOAD

    useEffect(() => {
        fetchEmployees();
    }, []);

    return (
        <div style={{ padding: "20px" }}>

            <h1>Employee Management</h1>

            <input
                placeholder="Name"
                value={name}
                onChange={(e) =>
                    setName(e.target.value)}
            />

            <input
                placeholder="Salary"
                value={salary}
                onChange={(e) =>
                    setSalary(e.target.value)}
            />

            <button onClick={addEmployee}>
                Add Employee
            </button>

            <hr />

            {
                employees.map(employee => (

                    <div key={employee.id}>

                        <h3>
                            {employee.name}
                        </h3>

                        <p>
                            Salary: {employee.salary}
                        </p>

                        <button
                            onClick={() =>
                                deleteEmployee(employee.id)
                            }
                        >
                            Delete
                        </button>

                        <hr />

                    </div>
                ))
            }

        </div>
    );
}

export default App;