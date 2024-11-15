import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Button } from "@/components/ui/button"
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog"
import { DatePicker } from "@/components/ui/datepicker"
import { useState, useEffect } from "react"
import axios from 'axios'

export default function Login() {
  const [username, setUsername] = useState<string>()
  const [name, setName] = useState<string>()
  const [password, setPassword] = useState<string>()
  const [date, setDate] = useState<Date>()

  useEffect(() => {
    console.log(username)
    console.log(date?.getDay() + " " + date?.getFullYear() + " " + date?.getMonth())
  },
    [date, username]
  )

  const submitCreateAccount = async () => {
    if (username != undefined && password != undefined && date != undefined && name != undefined) {
      let form = new FormData()
      form.append("email", username)
      form.append("password", username)
      form.append("name", name)
      // form.append("dob", date.toISOString()) TODO
      const response = await axios.post("http://localhost:8080/api/v1/user/register", form)
      console.log(response)
      console.log(response.status)
    }
  }

  return (
    <div className="flex h-screen justify-center items-center">
      <Tabs defaultValue="login" className="w-[300px]">
        <TabsList className="grid w-full grid-cols-2">
          <TabsTrigger value="login">Log In</TabsTrigger>
          <TabsTrigger value="signup">Sign Up</TabsTrigger>
        </TabsList>


        <TabsContent value="login" className="space-y-3">
          <div className="space-y-1">
            <Label htmlFor="email">Email</Label>
            <Input placeholder="Enter your email here" value={username} onChange={e => {setUsername(e.target.value)}}></Input>
          </div>
          <div className="space-y-1">
            <Label htmlFor="password">Password</Label>
            <Input placeholder="Enter your password here" value={password} onChange={e => {setPassword(e.target.value)}}></Input>
          </div>
          <div className="flex w-full justify-center flex-col space-y-4">
          <Button className="w-full"> Sign in </Button>
            <Dialog>
              <DialogTrigger>
                <Button variant="outline">Forgot Password?</Button>
              </DialogTrigger>
              <DialogContent>
                <DialogHeader>
                  <DialogTitle>Reset Password</DialogTitle>
                  <DialogDescription>
                  </DialogDescription>
                </DialogHeader>
                Enter your email to reset your password:
                <Input placeholder="Email"></Input>
                <Button>Submit</Button>
              </DialogContent>
            </Dialog>
          </div>
        </TabsContent>


        <TabsContent value="signup" className="space-y-3">
          <div className="space-y-1">
            <Label htmlFor="signup-email">Email</Label>
            <Input placeholder="Enter your email here" value={username} onChange={e => {setUsername(e.target.value)}}></Input>
          </div>
          <div className="space-y-1">
            <Label htmlFor="signup-email">Name</Label>
            <Input placeholder="Enter your name here" value={name} onChange={e => {setName(e.target.value)}}></Input>
          </div>
          <div className="space-y-1">
            <Label htmlFor="singup-dob">Date of Birth</Label>
            <DatePicker date={date} setDate={setDate}></DatePicker>
          </div>
          <div className="space-y-1">
            <Label htmlFor="signup-password">Password</Label>
            <Input placeholder="Enter your password here" value={password} onChange={e => {setPassword(e.target.value)}}></Input>
          </div>
          <div className="flex w-full justify-center">
            <Button className="w-[70%]" onClick={submitCreateAccount}>Create Account</Button>
          </div>
        </TabsContent>
      </Tabs>
    </div>
  )
}

// import { Button } from "@/components/ui/button"
// import {
//   Card,
//   CardContent,
//   CardDescription,
//   CardFooter,
//   CardHeader,
//   CardTitle,
// } from "@/components/ui/card"
// import { Input } from "@/components/ui/input"
// import { Label } from "@/components/ui/label"
// import {
//   Tabs,
//   TabsContent,
//   TabsList,
//   TabsTrigger,
// } from "@/components/ui/tabs"

// export function TabsDemo() {
//   return (
//     <Tabs defaultValue="account" className="w-[400px]">
//       <TabsList className="grid w-full grid-cols-2">
//         <TabsTrigger value="account">Account</TabsTrigger>
//         <TabsTrigger value="password">Password</TabsTrigger>
//       </TabsList>
//       <TabsContent value="account">
//         <Card>
//           <CardHeader>
//             <CardTitle>Account</CardTitle>
//             <CardDescription>
//               Make changes to your account here. Click save when you're done.
//             </CardDescription>
//           </CardHeader>
//           <CardContent className="space-y-2">
//             <div className="space-y-1">
//               <Label htmlFor="name">Name</Label>
//               <Input id="name" defaultValue="Pedro Duarte" />
//             </div>
//             <div className="space-y-1">
//               <Label htmlFor="username">Username</Label>
//               <Input id="username" defaultValue="@peduarte" />
//             </div>
//           </CardContent>
//           <CardFooter>
//             <Button>Save changes</Button>
//           </CardFooter>
//         </Card>
//       </TabsContent>
//       <TabsContent value="password">
//         <Card>
//           <CardHeader>
//             <CardTitle>Password</CardTitle>
//             <CardDescription>
//               Change your password here. After saving, you'll be logged out.
//             </CardDescription>
//           </CardHeader>
//           <CardContent className="space-y-2">
//             <div className="space-y-1">
//               <Label htmlFor="current">Current password</Label>
//               <Input id="current" type="password" />
//             </div>
//             <div className="space-y-1">
//               <Label htmlFor="new">New password</Label>
//               <Input id="new" type="password" />
//             </div>
//           </CardContent>
//           <CardFooter>
//             <Button>Save password</Button>
//           </CardFooter>
//         </Card>
//       </TabsContent>
//     </Tabs>
//   )
// }
