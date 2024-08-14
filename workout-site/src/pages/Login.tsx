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

export default function Login() {
  return (
    <div className="flex h-screen justify-center items-center">
      <Tabs defaultValue="login" className="w-[300px]">
        <TabsList className="grid w-full grid-cols-2">
          <TabsTrigger value="login">Log In</TabsTrigger>
          <TabsTrigger value="signup">Sign Up</TabsTrigger>
        </TabsList>


        <TabsContent value="login" className="space-y-5">
          <div className="space-y-1">
            <Label htmlFor="email">Email</Label>
            <Input placeholder="Enter your email here"></Input>
          </div>
          <div className="space-y-1">
            <Label htmlFor="password">Password</Label>
            <Input placeholder="Enter your password here"></Input>
          </div>
          <Button className="w-full"> Sign in </Button>
          <div className="flex w-full justify-center">
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


        <TabsContent value="signup">
          Change your password here.
          <Input></Input>
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
