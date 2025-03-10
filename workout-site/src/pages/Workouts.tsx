import RoutinesTable from "@/components/data_tables/RoutinesTable"
import DashboardSidebar from "@/components/DashboardSidebar"
import { SidebarProvider, SidebarTrigger } from "@/components/ui/sidebar"
import { useAuth } from "@/lib/AuthProvider"
import { api_url } from "@/lib/utils"
import axios from "axios"
import { useEffect } from "react"
import { useNavigate } from "react-router-dom"

export default function Workouts() {
  const navigate = useNavigate()
  const {isAuthenticated, setIsAuthenticated} = useAuth()

  useEffect(() => {
    if (isAuthenticated == false) {
      navigate("/login")
      console.log("Navigating back.")
    } else {

      // TODO: Change 1 to actual userid
      axios.get(api_url + "/api/user/current", {
        withCredentials: true
      })
      .then(resp => {
        console.log("USER IS: " + JSON.stringify(resp.data))
      })
      .catch(e =>
        console.log("Error occured: " + e)
      )
    }
  }, [isAuthenticated])

  return (
    <SidebarProvider>
      <DashboardSidebar />
      <main className="p-3">
        <SidebarTrigger />
        <div className='p-6'>
          <h1 className='text-5xl font-bold'>Workouts</h1>
          <div className="h-5" />
          <RoutinesTable />
        </div>
      </main>
    </SidebarProvider>
  )
}