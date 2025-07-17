import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

const TaskPage = ()=>{
    const [tasks,setTasks] = useState([]);
    const [loading,setLoading] = useState(true);
    const {empId} = useParams();
    const navigate = useNavigate();

    const token = localStorage.getItem("token");
    const storedRoles = JSON.parse(localStorage.getItem("roles")||"[]");
    const isAdmin = storedRoles.includes("ROLE_ADMIN");

    useEffect(()=>{
        axios
            .get(`http://localhost:3001/task/employee/${empId}`,{
                headers : {
                    Authorization : `Bearer ${token}`,
                },
            })
            .then((res)=>{
                setTasks(res.data);
                setLoading(false);
            })
            .catch((err)=>{
                console.log("Error fetching tasks : ",err);
                setLoading(false);
            })
    },[empId,token]);

    const handleAddTask = (empId)=>{
        if(!isAdmin){
            alert("Only admins can add tasks.")
            return;
        }
        navigate(`/tasks/add/${empId}`);
    }

    const handleEdit = (taskId)=>{
        if(!isAdmin){
            alert("Only admins can edit tasks.")
            return;
        }
        navigate(`/tasks/edit/${taskId}`);
    }

    const handleDelete = (taskId)=>{
        if(!isAdmin){
            alert("Only admins can delete tasks.")
            return;
        }
        if(window.confirm("Are you sure want to delete thsi task?")){
            axios
                .delete(`http://localhost:3001/task/${taskId}`,{
                    headers : {
                        Authorization : `Bearer ${token}`,
                    }
                })
                .then(()=>{
                    setTasks(tasks.filter((task)=>task.taskId!==taskId));
                })
                .catch((err)=>{
                    console.log("Error deleting task : ",err);
                })
        }
    };

    if(loading) return <p>Loading tasks...</p>

    return(
        <>
            <div>
                <h2>Tasks Assigned to Employee #{empId}</h2>

                <button onClick={() => navigate("/employee")}>⬅ Back</button>

                {isAdmin && (
                    <div>
                        <button onClick={()=>handleAddTask(empId)}>+ Add Task</button>
                    </div>
                )}
                <br/>
                <table>
                    <thead>
                    <tr>
                        <th>Task Id</th>
                        <th>Task Name</th>
                        <th>Description</th>
                        <th>Status</th>
                        {isAdmin && <th>Actions</th>}
                    </tr>
                    </thead>
                    <tbody>
                    {tasks.map((task)=>(
                        <tr key={task.taskId}>
                            <td>{task.taskId}</td>
                            <td>{task.taskName}</td>
                            <td>{task.description}</td>
                            <td>{task.status}</td>
                            {isAdmin && (
                                <td>
                                    <button onClick={()=>handleEdit(task.taskId)}>edit</button>
                                    <button onClick={()=>handleDelete(task.taskId)}>delete</button>
                                </td>
                            )}
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </>
    )
}

export default TaskPage;