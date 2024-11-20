import { Button } from "@/components/ui/button"
import { Link, useNavigate } from "react-router-dom"
import axios from "axios"
import { useEffect } from "react"
import { useAuth } from "@/lib/AuthProvider"
import { NavigationMenu, NavigationMenuContent, NavigationMenuItem, NavigationMenuLink, NavigationMenuList, NavigationMenuTrigger } from "@radix-ui/react-navigation-menu"
import { navigationMenuTriggerStyle } from "@/components/ui/navigation-menu"
import { Separator } from "@radix-ui/react-separator"
import { api_start } from "@/lib/utils"

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
        console.log("USER IS: " + resp.data)
      })
      .catch(e =>
        console.log("Error occured: " + e)
      )
    }
  }, [isAuthenticated])

  return (
    <>
      <div className="dark flex w-full p-2.5 bg-background">
        <NavigationMenu>
          <NavigationMenuList>
            <NavigationMenuItem>
              <NavigationMenuLink href="/login" className={`dark text-foreground ${navigationMenuTriggerStyle()}`}>
                Dashboard
              </NavigationMenuLink>
              <NavigationMenuLink href="/" className={`dark text-foreground ${navigationMenuTriggerStyle()}`}>
                Help
              </NavigationMenuLink>
              <NavigationMenuLink href="/" className={`dark text-foreground ${navigationMenuTriggerStyle()}`}>
                Donate
              </NavigationMenuLink>
            </NavigationMenuItem>
          </NavigationMenuList>
        </NavigationMenu>
      </div>
      <Separator className="my-4" />
      Hello, Hiep
    </>
  )
}