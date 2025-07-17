import { useEffect , useState } from "react";
import { useParams , useNavigate } from "react-router-dom";
import axios from "axios";

const EditTask = ()=>{
    const {taskId} = useParams();
    const navigate = useNavigate();
    const token = localStorage.getItem("token");

    const [task,setTask] = useState(null);
    const [loading,setLoading] = useState(true);

    useEffect(()=>{
        axios.get(`http://localhost:3001/task/tasks/${taskId}`, {
            headers : {
                Authorization : `Bearer ${token}`,
            },
        })
            .then((res)=>{
                setTask(res.data);
                setLoading(false);
            })
            .catch((err)=>{
                console.log("Error fetching tasks : ",err);
                alert("Failed to fetch task");
                setLoading(false);
            })
    },[taskId,token])

    const handleSubmit = ((e)=>{
        e.preventDefault();

        axios.put(`http://localhost:3001/task/update/${taskId}`,task,{
            headers : {
                Authorization : `Bearer ${token}`,
            },
        })
            .then(()=>{
                alert("Task updated successfully");
                navigate(`/tasks/${task.empId}`)
            })
            .catch((err)=>{
                console.log("Error updating task : ",err);
                alert("Failed to update task");
            })
    })

    const handleChange = (e)=>{
        const {name,value} = e.target;
        setTask(prev=>({...prev,[name]:value}));
    }

    if(loading) return <p>Loading task...</p>
    if(!task) return <p>Task not found.</p>

    return(
        <>
            <h2>Edit Task #{taskId}</h2>

            <button onClick={() => navigate(`/tasks/${task.empId}`)}>⬅ Back</button>

            <form onSubmit={handleSubmit}>
                <div>
                    <label>Task Name : </label>
                    <input type="text" name="taskName" value = {task.taskName} onChange={handleChange} required/>
                </div>
                <br/>
                <div>
                    <label>Description : </label>
                    <textarea name="description" value = {task.description} onChange = {handleChange} rows = "4" cols = "40" required></textarea>
                </div>
                <br/>
                <div>
                    <label>Status : </label>
                    <select name="status" value={task.status} onChange={handleChange} required>
                        <option value = "Assigned">Assigned</option>
                        <option value = "Yet to start">Yet to start</option>
                        <option value = "In progess">In progress</option>
                        <option value = "Completed">Completed</option>
                    </select>
                </div>
                <br/>
                <button type="submit">Update task</button>
            </form>
        </>
    )
}

export default EditTask;