import {useState} from "react";
import {useParams , useNavigate} from "react-router-dom";
import axios from "axios";

const AddTask = ()=>{
    const {empId} = useParams();
    const navigate = useNavigate();
    const token = localStorage.getItem("token");

    const [taskName,setTaskName] = useState("");
    const [description,setDescription] = useState("");
    const [status,setStatus] = useState("Assigned");

    const handleSubmit = (e)=>{
        e.preventDefault();

        const newTask = {
            taskName,
            description,
            status,
            empId : parseInt(empId),
        };

        axios.post("http://localhost:3001/task",newTask,{
            headers : {
                Authorization : `Bearer ${token}`,
            },
        })
            .then(()=>{
                alert("task added successfully!");
                navigate(`/tasks/${empId}`);
            })
            .catch((err)=>{
                console.log("Errot adding task : ",err);
                alert("Failed to add task");
            })
    };

    return(
        <>
            <h2>Add Task for Employee #{empId}</h2>

            <button onClick={() => navigate(`/tasks/${empId}`)}>⬅ Back</button>


            <form onSubmit={handleSubmit}>
                <div>
                    <label>Task Name : </label><br/>
                    <input type = "text" value = {taskName} onChange={(e)=>setTaskName(e.target.value)} required/>
                </div>
                <br/>
                <div>
                    <label>Description : </label>
                    <textarea value = {description} onChange={(e)=>setDescription(e.target.value)} rows = "4" cols = "40" required></textarea>
                    <br/>

                    <div>
                        <label>Status : </label>
                        <select value={status} onChange={(e)=>setStatus(e.target.value)}>
                            <option value="Assigned">Assigned</option>
                            <option value="Yet to start">Yet to start</option>
                            <option value="In progress">In progress</option>
                            <option value="Completed">Completed</option>
                        </select>
                    </div>
                    <br/>
                    <button type="submit">Add Task</button>
                </div>
            </form>
        </>
    )
}

export default AddTask;