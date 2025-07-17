import { useState} from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Register = ()=>{

  const[name,setName] = useState("");
  const[email,setEmail] = useState("");
  const[password,setPassword] = useState("");
  const[userName,setUserName] = useState("");
  const[selectedRoles,setSelectedRoles] = useState("");

  const navigate = useNavigate()
  
  // RAMYA T  - 23AD111
  const handleRoleChange = (e)=>{
    const value = e.target.value;
    if(e.target.value){
      setSelectedRoles((prev)=>[...prev,value]);
    }
    else{
      setSelectedRoles((prev)=>prev.filter((role)=>role!==value));
    }
  };

  async function handleSubmit(event){
    event.preventDefault();
    try{

      const response = await axios.post("http://localhost:3001/api/auth/register",{
        name ,
        email , 
        password , 
        userName , 
        roleNames : selectedRoles
      });
      console.log(response);
      navigate("/dashboard")
    }
    catch(e){
      console.log("Register error " , e);
      alert("Register error!!");
    }
  }

  return(
    <>
      <div>
        <h1>Register Form</h1>
        <form onSubmit={handleSubmit}>

          <label htmlFor = "name"> Name : </label>
          <input type = "text" id = "name" value = {name} onChange = {(e)=>setName(e.target.value)}/>
          <br/><br/>
          <label htmlFor = "email">Email : </label>
          <input type = "text" id = "email" value = {email} onChange = {(e)=>setEmail(e.target.value)}/>
          <br/><br/>
          <label htmlFor = "password">Password : </label>
          <input type = "password" id = "password" value = {password} onChange = {(e)=>setPassword(e.target.value)}/>
          <br/><br/>
          <label htmlFor = "userName"> Username : </label>
          <input type = "text" id = "userName" value = {userName} onChange = {(e)=>setUserName(e.target.value)}/>
          <br/><br/>

          <label>Select Roles : </label>
          <br/>
          <input type = "checkbox" id = "admin" value = "ADMIN" onChange = {handleRoleChange}/>
          <label htmlFor = "admin">Admin</label>
          <input type = "checkbox" id = "user" value = "USER" onChange = {handleRoleChange}/>
          <label htmlFor = "user">User</label>
          
          <br/><br/>
          <button type = "submit" > Register</button>
        </form>
      </div>
    </>
  )
}

export default Register;