import { useEffect , useState } from "react";
import {useNavigate , Link} from "react-router-dom";
import axios from "axios";

const Employee = ()=>{
    const [employees,setEmployees] = useState([]);
    const [loading,setLoading] = useState(true);

    const token = localStorage.getItem("token");
    const storedRoles = JSON.parse(localStorage.getItem("roles")||"[]")
    const isAdmin = storedRoles.includes("ROLE_ADMIN")

    const navigate = useNavigate();

    const handleEdit = (empId)=>{
        if(!isAdmin){
            alert("You don't have access to edit.");
            return;
        }
        navigate(`/employee/edit/${empId}`)
    }

    const handleDelete = (id)=>{
        if(!isAdmin){
            alert("You don't have access to delete.");
            return;
        }

        if(window.confirm("Are you sure you want to delete this employee?")){
            axios
                .delete(`http://localhost:3001/employee/${id}`,{
                    headers : {
                        Authorization : `Bearer ${token}`,
                    }
                })
                .then(()=>{
                    setEmployees(employees.filter(emp=>emp.empId !== id));
                })
                .catch(err=>{
                    console.log("Error deleting employee : " , err);
                })
        }
    }

    const handleTasks = (empId)=>{
        navigate(`/tasks/${empId}`)
    }

    useEffect(()=>{
        axios
            .get("http://localhost:3001/employee",{
                headers : {
                    Authorization : `Bearer ${token}`,
                },
            })
            .then((res)=>{
                setEmployees(res.data);
                setLoading(false);
            })
            .catch((err)=>{
                console.log("Error fetching employees : ",err);
                setLoading(false);
            });
    }, [token]);


    if(loading) return <p>Loading...</p>;

    return(
        <div className = "employee-page">
            <h2>All Employee Details</h2>
            <table className = "employee-table">
                <thead>
                <tr>
                    <th>Emp Id</th>
                    <th>Name</th>
                    <th>UserName</th>
                    <th>Email</th>
                    <th>Roles</th>
                    <th>Actions</th>
                    <th>Tasks</th>
                </tr>
                </thead>
                <tbody>
                {employees.map((emp)=>(
                    <tr key={emp.empId}>
                        <td>{emp.empId}</td>
                        <td>{emp.name}</td>
                        <td>{emp.userName}</td>
                        <td>{emp.email}</td>
                        <td>{emp.roles?.map(role => role.roleName).join(', ')}</td>
                        <td>
                            <button onClick = {()=>handleEdit(emp.empId)} >edit</button>
                            <button onClick = {()=>handleDelete(emp.empId)} >delete</button>
                        </td>
                        <td>
                            <button onClick = {()=>handleTasks(emp.empId)}>tasks</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}

export default Employee;