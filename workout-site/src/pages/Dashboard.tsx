import { Button } from "@/components/ui/button"
import { Link, useNavigate } from "react-router-dom"
import axios from "axios"
import { useEffect } from "react"
import { useAuth } from "@/lib/AuthProvider"
import { NavigationMenu, NavigationMenuContent, NavigationMenuItem, NavigationMenuLink, NavigationMenuList, NavigationMenuSub, NavigationMenuTrigger } from "@radix-ui/react-navigation-menu"
import { navigationMenuTriggerStyle } from "@/components/ui/navigation-menu"
import { Separator } from "@radix-ui/react-separator"
import { api_start } from "@/lib/utils"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"

export default function Dashboard () {
  const navigate = useNavigate()
  const {isAuthenticated, setIsAuthenticated} = useAuth()

  useEffect(() => {
    if (isAuthenticated == false) {
      navigate("/login")
      console.log("Navigating back.")
    } else {

      // TODO: Change 1 to actual userid
      axios.get(api_start + "/1", {
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
    <>
      {/* <div className="flex w-full p-2.5 bg-background"> */}
        <NavigationMenu className="justify-start py-2 my-2 mx-4 sticky top-0 w-[100%-4px] bg-background backdrop-blur-sm">
          <NavigationMenuList className="">
            <NavigationMenuLink  href="/login" className={`text-foreground ${navigationMenuTriggerStyle()}`}>
              Dashboard
            </NavigationMenuLink>
            <NavigationMenuLink href="/" className={`text-foreground ${navigationMenuTriggerStyle()}`}>
              Help
            </NavigationMenuLink>
            <NavigationMenuLink href="/" className={`text-foreground ${navigationMenuTriggerStyle()}`}>
              Donate
            </NavigationMenuLink>
            <NavigationMenuLink href="/" className={`text-foreground ${navigationMenuTriggerStyle()}`}>
              Profile
            </NavigationMenuLink>
          </NavigationMenuList>
        </NavigationMenu>
      {/* </div> */}
      <Separator className="my-4" />
      <div className="grid grid-cols-4 grid-rows-4 mx-8 my-5 gap-9">
        <Card className="col-span-4 row-span-2">
          asdfe
        </Card>
        <Card className="col-span-1 row-span-2">
          <CardHeader>
            <CardTitle>Friends</CardTitle>
            <CardDescription>
              Showing total visitors for the last 6 months
            </CardDescription>
          </CardHeader>
          <CardContent>
            asdf
          </CardContent>
        </Card>
        <Card className="col-span-1 row-span-2 h-[100%]">
          <CardHeader>
            <CardTitle>Workouts</CardTitle>
            <CardDescription>
              Showing total visitors for the last 6 months
            </CardDescription>
          </CardHeader>
          <CardContent>
            asdf
          </CardContent>
        </Card>
        <Card className="col-span-1 row-span-1">
          asdf
          e
          e
          e
          dasd
          dasdasd
          adsddasdadsf
            sad
            asfsadf
        </Card>
        <Card className="col-span-1 row-span-1">
          asdf
          e
          e
          e
          dasd
          dasdasd
          adsddasdadsf
            sad
            asfsadf
        </Card>
        <Card className="col-span-1 row-span-1">
          asdf
          e
          e
          e
          dasd
          dasdasd
          adsddasdadsf
            sad
            asfsadf
        </Card>
      </div>
    </>
  )
}