import { Dialog, DialogContent, DialogDescription, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog"
import { Button } from "../../ui/button"
import { useState } from "react"
import { Label } from "@/components/ui/label"
import { Input } from "@/components/ui/input"
import { createWorkout } from "@/services/Api"

export default function CreateNewWorkout() {
    const [name, setName] = useState("")

    const ConfirmCreateWorkout = async() => {
        if (name != "") {
            await createWorkout(name)
        }
    }

    return (
        <Dialog>
          <DialogTrigger>
            <Button>
              Create New Workout
            </Button>
          </DialogTrigger>
          <DialogContent>
            <DialogHeader>
              <DialogTitle>Workouts</DialogTitle>
            </DialogHeader>
            <Label htmlFor="name">Name</Label>
            <Input placeholder="Workout name" value={name} onChange={e => {setName(e.target.value)}}></Input>
            <Button onClick={ConfirmCreateWorkout}>Create</Button>
          </DialogContent>
        </Dialog>
      )
}