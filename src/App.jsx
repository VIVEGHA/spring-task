import './App.css'
import Layout from './components/Layout'
import Home from './components/Home'
import Login from './components/Login'
import Register from './components/Register'
import Dashboard from './components/Dashboard'
import Employee from './components/Employee'
import EditEmployee from './components/EditEmployee'
import TaskPage from './components/TaskPage'
import EditTask from './components/EditTask'
import AddTask from './components/AddTask'
import { BrowserRouter , Routes , Route } from 'react-router-dom'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="register" element={<Register />} />
          <Route path="login" element={<Login />} />
          <Route path="employee" element={<Employee />} />
          <Route path="/employee/edit/:id" element = {<EditEmployee/>}/>
          <Route path="/tasks/:empId" element = {<TaskPage/>}/>
          <Route path="dashboard" element={<Dashboard />} />
          <Route path="/tasks/add/:empId" element={<AddTask/>}/>
          <Route path="/tasks/edit/:taskId" element={<EditTask />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App