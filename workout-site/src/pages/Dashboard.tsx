import { Button } from "@/components/ui/button"
import { Link, useNavigate } from "react-router-dom"
import axios from "axios"
import { useEffect } from "react"
import { useAuth } from "@/lib/AuthProvider"
import { NavigationMenu, NavigationMenuLink, NavigationMenuList } from "@radix-ui/react-navigation-menu"
import { navigationMenuTriggerStyle } from "@/components/ui/navigation-menu"
import { Separator } from "@radix-ui/react-separator"
import { api_url } from "@/lib/utils"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import DashboardSidebar from "@/components/DashboardSidebar"
import { SidebarProvider, SidebarTrigger } from "@/components/ui/sidebar"
import FitnessStreak from "@/components/cards/FitnessStreak"
import TodaysWorkout from "@/components/cards/TodaysWorkout"
import ActiveMinutes from "@/components/cards/ActiveMinutes"
import { Dialog, DialogContent, DialogDescription, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog"
import ViewWorkouts from "@/components/cards/ViewWorkouts/ViewWorkouts"
import ViewRoutines from "@/components/cards/ViewRoutines/ViewWorkouts"

export default function Dashboard () {
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
      <main className="p-3 w-full">
        <SidebarTrigger />
        <div className='p-6'>
          <h1 className='text-5xl font-bold'>Dashboard</h1>
          <div className="h-5" />
          {/* <p>Welcome to your fitness dashboard!</p> */}
          <div className="grid gap-5 w-full grid-cols-1 lg:grid-cols-3">
            <FitnessStreak />
            <FitnessStreak />
            <ActiveMinutes />
            <TodaysWorkout />

            <ViewWorkouts />
            <ViewRoutines />
            {/* <Button className="bg-blue-700 font-bold text-lg text-white hover:bg-blue-400 py-8">
              View Routines
            </Button> */}
            <Button className="bg-yellow-700 font-bold text-lg text-white hover:bg-yellow-400 py-8">
              View Stats
            </Button>
          </div>
        </div>
      </main>
    </SidebarProvider>
  )

  // return (
  //   <div className="flex">
  //     {/* Sidebar */}
  //     <DashboardSidebar />

  //     {/* Main Content */}
  //     <div className="flex-1 p-6">
  //       <h1 className="text-5xl font-bold">Dashboard</h1>
  //       <p>Welcome to your fitness dashboard!</p>
  //     </div>
  //   </div>
  // );

  // return (
  //   <>
  //     {/* <div className="flex w-full p-2.5 bg-background"> */}
  //       <NavigationMenu className="justify-start py-2 my-2 mx-4 sticky top-0 w-[100%-4px] bg-background backdrop-blur-sm">
  //         <NavigationMenuList className="">
  //           <NavigationMenuLink  href="/login" className={`text-foreground ${navigationMenuTriggerStyle()}`}>
  //             Dashboard
  //           </NavigationMenuLink>
  //           <NavigationMenuLink href="/" className={`text-foreground ${navigationMenuTriggerStyle()}`}>
  //             Help
  //           </NavigationMenuLink>
  //           <NavigationMenuLink href="/" className={`text-foreground ${navigationMenuTriggerStyle()}`}>
  //             Donate
  //           </NavigationMenuLink>
  //           <NavigationMenuLink href="/" className={`text-foreground ${navigationMenuTriggerStyle()}`}>
  //             Profile
  //           </NavigationMenuLink>
  //         </NavigationMenuList>
  //       </NavigationMenu>
  //     {/* </div> */}
  //     <Separator className="my-4" />
  //     <div className="grid grid-cols-4 grid-rows-4 mx-8 my-5 gap-9">
  //       <Card className="col-span-4 row-span-2">
  //         asdfe
  //       </Card>
  //       <Card className="col-span-1 row-span-2">
  //         <CardHeader>
  //           <CardTitle>Friends</CardTitle>
  //           <CardDescription>
  //             Showing total visitors for the last 6 months
  //           </CardDescription>
  //         </CardHeader>
  //         <CardContent>
  //           asdf
  //         </CardContent>
  //       </Card>
  //       <Card className="col-span-1 row-span-2 h-[100%]">
  //         <CardHeader>
  //           <CardTitle>Workouts</CardTitle>
  //           <CardDescription>
  //             Showing total visitors for the last 6 months
  //           </CardDescription>
  //         </CardHeader>
  //         <CardContent>
  //           asdf
  //         </CardContent>
  //       </Card>
  //       <Card className="col-span-1 row-span-1">
  //         asdf
  //         e
  //         e
  //         e
  //         dasd
  //         dasdasd
  //         adsddasdadsf
  //           sad
  //           asfsadf
  //       </Card>
  //       <Card className="col-span-1 row-span-1">
  //         asdf
  //         e
  //         e
  //         e
  //         dasd
  //         dasdasd
  //         adsddasdadsf
  //           sad
  //           asfsadf
  //       </Card>
  //       <Card className="col-span-1 row-span-1">
  //         asdf
  //         e
  //         e
  //         e
  //         dasd
  //         dasdasd
  //         adsddasdadsf
  //           sad
  //           asfsadf
  //       </Card>
  //     </div>
  //   </>
  // )
}