import {useEffect , useState} from "react";
import {useNavigate , useParams} from "react-router-dom";
import axios from "axios";

const EditEmployee = ()=>{

    const [employee,setEmployee] = useState(null);
    const [selectedRoles,setSelectedRoles] = useState([]);
    const navigate = useNavigate();
    const {id} = useParams();

    const token = localStorage.getItem("token");

    useEffect(()=>{
        axios.get(`http://localhost:3001/employee/${id}`,{
            headers : {
                Authorization : `Bearer ${token}`,
            }
        })
            .then((res)=>{
                setEmployee(res.data);
                const existingRoles = res.data.roles.map((role)=>role.roleName);
                setSelectedRoles(existingRoles);
            })
            .catch(err => {
                console.log("Error fetching employee" , err);
                alert("Could not fetch employee data");
            })
    },[id,token]);

    const handleRoleChange = (e) => {
        const role = e.target.value;
        setSelectedRoles((prev) =>
            prev.includes(role)
                ? prev.filter((r) => r !== role)
                : [...prev, role]
        );
    };


    const handleUpdate = (e)=>{
        e.preventDefault();

        const password = e.target.password.value;

        const updateData = {
            name : e.target.name.value,
            userName : e.target.userName.value,
            email : e.target.email.value,
            roleNames : selectedRoles,
        };

        if(password.trim()!==""){
            updateData.password = password;
        }

        axios
            .put(`http://localhost:3001/employee/${id}`,updateData,{
                headers : {
                    Authorization : `Bearer ${token}`,
                },
            })
            .then(()=>{
                alert("Employee updated successfully!");
                navigate("/employee");
            })
            .catch((err=>{
                console.log("Update error : ",err);
                alert("Failed to update employee");
            }))
    };

    if(!employee) return <p>Loading...</p>

    return(
        <div>
            <h2>Edit Employee</h2>
            <button onClick={() => navigate("/employee")}>⬅ Back</button>
            <form onSubmit={handleUpdate}>
                <label>Name : </label>
                <input type="text" name = "name" defaultValue={employee.name}/>
                <br/><br/>

                <label>Username : </label>
                <input type="text" name = "userName" defaultValue={employee.userName}/>
                <br/><br/>

                <label>Email : </label>
                <input type="text" name = "email" defaultValue={employee.email}/>
                <br/><br/>

                <label>Password : </label>
                <input name = "password" type = "password" placeholder = "New Password"/>
                <br/><br/>

                <label>Roles : </label>
                <label>
                    <input type = "checkbox" value = "ADMIN" checked = {selectedRoles.includes("ADMIN")} onChange = {handleRoleChange}/>
                    Admin
                </label>
                <br/>
                <label>
                    <input type = "checkbox" value = "USER" checked = {selectedRoles.includes("USER")} onChange = {handleRoleChange}/>
                    User
                </label>
                <br/><br/>

                <button type = "submit">Update</button>
            </form>
        </div>
    )
}

export default EditEmployee;