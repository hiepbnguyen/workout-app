import { Link } from 'react-router-dom'
import { Home, Dumbbell, BarChart, Settings, BookUser } from "lucide-react";
import { useTheme } from './ThemeProvider';

import {
  Sidebar,
  SidebarContent,
  SidebarGroup,
  SidebarGroupContent,
  SidebarGroupLabel,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
} from "@/components/ui/sidebar"

export default function DashboardSidebar() {
  // return (
  //     <div className={`h-screen w-64 ${useTheme().theme === 'light' ? 'bg-white' : 'bg-black' } text-white flex flex-col p-4`}>
  //         <div className='text-3xl font-bold mb-6'>Fitness App</div>

  //         <nav className='flex flex-col gap-3'>
  //             <NavItem to="/" icon={<Home size={20} />} label="Dashboard" />
  //             <NavItem to="/workouts" icon={<Dumbbell size={20} />} label="Workouts" />
  //             <NavItem to="/stats" icon={<BarChart size={20} />} label="Stats" />
  //             <NavItem to="/settings" icon={<Settings size={20} />} label="Settings" />
  //         </nav>
  //     </div>
  // )

  const items = [
    {
      title: "Home",
      url: "/",
      icon: Home
    },
    {
      title: "Workouts",
      url: "/workouts",
      icon: Dumbbell
    },
    {
      title: "Friends",
      url: "/friends",
      icon: BookUser
    },
    {
      title: "Stats",
      url: "/stats",
      icon: BarChart
    },
    {
      title: "Settings",
      url: "/settings",
      icon: Settings
    },
  ]

  return (
    <Sidebar>
      <SidebarContent>
        <SidebarGroup>
          <SidebarGroupLabel>Fitness App</SidebarGroupLabel>
          <SidebarGroupContent>
            <SidebarMenu>
              {items.map((item) => (
                <SidebarMenuItem key={item.title}>
                  <SidebarMenuButton asChild>
                    <Link to={item.url}>
                      <item.icon size={64} />
                      <span className='text-lg font-semibold'>{item.title}</span>
                    </Link>
                  </SidebarMenuButton>
                </SidebarMenuItem>
              ))}
            </SidebarMenu>
          </SidebarGroupContent>
        </SidebarGroup>
      </SidebarContent>
    </Sidebar>
  )
}